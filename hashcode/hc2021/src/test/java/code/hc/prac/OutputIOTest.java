package code.hc.prac;

import junit.framework.TestCase;

import java.util.List;

public class OutputIOTest extends TestCase {

    public void testWrite() {
    }

    public void testRead() throws Exception{
        Pizzaria pizzaria = InputReader.readFile("src/test/resources/input/c_many_ingredients.in");
        List<int[]> read = OutputIO.read("src/test/resources/output/c_many_ingredients.inRandom_out");
        int value = pizzaria.scoreAll(read);
        System.out.println(value);
    }

    public void testReadA() throws Exception {
        Pizzaria pizzaria = InputReader.readFile("src/test/resources/input/a_example");
        List<int[]> ints = pizzaria.generateSolution();
        int value = pizzaria.scoreAll(ints);

        OutputIO.write("src/test/resources/output/a_example.out", ints);
        System.out.println(value);
    }

    public void testReadB() throws Exception {
        Pizzaria pizzaria = InputReader.readFile("src/test/resources/input/b_little_bit_of_everything.in");
        List<int[]> ints = pizzaria.generateSolution();
        int value = pizzaria.scoreAll(ints);

        OutputIO.write("src/test/resources/output/b_little_bit_of_everything.out", ints);
        System.out.println(value);
    }

    public void testReadC() throws Exception {
        Pizzaria pizzaria = InputReader.readFile("src/test/resources/input/c_many_ingredients.in");
        List<int[]> ints = pizzaria.generateSolution();
        int value = pizzaria.scoreAll(ints);

        OutputIO.write("src/test/resources/output/c_many_ingredients.out", ints);
        System.out.println(value);
    }

    public void testReadD() throws Exception {
        Pizzaria pizzaria = InputReader.readFile("src/test/resources/input/d_many_pizzas.in");
        List<int[]> ints = pizzaria.generateSolution();
        int value = pizzaria.scoreAll(ints);

        OutputIO.write("src/test/resources/output/d_many_pizzas.out", ints);
        System.out.println(value);
    }

    public void testReadE() throws Exception {
        Pizzaria pizzaria = InputReader.readFile("src/test/resources/input/e_many_teams.in");
        List<int[]> ints = pizzaria.generateSolution();
        int value = pizzaria.scoreAll(ints);

        OutputIO.write("src/test/resources/output/e_many_teams.out", ints);
        System.out.println(value);
    }
}