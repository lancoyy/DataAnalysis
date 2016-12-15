package com.aone.algorithmImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.Stack;

import org.gephi.data.attributes.api.AttributeColumn;
import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.data.attributes.api.AttributeOrigin;
import org.gephi.data.attributes.api.AttributeRow;
import org.gephi.data.attributes.api.AttributeTable;
import org.gephi.data.attributes.api.AttributeType;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.EdgeIterable;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.HierarchicalDirectedGraph;
import org.gephi.graph.api.HierarchicalGraph;
import org.gephi.graph.api.HierarchicalUndirectedGraph;
import org.gephi.graph.api.Node;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.gephi.statistics.spi.Statistics;
import org.gephi.utils.longtask.spi.LongTask;
import org.gephi.utils.progress.Progress;
import org.gephi.utils.progress.ProgressTicket;
import org.openide.util.Lookup;

import com.aone.algorithm.Community;
import com.aone.entity.AllNodesandEdges;
import com.aone.entity.Edges;
import com.aone.entity.Nodes;
/**
 * 经典GN算法
 * （本代码算法集体描述：首先我们对各个源节点计算每个边的阶数，遍历所有的节点，求出边的平均相对于所有节点的阶数值，然后去掉阶数最高的边，当形成一个新的社团（及有两个连通分量）
 * 跳出循环，得到社团划分）（最新代码改进，没形成新的社团，对模块度的增幅进行判断，小于一个值，则跳出循环，否则继续）
 * 默认为无向图//
 *自我：1.边介度值计算的时候完全不用对边进行快排。只需找出最大的介度值即可。
 *    2.分成的聚类群，每个都计算了模块度。但是没有找最大模块度。而且，判断阶数该算法的条件是形成两个无联通的群就停止计算了。
 *    3.应该不是无效图。用到了gephi中的方法，不是无向的吧。?
 * 
 * 
 * @see &quot;Community structure in social and biological networks by Michelle Girvan and Mark Newman&quot;
 * @see &quot;Scientiﬁc collaboration networks. II. Shortest paths, weighted networks, and centrality&quot;
 * @author zrh
 *
 */
public class GirvanNewman implements Statistics, LongTask, Community{
	/**节点or边？属性栏中的索引或者叫描述*/
	public static final String GN = "GirvanNewman";
    private double[] betweenness;//介度
    private int diameter;//直径
    GraphModel graphModel;
    ProjectController pc;//项目负责人？？？
	Workspace workspace;//工作空间
    /** */
    private double avgDist;//路径长度。
    /**节点的数目*/
    private int N;
    /**边的数目*/
    private int E_COUNT;
    /** */
    private ProgressTicket progress;
    /** */
    private boolean isCanceled;
    /**是否归一化*/
    private boolean isNormalized;
    
    private int ratio;    
    public GirvanNewman() {}
    public GirvanNewman(int ratio) {
    	this.ratio = ratio;
    }
    public double getPathLength() {
        return avgDist;
    }
    /**
     * 
     * @return
     */
    public double getDiameter() {
        return diameter;
    }   
    public boolean cancel() {
        this.isCanceled = true;
        return true;
    }
    /**
     *
     * @param progressTicket
     */
    public void setProgressTicket(ProgressTicket progressTicket) {
        this.progress = progressTicket;
    }
	public void execute(GraphModel graphModel, AttributeModel attributeModel) {
		
		HierarchicalUndirectedGraph graph = null;
        graph = graphModel.getHierarchicalUndirectedGraphVisible();
        execute(graph, attributeModel);
    }
	public void execute(HierarchicalUndirectedGraph hgraph, AttributeModel attributeModel) {
//		hgraph.readLock();
		/**定义节点属性列，标题为Girvan Newman*/
		graphModel = Lookup.getDefault().lookup(GraphController.class).getModel();
		//hgraph = graphModel.getDirectedGraph();
		AllNodesandEdges allNodesAndLinks = new AllNodesandEdges();
		HashSet<String> nodeSet=new  HashSet<String>();//创建随机的链表
		AttributeTable nodeTable = attributeModel.getNodeTable();//节点表
        AttributeColumn modCol = nodeTable.getColumn(GN);//获取GN
        if (modCol == null) {
            modCol = nodeTable.addColumn(GN, "Girvan Newman", AttributeType.INT, AttributeOrigin.COMPUTED, new Integer(0));
        }
//        将要移除的边
		Edge to_remove = null;
		int edgeNum=hgraph.getEdgeCount();//边的数目
		int nodeNum=hgraph.getNodeCount();//节点的数目
		List<Edges> edgeOfGraph=new LinkedList<Edges>();
		Edge[] edges=hgraph.getEdges().toArray();
		for(int i=0;i<edges.length;i++){
			//对边进行循环。
			Edges edge=new Edges();
			Nodes DataandWeightSource=new Nodes();
			Nodes DataandWeightTarget=new Nodes();
			DataandWeightSource.setName(edges[i].getSource().toString());//一条边的出发点
			DataandWeightTarget.setName(edges[i].getTarget().toString());//一条边的终点
			//System.out.println(hgraph.getEdges().toArray());
			edge.setSource(DataandWeightSource);//(edges[i].getSource().toString());
			edge.setTarget(DataandWeightTarget);
			edgeOfGraph.add(edge);//封装了edge的信息。
		}
		List<Node> nodeOfGraph=new LinkedList<Node>();
		for(int i=0;i<hgraph.getNodeCount();i++){
			nodeOfGraph.add(hgraph.getNode(i));
		}
		
		//System.out.println(tempQ+" "+Q);
		/**所有移除的边的集合*/
		double Q=0.0;
		List<Edge> edges_removed = new LinkedList<Edge>();
		int edges_remove_num = hgraph.getEdgeCount();
		//System.out.println(edges_remove_num);
		ArrayList<Edges> edgesInfos=new ArrayList<Edges>();
		for(int i = 0; i < edges_remove_num; i++) {
			double tempQ=0.0;
			
			if (!isCanceled) {
                hgraph.readUnlockAll();
                
                to_remove =new BetweennessEdge().execute(edgesInfos, hgraph.getGraphModel(), attributeModel);
                //System.out.println(to_remove.getSource()+" "+to_remove.getTarget());
    			if(to_remove != null) {
    				hgraph.removeEdge(to_remove);
    				
    				
    				edges_removed.add(to_remove);
    			}
            } else {
            	hgraph.readUnlockAll();
            	return;
            }
			
			/*Set<LinkedList<Node>> clusterSet=new HashSet<LinkedList<Node>>();
			while(true){
				Set<Node> component = new HashSet<Node>();
				component.add(hgraph.getNode(k));
				Node node1=hgraph.getNode(k);
				while(hgraph.getNeighbors(node1)!=null){
					for(Node node: hgraph.getNeighbors(node1)){
						component.add(node);
					}
				}
			}*/
//		-----------------------------------
/**当移除掉betweenness值较高的边之后，计算当前的社区，即弱连接的社团以及该社团包含的节点集合*/
		ConnectedComponents cc = new ConnectedComponents();
		Set<LinkedList<Node>> clusterSet =(Set<LinkedList<Node>>)cc.weaklyConnected(hgraph, attributeModel);
		Iterator iter = clusterSet.iterator();
		int count = 0;
		while(iter.hasNext()) {
			if (isCanceled) {
                hgraph.readUnlockAll();
                return;
            }
			/**该list即为一个社团，该集合内的节点为同一社团，所有让集合内的节点GN属性列相同*/
			List<Node> list = (LinkedList<Node>) iter.next();
			int edgeNumOfOneCom=0;
			int edgeNumOfOutOneCom=0;
			
			//计算社团间的边数edgeNumOfOutOneCom和社团内的边数edgeNumOfOneCom
			for(Edges edge : edgeOfGraph){
				boolean flag=false;
				//System.out.println(edgeOfGraph.get(j));
				for(int k=0;k<list.size();k++){
					
					if(list.toArray()[k].toString().equals(edge.getSource())){
						for(int m=0;m<list.size();m++){
							if(list.toArray()[m].toString().equals(edge.getTarget())){
								edgeNumOfOneCom++;
								flag=true;
								break;
							}
					    }
						if(flag==false){edgeNumOfOutOneCom++;break;}
					}else if(list.toArray()[k].toString().equals(edge.getTarget())){
						for(int m=0;m<list.size();m++){
							if(list.toArray()[m].toString().equals(edge.getSource())){
								edgeNumOfOneCom++;
								flag=true;
								break;
							}
					    }
						if(flag==false){edgeNumOfOutOneCom++;break;}
					}
					else;
				}
			}
			edgeNumOfOneCom=edgeNumOfOneCom/2;
			//计算模块度
			double eij=((double)edgeNumOfOneCom)/((double)edgeNum);
			double aiMultipleai=(((double)edgeNumOfOutOneCom)/((double)edgeNum))*(((double)edgeNumOfOutOneCom)/((double)edgeNum));
			double QOfOneCom=eij-aiMultipleai;
			tempQ=tempQ+QOfOneCom;
			/*System.out.println("edgeNumOfOneCom值："+edgeNumOfOneCom );
			System.out.println("edgeNumOfOutOneCom值："+edgeNumOfOutOneCom );*/
			for(Node n : list) {
				AttributeRow row = (AttributeRow) n.getNodeData().getAttributes();
				row.setValue(modCol, count);//标记分量信息。
			}
			count++;
		}
		
		
		
		
		
		//System.out.println("tempQ值： "+tempQ);
		if(clusterSet.size()==2){break;}/////？为什么还是2啊？？？？这个需要改吗？？？？？？？？
		}

		
		
		
		
		/**将移除的边复原*/
		for (Edge e : edges_removed)
        {	
			if(!isCanceled) {
				hgraph.addEdge(e);
			}
        }
		//hgraph.readUnlock();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	public Edge edgeBetweenness(HierarchicalGraph hgraph, AttributeModel attributeModel){
		ArrayList<Edges> edgesInfos=new ArrayList<Edges>();
        isCanceled = false;
        hgraph.readLock();
        Edge edgeOne=null;
	    int N = hgraph.getNodeCount();
        int M =hgraph.getEdgeCount(); 
	       double []   betweenness = new double[N];
	       double []   Edgesbetweenness = new double[M];
	        HashMap<Node, Integer> indicies = new HashMap<Node, Integer>();
	        int index = 0,edgesindex=0;
	        HashMap<Edge,String> map=new HashMap< Edge,String>();
	        HashMap<String,Edge> map2=new HashMap<String,Edge>();
	        for (Node s : hgraph.getNodes()) {  
	            indicies.put(s, index);
	      //      System.out.println(s+"  ***** "+N);
	            index++;
	        }
	        for (Edge edge:hgraph.getEdges()) {
	          map.put( edge,edgesindex+"");
	         map2.put(edgesindex+"", edge);
	         // System.out.println(edge.getSource()+"  ***** "+edge.getTarget());
	          edgesindex++;
	        }
	       
	        int count = 0;
	        for (Node s : hgraph.getNodes()) {
	            Stack<Node> S = new Stack<Node>();

	            LinkedList<Node>[] P = new LinkedList[N];
	            double[] theta = new double[N];
	            int[] d = new int[N];
	            for (int j = 0; j < N; j++) {
	                P[j] = new LinkedList<Node>();
	                theta[j] = 0;
	                d[j] = -1;
	            }

	            int s_index = indicies.get(s);

	            theta[s_index] = 1;
	            d[s_index] = 0;

	            LinkedList<Node> Q = new LinkedList<Node>();
	            Q.addLast(s);
	          //  System.out.println(s);
	            while (!Q.isEmpty()) {
	                Node v = Q.removeFirst();
	                S.push(v);
	                int v_index = indicies.get(v);

	                EdgeIterable edgeIter =hgraph.getEdges(v);
	                /*if (isDirected) {
	                    edgeIter = ((HierarchicalDirectedGraph) hgraph).getOutEdgesAndMetaOutEdges(v);
	                } else {
	                    edgeIter = hgraph.getEdgesAndMetaEdges(v);
	                }*/
	               // System.out.println(v+" $$$$$$$$$$$  ");
	                for (Edge edge : edgeIter) {
	                    Node reachable = hgraph.getOpposite(v, edge);
	               //     System.out.println(s+" ::  "+v+" ::  "+reachable);
	                    int r_index = indicies.get(reachable);
	                    if (d[r_index] < 0) {
	                        Q.addLast(reachable);
	                        d[r_index] = d[v_index] + 1;
	                    }
	                    if (d[r_index] == (d[v_index] + 1)) {
	                        theta[r_index] = theta[r_index] + theta[v_index];
	                        P[r_index].addLast(v);
	                    }
	                }
	            }
	            double[] delta = new double[N];
	            while (!S.empty()) {
	                Node w = S.pop();
	                int w_index = indicies.get(w);
	                ListIterator<Node> iter1 = P[w_index].listIterator();
	                while (iter1.hasNext()) {
	                    Node u = iter1.next();
	                    int u_index = indicies.get(u);
	                    String m;
	                    if(!map.get(hgraph.getEdge(u, w)).equals(null))
	                    	m=map.get(hgraph.getEdge(w,u));
	                    else m=map.get(hgraph.getEdge(u, w));
	             //    System.out.println( m);
	                    Edgesbetweenness[Integer.parseInt(m)]+=(theta[u_index] / theta[w_index]) * (1 + delta[w_index]);
	                    delta[u_index] += (theta[u_index] / theta[w_index]) * (1 + delta[w_index]);
	                }
	                if (w != s) {
	                    betweenness[w_index] += delta[w_index];
	                }
	            }
	            count++;
	           /* if (isCanceled) {
	                hgraph.readUnlockAll();
	                return;
	            }*/
	          	        }
	        HashMap<String,String> map3=new HashMap<String,String>();
	        for(int i=0;i<M;i++){
	        	map3.put(Edgesbetweenness[i]+"",i+"");
	        }
	        quickSort(Edgesbetweenness, 0,M-1);
	        
	       for(int i=0;i<M;i++){
	    	   edgeOne=map2.get(map3.get(Edgesbetweenness[M-1]+""));
	    	  
	    	String num= map3.get(Edgesbetweenness[i]+"");
	    	Edge edge=	map2.get(num);
	    	//System.out.println(num);
	    	 //System.out.println(edge.getSource()+"   "+edge.getTarget()+"    "+Edgesbetweenness[i]);
	       }
	       /*System.out.println("hellohello");*/
	       ArrayList<Nodes> al2=new ArrayList<Nodes>(); //所有节点的集合
	        for (Node s : hgraph.getNodes()) {
	            AttributeRow row = (AttributeRow) s.getNodeData().getAttributes();
	            int s_index = indicies.get(s);

	           
	                betweenness[s_index] /= 2;
	                Nodes dataandweight=new Nodes();
		           dataandweight.setName(s+"");
		           for(int j=0;j<edgesInfos.size();j++){
		        	   
		        	   if(edgesInfos.get(j).getSource().equals(s+"")){
		        		   dataandweight.setGroupId(edgesInfos.get(j).getSource().getName());
		        		   break;
		        	   }else  if(edgesInfos.get(j).getTarget().equals(s+"")){
		        		   dataandweight.setGroupId(edgesInfos.get(j).getTarget().getName());
		        		   break;
		        	   }
		           }
		          
		               dataandweight.setNoteWeight(betweenness[s_index]+"");
		           
		           //System.out.println(" betweenessCol: "+(s+"   ")+ betweenness[s_index]);//authority[s_index]);
		          // System.out.println(" closenessCol: "+ (s+"   ")+closeness[s_index]);//authority[s_index]);

		           al2.add(dataandweight);
	        }
	       //System.out.println(edgeOne.getSource()+" "+edgeOne.getTarget());
	       return edgeOne;
	}
	public static void quickSort(double[] edgesbetweenness, int lo0, int hi0) { 
		   
		int lo = lo0; 
	    int hi = hi0; 

	    if (lo >= hi) 
	      return; 

	    //确定指针方向的逻辑变量 
	    boolean transfer=true; 

	    while (lo != hi) { 
	      if (edgesbetweenness[lo] > edgesbetweenness[hi]) { 
	        //交换数字 
	        double temp = edgesbetweenness[lo]; 
	        edgesbetweenness[lo] = edgesbetweenness[hi]; 
	        edgesbetweenness[hi] = temp; 
	        //决定下标移动，还是上标移动 
	        transfer = (transfer == true) ? false : true; 
	      } 

	      //将指针向前或者向后移动 
	      if(transfer) 
	        hi--; 
	      else 
	        lo++; 

	      //显示每一次指针移动的数组数字的变化 
	      /*for(int i = 0; i < a.length; ++i) { 
	        System.out.print(a[i] + ","); 
	      } 
	      System.out.print(" (lo,hi) = " + "(" + lo + "," + hi + ")"); 
	      System.out.println("");*/ 
	    } 

	    //将数组分开两半，确定每个数字的正确位置 
	    lo--; 
	    hi++; 
	    quickSort(edgesbetweenness, lo0, lo); 
	    quickSort(edgesbetweenness, hi, hi0); 
	} 
	
	public String getReport() {
		return null;
	}
	
}
