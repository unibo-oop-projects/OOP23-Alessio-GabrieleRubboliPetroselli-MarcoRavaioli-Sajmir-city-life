package unibo.citysimulation.model;

import java.util.*;

import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneTable;
import unibo.citysimulation.utilities.Pair;

public class PathFinder {
    private static PathFinder instance;
    private Map<Zone, Map<Zone, List<Path>>> allPaths;

    private PathFinder() {
        allPaths = new HashMap<>();
    }

    public static PathFinder getInstance() {
        if (instance == null) {
            instance = new PathFinder();
        }
        return instance;
    }

    public void calculateAllPaths(List<Zone> zones) {
        for (int i = 0; i < zones.size(); i++) {
            for (int j = i + 1; j < zones.size(); j++) {
                Zone start = zones.get(i);
                Zone end = zones.get(j);
                if (!start.equals(end)) {
                    List<Path> paths = findAllPaths(start, end);
                    allPaths.computeIfAbsent(start, k -> new HashMap<>()).put(end, paths);
                    allPaths.computeIfAbsent(end, k -> new HashMap<>()).put(start, paths);
                    System.out.println(start + ", " + end);
                }
            }
        }
    }

    private List<Path> findAllPaths(Zone start, Zone end) {
        List<Path> allPaths = new ArrayList<>();
        findAllPathsHelper(start, end, new HashSet<>(), new ArrayList<>(), 0, allPaths);
        allPaths.sort(Comparator.comparingInt(Path::getTotalDuration));
        return allPaths;
    }

    private void findAllPathsHelper(Zone current, Zone end, Set<Zone> visited, List<Zone> path, int duration,
                                    List<Path> allPaths) {
        visited.add(current);
        path.add(current);

        if (current.equals(end)) {
            allPaths.add(new Path(new ArrayList<>(path), duration));
        } else {
            for (Map.Entry<Pair<Zone, Zone>, TransportLine> entry : ZoneTable.getInstance().getZonePairs().entrySet()) {
                Pair<Zone, Zone> zonePair = entry.getKey();
                if (zonePair.getFirst().equals(current) && !visited.contains(zonePair.getSecond())) {
                    findAllPathsHelper(zonePair.getSecond(), end, visited, path,
                            duration + entry.getValue().getDuration(), allPaths);
                }
            }
        }

        path.remove(path.size() - 1);
        visited.remove(current);
    }

    public TransportLine[] findBestPath(Zone start, Zone end) {
        List<Path> paths = allPaths.getOrDefault(start, Collections.emptyMap()).getOrDefault(end, Collections.emptyList());
        for (Path path : paths) {
            if (path.isValid()) {
                return path.getLinesPath();
            }
        }
        return new TransportLine[0]; // No valid path found
    }

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
                lines[i] = ZoneTable.getTransportLine(zones.get(i), zones.get(i + 1));
            }
            return lines;
        }
    }
}
