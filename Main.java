import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        int imax = 3;
        int[] board = new int[9];
        for (int j = 0; j < 3; j++){
            System.out.println("Enter " + (j+1) + " row of numbers (3 numbers, 0 = blank), space-separated:");
            for (; i < imax; i++)
                board[i] = sc.nextInt();
            imax += 3;
        }
        for (int y = 0; y < 9; y ++)
            System.out.println(board[y]);
        State initial = new State(board);
        
        System.out.println("\n UCS:");
        Node ucsResult = UCS.search(initial);
        printResult(ucsResult);
        
        System.out.println("\n A*:");
        Node astarResult = AStar.search(initial);
        printResult(astarResult);
    }
    
    static void printResult(Node goal) {
        if (goal == null) {
            System.out.println("No solution found.");
            return;
        }
        
        List<String> path = new ArrayList<>();
        Node cur = goal;
        while (cur.move != null) {
            path.add(cur.move.action);
            cur = cur.parent;
        }
        Collections.reverse(path);
        
        System.out.println("Path: " + path);
        System.out.println("Cost: " + goal.g);
    }
}
