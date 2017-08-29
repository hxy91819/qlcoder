package com.ivory.qlcoder.ch15;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import com.ivory.qlcoder.graph.table.DirectedGraph;
import com.ivory.qlcoder.graph.table.GraphInterface;
import com.ivory.qlcoder.util.FileUtil;

/**
 * 
 * 千里码第15题：使用图-邻接表处理
 * 
 * comment：比较好的数据结构设计，可扩展性强。
 *
 * @author huangxiangyu
 * @date: 2017年8月29日 下午9:31:54
 * @version 1.0
 * @since JDK 1.7
 */
public class TestGraphTable extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }
    @Test
    public void testChildGraph() {
        String filenameString = "C:\\Users\\hxy\\Downloads\\weixinguanxi10w.txt";

        ArrayList<String> arrayList = FileUtil.readFileByLines(filenameString);
        int arrayListSize = arrayList.size();
        
        GraphInterface<Integer> graph = new DirectedGraph<>();
        
        System.out.println("Adding vertexs and edges...");
        for(int i = 0; i < arrayListSize; i++){
            String[] tempStrings = arrayList.get(i).split(" ");
            if(tempStrings.length == 0){
                continue;
            }
            int userId = Integer.parseInt(tempStrings[0].trim());
            int userFriendId = Integer.parseInt(tempStrings[1].trim());
            
            //构造顶点,
            //由于添加顶点的时候,会删除已经存在的顶点,导致顶点的关系也被删除,所以此处需要判断是否存在顶点然后再添加
            if(!graph.hasVertex(userId)){
                graph.addVertex(userId);
            }
            if(!graph.hasVertex(userFriendId)){
                graph.addVertex(userFriendId);
            }
            
            //构造边
            graph.addEdge(userId, userFriendId);
            graph.addEdge(userFriendId, userId);
        }
        System.out.println("Number of graph's vertex = " + graph.getNumberOfVertices());
        System.out.println("Number of graph's edge = " + graph.getNumberOfEdges());
        
        System.out.println("Need some time, please wait...");
        
        System.out.println(100000 - graph.getNumberOfVertices() + graph.getChildGraphNumber());
        
        graph.clear();
    }
}
