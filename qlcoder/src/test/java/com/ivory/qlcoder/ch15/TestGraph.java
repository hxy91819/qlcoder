package com.ivory.qlcoder.ch15;

import java.util.Queue;
import java.util.Stack;

import com.ivory.qlcoder.graph.table.DirectedGraph;
import com.ivory.qlcoder.graph.table.GraphInterface;

/**
 * 
 * 调试图的方法（非解题使用，仅供参考）
 *
 * @author huangxiangyu
 * @date: 2017年8月29日 下午9:28:17
 * @version 1.0
 * @since JDK 1.7
 */
public class TestGraph {
    public static void main(String[] args) {
        GraphInterface<String> graph = new DirectedGraph<>();
        System.out.println("Graph is empty? " + graph.isEmpty());

        System.out.println("Adding vertexs...");
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");
        graph.addVertex("5");
        graph.addVertex("6");
        graph.addVertex("7");
        graph.addVertex("8");
        System.out.println("Number of graph's vertex = " + graph.getNumberOfVertices());// 5

        /*
         * <A,D> <A,C> <A,B> <D,C> <C,E>
         */
        System.out.println("Adding edges...");
        graph.addEdge("1", "2");
        graph.addEdge("3", "2");
        graph.addEdge("2", "4");
        graph.addEdge("5", "6");
        graph.addEdge("6", "8");
        graph.addEdge("7", "5");

        System.out.println("Number of graph's edge = " + graph.getNumberOfEdges());// 5

        System.out.println("vertexs between B and A has Edges? " + graph.hasEdge("2", "1"));// false
        System.out.println("vertex between D and C has Edges? " + graph.hasEdge("3", "2"));// true

        System.out.println("Breadth First traverse graph with initial vertex '1'...");
        Queue<String> bfsTraversalOrder = graph.getBreadthFirstTraversal("1");// A
                                                                              // D
                                                                              // C
                                                                              // B
                                                                              // E
        while (!bfsTraversalOrder.isEmpty())
            System.out.print(bfsTraversalOrder.poll() + " ");

        System.out.println("\nDFS traverse graph with inital vertex 1'...");
        Queue<String> dfsTraversalOrder = graph.getDepthFirstTraversal("1");
        while (!dfsTraversalOrder.isEmpty())
            System.out.print(dfsTraversalOrder.poll() + " ");

        System.out.println("\nTopological Order");
        Stack<String> stack = graph.getTopologicalSort();
        while (!stack.isEmpty())
            System.out.print(stack.pop() + " ");

        System.out.println("\ncleanning graph");
        graph.clear();
        System.out.println("Now, number of vertexs = " + graph.getNumberOfVertices());

    }
}
