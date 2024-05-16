package unibo.citysimulation.model.zone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;

import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.utilities.Pair;

public class ZoneTable {
    private static ZoneTable instance;
    private static Map<Pair<Zone, Zone>, TransportLine> zonePairs;

    private ZoneTable() {
        zonePairs = new HashMap<>();
    }

    public static ZoneTable getInstance() {
        if (instance == null) {
            instance = new ZoneTable();
        }
        return instance;
    }

    public void addPair(Zone zone1, Zone zone2, TransportLine transportLine) {
        zonePairs.put(new Pair<>(zone1, zone2), transportLine);
        zonePairs.put(new Pair<>(zone2, zone1), transportLine); // to ensure the table works both ways
    }

    public static TransportLine getTransportLine(Zone zone1, Zone zone2) {
        return zonePairs.get(new Pair<>(zone1, zone2));
    }

    public static int getTripDuration(TransportLine transportLines) {
        return transportLines.getDuration();
    }

    private static List<Path> findAllPaths(Zone start, Zone end) {
        List<Path> allPaths = new ArrayList<>();
        findAllPathsHelper(start, end, new HashSet<>(), new ArrayList<>(), 0, allPaths);
        allPaths.sort(Comparator.comparingInt(Path::getTotalDuration));
        return allPaths;
    }

    private static void findAllPathsHelper(Zone current, Zone end, Set<Zone> visited, List<Zone> path, int duration, List<Path> allPaths) {
        visited.add(current);
        path.add(current);

        if (current.equals(end)) {
            allPaths.add(new Path(new ArrayList<>(path), duration));
        } else {
            for (Map.Entry<Pair<Zone, Zone>, TransportLine> entry : zonePairs.entrySet()) {
                Pair<Zone, Zone> zonePair = entry.getKey();
                if (zonePair.getFirst().equals(current) && !visited.contains(zonePair.getSecond())) {
                    findAllPathsHelper(zonePair.getSecond(), end, visited, path, duration + entry.getValue().getDuration(), allPaths);
                }
            }
        }

        path.remove(path.size() - 1);
        visited.remove(current);
    }

    public static Path findBestPath(Zone start, Zone end) {
        List<Path> allPaths = findAllPaths(start, end);
        for (Path path : allPaths) {
            if (path.isValid()) {
                return path;
            }
        }
        return null; // No valid path found
    }

    public static TransportLine[] getBestLinesPath(Zone start, Zone end) {
        Path bestPath = findBestPath(start, end);
        return bestPath.getLinesPath();
    }
    
    /*public static List<Path> findShortestPaths(Zone start, Zone end) {
        Map<Zone, Integer> distances = new HashMap<>();
        Map<Zone, Zone> previous = new HashMap<>();
        PriorityQueue<Pair<Zone, Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(Pair::getSecond));

        distances.put(start, 0);
        queue.add(new Pair<>(start, 0));

        while (!queue.isEmpty()) {
            Zone current = queue.poll().getFirst();

            if (current.equals(end)) break;

            for (Map.Entry<Pair<Zone, Zone>, TransportLine> entry : zonePairs.entrySet()) {
                Pair<Zone, Zone> zonePair = entry.getKey();
                if (zonePair.getFirst().equals(current)) {
                    Zone neighbor = zonePair.getSecond();
                    int newDist = distances.get(current) + getTripDuration(entry.getValue());
                    if (newDist < distances.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                        distances.put(neighbor, newDist);
                        previous.put(neighbor, current);
                        queue.add(new Pair<>(neighbor, newDist));
                    }
                }
            }
        }

        List<Path> paths = new ArrayList<>();
        if (!distances.containsKey(end)) {
            return paths; // No path found
        }

        List<Zone> path = new ArrayList<>();
        for (Zone at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        paths.add(new Path(path, distances.get(end)));
        return paths;
    }*/

    public static class Path {
        private List<Zone> zones;
        private int totalDuration;

        public Path(List<Zone> zones, int totalDuration) {
            this.zones = zones;
            this.totalDuration = totalDuration;
        }

        public List<Zone> getZones() {
            return zones;
        }

        public int getTotalDuration() {
            return totalDuration;
        }

        public boolean isValid() {
            for (var line : getLinesPath()) {
                if (line.getCongestion() >= 100.0) {
                    return false;
                }
            }
            return true;
        }

        public TransportLine[] getLinesPath() {
            TransportLine[] lines = new TransportLine[zones.size() - 1];
            for (int i = 0; i < zones.size() - 1; i++) {
                lines[i] = getTransportLine(zones.get(i), zones.get(i + 1));
            }
            return lines;
        }

        @Override
        public String toString() {
            return "Path{" +
                    "zones=" + zones +
                    ", totalDuration=" + totalDuration +
                    '}';
        }
    }
}
