/**
 *  Copyright Murex S.A.S., 2003-2021. All Rights Reserved.
 * 
 *  This software program is proprietary and confidential to Murex S.A.S and its affiliates ("Murex") and, without limiting the generality of the foregoing reservation of rights, shall not be accessed, used, reproduced or distributed without the
 *  express prior written consent of Murex and subject to the applicable Murex licensing terms. Any modification or removal of this copyright notice is expressly prohibited.
 */
package code.hc.contest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;
import java.util.Map;


public class OutputWriter {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void writeFile(String fileName, Map<Intersection, List<Schedule>> solution) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            writer.write(Integer.toString(solution.size()));
            writer.newLine();
            for (Map.Entry<Intersection, List<Schedule>> entry : solution.entrySet()) {

                writer.write(Integer.toString(entry.getKey().id));
                writer.newLine();

                writer.write(Integer.toString(entry.getValue().size()));
                writer.newLine();

                for (Schedule schedule : entry.getValue()) {
                    writer.write(schedule.street.name);
                    writer.write(" ");
                    writer.write(Integer.toString(schedule.time));
                    writer.newLine();
                }
            }
        }

    }
}
