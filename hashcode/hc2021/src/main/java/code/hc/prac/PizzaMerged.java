package code.hc.prac;

import java.util.Arrays;

public class PizzaMerged extends Pizza{

    public final int[] pizzaIds;
    public final int diff;


    public PizzaMerged(int id, boolean[] ingre, int[] pizzaIds, int diff) {
        super(id, ingre);
        this.pizzaIds = pizzaIds;
        this.diff = diff;
    }

    @Override
    public int getScore() {
        if (score == -1) {
            score = super.count() - diff;
        }
        return score;
    }

    public boolean conflit(Pizza p) {
        for (int pizzaId : pizzaIds) {
                if (pizzaId == p.id) {
                    return true;
                }
        }
        return false;
    }




    public boolean conflit(PizzaMerged p) {
        for (int pizzaId : pizzaIds) {
            for (int i : p.pizzaIds) {
                if (pizzaId ==i) {
                    return true;
                }
            }
        }
        return false;
    }


}
