package enumerated;

import static enumerated.Outcome.*;

public enum RoShamBo6 implements Competitor<RoShamBo6> {
    PAPER, SCISSORS, ROCK;

    private static Outcome[][] table = {
            {DRAW, LOSE, WIN},  // PAPER
            {WIN, DRAW, LOSE},  // SCISSORS
            {LOSE, WIN, DRAW}   // ROCK
    };

    @Override
    public Outcome compete(RoShamBo6 it) {
        return table[this.ordinal()][it.ordinal()];
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo6.class, 20);
    }
}
