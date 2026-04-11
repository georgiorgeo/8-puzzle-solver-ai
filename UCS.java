//
//  UCS.java
//  AIproject1
//
//  Created by Γιωργος Γεωργιαδης on 9/4/26.
//

import java.util.*;

public class UCS {
    
    public static Node search(State initial) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingDouble(n -> n.g));
        HashSet<State> visited = new HashSet<>();
        
        Node startNode = new Node(initial, null, null, 0, 0);
        frontier.add(startNode);
        
        int expansions = 0;
        
        while (!frontier.isEmpty()){
            Node current = frontier.poll();
            if (visited.contains(current.state)) continue;
            visited.add(current.state);
            
            if (current.state.isGoal()) {
                System.out.println("UCS epektaseis: " + expansions);
                return current;
            }
            
            expansions++;
            
            List<Node> successors = Successor.getSuccessors(current);
            for (Node child : successors) {
                if (!visited.contains(child.state)) {
                    frontier.add(child);
                }
            }
        }
        
        System.out.println("UCS: No solution found. Expansions: " + expansions);
        return null;
    }
}
