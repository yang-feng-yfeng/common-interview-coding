package code.hc.contest;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class InputReader {
    public static City readFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        int duration = scanner.nextInt();
        int nbInter = scanner.nextInt();
        int nbStreets = scanner.nextInt();
        int nbVehicule = scanner.nextInt();
        int bonus = scanner.nextInt();

        String nextLine = scanner.nextLine();
        Map<String, Street> ls = new HashMap<>();
        List<Vehicule> lv = new ArrayList<>();

        for (int i = 0; i < nbStreets; i++) {
            String line = scanner.nextLine();
            String[] splits = line.split(" ");
            Street s = new Street();
            s.from = Integer.parseInt(splits[0]);
            s.to = Integer.parseInt(splits[1]);
            s.name = splits[2];
            s.duration = Integer.parseInt(splits[3]);
            ls.put(s.name, s);
        }

        for (int i = 0; i < nbVehicule; i++) {
            String line = scanner.nextLine();
            String[] splits = line.split(" ");
            Vehicule v = new Vehicule();
            v.id = i;
            for (int j = 0; j < Integer.parseInt(splits[0]); j++) {
                v.path.add(ls.get(splits[j + 1]));
            }
            lv.add(v);
        }
        City city = new City();
        city.streets = new ArrayList<>(ls.values());
        city.vehicules = lv;
        city.duration = duration;
        city.bonus = bonus;
        city.nbInter = nbInter;

        return city;
    }
}
