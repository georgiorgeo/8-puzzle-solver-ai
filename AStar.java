import java.util.*;

public class AStar {
    
    public static Node search(State initial) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingDouble(n -> n.f()));
        HashSet<State> visited = new HashSet<>();
        
        double h0 = Heuristic.compute(initial);
        Node startNode = new Node(initial, null, null, 0, h0);
        frontier.add(startNode);
        
        int expansions = 0;
        
        while (!frontier.isEmpty()){
            Node current = frontier.poll();
            if (visited.contains(current.state)) continue;
            visited.add(current.state);
            
            if (current.state.isGoal()) {
                System.out.println("A* epektaseis: " + expansions);
                return current;
            }
            
            expansions++;
            
            List<Node> successors = Successor.getSuccessors(current);
            for (Node child : successors) {
                if (!visited.contains(child.state)) {
                    child.h = Heuristic.compute(child.state);
                    frontier.add(child);
                }
            }
        }
        
        System.out.println("A*: No solution found. Expansions: " + expansions);
        return null;
    }
}
