package code.hc.contest;

import code.hc.contest.InputReader;
import org.junit.Test;

public class InputReaderTest {

    @Test
    public void testInput() throws Exception {
        City city = InputReader.readFile("src/test/resources/code.hc.con.input/a.txt");
        city.generateSolution2();
        OutputWriter.writeFile("src/test/resources/code.hc.con.output/a.output", city.solution);
    }

    @Test
    public void testInput1() throws Exception {
        City city = InputReader.readFile("src/test/resources/code.hc.con.input/b.txt");
        city.generateSolution2();
        OutputWriter.writeFile("src/test/resources/code.hc.con.output/b.output", city.solution);
    }

    @Test
    public void testInput2() throws Exception {
        City city = InputReader.readFile("src/test/resources/code.hc.con.input/c.txt");
        city.generateSolution2();
        OutputWriter.writeFile("src/test/resources/code.hc.con.output/c.output", city.solution);
    }

    @Test
    public void testInput3() throws Exception {
        City city = InputReader.readFile("src/test/resources/code.hc.con.input/d.txt");
        city.generateSolution2();
        OutputWriter.writeFile("src/test/resources/code.hc.con.output/d.output", city.solution);
    }

//    @Test
//    public void testInput4() throws Exception {
//        City city = InputReader.readFile("src/test/resources/code.hc.con.input/e.txt");
//        city.generateSolution1();
//        OutputWriter.writeFile("src/test/resources/code.hc.con.output/e.output", city.solution);
//    }

    @Test
    public void testInput5() throws Exception {
        City city = InputReader.readFile("src/test/resources/code.hc.con.input/f.txt");
        city.generateSolution2();
        OutputWriter.writeFile("src/test/resources/code.hc.con.output/f.output", city.solution);
    }


    @Test
    public void testInputtt() throws  Exception {
        System.out.println(" -4 / 2 = " + (-4 / 2 ));
        System.out.println(" -5 / 2 = " + (-5 / 2 ));
        System.out.println(" -3 / 2 = " + (-3 / 2 ));

        System.out.println(" -100 & 100 = " + (-100 & 100 ));
        System.out.println(" -1 & 1 = " + (-1 & 1 ));
    }
//    @Test
//    public void testInputEtoile() throws Exception {
//        City city = InputReader.readFile("src/test/resources/code.hc.con.input/e.txt");
//        city.generateSolution3();
//        OutputWriter.writeFile("src/test/resources/code.hc.con.output/e.output", city.solution);
//    }

}