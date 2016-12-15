package com.aone.algorithm;

import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.HierarchicalUndirectedGraph;
import org.gephi.utils.progress.ProgressTicket;

public interface Community {
	public String getReport();
	public void setProgressTicket(ProgressTicket progressTicket);
	public void execute(GraphModel graphModel, AttributeModel attributeModel);
	public void execute(HierarchicalUndirectedGraph hgraph, AttributeModel attributeModel);

}
