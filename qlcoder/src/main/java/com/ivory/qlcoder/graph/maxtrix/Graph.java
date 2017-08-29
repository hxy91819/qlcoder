package com.ivory.qlcoder.graph.maxtrix;

/**
 * 
 * 邻接矩阵实现图
 *
 * @author huangxiangyu
 * @date: 2017年8月27日 下午5:44:34
 * @version 1.0
 * @since JDK 1.7
 */
public class Graph {
    public final int MAX_VERTEX = 100;// 最大顶点数量
    Vertex[] vertex;// 顶点数组
    int[][] adjacency;// 邻接矩阵
    int numOfVertex;// 当前图中顶点的数量

    public Graph() {// 构造器
        vertex = new Vertex[MAX_VERTEX];
        adjacency = new int[MAX_VERTEX][MAX_VERTEX];
        numOfVertex = 0;
        // 将邻接矩阵初始化
        for (int i = 0; i < MAX_VERTEX; i++) {
            for (int j = 0; j < MAX_VERTEX; j++)
                adjacency[i][j] = 0;
        }
    }

    // 添加顶点
    public void addVertex(int v) {
        vertex[numOfVertex++] = new Vertex(v);
    }

    // 无向图 添加边
    public void addEdge(int start, int end) {
        adjacency[start][end] = 1;
        adjacency[end][start] = 1;
    }

    // 有向图添加边
    public void addEdge1(int start, int end) {
        adjacency[start][end] = -1;
    }

    // 打印某个顶点
    public void showVertex(int index) {
        System.err.print(vertex[index].label + " ");
    }

    // 打印邻接矩阵
    public void show() {
        for (int i = 0; i < MAX_VERTEX; i++) {
            for (int j = 0; j < MAX_VERTEX; j++) {
                if (j == MAX_VERTEX - 1)
                    System.out.println(adjacency[i][j] + "  ");
                else
                    System.out.print(adjacency[i][j] + "  ");
            }
        }
    }

    /**
     * 找到与某一顶点邻接而未被访问的顶点,如何做？
     * 在邻接矩阵中，找到指定顶点所在的行，从第一列开始向后寻找值为1的列，列号是邻接顶点的号码，检查此顶点是否访问过。
     * 如果该行没有值为1而又未访问过的点，则此顶点的邻接点都访问过了。
     */
    public int getUnVisitedVertex(int index) {
        for (int i = 0; i < numOfVertex; i++)
            if (adjacency[index][i] == 1 && vertex[i].wasVisited == false)
                return i;
        return -1;
    }

    // 图的深度优先遍历
    public void dfs() {
        vertex[0].wasVisited = true;// 从头开始访问
        showVertex(0);
        Stack stack = new Stack();
        stack.push(0);
        /**
         * 1.用peek()方法获取栈顶的顶点 2.试图找到这个顶点的未访问过的邻接点 3.如果没有找到这样的顶点，出栈 4.如果找到，访问之，入栈
         */
        while (!stack.isEmpty()) {
            int index = getUnVisitedVertex(stack.peek());
            if (index == -1)// 没有这个顶点
                stack.pop();
            else {
                vertex[index].wasVisited = true;
                showVertex(index);
                stack.push(index);
            }
        }
        // 栈为空，遍历结束，标记位重新初始化
        for (int i = 0; i < numOfVertex; i++)
            vertex[i].wasVisited = false;
    }

    // 指定起点的-图的深度优先遍历
    public void dfs(int startVertex) {
        vertex[startVertex].wasVisited = true;
        vertex[startVertex].wasMarked = true;

        showVertex(startVertex);
        Stack stack = new Stack();
        stack.push(startVertex);
        /**
         * 1.用peek()方法获取栈顶的顶点 2.试图找到这个顶点的未访问过的邻接点 3.如果没有找到这样的顶点，出栈 4.如果找到，访问之，入栈
         */
        while (!stack.isEmpty()) {
            int index = getUnVisitedVertex(stack.peek());
            if (index == -1)// 没有这个顶点
                stack.pop();
            else {
                vertex[index].wasVisited = true;
                vertex[index].wasMarked = true;
                showVertex(index);
                stack.push(index);
            }
        }
        // 栈为空，遍历结束，标记位重新初始化
        for (int i = 0; i < numOfVertex; i++)
            vertex[i].wasVisited = false;
    }

    // 图的广度优先遍历
    public void bfs() {
        vertex[0].wasVisited = true;
        showVertex(0);
        Queue queue = new Queue();
        queue.insert(0);
        int v2;
        while (!queue.isEmpty()) {// 直到队列为空
            int v1 = queue.remove();
            // 直到点v1没有未访问过的邻接点
            while ((v2 = getUnVisitedVertex(v1)) != -1) {
                // 取到未访问过的点，访问之
                vertex[v2].wasVisited = true;
                showVertex(v2);
                queue.insert(v2);
            }
        }
        for (int i = 0; i < numOfVertex; i++)
            vertex[i].wasVisited = false;
    }

    // 最小生成树 minimum spanning tree
    public void mst() {
        vertex[0].wasVisited = true;
        Stack stack = new Stack();
        stack.push(0);
        while (!stack.isEmpty()) {
            int currentVertex = stack.peek();
            int v = getUnVisitedVertex(currentVertex);
            if (v == -1)
                stack.pop();
            else {
                vertex[v].wasVisited = true;
                stack.push(v);
                // 当前顶点与下一个未访问过的邻接点
                showVertex(currentVertex);
                showVertex(v);
                System.out.print("  ");
            }
        }
        for (int i = 0; i < numOfVertex; i++)
            vertex[i].wasVisited = false;
    }

    // 获取不联通子图数量
    public int getChildGraphNumber() {
        int childGraphCount = 0;
        while (true) {
            // 循环找到没有被标记的顶点
            int i = 0;
            for (; i < numOfVertex; i++) {
                if (!vertex[i].wasMarked) { //如果点没有被标记，说明是不联通子图的一个点
                    dfs(i);//使用dfs进行图的遍历，遍历过的点进行标记
                    System.out.println();
                    childGraphCount++;
                    break;
                }
            }

            if (i == numOfVertex) {
                break;
            }
        }
        
        //重置标记
        for(int i=0; i < numOfVertex; i++){
            vertex[i].wasMarked = false;
        }
        return childGraphCount;
    }
    

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);

        graph.addEdge(1, 2);
        graph.addEdge(0, 1);
        graph.addEdge(2, 3);

        graph.addEdge(4, 5);
        graph.addEdge(6, 5);
        graph.show();
        // graph.dfs();
        // graph.bfs();
        // graph.mst();
        
        System.out.println(graph.getChildGraphNumber());
        
        System.out.println(graph.getChildGraphNumber());
    }

}

// 顶点
class Vertex {
    int label;// 如A，B,C
    boolean wasVisited;// 标识是否访问过此顶点,用于遍历
    boolean wasMarked;// 标识是否已经加入子图,用于找子图

    public Vertex(int vertex) {
        this.label = vertex;
        wasVisited = false;
        wasMarked = false;
    }
}

// 栈
class Stack {
    final int MAX_SIZE = 100;
    int stack[];
    int top;

    public Stack() {
        stack = new int[MAX_SIZE];
        top = -1;
    }

    public void push(int idata) {
        stack[++top] = idata;
    }

    public int pop() {
        return stack[top--];
    }

    public int peek() {
        return stack[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}

// 队列
class Queue {
    final int SIZE = 10;
    int[] qarray;
    int front;
    int rear;

    public Queue() {
        qarray = new int[SIZE];
        front = 0;
        rear = -1;
    }

    // 在队尾追加
    public void insert(int key) {
        if (rear == SIZE - 1)
            rear = -1;
        qarray[++rear] = key;
    }

    // 队头删除数据
    public int remove() {
        int temp = qarray[front++];
        if (front == SIZE)
            front = 0;
        return temp;
    }

    public boolean isEmpty() {
        return rear + 1 == front || front + SIZE - 1 == rear;
    }
}