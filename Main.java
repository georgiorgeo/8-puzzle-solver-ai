import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    
    // enarksh tou programmatos
    // prospelash tou arxikou pinaka, ektelountai oi UCS kai A* kai epeita ektupwnetai to apotelesma
    public static void main(String[] args) {
        // diabasma tou pinaka grammh-grammh apo ton xrhsth
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
        State data = new State(board);
        System.out.println("\n" + data);
        
        // ektelesh UCS kai ektypwsh apotelesmatos
        System.out.println("\n UCS:");
        Node ucsRes = UCS.search(data);
        printResult(ucsRes);
        
        // ektelesh A* kai ektypwsh apotelesmatos
        System.out.println("\n A*:");
        Node ARes = AStar.search(data);
        printResult(ARes);
    }
    
    // anaparagei thn diadromh apo thn arxikh katastash ews ton stoxo
    // ektypwnei to monopati, to plithos twn kinhsewn pou to apoteloun kai to sunoliko kostos
    static void printResult(Node goal) {
        if (goal == null) {
            System.out.println("No solution found.");
            return;
        }
        
        List<String> path = new ArrayList<>();
        Node cur = goal;
        while (cur.move != null) {
            path.add(0 , cur.move);
            cur = cur.prevNode;
        }
        System.out.println("Path: " + path);
        System.out.println("Moves: " + path.size());
        System.out.println("Cost: " + goal.g);
    }
}
