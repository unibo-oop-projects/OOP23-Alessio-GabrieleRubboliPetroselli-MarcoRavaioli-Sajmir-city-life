import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.transport.TransportLine;

import java.util.*;

public class PathFinder {
    private ZoneTable zoneTable;

    public PathFinder(ZoneTable zoneTable) {
        this.zoneTable = zoneTable;
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
            for (Edge edge : zoneTable.getEdges(currentZone)) {
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
