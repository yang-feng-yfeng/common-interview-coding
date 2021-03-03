/**
 *  Copyright Murex S.A.S., 2003-2021. All Rights Reserved.
 *
 *  This software program is proprietary and confidential to Murex S.A.S and its affiliates ("Murex") and, without limiting the generality of the foregoing reservation of rights, shall not be accessed, used, reproduced or distributed without the
 *  express prior written consent of Murex and subject to the applicable Murex licensing terms. Any modification or removal of this copyright notice is expressly prohibited.
 */
package code.hc.prac;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class OutputIO {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void write(String fileName, List<int[]> solution) throws IOException {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(Integer.toString(solution.size()));
            writer.newLine();
            for (int[] pizzas : solution) {
                writer.write(pizzas.length + " ");
                for (int pizza : pizzas) {
                    writer.write(pizza + " ");
                }
                writer.newLine();
            }
        }
    }

    public static List<int[]> read(String fileName) throws FileNotFoundException {
        List<int[]> solution = new ArrayList<>();
        Scanner scanner = new Scanner(new File(fileName));
        int nbTeams = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < nbTeams; i++) {
            String nextLine = scanner.nextLine();
            String[] splited = nextLine.split(" ");

            int nbPizza = Integer.parseInt(splited[0]);
            int[] pizzas = new int[nbPizza];

            for (int j = 1; j < splited.length; j++) {
                pizzas[j - 1] = (Integer.parseInt(splited[j]));
            }
            solution.add(pizzas);
        }
        return solution;
    }
}
