import java.util.List;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar {

    // xrhsimopoiei ton algorithmo A* (A-star)
    // xrhsimopoiei priority queue me proteraiothta th timh f = g + h
    public static Node search(State start) {
        // priority queue taksinomhmenh kata auksousa timh f = g + h
        // hash (closed) set gia na mhn episkefthoume ksana thn idia katastash
        PriorityQueue<Node> nodes = new PriorityQueue<>((a, b) -> Double.compare(a.f(), b.f()));
        HashSet<State> used = new HashSet<>();

        double h = Heuristic.compute(start);

        Node node = new Node(start, null, null, 0, h);
        nodes.add(node);

        int expansions = 0;

        while (nodes.isEmpty() == false) {
            Node current = nodes.poll();

            // an h katastash exei hdh epektathei, thn agnooume
            if (used.contains(current.state)) {
                continue;
            }
            used.add(current.state);

            // an ftasoume ston stoxo (tk), epistrefoume ton kombo
            if (current.state.goal()) {
                System.out.println("epektaseis :" + expansions);
                return current;
            }
            expansions++;

            List<Node> moves = Moves.getMoves(current);

            // paragwgh kai prosthiki twn paidiwn-kombwn sto metwpo me upologismo kostous kinhshs
            for (Node move : moves) {
                if (used.contains(move.state) == false) {
                    move.h = Heuristic.compute(move.state);
                    nodes.add(move);

                }
            }
        }
        System.out.println("epektaseis :" + expansions);
        return null;
    }
}
