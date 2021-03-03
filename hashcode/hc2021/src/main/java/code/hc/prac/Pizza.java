package code.hc.prac;

import java.util.Arrays;

public class Pizza {

    public final int id;
    public final boolean[] ingres;
    public int score = -1;

    public Pizza(int id, boolean[] ingres) {
        this.id = id;
        this.ingres = ingres;
    }

    public int getScore() {
        if (score == -1) {
            score = count();
        }
        return score;
    }

    public int count() {
        int trueCount = 0;
        for (int i = 0; i < ingres.length; i++) {
            if (ingres[i] /* or array[i] */) {
                trueCount++;
            }
        }
        return trueCount;
    }

}
