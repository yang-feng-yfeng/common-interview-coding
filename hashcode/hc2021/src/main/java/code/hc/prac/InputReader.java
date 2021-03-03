/**
 *  Copyright Murex S.A.S., 2003-2019. All Rights Reserved.
 *
 *  This software program is proprietary and confidential to Murex S.A.S and its affiliates ("Murex") and, without limiting the generality of the foregoing reservation of rights, shall not be accessed, used, reproduced or distributed without the
 *  express prior written consent of Murex and subject to the applicable Murex licensing terms. Any modification or removal of this copyright notice is expressly prohibited.
 */
package code.hc.prac;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class InputReader {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods
    //~ ----------------------------------------------------------------------------------------------------------------

    public static Pizzaria readFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        int nbPizzas = scanner.nextInt();
        int nb2PerTeams = scanner.nextInt();
        int nb3PerTeams = scanner.nextInt();
        int nb4PerTeams = scanner.nextInt();
        String nextLine = scanner.nextLine();

        Map<String, Integer> ingredientsMap = new HashMap<>();
        int currentIngredient = 0;
        // Onion pepper olive 0, 1, 2
        // pizza1 [1, 1, 1, 0, 0, 0, 0...]
        // 4 teams , 4 pizza, ingredients ||
        Map<Integer, Pizza> allPizza = new HashMap<>();

        for (int i = 0; i < nbPizzas; i++) {
            nextLine = scanner.nextLine();
            boolean[] ingredients = new boolean[10000];
            String[] splited = nextLine.split(" ");

            int nbIngredients = Integer.parseInt(splited[0]);
            for (int i1 = 1; i1 < nbIngredients + 1; i1++) {
                String ingre = splited[i1];
                Integer index = ingredientsMap.get(ingre);
                if (index == null) {
                    index = currentIngredient++;
                    ingredientsMap.put(ingre, index);
                }
                ingredients[index] = true;
            }
            Pizza pizza = new Pizza(i, ingredients);
            allPizza.put(i, pizza);
        }
        return new Pizzaria(allPizza, nb2PerTeams, nb3PerTeams, nb4PerTeams, ingredientsMap.size());
    }

}
