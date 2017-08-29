package com.ivory.qlcoder.ch14;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import com.ivory.qlcoder.graph.maxtrix.Graph;
import com.ivory.qlcoder.graph.table.DirectedGraph;
import com.ivory.qlcoder.graph.table.GraphInterface;
import com.ivory.qlcoder.util.FileUtil;
import com.ivory.qlcoder.vo.AllUserVo;

/**
 * 
 * 千里码第十五题，热点营销，邻接矩阵图解法
 * 
 * comment:只能处理少量顶点的图，受内存限制局限性很大。且因为是使用数组处理，需要预先固定数组长度
 * 
 * @author huangxiangyu
 * @date: 2017年8月29日 下午9:02:45
 * @version 1.0
 * @since JDK 1.7
 */
public class TestGraphMaxtrx extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testChildGraph() {
        String filenameString = "C:\\Users\\hxy\\Downloads\\weixinguanxi.txt";

        ArrayList<String> arrayList = FileUtil.readFileByLines(filenameString);
        int arrayListSize = arrayList.size();
        
        Graph graph = new Graph();
        
        System.out.println("adding vertexs...");
        for(int i = 0; i < 100; i++){
            graph.addVertex(i);
        }

        System.out.println("Adding edges...");
        for(int i = 0; i < arrayListSize; i++){
            String[] tempStrings = arrayList.get(i).split(" ");
            if(tempStrings.length == 0){
                continue;
            }
            int userId = Integer.parseInt(tempStrings[0].trim());
            int userFriendId = Integer.parseInt(tempStrings[1].trim());
            
            //构造边
            graph.addEdge(userId - 1, userFriendId - 1);
            graph.addEdge(userFriendId - 1, userId - 1);
        }
        System.out.println("不联通子图数量：" + graph.getChildGraphNumber());
    }
}
