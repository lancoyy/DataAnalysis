package com.aone.serviceImpl;

import java.util.HashSet;
import java.util.List;

import org.gephi.data.attributes.api.AttributeColumn;
import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.HierarchicalGraph;
import org.gephi.graph.api.Node;
import org.gephi.statistics.spi.Statistics;
import org.openide.util.Lookup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;

import processing.core.*;
import com.aone.service.DetecteCom;

import org.gephi.data.attributes.api.AttributeController;
import org.gephi.filters.api.FilterController;
import org.gephi.filters.api.Query;
import org.gephi.filters.plugin.graph.GiantComponentBuilder;
import org.gephi.filters.plugin.graph.GiantComponentBuilder.GiantComponentFilter;
import org.gephi.filters.plugin.partition.PartitionBuilder.NodePartitionFilter;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphView;
import org.gephi.layout.spi.Layout;
import org.gephi.partition.api.Part;
import org.gephi.partition.api.Partition;
import org.gephi.partition.api.PartitionController;
import org.gephi.partition.plugin.NodeColorTransformer;
import org.gephi.ranking.api.Ranking;
import org.gephi.ranking.api.RankingController;
import org.gephi.ranking.plugin.transformer.AbstractSizeTransformer;

import com.aone.algorithmImpl.GirvanNewman;
import com.aone.algorithmImpl.Modularity;
import com.aone.entity.AllNodesandEdges;
import com.aone.entity.Edges;
import com.aone.entity.Group;
import com.aone.entity.Nodes;


/***
* 类功能：社团划分类，将数据读入graph中，经过社团划分算法，导出符合D3的json
* 类型：普通java类
* 集成关系：无
* 作者：高红。李佩伦
*/
@Service(value="detecteCom")
public class DetecteComImpl extends PApplet implements DetecteCom{
	
	ProjectController pc;
	Workspace workspace;
	GraphModel graphModel;
	static boolean allWrite;
    static DirectedGraph graph;
	HierarchicalGraph hierarchicalGraph;
	
	FilterController filterController;
	static Statistics statistics;
	int GN=2;
	int FastUnfolding=1;
	AttributeColumn modColumn;
	AttributeModel attributeModel;
	

	float scalingRatio=1F;
	private boolean locked = false;
	private float transX = 0;
	private float transY = 0;
	private float xOffset = 0;
	private float yOffset = 0;
	
	public static final int allnetwork = 1;
	public static final int circle = 2;
	public static final int forceatlas = 3;
	
	Partition p;
	Part currentPart;
	
	ArrayList<GraphView> undoList = new ArrayList<GraphView>();
	int undoIndex;
//	private PartitionController partitionController;
	AllNodesandEdges allNodesandEdges;
	
	public static boolean isAllWrite() {
		return allWrite;
	}


	public static void setAllWrite(boolean allWrite) {
		DetecteComImpl.allWrite = allWrite;
	}

	private Layout layout;
//	private PartitionController partitionController;
	

	public AllNodesandEdges DetecteCom(int num,List listAll) throws Exception{
	//	System.out.println("DetecteCom  "+listAll.size());
		
		//Init a project - and therefore a workspace
		//Gephi支持多工作空间，Gephi中不同的Workspace的同时进行不同图形的展示与操作。
        pc = Lookup.getDefault().lookup(ProjectController.class);
        filterController = Lookup.getDefault().lookup(FilterController.class);
	    pc.newProject();
	    workspace = pc.getCurrentWorkspace();
	    
	    //通过Lookup全局类，得到各个对象的操作模型或控制器
		graphModel = Lookup.getDefault().lookup(GraphController.class).getModel();
		graph = graphModel.getDirectedGraph();		
		modColumn = null;
		attributeModel = Lookup.getDefault().lookup(AttributeController.class).getModel();

		//生成graph
		for(int i =0;i<listAll.size();i++){
			Edges edges = new Edges();
			edges = (Edges)listAll.get(i);
			String str1 = edges.getSource().getName();
			String str2 = edges.getTarget().getName();
			
		    
			Node node1 = graphModel.factory().newNode(str1);
			Node node2 = graphModel.factory().newNode(str2);
			
			node1.getNodeData().setX((float) (Math.random()*800));
			node1.getNodeData().setY((float) (Math.random()*600));
		
			node2.getNodeData().setX((float) (Math.random()*800));
			node2.getNodeData().setY((float) (Math.random()*600));
			
			node1.getNodeData().setLabel(str1);
			node2.getNodeData().setLabel(str2);
			
			node1.getNodeData().setSize(5);
			node2.getNodeData().setSize(5);
		//	System.out.println("str1:  "+str1+"  str2:  "+str2); 
			if(graph.getNode(str1) == null){
				graph.addNode(node1);
//				count++;
//				System.out.println(node1.getNodeData().getLabel()+" "+node1.getNodeData().x()+" "+node1.getNodeData().y());
			}
			if(graph.getNode(str2)==null){
				graph.addNode(node2);
//				System.out.println(node2.getNodeData().getLabel()+" "+node2.getNodeData().x()+" "+node2.getNodeData().y());
			}
			
			Edge edge = graphModel.factory().newEdge(graph.getNode(str1), graph.getNode(str2));

			if(graph.contains(edge)){
				edge.setWeight(edge.getWeight()+3);
			}else{
				graph.addEdge(edge);
			}
	    } 
		
		int number=-1;
		 //如果num为0，则使用xml中默认设置的算法进行社团划分
		if(num==0){
			number =1;
//			XmlReaderandWriter XmlReaderandWriter = new XmlReaderandWriter();
//			ConnInfo connInfo1 = XmlReaderandWriter.readerXml("ConnInfo.xml");
//		    number=Integer.parseInt(connInfo1.getAlgorithm());
        }
		else
		{
			number=num;
		}
    //    ArrayList<DataandWeight> al2=new ArrayList<DataandWeight>();
       // System.out.println(number);
       
		//根据number选择社团划分算法执行，参数：graph.getGraphModel()、attributeModel
        switch (number) {
		case 1:
			statistics = new Modularity();
			//Executes the statistics algorithm
			statistics.execute(graph.getGraphModel(), attributeModel);
			modColumn = attributeModel.getNodeTable().getColumn(Modularity.MODULARITY_CLASS);
			contract(modColumn, graph);
			setGraph(graph);
			break;

		case 2:
			statistics = new GirvanNewman(10);
			statistics.execute(graph.getGraphModel(), attributeModel);
			modColumn = attributeModel.getNodeTable().getColumn(GirvanNewman.GN);
			contract(modColumn, graph);
			break;
		}
			
			
		
		/*	*/
		/*
		 * 向前台传点的信息（点的名字。所在团）
		 */
	AllNodesandEdges allNodesandEdges = new AllNodesandEdges();
	
	
	partitionUpdate1(p);
//	System.out.println("社团个数：  "+p.getPartsCount());
	ArrayList<Group> groupList=new ArrayList<Group>();//保存每个社团的节点，社团名，社团节点数目
	ArrayList<Nodes> nodeList=new ArrayList<Nodes>(); 
    for (Part pi : p.getParts()) {
    	Group group = new Group();
    	group.setGroupId(pi.getValue()+"");
    	ArrayList<Nodes> nodeList1=new ArrayList<Nodes>(); //所有节点的集合
    	for(Object n:pi.getObjects())
    	{ 
    		
    		int nodeNb=0;
    		Nodes node = new Nodes(); 		 
    		node.setName(""+n);
			node.setGroupId(pi.getValue()+"");
			node.setNoteWeight("1");
			nodeList1.add(node);
			nodeList.add(node);
			for (Node neighbor : graph.getNeighbors((Node) n).toArray()) {
				  nodeNb++;
			}
			  //取得邻居节点			  
//			  nodeInfo.setNodeNB(nodeNb+"");
    		//System.out.println(n+"  "+nodeNb);
    	}
    	group.setNoteList(nodeList1);
    	
    	//for(Nodes sss:group.getNoteList()){
    		//System.out.println(sss.getName());
    	//}
    	//System.out.println("*************");
    	groupList.add(group);
    	//nodeList.clear();
    }
 // System.out.println("1111111111111111111111111111111111111111111111111111111111111");
   // System.out.println(groupList.size());
//    for(int i=0;i<groupInfos.size();i++){
//		GroupInfo groupInfo1=(GroupInfo)groupInfos.get(i);
//	
//		//System.out.println(i+"   Groupid  "+groupInfo1.getGroupId()+"  nodeSet  "+groupInfo1.getSet().size());
//      
//	
//	}
    //超团
	ArrayList< Nodes> superNodeList=new ArrayList<Nodes>();
	for(int i=0;i<groupList.size();i++){
		Nodes superNode=new  Nodes();
		
		superNode.setName(groupList.get(i).getGroupId());
		superNode.setGroupId(groupList.get(i).getGroupId());
		superNode.setNoteWeight(groupList.get(i).getNoteList().size()+"");
		superNodeList.add(superNode);
	}
    /*
	 * 向前台传社团边的信息（发件人，收件人）
	 *        -gh
	 * 
	 * */

		ArrayList<Edges> edgeList = new ArrayList<Edges>();//所有线的集合
		ArrayList<Edges> inEdgeList=new ArrayList<Edges>();//社团内部的线
//		ArrayList<EdgesInfo> allEdgesSet=new HashSet<EdgesInfo>();
		ArrayList<Group> groupList1=new ArrayList<Group>();//保存每个社团的节点，社团名，社团节点数目以及社团中的边
		ArrayList<Edges> outEdgeList=new ArrayList<Edges>();
		Edge[] edge=graph.getEdges().toArray();
	 
		for (Edge en : edge) {
		    Edges edge1=new Edges();  
			
			org.gephi.graph.api.Node n1, n2;
	    	n1 = en.getSource();
	    	n2 = en.getTarget();
	    	Nodes source = new Nodes();
	    	Nodes target = new Nodes();
	    	source.setName(""+n1);
	    	target.setName(""+n2);
//	    	System.out.println("n1: "+n1+"  "+n2);
	    	for(int i=0;i<groupList.size();i++){//判断线的两个端点是不是在同一个社团中
	    		 Group group=(Group)groupList.get(i);
	    		//System.out.println(group.getNoteList().size());
	    		 for(Nodes node : group.getNoteList()){
		    		 if(node.getName().equals(n1+"")){
		    			// System.out.println("groupInfo2.getGroupId()  "+group.getGroupId());
		    			source.setGroupId(group.getGroupId());
		    		 }
		    		 if(node.getName().equals(n2+"")){
		    			target.setGroupId(group.getGroupId());
		    		 }
	    		 }
	    	}
	    	edge1.setSource(source);
	    	edge1.setTarget(target);
    		 if(source.getGroupId().equals(target.getGroupId())){
    			 inEdgeList.add(edge1);
    		 }else{
    			// System.out.println(edgesInfo.getSourceGroup()+"  "+edgesInfo.getTargetGroup());
    			 outEdgeList.add(edge1);
    		 }
    		 edgeList.add(edge1);
		}
		/*
		 * *
		 */
	
		 
		for(int i=0;i<groupList.size();i++){
			 Group group=(Group)groupList.get(i);
			// System.out.print("dete "+groupInfo2.getGroupId());
			 //社团内边的信息
			ArrayList<Edges> groupInEdgeList=new ArrayList<Edges>();
			
			for(Edges edges:inEdgeList)	//System.out.println("sourceGROUP  "+(String)edgesInfo.getSourceGroup()+"   tARGETGroup:    "+(String)edgesInfo.getSourceGroup());
			{
				if(edges.getSource().getGroupId().equals(group.getGroupId())){
					groupInEdgeList.add(edges);
				}
			}
			//System.out.println("");
			group.setEdgeList(groupInEdgeList);;
			groupList1.add(group);
		}
	//	System.out.println("outside   "+OuteSet.size());
		
		ArrayList<Edges> superNodeEdgeList = new ArrayList<Edges>();//超点所有线的集合
		for(int i=0;i<groupList1.size();i++){
			Group group=groupList1.get(i);
			HashSet<String> nodeSet=new HashSet<String>();//用于保存社团中的点
			//获得该社团中所有的点
			for(int k=0;k<group.getNoteList().size();k++){
				nodeSet.add(group.getNoteList().get(k).getName());
			}
		//	System.out.println();
			for(int j=groupList1.size()-1;j>=0;j--){
				Group group1=groupList1.get(j);
				HashSet<String> nodeSet1=new HashSet<String>();//用于保存社团中的点
				//获得该社团中所有点
				for(int n=0;n<group1.getNoteList().size();n++){
					nodeSet1.add(group1.getNoteList().get(n).getName());
				}
			//	System.out.println(i+"   "+j+"   groupInfo   "+nodeSet.size()+"   groupInfo2   "+node1Set.size());
				for(Edges  edges:outEdgeList){
				
				    String source=edges.getSource().getName();
				    String target=edges.getTarget().getName();
				//    System.out.println(i+"   "+j+"  "+source+"    "+target+"   "+nodeSet.contains(source)+"   "+node1Set.contains(target)+"   "+nodeSet.contains(target)+"   "+node1Set.contains(source));
					if((nodeSet.contains(source)&&nodeSet1.contains(target))||(nodeSet.contains(target)&&nodeSet1.contains(source))){
						Edges outEdge = new Edges();
						//System.out.println(" first  "+groupInfo.getGroupId()+"   "+groupInfo1.getGroupId());
						Nodes sourceNode = new Nodes();
						Nodes targetNode = new Nodes();
						sourceNode.setGroupId(group.getGroupId()+"");
						targetNode.setGroupId(group1.getGroupId()+"");
						sourceNode.setName(superNodeList.get(i).getName());
						targetNode.setName(superNodeList.get(j).getName());
						outEdge.setSource(sourceNode);
						outEdge.setTarget(targetNode);
					//	System.out.println(datas.getOutDegree()+"   "+datas.getInDegree());
						superNodeEdgeList.add(outEdge);
						break;
					}
				}
			}
		}
		
		
		
		
		if(graph.getEdgeCount()<5000){
		allNodesandEdges.setEdgeList(edgeList);;
		allNodesandEdges.setNodeList(nodeList);
		allNodesandEdges.setAllGroups(groupList1);
		}else{
			allNodesandEdges.setEdgeList(superNodeEdgeList);
			allNodesandEdges.setNodeList(superNodeList);
			allNodesandEdges.setAllGroups(groupList1);
		}
		allNodesandEdges.setOutEdgeList(outEdgeList);
//		allNodesAndLinks.setALLEdgesInfos(allEdgesSet);
		//setAllNodesAndLinks(allNodesAndLinks);
		ArrayList<Group> abc=allNodesandEdges.getAllGroups();

		
	
		
		return allNodesandEdges;
		
	}
	public void setGraph(DirectedGraph graph) {
		this.graph = graph;
	}


	public static DirectedGraph getGraph(){

		return graph;
	}
	
	public AttributeColumn getColumn(){
		
		return modColumn;
	}
	
	public AttributeModel getattributeModel(){
		
		return attributeModel;
	}
	
	private void removeWeakComponent() {
		
		GiantComponentFilter filter = new GiantComponentBuilder.GiantComponentFilter();
		filter.init(graph);
		Query query = Lookup.getDefault().lookup(FilterController.class).createQuery(filter);
		graph.readUnlockAll();
		GraphView view = Lookup.getDefault().lookup(FilterController.class).filter(query);
		GraphModel graphModel = graph.getGraphModel();
		graphModel.setVisibleView(view);    //Set the filter result as the visible view
		graph = (DirectedGraph) graphModel.getGraphVisible();
		
	}
	
	private Graph contract(AttributeColumn column, Graph graph) {
//		Graph g =  graph;
		p=Lookup.getDefault().lookup(PartitionController.class).buildPartition(column, graph);
		
		return graph;
	}
	
	public void partitionUpdate1(Partition p) {
		this.p = p;
		/**瀵逛笉鍚岀ぞ鍥㈤殢鏈虹潃鑹*/
		NodeColorTransformer nodeColorTransformer = new NodeColorTransformer();
		nodeColorTransformer.randomizeColors(p);
		Lookup.getDefault().lookup(PartitionController.class).transform(p, nodeColorTransformer);
		
	}
	
	public void keynodeUpdate(int min, int max, String columnId) {
		@SuppressWarnings("rawtypes")
		Ranking centralityRanking = Lookup.getDefault().lookup(RankingController.class).getModel().getRanking(Ranking.NODE_ELEMENT, columnId);	
		@SuppressWarnings("rawtypes")
		//瀵硅妭鐐硅繘琛屽ぇ灏忚浆鎹�
		AbstractSizeTransformer sizeTransformer = (AbstractSizeTransformer) Lookup.getDefault().lookup(RankingController.class).getModel().getTransformer(Ranking.NODE_ELEMENT, org.gephi.ranking.api.Transformer.RENDERABLE_SIZE);
		sizeTransformer.setMinSize(min);
		sizeTransformer.setMaxSize(max);
		Lookup.getDefault().lookup(RankingController.class).transform(centralityRanking, sizeTransformer);
		
	}

	
	@SuppressWarnings("unchecked")
	public void valueChanged(String value) {
		Node node = null;
		if(layout != null && layout.canAlgo())
    		layout.endAlgo();
		if((node = seekNode(value)) != null && p != null) {
//			System.out.println("=================");
			filterController = Lookup.getDefault().lookup(FilterController.class);
			NodePartitionFilter nodePartitionFilter = new NodePartitionFilter(p);
  	        nodePartitionFilter.unselectAll();
  	        /**鍒ゆ柇鑺傜偣鏄惁鍦ㄧぞ鍥腑*/
			Part[] parts = p.getParts();
  	        for(Part part : parts) {
  	        	if(part.isInPart(node)) {
  	        		/**杩欓噷鍙互鍖呭惈澶氫釜绀惧洟锛屽綋鎯宠繃婊ゅ墿涓や釜浠ヤ笂绀惧洟鏃讹紝鍙互灏嗘劅鍏磋叮绀惧洟娣诲姞杩涙潵*/
  	        		nodePartitionFilter.addPart(part);
  	        		currentPart = part;
  	        	}
  	        }
  	        /**鎵ц鍏蜂綋杩囨护鎿嶄綔鏃讹紝闇�噴鏀炬墍鏈夊graph杩涜璇婚攣鐨勬搷浣*/
  	        graph.readUnlockAll();
  	        Query query = filterController.createQuery(nodePartitionFilter);
  	        /**灏嗘墽琛岃繃婊ゆ搷浣滃悗鐨剉iew鏇夸唬褰撳墠view锛屽氨鏄綋鍓嶇殑瑙嗗浘锛屾垨鑰呭彲浠ョ畝鍗曠悊瑙ｄ负瑙傜湅graph鐨勭獥鍙*/
  	        GraphView view = filterController.filter(query);
  	        graphModel.setVisibleView(view); 
	        graph = graphModel.getDirectedGraphVisible();
		}
		if(layout != null)
			layout.initAlgo();
	}
	
    private void partitionUpdate(Partition p2) {
		// TODO Auto-generated method stub
		
	}

	private Node seekNode(String label) {
    	Node[] nodes = graph.getNodes().toArray();
  		for(Node n : nodes) {
  			if(n.getNodeData().getLabel().equals(label)) {
  				return n;
  			} 
  		}
  		return null;
  		
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PApplet.main(new String[] { "--present", "edu.uestc.ginkgo.usedjson.DB2Json" });
		
	}      
}

