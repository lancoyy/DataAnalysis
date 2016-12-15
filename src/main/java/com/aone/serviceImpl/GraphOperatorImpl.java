package com.aone.serviceImpl;
import java.util.ArrayList;

import org.gephi.data.attributes.api.AttributeColumn;
import org.gephi.data.attributes.api.AttributeController;
import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.filters.api.FilterController;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.GraphView;
import org.gephi.graph.api.Node;
import org.gephi.partition.api.Part;
import org.gephi.partition.api.Partition;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.gephi.ranking.api.Ranking;
import org.gephi.ranking.api.RankingController;
import org.gephi.ranking.plugin.transformer.AbstractSizeTransformer;
import org.gephi.statistics.spi.Statistics;
import org.openide.util.Lookup;
import org.springframework.stereotype.Service;

import com.aone.algorithmImpl.EigenvectorCentrality;
import com.aone.algorithmImpl.GraphDistance;
import com.aone.algorithmImpl.Hits;
import com.aone.algorithmImpl.PageRank;
import com.aone.algorithmImpl.WeightedDegree;
import com.aone.entity.AllNodesandEdges;
import com.aone.entity.Edges;
import com.aone.entity.Group;
import com.aone.entity.Nodes;
import com.aone.service.GraphOperator;
/******************
 * 这个类现在的功能比较单一，只负责调用角色划分算法实现角色划分的功能，它直接返回graph对象
 *****************/
@Service(value="graphOperator")
public class GraphOperatorImpl implements GraphOperator{

	static Statistics statistics;
	DirectedGraph graph;
	private org.gephi.layout.spi.Layout layout;
	GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getModel();
	Partition p;
	Part currentPart;
	
	float scalingRatio=1F;
	public static final int PAGERANK = 3;
	public static final int	CLOSENESS = 4;
	public static final int BETWEENNESS = 5;
	public static final int DEGREE = 6;
	public static final int HITS = 7;
	public static final int EIGENVECTORCENTRALITY = 8;
	ProjectController pc;
	Workspace workspace;

	FilterController filterController;
	
	int GN=2;
	int FastUnfolding=1;
	AttributeColumn modColumn;
	AttributeModel attributeModel;

	private boolean locked = false;
	private float transX = 0;
	private float transY = 0;
	private float xOffset = 0;
	private float yOffset = 0;
	
	public static final int allnetwork = 1;
	public static final int circle = 2;
	public static final int forceatlas = 3;
	
	ArrayList<GraphView> undoList = new ArrayList<GraphView>();
	int undoIndex;
	/**************
	 * 调用角色分析算法
	 **************/
	public AllNodesandEdges GraphOperator2(int num,ArrayList<Edges> edgesInfos){

		pc = Lookup.getDefault().lookup(ProjectController.class);
		filterController = Lookup.getDefault().lookup(FilterController.class);
		pc.newProject();
		workspace = pc.getCurrentWorkspace();

		graphModel = Lookup.getDefault().lookup(GraphController.class).getModel();
		graph = graphModel.getDirectedGraph();
		modColumn = null;
		attributeModel = Lookup.getDefault().lookup(AttributeController.class).getModel();
		AllNodesandEdges allNodesandEdges = new AllNodesandEdges();
		ArrayList<Group> groupList = new ArrayList<Group>();	
		//ArrayList<DataandWeight> al2=new ArrayList<DataandWeight>(); //所有节点的集合
		modColumn = null;
		attributeModel = Lookup.getDefault().lookup(AttributeController.class).getModel();
		
		//根据edgesInfos创建图
		for(int i =0;i<edgesInfos.size();i++){
			Edges edges = new Edges();
			edges = (Edges)edgesInfos.get(i);
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
		//	System.out.println(" node "+str1+"  "+str2);
			if(graph.getNode(str1) == null){
				graph.addNode(node1);
//				count++;
//				System.out.println(node1.getNodeData().getLabel()+" "+node1.getNodeData().x()+" "+node1.getNodeData().y());
			}
			if(graph.getNode(str2)==null){
				graph.addNode(node2);
//				count++;
//				System.out.println(node2.getNodeData().getLabel()+" "+node2.getNodeData().x()+" "+node2.getNodeData().y());
			}
			
			Edge edge = graphModel.factory().newEdge(graph.getNode(str1), graph.getNode(str2));
			
			if(graph.contains(edge)){
				edge.setWeight(edge.getWeight()+3);
			}else{
				graph.addEdge(edge);
			}
	    }  
		//System.out.println("graph.edgecount()  "+graph.getEdgeCount());
		//System.out.println("graph.edgecount()  "+graph.getNodeCount());
		 AttributeColumn modColumn = null;
		 AttributeModel attributeModel = Lookup.getDefault().lookup(AttributeController.class).getModel();

	
        ArrayList<Nodes> nodeList=new ArrayList<Nodes>();
    //    System.out.println(number);
		switch(num) {
		case PAGERANK:
			PageRank pageRank= new PageRank();
			nodeList= pageRank.execute(edgesInfos,graph.getGraphModel(), attributeModel);
			modColumn = attributeModel.getNodeTable().getColumn(PageRank.PAGERANK);
			break;
		case CLOSENESS:
			GraphDistance graphDistance = new GraphDistance();
			nodeList=graphDistance.execute(num,edgesInfos,graph.getGraphModel(), attributeModel);
			modColumn = attributeModel.getNodeTable().getColumn(GraphDistance.CLOSENESS);
			break;
		case BETWEENNESS:
			GraphDistance graphDistance2 = new GraphDistance();
			nodeList=graphDistance2.execute(num,edgesInfos,graph.getGraphModel(), attributeModel);
			modColumn = attributeModel.getNodeTable().getColumn(GraphDistance.BETWEENNESS);
			break;
		case DEGREE:
			WeightedDegree weightDegree = new WeightedDegree();
			nodeList=weightDegree.execute(edgesInfos,graph.getGraphModel(), attributeModel);
			modColumn = attributeModel.getNodeTable().getColumn(WeightedDegree.WDEGREE);
//			System.out.println("------"+modColumn);
			break;
		case HITS:
			 Hits hits= new Hits();	
			 nodeList= hits.execute(edgesInfos,graph.getGraphModel(), attributeModel);
			 modColumn = attributeModel.getNodeTable().getColumn(Hits.HUB);
			break;
		case EIGENVECTORCENTRALITY:
			 EigenvectorCentrality eigenvectorCentrality= new EigenvectorCentrality();	
			 nodeList= eigenvectorCentrality.execute(edgesInfos,graph.getGraphModel(), attributeModel);
			 modColumn = attributeModel.getNodeTable().getColumn(EigenvectorCentrality.EIGENVECTOR);
			break;
		}
		keynodeUpdate(4, 25, modColumn.getId());//分配节点大小
	    ArrayList<Edges> edgeList = new ArrayList<Edges>();//所有线的集合
		Edge[] edge=graph.getEdges().toArray();
		int count1=0;
		//封装边的出度入度信息
		for (Edge en : edge) {	
			Edges edges = new Edges();
			count1++;
			org.gephi.graph.api.Node n1, n2;
	    	n1 = en.getSource();
	    	n2 = en.getTarget();
	    	Nodes source = new Nodes();
	    	Nodes target = new Nodes();
	    	source.setName(n1+"");
	    	target.setName(n2+"");
	    	edges.setSource(source);
	    	edges.setTarget(target);
	    	edgeList.add(edges);
		}
		allNodesandEdges.setEdgeList(edgeList);
		allNodesandEdges.setNodeList(nodeList);
		return allNodesandEdges;

	}
	//分配节点大小
	public void keynodeUpdate(int min, int max, String columnId) {
		@SuppressWarnings("rawtypes")
		Ranking centralityRanking = Lookup.getDefault().lookup(RankingController.class).getModel().getRanking(Ranking.NODE_ELEMENT, columnId);	
		@SuppressWarnings("rawtypes")
		//对节点进行大小转换
		AbstractSizeTransformer sizeTransformer = (AbstractSizeTransformer) Lookup.getDefault().lookup(RankingController.class).getModel().getTransformer(Ranking.NODE_ELEMENT, org.gephi.ranking.api.Transformer.RENDERABLE_SIZE);
		sizeTransformer.setMinSize(min);
		sizeTransformer.setMaxSize(max);
		Lookup.getDefault().lookup(RankingController.class).transform(centralityRanking, sizeTransformer);
		
	}

//	
//	public void partitionUpdate2(Partition p) {
//		this.p = p;
//		/**瀵逛笉鍚岀ぞ鍥㈤殢鏈虹潃鑹*/
//		NodeColorTransformer nodeColorTransformer = new NodeColorTransformer();
//		nodeColorTransformer.randomizeColors(p);
//		Lookup.getDefault().lookup(PartitionController.class).transform(p, nodeColorTransformer);
//		
//	}
////	
//	private Graph contract(AttributeColumn column, Graph graph) {
////		Graph g =  graph;
//		p=Lookup.getDefault().lookup(PartitionController.class).buildPartition(column, graph);
//		
//		return graph;
//	}
//	
	public DirectedGraph getGraph(){

//		System.out.println(graph.getEdges()+ "-----"+ graph.getNodes());
		return graph;
	}
	@Override
	public String toString() {
		return "GraphOperator [graph=" + graph + ", layout=" + layout
				+ ", graphModel=" + graphModel + ", p=" + p + ", currentPart="
				+ currentPart + "]";
	}
}
