package code.hc.contest;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class City {

    public List<Street> streets = new ArrayList<>();
    public Intersection[] intersections;
    public List<Vehicule> vehicules = new ArrayList<>();

    public int duration;
    public int bonus;
    public int nbInter;

    public Map<Intersection, List<Schedule>> solution = new HashMap<>();

    public void cleanUp() {
        for (Vehicule v : vehicules) {
            for (Street s: v.path) {
                v.allCost += s.duration;
            }

            if (v.allCost > duration) {
                v.isActive = false;
            }
        }

        Set<Street> allUsed = new HashSet<>();

        for (Vehicule v : vehicules) {
            if (v.isActive) {
                allUsed.addAll(v.path);
            }
        }

        for (Street s : streets) {
            if (!allUsed.contains(s)) {
                s.isActive = false;
            }
        }
    }

    public void generateSolution3() {
        cleanUp();
        intersections = new Intersection[nbInter];
        for (int i = 0; i < nbInter; i++) {
            intersections[i] = new Intersection();
            intersections[i].id = i;
        }
        for (Street s : streets) {
            if (s.isActive) {
                intersections[s.to].inStreets.add(s);
            }
        }

        for (Intersection intersection: intersections) {
            List<Schedule> schedules = new ArrayList<>();
//            int index = 1;
            if (intersection.id != 499) {
                for (Street s : intersection.inStreets) {
                    Schedule schedule = new Schedule();
                    schedule.street = s;
                    schedule.time = 1;
                    schedules.add(schedule);
                }
                if (!schedules.isEmpty()) {
                    solution.put(intersection, schedules);
                }
            } else {
                Map<Street, List<Integer>> streetToStartTime = new HashMap<>();
                for (Vehicule v : vehicules) {
                    if (v.isActive) {


                        boolean passed = false;
                        Street passedStreet = null;
                        int value = 0;
                        for (Street s : v.path) {
                            if (s.to == 499) {
                                passedStreet = s;
                                passed = true;
                                break;
                            }
                            value += s.duration;
                        }
                        if (passed) {
                            List<Integer> times = streetToStartTime.getOrDefault(passedStreet, new ArrayList<>());
                            times.add(value);
                            streetToStartTime.put(passedStreet, times);
                        }
                    }
                }
                //System.out.println(streetToStartTime.size());
                //

                Map<Integer, Map<Street, Integer>> timeToStreetCount = new HashMap<>();
                Set<Integer> allTimes = new HashSet<>();

                for (Map.Entry<Street, List<Integer>> entry : streetToStartTime.entrySet()) {
                    Street s = entry.getKey();
                    List<Integer> times = entry.getValue();

                    for (Integer time : times) {
                        allTimes.add(time);
                        Map<Street, Integer> streetCount = timeToStreetCount.getOrDefault(time, new HashMap<>());
                        int count = streetCount.getOrDefault(s, 0);
                        streetCount.put(s, count+1);
                        timeToStreetCount.put(time, streetCount);
                    }
                }
                int actualTime = 0;
                Set<Street> used = new HashSet<>();
                for (int i = 0; i<= duration; i++) {
                    Map<Street, Integer> streetCount = timeToStreetCount.get(i);

                    if (streetCount != null ) {
                        Map<Street, Integer>  streetCountSorted = sortByValue(streetCount, false);
                        for (Map.Entry<Street, Integer> entry : streetCountSorted.entrySet()) {
                            Street s = entry.getKey();
//                            int count = entry.getValue();

                            if (s != null && !used.contains(s)) {
                                List<Integer> times = streetToStartTime.get(s);
                                int count = 0;
                                for (Integer time : times) {
                                    if (time <= actualTime) {
                                        count++;
                                    }
                                }
                                Schedule schedule = new Schedule();
                                schedule.street = s;
                                schedule.time = count;
                                actualTime += count;
                                schedules.add(schedule);
                                used.add(s);
                            }
                        }
                    }
                }
                if (!schedules.isEmpty()) {
                    solution.put(intersection, schedules);
                }
            }
        }
    }

    private static Map<Street, Integer> sortByValue(Map<Street, Integer> unsortMap, final boolean order)
    {
        List<Map.Entry<Street, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().name.compareTo(o2.getKey().name)
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().name.compareTo(o1.getKey().name)
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    }


    public void generateSolution2() {
        cleanUp();
        intersections = new Intersection[nbInter];
        for (int i = 0; i < nbInter; i++) {
            intersections[i] = new Intersection();
            intersections[i].id = i;
        }

        for (Street s : streets) {
            if (s.isActive) {
                intersections[s.to].inStreets.add(s);
            }
        }

        Map<Intersection, Map<Street, Integer>> intersectionFrequency = new HashMap<>();

        for (Vehicule v : vehicules) {
            if (v.isActive) {
                for (Street s : v.path) {
                    if (s.isActive) {
                        Intersection intersection = intersections[s.to];
                        Map<Street, Integer> frequencyMap = intersectionFrequency.getOrDefault(intersection, new HashMap<>());
                        Integer count = frequencyMap.getOrDefault(s, 0);
                        frequencyMap.put(s, count + 1);
                        intersectionFrequency.put(intersection, frequencyMap);
                    }
                }
            }
        }

        Map<Integer, Map<Street, Integer>> startingPointsMap = new HashMap<>();
        for (Vehicule vehicule : vehicules) {
            if (vehicule.isActive) {
                Street s1 = vehicule.path.get(0);
                int intersection = s1.to;
                Map<Street, Integer> streetIntegerMap = startingPointsMap.getOrDefault(intersection, new HashMap<>());
                int count = streetIntegerMap.getOrDefault(s1, 0);
                streetIntegerMap.put(s1, count + 1);
                startingPointsMap.put(intersection, streetIntegerMap);
            }
        }

        for (Intersection intersection : intersections) {
            List<Schedule> schedules = new ArrayList<>();
            Map<Street, Integer> streetIntegerMap = intersectionFrequency.get(intersection);

            Map<Street, Integer> streetIntegerMap2 = startingPointsMap.get(intersection.id);

            if (streetIntegerMap != null) {
                double total = 0.0;
                double demi_average = 0.0;
                double average = 0.0;
                for (Map.Entry<Street, Integer> entry : streetIntegerMap.entrySet()) {
                    total += entry.getValue();
                }
                if (streetIntegerMap.size() > 0) {
                    average = total / streetIntegerMap.size();
                    demi_average = average / 2;
                    if (demi_average != 0) {
                        Set<Street> toDoAfter = new HashSet<>();
                        for (Map.Entry<Street, Integer> entry : streetIntegerMap.entrySet()) {
                            if (streetIntegerMap2 == null || streetIntegerMap2.containsKey(entry.getKey())) {
                                Schedule schedule = new Schedule();
                                schedule.street = entry.getKey();
                                double value = (entry.getValue() / demi_average);
                                if (value < 1) {
                                    schedule.time = 1;
                                    schedules.add(schedule);
                                } else {
                                    schedule.time = (int) value;
                                    schedules.add(schedule);
                                }
                            } else {
                                toDoAfter.add(entry.getKey());
                            }
                        }
                        for (Street street : toDoAfter) {
                            Schedule schedule = new Schedule();
                            schedule.street = street;
                            double value = (streetIntegerMap.get(street) / demi_average);
                            if (value < 1) {
                                schedule.time = 1;
                                schedules.add(schedule);
                            } else {
                                schedule.time = (int) value;
                                schedules.add(schedule);
                            }
                        }

                    }


                }
            }
            if (!schedules.isEmpty()) {
                solution.put(intersection, schedules);
            }
        }

    }

    public void generateSolution1() {
        cleanUp();
        intersections = new Intersection[nbInter];
        for (int i = 0; i < nbInter; i++) {
            intersections[i] = new Intersection();
            intersections[i].id = i;
        }
        for (Street s : streets) {
            if (s.isActive) {
                intersections[s.to].inStreets.add(s);
            }
        }

        Map<Integer, Map<Street, Integer>> startingPointsMap = new HashMap<>();

        for (Vehicule vehicule : vehicules) {
            if (vehicule.isActive) {
                Street s1 = vehicule.path.get(0);
                int intersection = s1.to;
                Map<Street, Integer> streetIntegerMap = startingPointsMap.getOrDefault(intersection, new HashMap<>());
                int count = streetIntegerMap.getOrDefault(s1, 0);
                streetIntegerMap.put(s1, count + 1);
                startingPointsMap.put(intersection, streetIntegerMap);
            }
        }


        for (Intersection intersection: intersections) {
            List<Schedule> schedules = new ArrayList<>();
//            int index = 1;
            Map<Street, Integer> streetIntegerMap = startingPointsMap.get(intersection.id);
            Set<Street> used = new HashSet<>();
            if (streetIntegerMap != null) {
                Map<Street, Integer>  streetCountSorted = sortByValue(streetIntegerMap, false);
                for (Map.Entry<Street, Integer> entry : streetCountSorted.entrySet()) {
                    Street street = entry.getKey();
                    int count = entry.getValue();
                    Schedule schedule = new Schedule();
                    schedule.street = street;
                    schedule.time = 2;
                    schedules.add(schedule);
                    used.add(street);
                }
            }

            for (Street s : intersection.inStreets) {
                if (!used.contains(s)) {
                    Schedule schedule = new Schedule();
                    schedule.street = s;
                    schedule.time = 1;
                    schedules.add(schedule);
                }
            }
            if (!schedules.isEmpty()) {
                solution.put(intersection, schedules);
            }
        }

    }



}
