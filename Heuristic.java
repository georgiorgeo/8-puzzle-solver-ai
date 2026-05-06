public class Heuristic {

    // sxediasame mia apodekth euretikh sunarthsh pou upologizei tis apostaseis twn plakidiwn apo ton stoxo
    // kai lambanei up'opshn tis eidikes kinhseis teleport apo 2 se 6 kai antistrofa
    public static double compute(State state) {
        double h = 0;

        for (int pos = 0; pos < 9; pos++) {
            int tile = state.board[pos];

            if (tile != 0) {
                int goalPos = findGoal(tile);
                h = h + distance(pos, goalPos);
            }
        }

        return h;
    }

    // epistrefei to index ths theshs tou plakidiou-orismatos ston pinaka telikhs katastashs (stoxou)
    private static int findGoal(int tile) {
        for (int i = 0; i < 9; i++) {
            if (State.goal[i] == tile) {
                return i;
            }
        }

        return -1;
    }

    // epistrefei to elaxisto kostos kinhsewn pou apaiteitai gia na metakinithei ena plakidio
    // apo th thesh a sth thesh b, lambanontas upopshn treis diadromes: apeutheias (me kinhseis up/down/left/right/wrap),
    // me tis klassikes kinhseis + teleport apo 2 se 6 kai me klassikes kinhseis + teleport apo 6 se 2
    private static double distance(int a, int b) {
        double direct = manhattan(a, b);

        double tpDown = manhattan(a, 2) + 0.5 + manhattan(6, b);
        double tpUp = manhattan(a, 6) + 0.5 + manhattan(2, b);

        if (tpDown < direct) {
            direct = tpDown;
        }

        if (tpUp < direct) {
            direct = tpUp;
        }

        return direct;
    }

    // tropopoihmenh apostash manhattan gia na lambanei upopshn tis wrap kinhseis
    // allazei thn apostash gia tis wrap kinhseis apo 2 se 1 giati metakineitai sthn ousia duo theseis (an htan me kanonikes kinhseis)
    // alla kostizei 1.0 san tis kanonikes kinhseis
    private static double manhattan(int a, int b) {
        int curRow = a / 3;
        int curCol = a % 3;

        int goalRow = b / 3;
        int goalCol = b % 3;

        int rowDiff = curRow - goalRow;
        int colDiff = curCol - goalCol;

        if (rowDiff < 0) {
            rowDiff = -rowDiff;
        }
        if (colDiff < 0) {
            colDiff = -colDiff;
        }
        if (rowDiff == 2) {
            rowDiff = 1;
        }
        if (colDiff == 2) {
            colDiff = 1;
        }
        return rowDiff + colDiff;
    }
}
