// ypologizei thn euretikh sunarthsh h(n) gia mia katastash
// h euretikh einai to athroisma twn mikroterwn diadromwn kathe plakidiou gia th thesh-stoxo tou
public class Heuristic {

        // epistrefei to index ths theshs-stoxou
        private static int findGoal(int tile) {
        for (int i = 0; i < 9; i++) {
            if (State.goal[i] == tile) {
                return i;
            }
        }
        return -1;
    }

    // upologizei to sunoliko kostos pou prokuptei apo thn euretikh sunarthsh
    public static double compute(State state) {
        double h = 0;

        for (int pos = 0; pos < 9; pos++) {
            int tile = state.board[pos];

            if (tile != 0) { //agnoei to keno
                int goal = findGoal(tile);
                h = h + shortestDistance(pos, goal);
            }
        }
        return h;
    }

    // briskei th mikroterh diadromh metaksu duo thesewn tou board
    // xrhsimopoiei aploikh ulopoihsh algorithmou dijkstra panw ston grafo twn dunatwn kinhsewn
    private static double shortestDistance(int start, int finish) {
        double[] distance = new double[9];
        boolean[] used = new boolean[9];

        for (int i = 0; i < 9; i++) {
            distance[i] = -1;
        }
        distance[start] = 0;

        for (int count = 0; count < 9; count++) {
            int current = -1;

            for (int i = 0; i < 9; i++) {
                if (used[i] == false && distance[i] != -1) {
                    if (current == -1 || distance[i] < distance[current]) {
                        current = i;
                    }
                }
            }
            if (current == -1) {
                break;
            }
            if (current == finish) {
                return distance[current];
            }
            used[current] = true;

            for (int pos = 0; pos < 9; pos++) {
                double cost = moveCost(current, pos);

                if (cost != -1) {
                    double newDistance = distance[current] + cost;

                    if (newDistance < distance[pos] || distance[pos] == -1) {
                        distance[pos] = newDistance;
                    }
                }
            }
        }
        return distance[finish];
    }

    // epistrefei to kostos ths kinhshs apo thesh a se thesh b
    // an h kinhsh den einai egkurh, epistrefei -1 (sfalma)
    private static double moveCost(int a, int b) {
        int curRow = a / 3;
        int curCol = a % 3;
        int newRow = b / 3;
        int newCol = b % 3;

        if (curRow == newRow && Math.abs(curCol - newCol) == 1) {
            return 1.0;
        }
        if (curCol == newCol && Math.abs(curRow - newRow) == 1) {
            return 1.0;
        }
        if (curRow == newRow && Math.abs(curCol - newCol) == 2) {
            return 1.0;
        }
        if (curCol == newCol && Math.abs(curRow - newRow) == 2) {
            return 1.0;
        }
        if ((a == 2 && b == 6) || (a == 6 && b == 2)) {
            return 0.5;
        }
        return -1;
    }
}