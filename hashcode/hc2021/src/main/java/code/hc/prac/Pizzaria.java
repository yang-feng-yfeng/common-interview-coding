package code.hc.prac;

import java.util.*;

public class Pizzaria {

    public final Map<Integer, Pizza> allPizza;
    public final int team2Nb;
    public final int team3Nb;
    public final int team4Nb;
    public final int nbIngredients;

    public Pizzaria(Map<Integer, Pizza> allPizza, int team2Nb, int team3Nb, int team4Nb, int nbIngredients) {
        this.allPizza = allPizza;
        this.team2Nb = team2Nb;
        this.team3Nb = team3Nb;
        this.team4Nb = team4Nb;
        this.nbIngredients = nbIngredients;
    }

    public List<int[]> generateSolution() {

        List<int[]> solution = new ArrayList<>();

        // Start with 4 people team
        // Pair pizzas
        List<Integer> usedPizza = new ArrayList<>();
        List<PizzaMerged> pizzaMergeds = new ArrayList<>();
        int mergedIndex = allPizza.size();
        for (int i = 0; i < allPizza.size(); i++) {
            Pizza pizza1 = allPizza.get(i);
            for (int j = i+1; j < allPizza.size(); j++) {
                Pizza pizza2 = allPizza.get(j);

                PizzaMerged pizzaMerged = new PizzaMerged(
                        mergedIndex++,
                        ingres(pizza1, pizza2, nbIngredients),
                        new int[]{i, j},
                        diffScore(pizza1, pizza2, nbIngredients));
                pizzaMergeds.add(pizzaMerged);
            }
        }

        // Acsding order
        PriorityQueue<PizzaMerged> pizzaMergeds4 = new PriorityQueue<>(team4Nb, Comparator.comparingInt(PizzaMerged::getScore));

        for (int i = 0; i < pizzaMergeds.size(); i++) {
            for (int i1 = i; i1 < pizzaMergeds.size(); i1++) {
                PizzaMerged pizzaMerged1 = pizzaMergeds.get(i);
                PizzaMerged pizzaMerged2 = pizzaMergeds.get(i1);

                if (!pizzaMerged1.conflit(pizzaMerged2)) {

                    PizzaMerged pizzaMerged44 = new PizzaMerged(mergedIndex++,
                            ingres(pizzaMerged1, pizzaMerged2, nbIngredients),
                            new int[]{pizzaMerged1.pizzaIds[0], pizzaMerged1.pizzaIds[1],
                                    pizzaMerged2.pizzaIds[0], pizzaMerged2.pizzaIds[1]},
                            diffScore(pizzaMerged1, pizzaMerged2, nbIngredients));
                    addToQueue(pizzaMergeds4, team4Nb, pizzaMerged44);
                }
            }
        }

        for (PizzaMerged pizzaMerged : pizzaMergeds4) {
            solution.add(pizzaMerged.pizzaIds);
            for (int pizzaId : pizzaMerged.pizzaIds) {
                usedPizza.add(pizzaId);
            }

        }

        // For 3 teams
        List<PizzaMerged> pizzaMergeds3 = new ArrayList<>();
        for (int i = 0; i < allPizza.size(); i++) {
            if (!usedPizza.contains(i)) {
                Pizza pizza1 = allPizza.get(i);
                for (int j = i+1; j < allPizza.size(); j++) {
                    if (!usedPizza.contains(j)) {
                        Pizza pizza2 = allPizza.get(j);

                        PizzaMerged pizzaMerged = new PizzaMerged(mergedIndex++,
                                ingres(pizza1, pizza2, nbIngredients),
                                new int[]{i, j},
                                diffScore(pizza1, pizza2, nbIngredients));
                        pizzaMergeds3.add(pizzaMerged);
                    }
                }
            }
        }
        PriorityQueue<PizzaMerged> pizzaMergeds3Queue = new PriorityQueue<>(team3Nb, Comparator.comparingInt(PizzaMerged::getScore));

        for (int i = 0; i < pizzaMergeds3.size(); i++) {
            for (int i1 = i; i1 < allPizza.size(); i1++) {
                PizzaMerged pizzaMerged1 = pizzaMergeds3.get(i);
                if (!usedPizza.contains(i1)) {
                    Pizza pizzaMerged2 = allPizza.get(i1);
                    if (!pizzaMerged1.conflit(pizzaMerged2)) {

                        PizzaMerged pizzaMerged33 = new PizzaMerged(mergedIndex++, ingres(pizzaMerged1, pizzaMerged2, nbIngredients),
                                new int[]{pizzaMerged1.pizzaIds[0], pizzaMerged1.pizzaIds[1],
                                        pizzaMerged2.id},
                                diffScore(pizzaMerged1, pizzaMerged2, nbIngredients));
                        addToQueue(pizzaMergeds3Queue, team3Nb, pizzaMerged33);
                    }

                }

            }
        }

        for (PizzaMerged pizzaMerged : pizzaMergeds3Queue) {
            solution.add(pizzaMerged.pizzaIds);
            for (int pizzaId : pizzaMerged.pizzaIds) {
                usedPizza.add(pizzaId);
            }
        }

        // For 2 teams
        List<PizzaMerged> pizzaMergeds22 = new ArrayList<>();
        for (int i = 0; i < allPizza.size(); i++) {
            if (!usedPizza.contains(i)) {
                Pizza pizza1 = allPizza.get(i);
                for (int j = i+1; j < allPizza.size(); j++) {
                    if (!usedPizza.contains(j)) {
                        Pizza pizza2 = allPizza.get(j);

                        PizzaMerged pizzaMerged = new PizzaMerged(mergedIndex++,
                                ingres(pizza1, pizza2, nbIngredients),
                                new int[]{i, j},
                                diffScore(pizza1, pizza2, nbIngredients));
                        pizzaMergeds22.add(pizzaMerged);
                    }
                }
            }
        }
        PriorityQueue<PizzaMerged> pizzaMergeds2Queue = new PriorityQueue<>(team2Nb, Comparator.comparingInt(PizzaMerged::getScore));
        for (PizzaMerged pizzaMerged : pizzaMergeds22) {
            addToQueue(pizzaMergeds2Queue, team2Nb, pizzaMerged);
        }

        for (PizzaMerged pizzaMerged : pizzaMergeds2Queue) {
            solution.add(pizzaMerged.pizzaIds);
        }

        return solution;
    }


    public static void addToQueue(PriorityQueue<PizzaMerged> q, int max, PizzaMerged pizzaMerged ) {
        if (q.size() < max) {
            q.add(pizzaMerged);
        } else {
            if (q.peek().getScore() > pizzaMerged.getScore()) {
                q.poll();
                q.add(pizzaMerged);
            }
        }
    }

    public int scoreAll(List<int[]> solution) {
        int score = 0;
        for (int[] team : solution) {

            if (team.length == 2) {
                int score2 = score2(allPizza.get(team[0]), allPizza.get(team[1]), nbIngredients);
                score += score2 * score2;
            }
            else if (team.length == 3) {
                int score3 = score3(allPizza.get(team[0]), allPizza.get(team[1]),
                        allPizza.get(team[2]), nbIngredients);
                score += score3* score3;
            }
            else if (team.length == 4) {
                int score4 = score4(allPizza.get(team[0]), allPizza.get(team[1]),
                        allPizza.get(team[2]), allPizza.get(team[3]),
                        nbIngredients);
                score += score4 * score4;
            }
        }
        return score;
    }

    public static boolean[] ingres(Pizza a, Pizza b, int nbIngredients) {
        boolean[] ingres = new boolean[a.ingres.length];
        for (int i = 0; i < nbIngredients; i++) {
            if (a.ingres[i] || b.ingres[i] )
                ingres[i] = true;
        }
        return ingres;
    }

    public static int diffScore(Pizza a, Pizza b,  int nbIngredients) {
        int diff = 0;
        for (int i = 0; i < nbIngredients; i++) {
            if (a.ingres[i] && b.ingres[i])
                diff++;
        }
        return diff;
    }

    public static int score2(Pizza a, Pizza b, int nbIngredients) {
        int score = 0;
        for (int i = 0; i < nbIngredients; i++) {
            if (a.ingres[i]  || b.ingres[i] )
                score++;
        }
        return score;
    }
    public static int score3(Pizza a, Pizza b, Pizza c, int nbIngredients) {
        int score = 0;
        for (int i = 0; i < nbIngredients; i++) {
            if (a.ingres[i]  || b.ingres[i]  || c.ingres[i] )
                score++;
        }
        return score;
    }

    public static int score4(Pizza a, Pizza b, Pizza c, Pizza d, int nbIngredients) {
        int score = 0;
        for (int i = 0; i < nbIngredients; i++) {
            if (a.ingres[i]  || b.ingres[i]  || c.ingres[i]  || d.ingres[i] )
                score++;
        }
        return score;
    }

}
