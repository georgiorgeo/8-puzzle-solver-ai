//
//  Node.java
//  AIproject1
//
//  Created by Γιωργος Γεωργιαδης on 9/4/26.
//

public class Node {
    State state;
    Node parent;
    Move move;
    double g;
    double h;
    
    public Node(State state, Node parent, Move move, double g, double h) {
        this.state = state;
        this.parent = parent;
        this.move = move;
        this.g = g;
        this.h = h;
    }
    
    public double f() {
        return g + h;
    }
}
