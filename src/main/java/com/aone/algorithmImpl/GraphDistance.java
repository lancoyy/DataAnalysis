
package com.aone.algorithmImpl;

import java.io.IOException;
import java.util.HashMap;

import org.gephi.statistics.plugin.ChartUtils;
import org.gephi.statistics.spi.Statistics;
import org.gephi.graph.api.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Stack;

import org.gephi.data.attributes.api.AttributeTable;
import org.gephi.data.attributes.api.AttributeColumn;
import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.data.attributes.api.AttributeOrigin;
import org.gephi.data.attributes.api.AttributeRow;
import org.gephi.data.attributes.api.AttributeType;
import org.gephi.utils.TempDirUtils;
import org.gephi.utils.TempDirUtils.TempDir;
import org.gephi.utils.longtask.spi.LongTask;
import org.gephi.utils.progress.Progress;
import org.gephi.utils.progress.ProgressTicket;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

import com.aone.algorithm.importantNode;
import com.aone.entity.Edges;
import com.aone.entity.Nodes;

/**
 * 
 *CLOSENESS算法
 * @author zrh
 */
public class GraphDistance implements Statistics, LongTask ,importantNode{

    public static final String BETWEENNESS = "betweenesscentrality";
    public static final String CLOSENESS = "closnesscentrality";
    public static final String ECCENTRICITY = "eccentricity";
    /** */
    private double[] betweenness;
    /** */
    private double[] closeness;
    /** */
    private double[] eccentricity;
    /** */
    private int diameter;
    private int radius;
    /** */
    private double avgDist;
    /** */
    private int N;
    /** */
    private boolean isDirected;
    /** */
    private ProgressTicket progress;
    /** */
    private boolean isCanceled;
    private int shortestPaths;
    private boolean isNormalized = true;

    public GraphDistance() {
        GraphController graphController = Lookup.getDefault().lookup(GraphController.class);
        if (graphController != null && graphController.getModel() != null) {
//            isDirected = graphController.getModel().isDirected();
            isDirected = false;
        }
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

    /**
     *
     * @param graphModel
     */
    public ArrayList< Nodes> execute(int number,ArrayList<Edges> edgesInfos,GraphModel graphModel, AttributeModel attributeModel) {
        HierarchicalGraph graph = null;
        if (isDirected) {
            graph = graphModel.getHierarchicalDirectedGraphVisible();
        } else {
            graph = graphModel.getHierarchicalUndirectedGraphVisible();
        }
        ArrayList<Nodes> al2=  execute(number,edgesInfos,graph, attributeModel);
        return al2;
    }
    
    /**********************
     * @param 关系图模型
     * @param 属性模型类
     * @return ArrayList< DataandWeight>
     * @函数功能  计算关系网络节点的betweenness、closeness、eccentricity（偏离度）
     *********************/
    public ArrayList< Nodes> execute(int number,ArrayList<Edges> edgesInfos,HierarchicalGraph hgraph, AttributeModel attributeModel) {
        isCanceled = false;
        AttributeTable nodeTable = attributeModel.getNodeTable();
        AttributeColumn eccentricityCol = nodeTable.getColumn(ECCENTRICITY);
        AttributeColumn closenessCol = nodeTable.getColumn(CLOSENESS);
        AttributeColumn betweenessCol = nodeTable.getColumn(BETWEENNESS);
        if (eccentricityCol == null) {
            eccentricityCol = nodeTable.addColumn(ECCENTRICITY, "Eccentricity", AttributeType.DOUBLE, AttributeOrigin.COMPUTED, new Double(0));
        }
        if (closenessCol == null) {
            closenessCol = nodeTable.addColumn(CLOSENESS, "Closeness Centrality", AttributeType.DOUBLE, AttributeOrigin.COMPUTED, new Double(0));
        }
        if (betweenessCol == null) {
            betweenessCol = nodeTable.addColumn(BETWEENNESS, "Betweenness Centrality", AttributeType.DOUBLE, AttributeOrigin.COMPUTED, new Double(0));
        }

        hgraph.readLock();

        N = hgraph.getNodeCount();//统计图中点的个数

        betweenness = new double[N];
        eccentricity = new double[N];
        closeness = new double[N];
        diameter = 0;
        avgDist = 0;
        shortestPaths = 0;
        radius = Integer.MAX_VALUE;
        HashMap<Node, Integer> indicies = new HashMap<Node, Integer>();
        int index = 0;
        //给每个点编号
        for (Node s : hgraph.getNodes()) {
            indicies.put(s, index);
            index++;
        }

        Progress.start(progress, hgraph.getNodeCount());
        int count = 0;
        for (Node s : hgraph.getNodes()) {
            Stack<Node> S = new Stack<Node>();

            LinkedList<Node>[] P = new LinkedList[N];//保存到达每个点的所有前驱节点
            double[] theta = new double[N];
            int[] d = new int[N];
            //初始化
            for (int j = 0; j < N; j++) {
                P[j] = new LinkedList<Node>();
                theta[j] = 0;//以s为起点，j为终点的边的条数
                d[j] = -1;//保存点s到点j的距离
            }

            int s_index = indicies.get(s);//获得节点s的编号

            theta[s_index] = 1;
            d[s_index] = 0;
            LinkedList<Node> Q = new LinkedList<Node>();//保存外围的点
            Q.addLast(s);
            while (!Q.isEmpty()) {
                Node v = Q.removeFirst();
                S.push(v);
                int v_index = indicies.get(v);

                EdgeIterable edgeIter = null;
                if (isDirected) {
                	//得到以节点v为起点的边的迭代器
                    edgeIter = ((HierarchicalDirectedGraph) hgraph).getOutEdgesAndMetaOutEdges(v);
                } else {
                    edgeIter = hgraph.getEdgesAndMetaEdges(v);
                }

                for (Edge edge : edgeIter) {
                	//得到与节点v通过边edge相连的点
                    Node reachable = hgraph.getOpposite(v, edge);

                    int r_index = indicies.get(reachable);
                    //若节点s到节点reachable的距离小于零，则添加s到reachable的距离
                    if (d[r_index] < 0) {
                        Q.addLast(reachable);
                        d[r_index] = d[v_index] + 1;
                    }
                    //
                    if (d[r_index] == (d[v_index] + 1)) {
                        theta[r_index] = theta[r_index] + theta[v_index];
                        P[r_index].addLast(v);
                    }
                }
            }
            double reachable = 0;//s能到达的点的个数
            for (int i = 0; i < N; i++) {
                if (d[i] > 0) {
                    avgDist += d[i];
                    eccentricity[s_index] = (int) Math.max(eccentricity[s_index], d[i]);
                    closeness[s_index] += d[i];//s到所有点距离的和
                    diameter = Math.max(diameter, d[i]);
                    reachable++;
                }
            }

            radius = (int) Math.min(eccentricity[s_index], radius);

            if (reachable != 0) {
            	//计算点s到每个点的平均距离
                closeness[s_index] = closeness[s_index]/reachable;
            }

            shortestPaths += reachable;
            //betweenness
            double[] delta = new double[N];
            while (!S.empty()) {
                Node w = S.pop();
                int w_index = indicies.get(w);
                ListIterator<Node> iter1 = P[w_index].listIterator();
                while (iter1.hasNext()) {
                    Node u = iter1.next();
                    int u_index = indicies.get(u);
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
            Progress.progress(progress, count);
        }

        avgDist /= shortestPaths;//mN * (mN - 1.0f);
        ArrayList<Nodes> nodeList=new ArrayList<Nodes>(); //所有节点的集合
        for (Node s : hgraph.getNodes()) {
            AttributeRow row = (AttributeRow) s.getNodeData().getAttributes();
            int s_index = indicies.get(s);

            if (!isDirected) {
                betweenness[s_index] /= 2;
            }
            //closeness,求距离的导数
            closeness[s_index] = (closeness[s_index] == 0) ? 0 : 1.0 / closeness[s_index];
            betweenness[s_index] /= isDirected ? (N - 1) * (N - 2) : (N - 1) * (N - 2) / 2;
            
            //测试
//            if(number==5){
//            System.out.println(s.getNodeData().getLabel()+"..."+betweenness[s_index]);
//            }
//            else if(number==4){
//            	System.out.println(s.getNodeData().getLabel()+"..."+closeness[s_index]);
//            }

            row.setValue(eccentricityCol, eccentricity[s_index]);
            row.setValue(closenessCol, closeness[s_index]);
            row.setValue(betweenessCol, betweenness[s_index]);
            Nodes node=new Nodes();
            //将节点信息封装到DataandWeight中
	           node.setName(s+"");
	           for(int j=0;j<edgesInfos.size();j++){
	        	   if(edgesInfos.get(j).getSource().getName().equals(s+"")){
	        		   node.setGroupId(edgesInfos.get(j).getSource().getGroupId());
	        		   break;
	        	   }else  if(edgesInfos.get(j).getTarget().getName().equals(s+"")){
	        		   node.setGroupId(edgesInfos.get(j).getTarget().getGroupId());
	        		   break;
	        	   }
	           }
	           //根据number设置相关算法的权重
	           if(number==4){
	        	   node.setNoteWeight(closeness[s_index]+"");
	           }
	           else{
	               node.setNoteWeight(betweenness[s_index]+"");
	           }
	           //System.out.println(" betweenessCol: "+(s+"   ")+ betweenness[s_index]);//authority[s_index]);
	          // System.out.println(" closenessCol: "+ (s+"   ")+closeness[s_index]);//authority[s_index]);

	           nodeList.add(node);
        }
        hgraph.readUnlock();
        return nodeList;
    }

    public void setNormalized(boolean isNormalized) {
        this.isNormalized = isNormalized;
    }

    public boolean isNormalized() {
        return isNormalized;
    }

    public void setDirected(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public boolean isDirected() {
        return isDirected;
    }

    private String createImageFile(TempDir tempDir, double[] pVals, String pName, String pX, String pY) {
        //distribution of values
        Map<Double, Integer> dist = new HashMap<Double, Integer>();
        for (int i = 0; i < N; i++) {
            Double d = pVals[i];
            if (dist.containsKey(d)) {
                Integer v = dist.get(d);
                dist.put(d, v + 1);
            } else {
                dist.put(d, 1);
            }
        }

        //Distribution series
        XYSeries dSeries = ChartUtils.createXYSeries(dist, pName);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(dSeries);

        JFreeChart chart = ChartFactory.createXYLineChart(
                pName,
                pX,
                pY,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);
        chart.removeLegend();
        ChartUtils.decorateChart(chart);
        ChartUtils.scaleChart(chart, dSeries, isNormalized);
        return ChartUtils.renderChart(chart, pName + ".png");
    }

    /**
     *
     * @return
     */
    public String getReport() {
        String htmlIMG1 = "";
        String htmlIMG2 = "";
        String htmlIMG3 = "";
        try {
            TempDir tempDir = TempDirUtils.createTempDir();
            htmlIMG1 = createImageFile(tempDir, betweenness, "Betweenness Centrality Distribution", "Value", "Count");
            htmlIMG2 = createImageFile(tempDir, closeness, "Closeness Centrality Distribution", "Value", "Count");
            htmlIMG3 = createImageFile(tempDir, eccentricity, "Eccentricity Distribution", "Value", "Count");
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

        String report = "<HTML> <BODY> <h1>Graph Distance  Report </h1> "
                + "<hr>"
                + "<br>"
                + "<h2> Parameters: </h2>"
                + "Network Interpretation:  " + (isDirected ? "directed" : "undirected") + "<br />"
                + "<br /> <h2> Results: </h2>"
                + "Diameter: " + diameter + "<br />"
                + "Radius: " + radius + "<br />"
                + "Average Path length: " + avgDist + "<br />"
                + "Number of shortest paths: " + shortestPaths + "<br /><br />"
                + htmlIMG1 + "<br /><br />"
                + htmlIMG2 + "<br /><br />"
                + htmlIMG3
                + "<br /><br />" + "<h2> Algorithm: </h2>"
                + "Ulrik Brandes, <i>A Faster Algorithm for Betweenness Centrality</i>, in Journal of Mathematical Sociology 25(2):163-177, (2001)<br />"
                + "</BODY> </HTML>";

        return report;
    }

    /**
     * 
     * @return
     */
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

	public void execute(GraphModel arg0, AttributeModel arg1) {
		
	}

	@Override
	public ArrayList<Nodes> execute(ArrayList<Edges> edgesInfos, GraphModel graphModel, AttributeModel attributeModel) {
		// TODO Auto-generated method stub
		return null;
	}

}
