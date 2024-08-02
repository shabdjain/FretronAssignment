import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class Flight {
    List<Point> path;

    public Flight(int[][] coordinates) {
        path = new ArrayList<>();
        for (int[] coordinate : coordinates) {
            path.add(new Point(coordinate[0], coordinate[1]));
        }
    }
}

public class FlightPaths extends JPanel {
    private List<Flight> flights;

    public FlightPaths(List<Flight> flights) {
        this.flights = flights;
    }

    private boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4) return true;

        if (o1 == 0 && onSegment(p1, p2, q1)) return true;
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false;
    }

    private int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0) return 0;
        return (val > 0) ? 1 : 2;
    }

    private boolean onSegment(Point p, Point q, Point r) {
        return q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            g.setColor(Color.getHSBColor(i * 0.1f, 0.9f, 0.9f));
            for (int j = 0; j < flight.path.size() - 1; j++) {
                Point start = flight.path.get(j);
                Point end = flight.path.get(j + 1);
                boolean intersects = false;
                for (int k = 0; k < flights.size(); k++) {
                    if (i == k) continue;
                    Flight otherFlight = flights.get(k);
                    for (int l = 0; l < otherFlight.path.size() - 1; l++) {
                        Point otherStart = otherFlight.path.get(l);
                        Point otherEnd = otherFlight.path.get(l + 1);
                        if (doIntersect(start, end, otherStart, otherEnd)) {
                            intersects = true;
                            break;
                        }
                    }
                    if (intersects) break;
                }
                if (!intersects) {
                    g.drawLine(start.x * 50, start.y * 50, end.x * 50, end.y * 50);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of flights:");
        int numberOfFlights = scanner.nextInt();

        List<Flight> flights = new ArrayList<>();
        for (int i = 0; i < numberOfFlights; i++) {
            System.out.println("Enter the number of coordinates for Flight " + (i + 1) + ":");
            int numberOfCoordinates = scanner.nextInt();
            int[][] coordinates = new int[numberOfCoordinates][2];
            for (int j = 0; j < numberOfCoordinates; j++) {
                System.out.println("Enter coordinates " + (j + 1) + " (x y):");
                coordinates[j][0] = scanner.nextInt();
                coordinates[j][1] = scanner.nextInt();
            }
            flights.add(new Flight(coordinates));
        }

        JFrame frame = new JFrame("Flight Paths");
        FlightPaths flightPaths = new FlightPaths(flights);
        frame.add(flightPaths);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}