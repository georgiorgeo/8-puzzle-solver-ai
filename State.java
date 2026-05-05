import java.util.Arrays;

// antiprosopeuei enan pinaka puzzle 3x3 ws ena grammiko array 9 grammwn 
// me index 0..8 gia eukoloterh diaxeirhsh
public class State {
    int[] board;   // ta plakidia tou game, 0 shmainei keno

    // h telikh katastash pou theloume na ftasei
    public static final int[] goal = {1, 2, 3, 6, 5, 4, 7, 0, 8};
    
    public State(int[] board) {
        this.board = Arrays.copyOf(board, 9);
    }
    
    public boolean goal() {
        for (int i = 0; i < 9; i++) {
        if (board[i] != goal[i]) {
            return false;
        }
    }

    return true;
}
    
    // epistrefei to index ths theshs tou pinaka pou einai keno, h -1 an den brethei (sfalma)
    public int findBlank() {
        for (int i = 0; i < 9; i++) {
            if (board[i] == 0) { 
                return i;
            }
        }
        return -1;          
    }

    // kwdikopoiei ton pinaka ws enan akeraio 9 psifiwn gia eukoloterh diaxeirhsh (hashing)
    public int hashCode() {
        int hash = 0;

        for (int i = 0; i < 9; i++) {
            hash = hash * 10 + board[i];
        }

        return hash;
    }
    
    public boolean equals(Object obj) {
    State other = (State) obj;

    for (int i = 0; i < 9; i++) {
        if (board[i] != other.board[i]) {
            return false;
        }
    }
    return true;
}
    
    public String toString() {
    String s = "";

    for (int row = 0; row < 3; row++) {
        for (int col = 0; col < 3; col++) {
            int pos = row * 3 + col;

            s = s + board[pos] + " ";
        }

        s = s + "\n";
    }

    return s;
}
}