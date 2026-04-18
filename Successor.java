//
//  Untitled.swift
//  AIproject1
//
//  Created by Γιωργος Γεωργιαδης on 9/4/26.
//

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Successor {
    
    public static List<Node> getSuccessors(Node node) {
        List<Node> successors = new ArrayList<>();
        State state = node.state;
        int blank = state.blankIndex();
        int row = blank / 3;
        int col = blank % 3;
        
        //UP, DOWN, LEFT, RIGHT
        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};
        String[] names = {"UP", "DOWN", "LEFT", "RIGHT"};
        
        for (int i = 0; i < 4; i++) {
            int newRow = row + dRow[i];
            int newCol = col + dCol[i];
            if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                successors.add(makeNode(node, blank, newRow * 3 + newCol, names[i], 1.0));
            }
        }
        
        //background kinhseis sthn idia grammh
        if (col == 0 && state.board[row * 3 + 2] != 0)
            successors.add(makeNode(node, blank, row * 3 + 2, "WRAP_LEFT", 1.0));
        if (col == 2 && state.board[row * 3 + 0] != 0)
            successors.add(makeNode(node, blank, row * 3 + 0, "WRAP_RIGHT", 1.0));
        
        //background kinhseis sthn idia sthlh
        if (row == 0 && state.board[2 * 3 + col] != 0)
            successors.add(makeNode(node, blank, 2 * 3 + col, "WRAP_UP", 1.0));
        if (row == 2 && state.board[0 * 3 + col] != 0)
            successors.add(makeNode(node, blank, col, "WRAP_DOWN", 1.0));
        
        //Teleport: keno sth katw aristera thesh=index 6 <--> keno sth panw deksia thesh=index 2
        if (blank == 6)
            successors.add(makeNode(node, blank, 2, "TELEPORT", 0.5));
        if (blank == 2)
            successors.add(makeNode(node, blank, 6, "TELEPORT", 0.5));
        
        return successors;
    }
    
    private static Node makeNode(Node parent, int blankIdx, int tileIdx, String action, double cost) {
        int[] newBoard = Arrays.copyOf(parent.state.board, 9);
        newBoard[blankIdx] = newBoard[tileIdx];
        newBoard[tileIdx] = 0;
        State newState = new State(newBoard);
        Move move = new Move(action, cost);
        return new Node(newState, parent, move, parent.g + cost, 0);
    }
}
