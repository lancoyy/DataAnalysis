package com.aone.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.aone.entity.Edges;
import com.aone.entity.Nodes;
import com.aone.service.NodeConvertNum;

@Service(value="nodeConvertNum")
public class NodeConvertNumImpl implements NodeConvertNum{
	public static String[] nodes;
	public static String[] Subnodes;
	public static String[] Importnodes;
	public static String[] ImportSubnodes;

	
	/**
	 * @author Joe
     * @function 将后台数据转换成前台D3需要的形式，将edgeList中边的源节点和目标节点名字转化成id
	 * @param nodesList ：所有点的集合
	 * @param edgeList ：所有边的集合
	 * @return
	 */
	public ArrayList<Edges> convertToNum(ArrayList<Nodes> nodesList,ArrayList<Edges> edgeList){
		ArrayList al3 = new ArrayList();
		ArrayList<Edges> results = new ArrayList<Edges>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
	 	int j=0;
	 	int N = nodesList.size();//节点数
 		nodes = new String[N]; //index为节点id，值为节点名
 		
 		//将点的名字转化成id，存入map
	 	for(int i=0;i < nodesList.size();i++){
	 		 nodes[j] = nodesList.get(i).getName();
	 		map.put(nodesList.get(i).getName(),j++);
	 	}
	 	//将edgeList中边的源节点和目标节点名字转化成id
		for (int i = 0; i < edgeList.size(); i++) {
			Edges edges = (Edges) edgeList.get(i);
			Edges edges1 = new Edges();
			String target = edges.getTarget().getName();
			String source = edges.getSource().getName();
			String target1 = map.get(target) + "";
			String source1 = map.get(source) + "";
			Nodes node1 = new Nodes();
			Nodes node2 = new Nodes();
			if (!(target1.equals("null") || source1.equals("null"))) {
				node1.setName(source1);
				node2.setName(target1);
				edges1.setSource(node1);
				edges1.setTarget(node2);
				results.add(edges1);
			}
		}
		al3.add(map);
		al3.add(results);
		return results;
	}
	
	/**
	 * @author 刘庆
	 * @function 根据所给的点和线的集合，把它拼成相应的json格式字符串
	 * @param al2:所有的点的集合
	 * @param al1:所有线的集合
	 * @return
	 */
	public StringBuilder JsonDataAndWeightConvert(ArrayList<Nodes> al2,ArrayList<Edges> al1){
		 StringBuilder sb = new StringBuilder(10000);
		String data = "{\"nodes\":[";
		 sb.append(data);
		int j=0;
		for(int i=0;i<al2.size();i++){
			 if(i!=al2.size()-1){
				 data = "{\"name\":\""+((Nodes)al2.get(i)).getName()+"\",\"Nodeweight\":\""+((Nodes)al2.get(i)).getNoteWeight()+"\",\"group\":"+((Nodes)al2.get(i)).getGroupId()+"},";
				}else {
					 data = "{\"name\":\""+((Nodes)al2.get(i)).getName()+"\",\"Nodeweight\":\""+((Nodes)al2.get(i)).getNoteWeight()+"\",\"group\":"+((Nodes)al2.get(i)).getGroupId()+"}";
				}
				sb.append(String.valueOf(data));
			}
		  
		  data ="],\"links\":[";
		  sb.append(data);
		 for(int i=0;i<al1.size();i++){
			 Edges datas = (Edges)al1.get(i);
			// System.out.println(i+"  "+datas.getOutDegree()+" "+datas.getInDegree());
			 if(i!=al1.size()-1){
				 data = "{\"source\":"+datas.getSource().getName()+",\"target\":"+datas.getTarget().getName()+",\"value\":1},";
				}else {
					 data = "{\"source\":"+datas.getSource().getName()+",\"target\":"+datas.getTarget().getName()+",\"value\":1}";
				}
				sb.append(String.valueOf(data));
			}
		// data = data.substring(0,data.length()-1);
		 data ="]}";
		sb.append(String.valueOf(data));
//	 System.out.println(sb); 
		///String data1=sb.toString();
		 return sb;
/*
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

	ArrayList<Edges> list = new ArrayList<Edges>();
	ArrayList< Nodes> nodelist = new ArrayList<Nodes>();	
	Edges edgesInfo1 = new Edges();
	Nodes source1 = new Nodes();
	Nodes target1 = new Nodes();
	source1.setName("9");
	target1.setName("7");
	edgesInfo1.setSource(source1);
	edgesInfo1.setTarget(target1);
	list.add(edgesInfo1);
	
	Edges edgesInfo2 = new Edges();
	Nodes source2 = new Nodes();
	Nodes target2 = new Nodes();
	source2.setName("7");
	target2.setName("8");
	edgesInfo2.setSource(source2);
	edgesInfo2.setTarget(target2);
	list.add(edgesInfo2);
	
	Edges edgesInfo3 = new Edges();
	Nodes source3 = new Nodes();
	Nodes target3 = new Nodes();
	source3.setName("7");
	target3.setName("5");
	edgesInfo3.setSource(source3);
	edgesInfo3.setTarget(target3);
	list.add(edgesInfo3);
	
	Edges edgesInfo4 = new Edges();
	Nodes source4 = new Nodes();
	Nodes target4 = new Nodes();
	source4.setName("8");
	target4.setName("6");
	edgesInfo4.setSource(source4);
	edgesInfo4.setTarget(target4);
	list.add(edgesInfo4);
	
	Edges edgesInfo5 = new Edges();
	Nodes source5 = new Nodes();
	Nodes target5 = new Nodes();
	source5.setName("6");
	target5.setName("5");
	edgesInfo5.setSource(source5);
	edgesInfo5.setTarget(target5);
	list.add(edgesInfo5);
	
	Edges edgesInfo6 = new Edges();
	Nodes source6 = new Nodes();
	Nodes target6 = new Nodes();
	source6.setName("8");
	target6.setName("5");
	edgesInfo6.setSource(source6);
	edgesInfo6.setTarget(target6);
	list.add(edgesInfo6);
	
	Edges edgesInfo7 = new Edges();
	Nodes source7 = new Nodes();
	Nodes target7 = new Nodes();
	source7.setName("7");
	target7.setName("6");
	edgesInfo7.setSource(source7);
	edgesInfo7.setTarget(target7);
	list.add(edgesInfo7);
	
	Edges edgesInfo8 = new Edges();
	Nodes source8 = new Nodes();
	Nodes target8 = new Nodes();
	source8.setName("5");
	target8.setName("4");
	edgesInfo8.setSource(source8);
	edgesInfo8.setTarget(target8);
	list.add(edgesInfo8);
	
	Edges edgesInfo9 = new Edges();
	Nodes source9 = new Nodes();
	Nodes target9 = new Nodes();
	source9.setName("6");
	target9.setName("4");
	edgesInfo9.setSource(source9);
	edgesInfo9.setTarget(target9);
	list.add(edgesInfo9);
	
	Edges edgesInfo10 = new Edges();
	Nodes source10 = new Nodes();
	Nodes target10 = new Nodes();
	source10.setName("4");
	target10.setName("3");
	edgesInfo10.setSource(source10);
	edgesInfo10.setTarget(target10);
	list.add(edgesInfo10);
	
	Edges edgesInfo11 = new Edges();
	Nodes source11 = new Nodes();
	Nodes target11 = new Nodes();
	source11.setName("3");
	target11.setName("1");
	edgesInfo11.setSource(source11);
	edgesInfo11.setTarget(target11);
	list.add(edgesInfo11);
	
	Edges edgesInfo12 = new Edges();
	Nodes source12 = new Nodes();
	Nodes target12 = new Nodes();
	source12.setName("1");
	target12.setName("2");
	edgesInfo12.setSource(source12);
	edgesInfo12.setTarget(target12);
	list.add(edgesInfo12);
	
	Edges edgesInfo13 = new Edges();
	Nodes source13 = new Nodes();
	Nodes target13 = new Nodes();
	source13.setName("4");
	target13.setName("1");
	edgesInfo13.setSource(source13);
	edgesInfo13.setTarget(target13);
	list.add(edgesInfo13);
	
	Edges edgesInfo14 = new Edges();
	Nodes source14 = new Nodes();
	Nodes target14 = new Nodes();
	source14.setName("3");
	target14.setName("2");
	edgesInfo14.setSource(source14);
	edgesInfo14.setTarget(target14);
	list.add(edgesInfo14);
	
	//点的集合
	nodelist.add(target13);
	nodelist.add(target14);
	nodelist.add(source14);
	nodelist.add(source13);
	nodelist.add(source8);
	nodelist.add(source9);
	nodelist.add(source7);
	nodelist.add(source6);
	nodelist.add(source1);
	JsonDataAndWeightConvert aaa=new JsonDataAndWeightConvert();
	StringBuilder bbb=aaa.JsonDataAndWeightConvert(nodelist, list);
	
	System.out.println(bbb.toString());
	
	}
	*/
	}
	
	
	
}
