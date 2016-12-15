package com.aone.algorithmImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import org.gephi.statistics.plugin.ChartUtils;
import org.gephi.statistics.spi.Statistics;
import org.gephi.utils.longtask.spi.LongTask;
import org.gephi.utils.progress.Progress;
import org.gephi.utils.progress.ProgressTicket;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.openide.util.Lookup;

import com.aone.algorithm.ImportantNode;
import com.aone.entity.Edges;
import com.aone.entity.Nodes;


/**
 * 
 * @author pjmcswee
 */
public class EigenvectorCentrality implements Statistics, LongTask ,ImportantNode{

	public static final String EIGENVECTOR = "eigencentrality";
	private int numRuns = 100;
	private double[] centralities;
	private double sumChange;
	private ProgressTicket progress;
	/** */
	private double epsilon = 0.01f;
	private boolean useEdgeWeight = false;
    /** */
    private double probability = 0.85f;
	private boolean isCanceled;
	private boolean isDirected;

	public EigenvectorCentrality() {
		GraphController graphController = Lookup.getDefault().lookup(GraphController.class);
		if (graphController != null && graphController.getModel() != null) {
			isDirected = graphController.getModel().isDirected();
		}
	}

	public void setNumRuns(int numRuns) {
		this.numRuns = numRuns;
	}

	/**
	 * 
	 * @return
	 */
	public int getNumRuns() {
		return numRuns;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isDirected() {
		return isDirected;
	}

	/**
	 * 
	 * @param isDirected
	 */
	public void setDirected(boolean isDirected) {
		this.isDirected = isDirected;
	}

	/**
	 * 
	 * @param graphModel
	 * @param attributeModel
	 */
	public ArrayList<Nodes> execute(ArrayList<Edges> edgesInfos, GraphModel graphModel, AttributeModel attributeModel) {
		HierarchicalGraph graph = null;
		isDirected=false;
		if (isDirected) {
			graph = graphModel.getHierarchicalDirectedGraphVisible();
		} else {
			graph = graphModel.getHierarchicalUndirectedGraphVisible();
		}
		ArrayList<Nodes> al2 = execute(edgesInfos, graph, attributeModel);
		return al2;
	}

	public ArrayList<Nodes> execute(ArrayList<Edges> edgesInfos, HierarchicalGraph hgraph, AttributeModel attributeModel) {

		AttributeTable nodeTable = attributeModel.getNodeTable();
		AttributeColumn eigenCol = nodeTable.getColumn(EIGENVECTOR);
		if (eigenCol == null) {
			eigenCol = nodeTable.addColumn(EIGENVECTOR, "Eigenvector Centrality", AttributeType.DOUBLE, AttributeOrigin.COMPUTED, new Double(0));
		}

		int N = hgraph.getNodeCount();
		hgraph.readLock();

		double[] temp = new double[N];
		centralities = new double[N];

		Progress.start(progress);
		HashMap<Node, Integer> indicies = new HashMap<Node, Integer>();
		//HashMap<Integer, Node> indicies = new HashMap<Integer, Node>();
		HashMap<Node, Integer> invIndicies = new HashMap<Node, Integer>();
		// 中心性初始化为1
		 int index = 0;
		 double[] weights = null;
	        if (useEdgeWeight) {
	            weights = new double[N];
	        }
	        for (Node s : hgraph.getNodes()) {
	        	
	            indicies.put(s,index);
	            centralities[index] = 1.0f;
	            temp[index] = 0.0f;
	           /* if (useEdgeWeight) {
	            	//System.out.println("进入useedgeweight1了吗？");
	                double sum = 0;
	                EdgeIterable eIter;
	                if (isDirected) {
	                    eIter = ((HierarchicalDirectedGraph) hgraph).getOutEdgesAndMetaOutEdges(s);
	                } else {
	                    eIter = ((HierarchicalUndirectedGraph) hgraph).getEdgesAndMetaEdges(s);
	                }
	                for (Edge edge : eIter) {
	                    sum += edge.getWeight();
	                }
	                weights[index] = sum;
	            }*/
	            
	            index++;
	        }
	        int m=7;
	        double sum=0.0;
	        while (m>0) {
	            double r = probability;
	        	boolean done = true;
	            for (Node s : hgraph.getNodes()) {
	                int s_index = indicies.get(s);
	                //System.out.println("s_index:  "+s_index);
	                boolean out;
	                if (isDirected) {
	                    out = ((HierarchicalDirectedGraph) hgraph).getTotalOutDegree(s) > 0;
	                } else {
	                    out = hgraph.getTotalDegree(s) > 0;
	                }
	                if (out) {
	                    r += (1.0 - probability) * (centralities[s_index] / N);
	                } else {
	                    r += (centralities[s_index] / N);
	                }
	                EdgeIterable eIter;
	                if (isDirected) {
	                    eIter = ((HierarchicalDirectedGraph) hgraph).getInEdgesAndMetaInEdges(s);
	                } else {
	                    eIter = ((HierarchicalUndirectedGraph) hgraph).getEdgesAndMetaEdges(s);
	                }
	                for (Edge edge : eIter) {
	                    Node neighbor = hgraph.getOpposite(s, edge);
	                    int neigh_index = indicies.get(neighbor);
	                    int normalize;
	                    if (isDirected) {  
	                        normalize = ((HierarchicalDirectedGraph) hgraph).getTotalOutDegree(neighbor);
	                    } else {
	                        normalize = ((HierarchicalUndirectedGraph) hgraph).getTotalDegree(neighbor);
	                    }
	                    if (useEdgeWeight) {
	                    	System.out.println("进入useedgeweight2了吗？");
	                        double weight = edge.getWeight() / weights[neigh_index];
	                        temp[s_index] += centralities[neigh_index] * weight;
	                    } else {
	                        temp[s_index] +=centralities[neigh_index]/(normalize*1.0);
	                    }
	                }
	                if (Math.abs(temp[s_index] - centralities[s_index])/centralities[s_index]>=epsilon ) {
	                    done = false;
	                }
	                //temp[s_index]=(1-probability)+probability*temp[s_index];
	            }
	            sum=0.0;
	            //归一化处理
	            for(int i=0;i<temp.length;i++)
		           {
		        	   sum+=temp[i]*temp[i];
		           }
		           for(int i=0;i<temp.length;i++)
		           {
		        	   temp[i]=temp[i]/Math.sqrt(sum);
		           }
		        for(Node s : hgraph.getNodes()){
		            	int index1 = indicies.get(s);
		            	centralities[index1]=temp[index1];
		            }
	            if (done) {
	                break;
	            }
	            m--;
	            //System.out.println("hello!!");
	            //m--;
	        }
	        //测试
//	    for(Node s : hgraph.getNodes()){
//	        int index1 = indicies.get(s);
//	        System.out.println(s.getNodeData().getLabel()+"..."+centralities[index1]);
//	    }		

		ArrayList<Nodes> nodeList = new ArrayList<Nodes>(); // 所有节点的集合
		for (Node s : hgraph.getNodes()) {
			int s_index = indicies.get(s);
			Nodes node = new Nodes();
			node.setName(s + "");
			for (int i = 0; i < edgesInfos.size(); i++) {
				if (edgesInfos.get(i).getSource().getName().equals(s + "")) {
					node.setGroupId(edgesInfos.get(i).getSource().getGroupId());
					break;
				} else if (edgesInfos.get(i).getTarget().getName().equals(s + "")) {
					node.setGroupId(edgesInfos.get(i).getTarget().getGroupId());
					break;
				}
			}
			node.setNoteWeight(centralities[s_index] + "");
			// dataandweight.setGroup(count+"");
			nodeList.add(node);
			// set.add(""+n);

			/*
			 * AttributeRow row = (AttributeRow) s.getNodeData().getAttributes(); row.setValue(eigenCol,
			 * centralities[i]);
			 */

		}
		hgraph.readUnlock();

		return nodeList;
	}

	/**
	 * 
	 * @return
	 */
	public String getReport() {
		// distribution of values
		Map<Double, Integer> dist = new HashMap<Double, Integer>();
		for (int i = 0; i < centralities.length; i++) {
			Double d = centralities[i];
			if (dist.containsKey(d)) {
				Integer v = dist.get(d);
				dist.put(d, v + 1);
			} else {
				dist.put(d, 1);
			}
		}

		// Distribution series
		XYSeries dSeries = ChartUtils.createXYSeries(dist, "Eigenvector Centralities");

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(dSeries);

		JFreeChart chart = ChartFactory.createScatterPlot("Eigenvector Centrality Distribution", "Score", "Count", dataset, PlotOrientation.VERTICAL, true, false, false);
		chart.removeLegend();
		ChartUtils.decorateChart(chart);
		ChartUtils.scaleChart(chart, dSeries, true);
		String imageFile = ChartUtils.renderChart(chart, "eigenvector-centralities.png");

		String report = "<HTML> <BODY> <h1>Eigenvector Centrality Report</h1> " + "<hr>" + "<h2> Parameters: </h2>" + "Network Interpretation:  "
				+ (isDirected ? "directed" : "undirected") + "<br>" + "Number of iterations: " + numRuns + "<br>" + "Sum change: " + sumChange + "<br> <h2> Results: </h2>"
				+ imageFile + "</BODY></HTML>";

		return report;

	}

	public boolean cancel() {
		this.isCanceled = true;
		return true;
	}

	public void setProgressTicket(ProgressTicket progressTicket) {
		this.progress = progressTicket;

	}

	public void execute(GraphModel arg0, AttributeModel arg1) {
		
	}

	@Override
	public ArrayList<Nodes> execute(int number, ArrayList<Edges> edgesInfos, GraphModel graphModel,
			AttributeModel attributeModel) {
		// TODO Auto-generated method stub
		return null;
	}
}
