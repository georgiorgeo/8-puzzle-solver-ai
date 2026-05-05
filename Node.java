// h klash pou dhmiourgei to antikeimeno tupou kombou 
public class Node {
    State state;    // h katastash tou kombou
    Node prevNode;  // o kombos proeleushs (goneas)
    String move;    // h kinhsh apo thn opoia proekupse autos o kombos 
    double g;       // to pragmatiko kostos apo thn AK sth twrinh katastash-kombo
    double h;       // h ektimhsh ths euretikhs sunarthshs gia to upoloipomeno kostos (0 gia UCS, upologizetai apo thn euretikh gia A*)
    
    public Node(State state, Node prevNode, String move, double g, double h) {
        this.state = state;
        this.prevNode = prevNode;
        this.move = move;
        this.g = g;
        this.h = h;
    }
    
    // epistrefei to sunoliko ektimoumeno kostos f = g + h to opoio xrhsimopoiei h A* 
    // prokeimenou na organwsei th seira proteraiothtas (priority queue)
    public double f() {
        return g + h;
    }
}
