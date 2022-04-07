public class Program {
    static final int[][] states={
            { 1, 1, 0, 0, 0, 0 }, //0  | 0 0 0 |
            { 1, 4, 3, 4, 0, 0 }, //1  | 0 1 0 |
            { 0, 3, 0, 0, 0, 0 }, //2  | 1 0 0 |
            { 0, 4, 0, 0, 3, 1 }, //3  | 0 1 1 |
            { 0, 0, 0, 3, 0, 0 }, //4  | 1 0 1 |
            { 0, 0, 0, 1, 0, 1 }, //5  | 0 0 1 |
    };
    static final String[] lblStates={
            "0 0 0",
            "0 1 0",
            "1 0 0",
            "0 1 1",
            "1 0 1",
            "0 0 1"
    };

    static class Node{
        public Node parent;
        public int node;

        Node(int node, Node parent) {
            this.node = node;
            this.parent = parent;
        }


    @Override
    public boolean equals(Object obj) {
        return this.node == ((Node)obj).node;
    }
    }



    static void BFS(Node start, Node goal) throws InterruptedException {
        if (start.equals(goal)){
            PrintPath(start);
            return;
        }

        Queue<Node> open = new Queue<Node>();
        open.enqueue(start);
        HashSet<Node> closed = new HashSet<Node>();

        while (!open.isEmpty()){
            Node x = open.dequeue();
            List<Node> successorsOfX = GetChildrenOf(x);
            closed.add(x);


            for (Node successor: successorsOfX) {
                if (successor.equals(goal)){
                    PrintPath(successor);
                    System.out.println();
                    return;
                }else if(!closed.contains(successor) && !open.equals(successor)){
                    open.enqueue(successor);
                }
            }
        }
    }


    static void PrintPath(Node node){
        if (node.parent != null){
            PrintPath(node.parent);
            System.out.println(lblStates[node.node]);
        }else {
            System.out.println(lblStates[node.node]);
        }
    }

    static List<Node> GetChildrenOf(Node parent){
        List<Node> result = new ArrayList<Node>();
        for (int i = 0; i <states.length ; i++) {
            int[] cost=states[parent.node];
            if (!cost.equals(0)){
                Node newNode = new Node(i, parent);
                result.add(newNode);
                System.out.print(cost[i]);
            }
        }
        System.out.println();
        return result;
    }


    public static void main(String[] args) throws InterruptedException {
        int start = 0;
        int goal = 4;

        BFS(new Node(start, null), new Node(goal, null));
    }
}