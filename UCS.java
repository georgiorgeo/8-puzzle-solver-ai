import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

// ulopoiei ton algorithmo Uniform Cost Search
// xrhsimopoiei priority queue me proteraiothta to kostos diadromhs (g)
public class UCS {

    public static Node search(State start) {
        // priority queue taksinomhmenh kata auksousa timh g
        // hash (closed) set gia na mh ksanapiskefthoume thn idia katastash
        PriorityQueue<Node> nodes = new PriorityQueue<>((a, b) -> Double.compare(a.g, b.g));
        HashSet<State> used = new HashSet<>();

        Node node = new Node(start, null, null, 0, 0);
        nodes.add(node);

        int expansions = 0;

        while (nodes.isEmpty() == false) {
            Node cur = nodes.poll();

            // an h katastash exei epektathei
            // agnooume
            if (used.contains(cur.state)) { 
                continue;                   
            }

            used.add(cur.state);

            // an ftasoume ston stoxo (tk), epistrefoume ton kombo
            if (cur.state.goal()) {
                System.out.println("epektaseis :" + expansions);
                return cur;
            }

            expansions++;

            List<Node> moves = Moves.getMoves(cur);

            // paragwgh kai prosthiki twn paidiwn-kombwn sto metwpo me upologismo kostous kinhshs
            for (Node move : moves) {
                if (used.contains(move.state) == false ) {
                    nodes.add(move);
                }
            }
        }

        System.out.println("epektaseis :" + expansions);
        return null;
    }
}