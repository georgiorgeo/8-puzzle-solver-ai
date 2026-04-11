//
//  Heuristic.java
//  AIproject1
//
//  Created by Γιωργος Γεωργιαδης on 9/4/26.
//

public class Heuristic {
    
    
    // This program uses the Manhattan distance heuristic:
    
    // Goal position of each tile (index = tile value)
    // goal board: {1,2,3,6,5,4,7,0,8}
    // tile 1 is at index 0 -> row=0,col=0
    // tile 2 is at index 1 -> row=0,col=1
    // tile 3 is at index 2 -> row=0,col=2
    // tile 6 is at index 3 -> row=1,col=0
    // tile 5 is at index 4 -> row=1,col=1
    // tile 4 is at index 5 -> row=1,col=2
    // tile 7 is at index 6 -> row=2,col=0
    // tile 8 is at index 8 -> row=2,col=2
    
    private static int[] goalRow = {0, 0, 0, 0, 1, 1, 1, 2, 2, 2};
    private static int[] goalCol = {0, 0, 1, 2, 2, 1, 0, 0, 2, 0};
    
    public static double compute(State state) {
        double h = 0;
        for (int i = 0; i < 9; i++){
            int tile = state.board[i];
            if (tile == 0) continue; //skip blank
            int curRow = i / 3;
            int curCol = i % 3;
            h += Math.abs(curRow - goalRow[tile]) + Math.abs(curCol - goalCol[tile]);
        }
        return h;
    }
}
