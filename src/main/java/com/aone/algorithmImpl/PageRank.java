package com.aone.algorithmImpl;


import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.gephi.data.attributes.api.AttributeTable;
import org.gephi.data.attributes.api.AttributeColumn;
import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.data.attributes.api.AttributeOrigin;
import org.gephi.data.attributes.api.AttributeRow;
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
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.openide.util.Lookup;

import com.aone.entity.Edges;
import com.aone.entity.Nodes;
import com.aone.algorithm.ImportantNode;


/**
 * 
 * @author zrh
 */
public class PageRank implements Statistics, LongTask ,ImportantNode {

    public static final String PAGERANK = "pageranks";
    /** */
    private ProgressTicket progress;
    /** */
    private boolean isCanceled;
    /** */
    private double epsilon = 0.01f;
    /** */
    private double probability = 0.85f;
    private boolean useEdgeWeight = false;
    /** */
    public  static double[] pageranks;
    /** */
    private boolean isDirected;

    public PageRank() {
        GraphController graphController = Lookup.getDefault().lookup(GraphController.class);
        if (graphController != null && graphController.getModel() != null) {
            isDirected = graphController.getModel().isDirected();
        }
    }

    public void setDirected(boolean isDirected) {
        this.isDirected = isDirected;
    }

    /**
     *
     * @return
     */
    public boolean getDirected() {
        return isDirected;
    }

    public  ArrayList<Nodes> execute(ArrayList<Edges> edgesInfos,GraphModel graphModel, AttributeModel attributeModel) {
        HierarchicalGraph graph;
        isDirected=false;
        if (isDirected) {
        	//System.out.println("hello");
            graph = graphModel.getHierarchicalDirectedGraphVisible();
        } else {
            graph = graphModel.getHierarchicalUndirectedGraphVisible();
        }
        ArrayList< Nodes> al2=  execute( edgesInfos,graph, attributeModel);
        return al2;
    }

    public  ArrayList< Nodes> execute(ArrayList<Edges> edgesInfos,HierarchicalGraph hgraph, AttributeModel attributeModel) {
        isCanceled = false;

        hgraph.readLock();
        int N = hgraph.getNodeCount();
        pageranks = new double[N];
        double[] temp = new double[N];
        HashMap<Node, Integer> indicies = new HashMap<Node, Integer>();
        int index = 0;

        Progress.start(progress);
        double[] weights = null;
        if (useEdgeWeight) {
            weights = new double[N];
        }
        //初始化每个点
        for (Node s : hgraph.getNodes()) {
        	
            indicies.put(s, index);
            pageranks[index] = 1.0f;
            temp[index] = 0.0f;
            if (useEdgeWeight) {
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
            }
            
            index++;
        }
        int m=7;
        double sum=0.0;
        //pagerank算法
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
                    r += (1.0 - probability) * (pageranks[s_index] / N);
                } else {
                    r += (pageranks[s_index] / N);
                }
                EdgeIterable eIter;
                if (isDirected) {
                    eIter = ((HierarchicalDirectedGraph) hgraph).getInEdgesAndMetaInEdges(s);
                } else {
                    eIter = ((HierarchicalUndirectedGraph) hgraph).getEdgesAndMetaEdges(s);
                }
                //获得s邻居节点的PR值
                for (Edge edge : eIter) {
                    Node neighbor = hgraph.getOpposite(s, edge);//返回节点s的相邻节点
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
                        temp[s_index] += pageranks[neigh_index] * weight;
                    } else {
                    	//将PR值平分给所连接的节点
                        temp[s_index] +=pageranks[neigh_index]/(normalize*1.0);
                    }
                }
                //判断是否收敛
                if (Math.abs(temp[s_index] - pageranks[s_index])/pageranks[s_index]>=epsilon ) {
                    done = false;
                }
                temp[s_index]=(1-probability)+probability*temp[s_index];
            }
            sum=0.0;
            for(int i=0;i<temp.length;i++)
	           {
	        	   sum+=temp[i]*temp[i];
	           }
            //归一化处理
	           for(int i=0;i<temp.length;i++)
	           {
	        	   temp[i]=temp[i]/Math.sqrt(sum);
	           }
	        for(Node s : hgraph.getNodes()){
	            	int index1 = indicies.get(s);
	            	pageranks[index1]=temp[index1];
	            }
            if (done) {
                break;
            }
            m--;
            //System.out.println("hello!!");
            //m--;
        }
        
        //测试
//        for(Node s : hgraph.getNodes()){
//        	int index1 = indicies.get(s);
//            System.out.println(s.getNodeData().getLabel()+"..."+pageranks[index1]);
//        }
        
        AttributeTable nodeTable = attributeModel.getNodeTable();
        AttributeColumn pangeRanksCol = nodeTable.getColumn(PAGERANK);
        if (pangeRanksCol == null) {
            pangeRanksCol = nodeTable.addColumn(PAGERANK, "PageRank", AttributeType.DOUBLE, AttributeOrigin.COMPUTED, new Double(0));
        }
        ArrayList<Nodes> nodeList=new ArrayList<Nodes>(); //所有节点的集合

       // ArrayList<DataandWeight> datasandeight=new ArrayList<DataandWeight>();
        for (Node s : hgraph.getNodes()) {
            int s_index = indicies.get(s);
            Nodes node=new Nodes();
            node.setName(s+"");
	           for(int i=0;i<edgesInfos.size();i++){
	        	   if(edgesInfos.get(i).getSource().getName().equals(s+"")){
	        		   node.setGroupId(edgesInfos.get(i).getSource().getGroupId());
	        		   break;
	        	   }else  if(edgesInfos.get(i).getTarget().getName().equals(s+"")){
	        		   node.setGroupId(edgesInfos.get(i).getTarget().getGroupId());
	        		   break;
	        	   }
	           }
	           node.setNoteWeight(pageranks[s_index]+"");
	          // dataandweight.setGroup(count+"");
	           //System.out.println(dataandweight.getNode()+"  "+dataandweight.getGroup());
				nodeList.add(node);
          
            AttributeRow row = (AttributeRow) s.getNodeData().getAttributes();
            //row.setValue(pangeRanksCol, pageranks[s_index]);
        }

        hgraph.readUnlockAll();
        return nodeList;
    }

    public double[] getPageranks() {
		return pageranks;
	}

	public void setPageranks(double[] pageranks) {
		this.pageranks = pageranks;
	}

	/**
     *
     * @return
     */
    public String getReport() {
        //distribution of values
        Map<Double, Integer> dist = new HashMap<Double, Integer>();
        for (int i = 0; i < pageranks.length; i++) {
            Double d = pageranks[i];
            if (dist.containsKey(d)) {
                Integer v = dist.get(d);
                dist.put(d, v + 1);
            } else {
                dist.put(d, 1);
            }
        }

        StandardChartTheme standardChartTheme=new StandardChartTheme("CN");  
        //设置标题字体  
        standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));  
        //设置图例的字体  
        standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));  
        //设置轴向的字体  
        standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,15));  
        //应用主题样式  
        ChartFactory.setChartTheme(standardChartTheme);
        //Distribution series
        XYSeries dSeries = ChartUtils.createXYSeries(dist, "PageRanks");

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(dSeries);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "PageRank分布",
                "分数",
                "个数",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);
        chart.removeLegend();
        ChartUtils.decorateChart(chart);
        ChartUtils.scaleChart(chart, dSeries, true);
        String imageFile = ChartUtils.renderChart(chart, "pageranks.png");
        
        String report = "<HTML> <BODY> <h1>PageRank报告 </h1> "
                + "<hr> <br />"
                + "<h2> 参数: </h2>"
                + "极小值 = " + epsilon + "<br>"
                + "概率 = " + probability
                + "<br> <h2> 结果: </h2>"
                + imageFile
                + "<br /><br />" + "<h2> 算法: </h2>"
                + "PageRank<br />"
                + "</BODY> </HTML>";

        return report;

    }

    /**
     *
     * @return
     */
    public boolean cancel() {
        isCanceled = true;
        return true;
    }

    /**
     *
     * @param progressTicket
     */
    public void setProgressTicket(ProgressTicket progressTicket) {
        progress = progressTicket;
    }

    /**
     *
     * @param prob
     */
    public void setProbability(double prob) {
        probability = prob;
    }

    /**
     *
     * @param eps
     */
    public void setEpsilon(double eps) {
        epsilon = eps;
    }

    /**
     *
     * @return
     */
    public double getProbability() {
        return probability;
    }

    /**
     *
     * @return
     */
    public double getEpsilon() {
        return epsilon;
    }

    public boolean isUseEdgeWeight() {
        return useEdgeWeight;
    }

    public void setUseEdgeWeight(boolean useEdgeWeight) {
        this.useEdgeWeight = useEdgeWeight;
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
