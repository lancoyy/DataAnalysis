package com.aone.algorithm;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.gephi.graph.api.HierarchicalUndirectedGraph;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.NodeIterable;
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

/**
 *
 * @author pjmcswee
 */
public class ConnectedComponents implements Statistics, LongTask {

    public static final String WEAKLY = "componentnumber";
    public static final String STRONG = "strongcompnum";
    private boolean isDirected;
    private ProgressTicket progress;
    private boolean isCanceled;
    private int componentCount;
    private int stronglyCount;
    private int[] componentsSize;
    int count;

    public ConnectedComponents() {
        GraphController graphController = Lookup.getDefault().lookup(GraphController.class);
        if (graphController != null && graphController.getModel() != null) {
            isDirected = graphController.getModel().isDirected();
        }
    }

    public void execute(GraphModel graphModel, AttributeModel attributeModel) {

        HierarchicalUndirectedGraph undirectedGraph = graphModel.getHierarchicalUndirectedGraphVisible();
        weaklyConnected(undirectedGraph, attributeModel);
        if (isDirected) {
            HierarchicalDirectedGraph directedGraph = graphModel.getHierarchicalDirectedGraphVisible();
            top_tarjans(directedGraph, attributeModel);
        }
    }

    public Set weaklyConnected(HierarchicalUndirectedGraph hgraph, AttributeModel attributeModel) {
        isCanceled = false;
        componentCount = 0;
        Set<LinkedList<Node>> clusterSet = new HashSet<LinkedList<Node>>();
        AttributeTable nodeTable = attributeModel.getNodeTable();
        AttributeColumn componentCol = nodeTable.getColumn(WEAKLY);
        if (componentCol == null) {
            componentCol = nodeTable.addColumn(WEAKLY, "Component ID", AttributeType.INT, AttributeOrigin.COMPUTED, new Integer(0));
        }

        List<Integer> sizeList = new ArrayList<Integer>();

        hgraph.readLock();

        HashMap<Node, Integer> indicies = new HashMap<Node, Integer>();
        int index = 0;
        for (Node s : hgraph.getNodes()) {
            indicies.put(s, index);
            index++;
        }


        int N = hgraph.getNodeCount();

        //Keep track of which nodes have been seen
        int[] color = new int[N];

        Progress.start(progress, hgraph.getNodeCount());
        int seenCount = 0;
        while (seenCount < N) {
            //The search Q
            LinkedList<Node> Q = new LinkedList<Node>();
            //The component-list
            LinkedList<Node> component = new LinkedList<Node>();

            //Seed the seach Q
            NodeIterable iter = hgraph.getNodes();
            for (Node first : iter) {
                if (color[indicies.get(first)] == 0) {
                    Q.add(first);
                    iter.doBreak();
                    break;
                }
            }

            //While there are more nodes to search
            while (!Q.isEmpty()) {
                if (isCanceled) {
                    hgraph.readUnlock();
                    return null;
                }
                //Get the next Node and add it to the component list
                Node u = Q.removeFirst();
                component.add(u);

                //Iterate over all of u's neighbors
                EdgeIterable edgeIter = hgraph.getEdgesAndMetaEdges(u);

                //For each neighbor
                for (Edge edge : edgeIter) {
                    Node reachable = hgraph.getOpposite(u, edge);
                    int id = indicies.get(reachable);
                    //If this neighbor is unvisited
                    if (color[id] == 0) {
                        color[id] = 1;
                        //Add it to the search Q
                        Q.addLast(reachable);
                        //Mark it as used 

                        Progress.progress(progress, seenCount);
                    }
                }
                color[indicies.get(u)] = 2;
                seenCount++;
            }
            for (Node s : component) {
                AttributeRow row = (AttributeRow) s.getNodeData().getAttributes();
                row.setValue(componentCol, componentCount);
            }
            sizeList.add(component.size());
            clusterSet.add(component);
            componentCount++;
        }
        hgraph.readUnlock();

        componentsSize = new int[sizeList.size()];
        for (int i = 0; i < sizeList.size(); i++) {
            componentsSize[i] = sizeList.get(i);
        }
        return clusterSet;
    }

    public void top_tarjans(HierarchicalDirectedGraph hgraph, AttributeModel attributeModel) {
        count = 1;
        stronglyCount = 0;
        AttributeTable nodeTable = attributeModel.getNodeTable();
        AttributeColumn componentCol = nodeTable.getColumn(STRONG);
        if (componentCol == null) {
            componentCol = nodeTable.addColumn(STRONG, "Strongly-Connected ID", AttributeType.INT, AttributeOrigin.COMPUTED, new Integer(0));
        }

        hgraph.readLock();

        HashMap<Node, Integer> indicies = new HashMap<Node, Integer>();
        int v = 0;
        for (Node s : hgraph.getNodes()) {
            indicies.put(s, v);
            v++;
        }
        int N = hgraph.getNodeCount();
        int[] index = new int[N];
        int[] low_index = new int[N];

        while (true) {
            //The search Q
            LinkedList<Node> S = new LinkedList<Node>();
            //The component-list
            //LinkedList<Node> component = new LinkedList<Node>();
            //Seed the seach Q
            Node first = null;
            NodeIterable iter = hgraph.getNodes();
            for (Node u : iter) {
                if (index[indicies.get(u)] == 0) {
                    first = u;
                    iter.doBreak();
                    break;
                }
            }
            if (first == null) {
                hgraph.readUnlockAll();
                return;
            }
            tarjans(componentCol, S, hgraph, first, index, low_index, indicies);
        }
    }

    private void tarjans(AttributeColumn col, LinkedList<Node> S, HierarchicalDirectedGraph hgraph, Node f, int[] index, int[] low_index, HashMap<Node, Integer> indicies) {
        int id = indicies.get(f);
        index[id] = count;
        low_index[id] = count;
        count++;
        S.addFirst(f);
        EdgeIterable edgeIter = hgraph.getOutEdgesAndMetaOutEdges(f);
        for (Edge e : edgeIter) {
            Node u = hgraph.getOpposite(f, e);
            int x = indicies.get(u);
            if (index[x] == 0) {
                tarjans(col, S, hgraph, u, index, low_index, indicies);
                low_index[id] = Math.min(low_index[x], low_index[id]);
            } else if (S.contains(u)) {
                low_index[id] = Math.min(low_index[id], index[x]);
            }
        }
        if (low_index[id] == index[id]) {
            Node v = null;
            while (v != f) {
                v = S.removeFirst();
                AttributeRow row = (AttributeRow) v.getNodeData().getAttributes();
                row.setValue(col, stronglyCount);
            }
            stronglyCount++;
        }
    }

    public int getConnectedComponentsCount() {
        return componentCount;
    }

    public void setDirected(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public boolean isDirected() {
        return isDirected;
    }

    public int[] getComponentsSize() {
        return componentsSize;
    }

    public int getGiantComponent() {
        int[] sizes = getComponentsSize();
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < sizes.length; i++) {
            if (sizes[i] > max) {
                max = sizes[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public String getReport() {
        Map<Integer, Integer> sizeDist = new HashMap<Integer, Integer>();
        for(int v : componentsSize) {
            if(!sizeDist.containsKey(v)) {
                sizeDist.put(v, 0);
            }
            sizeDist.put(v, sizeDist.get(v) + 1);
        }
        
        //Distribution series
        XYSeries dSeries = ChartUtils.createXYSeries(sizeDist, "Size Distribution");

        XYSeriesCollection dataset1 = new XYSeriesCollection();
        dataset1.addSeries(dSeries);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Size Distribution",
                "Size (number of nodes)",
                "Count",
                dataset1,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);
        chart.removeLegend();
        ChartUtils.decorateChart(chart);
        ChartUtils.scaleChart(chart, dSeries, false);
        String imageFile = ChartUtils.renderChart(chart, "cc-size-distribution.png");

        NumberFormat f = new DecimalFormat("#0.000");


        String report = "<HTML> <BODY> <h1>Connected Components Report </h1> "
                + "<hr>"
                + "<br>"
                + "<h2> Parameters: </h2>"
                + "Network Interpretation:  " + (isDirected ? "directed" : "undirected") + "<br>"
                + "<br> <h2> Results: </h2>"
                + "Number of Weakly Connected Components: " + componentCount + "<br>"
                + (isDirected ? "Number of Stronlgy Connected Components: " + stronglyCount + "<br>" : "")
                + "<br /><br />"+imageFile
                + "<br />" + "<h2> Algorithm: </h2>"
                + "Robert Tarjan, <i>Depth-First Search and Linear Graph Algorithms</i>, in SIAM Journal on Computing 1 (2): 146–160 (1972)<br />"
                + "</BODY> </HTML>";

        return report;
    }

    public boolean cancel() {
        isCanceled = true;
        return true;
    }

    public void setProgressTicket(ProgressTicket progressTicket) {
        progress = progressTicket;
    }
}
