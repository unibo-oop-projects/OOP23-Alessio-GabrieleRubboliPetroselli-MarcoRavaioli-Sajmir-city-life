/*import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneTable;
import unibo.citysimulation.model.transport.TransportLine;

import java.util.*;

public class PathFinder {
    //per calcolare i percorsi possibili tra due zone usiamo un algoritmo che ci ordina le strade possibili in ordine crescente di durata

    //per ogni zona di partenza dobbiamo calcolare i percorsi possibili per la zona di arrivo

    //per ogni zona di partenza si calcolano tutte le liste di percorsi possibili verso tutte le altre zone

    //per ogni zona di partenza, per ogni zona di arrivo, per ogni percorso disponibile 
    public Optional<TransportLine[]> getShortestPath(){

    }

    // Trova una lista di percorsi diversi tra un nodo di partenza e uno di arrivo
    public List<TransportLine[]> getAllPossiblePaths(Zone sourceZone, Zone destinationZone) {
        // Coda di priorità per gestire i nodi da esplorare
        PriorityQueue<NodeWithPaths> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.totalDuration));

        // Inizializzazione
        queue.offer(new NodeWithPaths(sourceZone, new TransportLine[0], 0));

        // Lista dei percorsi trovati
        List<TransportLine[]> paths = new ArrayList<>();

        // Ricerca dei cammini
        while (!queue.isEmpty()) {
            NodeWithPaths node = queue.poll();
            Zone currentZone = node.zone;
            TransportLine[] currentPath = node.path;
            int totalDuration = node.totalDuration;

            // Se raggiungiamo la destinazione, aggiungiamo il percorso alla lista dei percorsi
            if (currentZone.equals(destinationZone)) {
                paths.add(currentPath);
                continue; // Continua la ricerca per trovare altri percorsi
            }

            // Esplora i vicini del nodo corrente
            for (Edge edge : zoneTable.getEdges(currentZone)) {
                Zone neighbor = edge.getDestination();
                TransportLine transportLine = edge.getTransportLine();
                int duration = transportLine.getDuration();

                // Calcola la nuova durata totale del percorso
                int newTotalDuration = totalDuration + duration;

                // Crea un nuovo percorso con l'aggiunta della linea di trasporto corrente
                TransportLine[] newPath = Arrays.copyOf(currentPath, currentPath.length + 1);
                newPath[newPath.length - 1] = transportLine;

                // Aggiungi il nuovo percorso alla coda di priorità
                queue.offer(new NodeWithPaths(neighbor, newPath, newTotalDuration));
            }
        }

        return paths;
    }

    // Trova una lista di percorsi diversi tra un nodo di partenza e uno di arrivo
    public List<List<TransportLine>> findAllPaths(Zone sourceZone, Zone destinationZone) {
        // Coda di priorità per gestire i nodi da esplorare
        PriorityQueue<NodeWithPath> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.path.size()));

        // Inizializzazione
        queue.offer(new NodeWithPath(sourceZone, new ArrayList<>()));

        // Lista dei percorsi trovati
        List<List<TransportLine>> paths = new ArrayList<>();

        // Ricerca dei cammini
        while (!queue.isEmpty()) {
            NodeWithPath node = queue.poll();
            Zone currentZone = node.zone;
            List<TransportLine> currentPath = node.path;

            // Se raggiungiamo la destinazione, aggiungiamo il percorso alla lista dei percorsi
            if (currentZone.equals(destinationZone)) {
                paths.add(new ArrayList<>(currentPath));
                continue; // Continua la ricerca per trovare altri percorsi
            }

            // Esplora i vicini del nodo corrente
            for (TransportLine edge : zoneTable.get(currentZone)) {
                Zone neighbor = edge.getDestination();

                // Se il vicino non è già stato visitato
                if (!currentPath.contains(edge.getTransportLine())) {
                    List<TransportLine> newPath = new ArrayList<>(currentPath);
                    newPath.add(edge.getTransportLine());
                    queue.offer(new NodeWithPath(neighbor, newPath));
                }
            }
        }

        return paths;
    }

    // Classe per rappresentare un nodo con un percorso
    private static class NodeWithPath {
        Zone zone;
        List<TransportLine> path;

        public NodeWithPath(Zone zone, List<TransportLine> path) {
            this.zone = zone;
            this.path = path;
        }
    }
}

import java.util.*;

import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneTable;
import unibo.citysimulation.utilities.Pair;

public class PathFinder {

    public List<List<TransportLine>> getAllPossiblePaths(Zone sourceZone, Zone destinationZone) {
        // PriorityQueue per gestire i nodi da esplorare, ordinati per durata del percorso
        PriorityQueue<NodeWithPath> queue = new PriorityQueue<>(Comparator.comparingInt(NodeWithPath::getTotalDuration));

        // Inizializzazione
        queue.offer(new NodeWithPath(sourceZone, new ArrayList<>(), 0));

        // Lista dei percorsi trovati
        List<List<TransportLine>> paths = new ArrayList<>();

        // Set per evitare cicli
        Set<Zone> visited = new HashSet<>();

        // Ricerca dei cammini
        while (!queue.isEmpty()) {
            NodeWithPath node = queue.poll();
            Zone currentZone = node.zone;
            List<TransportLine> currentPath = node.path;

            // Se raggiungiamo la destinazione, aggiungiamo il percorso alla lista dei percorsi
            if (currentZone.equals(destinationZone)) {
                paths.add(new ArrayList<>(currentPath));
                continue; // Continua la ricerca per trovare altri percorsi
            }

            // Evita di riesplorare i nodi già visitati
            if (!visited.add(currentZone)) {
                continue;
            }

            // Esplora i vicini del nodo corrente
            for (TransportLine transportLine : getAdjacentTransportLines(currentZone)) {
                Zone neighbor = getOtherZone(transportLine, currentZone);
                List<TransportLine> newPath = new ArrayList<>(currentPath);
                newPath.add(transportLine);

                queue.offer(new NodeWithPath(neighbor, newPath, node.totalDuration + transportLine.getDuration()));
            }
        }

        // Ordina i percorsi trovati in base alla durata totale
        paths.sort(Comparator.comparingInt(this::calculateTotalDuration));

        return paths;
    }

    // Classe per rappresentare un nodo con un percorso
    private static class NodeWithPath {
        Zone zone;
        List<TransportLine> path;
        int totalDuration;

        public NodeWithPath(Zone zone, List<TransportLine> path, int totalDuration) {
            this.zone = zone;
            this.path = path;
            this.totalDuration = totalDuration;
        }

        public int getTotalDuration() {
            return totalDuration;
        }
    }

    private List<TransportLine> getAdjacentTransportLines(Zone zone) {
        List<TransportLine> transportLines = new ArrayList<>();
        for (Pair<Zone, Zone> pair : zoneTable.getZonePairs().keySet()) {
            if (pair.getFirst().equals(zone) || pair.getSecond().equals(zone)) {
                transportLines.add(zoneTable.getTransportLine(pair.getFirst(), pair.getSecond()));
            }
        }
        return transportLines;
    }

    private Zone getOtherZone(TransportLine transportLine, Zone currentZone) {
        Pair<Zone, Zone> link = transportLine.getLink();
        return link.getFirst().equals(currentZone) ? link.getSecond() : link.getFirst();
    }

    private int calculateTotalDuration(List<TransportLine> path) {
        return path.stream().mapToInt(TransportLine::getDuration).sum();
    }
}*/

