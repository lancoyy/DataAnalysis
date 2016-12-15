package com.aone.algorithmImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

import javax.naming.LinkLoopException;

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
import org.gephi.graph.api.Node;
import org.gephi.utils.progress.Progress;
import org.openide.util.Lookup;

import com.aone.entity.Edges;

public class BetweennessEdge {
	
	public Edge execute(ArrayList<Edges> edgesInfos,GraphModel graphModel, AttributeModel attributeModel) {
		
		HierarchicalGraph graph = null;
		graph = graphModel.getHierarchicalUndirectedGraphVisible();
        Edge al2=  execute(edgesInfos,graph, attributeModel);
        return al2;
    }

	private Edge execute(ArrayList<Edges> edgesInfos,
			HierarchicalGraph graph, AttributeModel attributeModel) {
		 
		  
		    Edge edgeOne=null;
	        int N = graph.getNodeCount();    
            int M =graph.getEdgeCount(); 
	        double []   betweenness = new double[N];
	        double []   Edgesbetweenness = new double[M];
	        HashMap<Node, Integer> indicies = new HashMap<Node, Integer>();
	        int index = 0,edgesindex=0;
	        HashMap<Edge,String> map=new HashMap< Edge,String>();
	        HashMap<String,Edge> map2=new HashMap<String,Edge>();
	        for (Node s : graph.getNodes()) {
	            indicies.put(s, index);
	            index++;
	        }
	        for (Edge edge:graph.getEdges()) {
		        map.put( edge,edgesindex+"");
		        map2.put(edgesindex+"", edge);
		        edgesindex++;
	        }
	        int count = 0;
	        for (Node s : graph.getNodes()) {
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
	            while (!Q.isEmpty()) {
	                Node v = Q.removeFirst();
	                S.push(v);
	                int v_index = indicies.get(v);

	                EdgeIterable edgeIter =graph.getEdges(v);
	                /*if (isDirected) {
	                    edgeIter = ((HierarchicalDirectedGraph) hgraph).getOutEdgesAndMetaOutEdges(v);
	                } else {
	                    edgeIter = hgraph.getEdgesAndMetaEdges(v);
	                }*/
	               // System.out.println(v+" $$$$$$$$$$$  ");
	                for (Edge edge : edgeIter) {
	                    Node reachable = graph.getOpposite(v, edge);
	                    int r_index = indicies.get(reachable);
	                    if (d[r_index] < 0) {
	                        Q.addLast(reachable);
	                        d[r_index] = d[v_index] + 1;
	                    }
	                    if (d[r_index] == (d[v_index] + 1)){
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
	                    if(!map.get(graph.getEdge(u, w)).equals(null))    
	                    	m=map.get(graph.getEdge(w,u));
	                    else m=map.get(graph.getEdge(u, w));
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
	        edgeOne=map2.get(map3.get(Edgesbetweenness[M-1]+""));
	      /* for(int i=0;i<M;i++){
	    	   edgeOne=map2.get(map3.get(Edgesbetweenness[M-1]+""));
	    	   
	    	String num= map3.get(Edgesbetweenness[i]+"");
	    	Edge edge=	map2.get(num);
	       }*/
	        return edgeOne;
}
	public static void quickSort(double[] edgesbetweenness, int lo0, int hi0){ 
		int lo = lo0; 
	    int hi = hi0; 
	    if (lo >= hi) return; 
	    //确定指针方向的逻辑变量 
	    boolean transfer=true; 
	    while (lo != hi) { 
	      if (edgesbetweenness[lo] > edgesbetweenness[hi]){ 
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
	}
