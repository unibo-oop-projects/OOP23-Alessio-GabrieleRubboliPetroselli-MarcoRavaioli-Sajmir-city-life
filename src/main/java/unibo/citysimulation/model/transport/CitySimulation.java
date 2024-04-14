package unibo.citysimulation.model.transport;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
public class CitySimulation extends JPanel {

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;

    private Map<String, Point> zoneMap = new HashMap<>();

    public CitySimulation() {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setBackground(Color.LIGHT_GRAY);

        // Add zones
        addZone("Zona 1", WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2);
        addZone("Zona 2", WINDOW_WIDTH / 4, WINDOW_HEIGHT / 4);
        addZone("Zona 3", WINDOW_WIDTH * 3 / 4, WINDOW_HEIGHT / 4);
        addZone("Zona 4", WINDOW_WIDTH / 4, WINDOW_HEIGHT * 3 / 4);
        addZone("Zona 5", WINDOW_WIDTH * 3 / 4, WINDOW_HEIGHT * 3 / 4);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the zones
        for (String zone : zoneMap.keySet()) {
            Point point = zoneMap.get(zone);
            drawZone(g, point.x, point.y, zone);
        }

        // Draw lines between zones
        drawLine(g, zoneMap.get("Zona 1"), zoneMap.get("Zona 2"));
        drawLine(g, zoneMap.get("Zona 1"), zoneMap.get("Zona 3"));
        drawLine(g, zoneMap.get("Zona 1"), zoneMap.get("Zona 4"));
        drawLine(g, zoneMap.get("Zona 1"), zoneMap.get("Zona 5"));
    }

    private void addZone(String name, int x, int y) {
        zoneMap.put(name, new Point(x, y));
    }

    private void drawZone(Graphics g, int x, int y, String name) {
        g.setColor(Color.BLUE);
        g.fillOval(x - 15, y - 15, 30, 30);
        g.setColor(Color.BLACK);
        g.drawOval(x - 15, y - 15, 30, 30);
        g.drawString(name, x - 25, y - 20);
    }

    private void drawLine(Graphics g, Point start, Point end) {
        g.setColor(Color.RED);
        g.drawLine(start.x, start.y, end.x, end.y);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("City Simulation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new CitySimulation());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}