import java.util.ArrayList;

// paragei oles tis egkures kinhseis apo enan kombo
public class Moves {

    // epistrefei lista me ola ta paidia tou trexontos kombou
    public static ArrayList<Node> getMoves(Node node) {
        ArrayList<Node> move = new ArrayList<>();

        State state = node.state;

        // briksei thn thesh tou kenou kai upologizei th grammh kai th sthlh tou
        int blank = state.findBlank();

        int row = blank / 3;
        int col = blank % 3;

        // kanonikes kinhseis (panw, katw, deksia, aristera) me kostos 1.0
        if (row > 0) {
            move.add(makeNode(node, blank, blank - 3, "UP", 1.0));
        }
        if (row < 2) {
            move.add(makeNode(node, blank, blank + 3, "DOWN", 1.0));
        }
        if (col > 0) {
            move.add(makeNode(node, blank, blank - 1, "LEFT", 1.0));
        }
        if (col < 2) {
            move.add(makeNode(node, blank, blank + 1, "RIGHT", 1.0));
        }

        // kinhseis wrap grammhs: to keno sthn aristeroterh sthlh mpainei sthn deksioterh sthlh
        // sthn idia grammh kai antistrofa me kostos 1.0
        if (col == 0) {
            move.add(makeNode(node, blank, row * 3 + 2, "WRAP_LEFT", 1.0));
        }
        if (col == 2) {
            move.add(makeNode(node, blank, row * 3, "WRAP_RIGHT", 1.0));
        }
        
        // kinhseis wrap sthlhs: analogws me to wrap grammhs alla gia tis pio panw kai katw grammes me kostos 1.0
        if (row == 0) {
            move.add(makeNode(node, blank, 6 + col, "WRAP_UP", 1.0));
        }
        if (row == 2) {
            move.add(makeNode(node, blank, col, "WRAP_DOWN", 1.0));
        }

        // teleport metaksu thesewn 2 kai 6 me kostos 0.5
        if (blank == 6) {
            move.add(makeNode(node, blank, 2, "TELEPORT", 0.5));
        }
        if (blank == 2) {
            move.add(makeNode(node, blank, 6, "TELEPORT", 0.5));
        }
        return move;
    }
    
    // dhmiourgei enan neo kombo emfarmozontas th kinhsh (antallagh oustiastika kenou me plakidio)
    private static Node makeNode(Node prevNode, int blank, int tile, String moves, double cost) {
        int[] newBoard = new int[9];

        for (int i = 0; i < 9; i++) {
            newBoard[i] = prevNode.state.board[i];
        }
        newBoard[blank] = newBoard[tile];
        newBoard[tile] = 0;
        State newState = new State(newBoard);
        return new Node(newState, prevNode, moves, prevNode.g + cost, 0);
    }
}
