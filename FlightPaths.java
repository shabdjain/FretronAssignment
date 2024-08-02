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
