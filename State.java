import java.util.Arrays;

public class State {
    int[] board;
    
    public State(int[] board) {
        this.board = Arrays.copyOf(board, 9);
    }
    
    public int blankIndex() {
        for (int i = 0; i < 9; i++)
            if (board[i] == 0) return i;
        return -1;
    }
    
    public boolean isGoal() {
        int[] goal = {1, 2, 3, 6, 5, 4, 7, 0, 8};
        return Arrays.equals(board, goal);
    }
    
    public boolean equals(Object o) {
        if (!(o instanceof State)) return false;
        return Arrays.equals(board, ((State) o).board);
    }
    
    public int hashCode() {
        return Arrays.hashCode(board);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            sb.append(board[i] == 0 ? " " : board[i]);
            sb.append(i % 3 == 2 ? "\n" : " ");
        }
        return sb.toString();
    }
}



