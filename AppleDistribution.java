
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AppleDistribution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the weights of the apples
        System.out.println("Enter the number of apples:");
        int numberOfApples = scanner.nextInt();
        List<Integer> appleWeights = new ArrayList<>();
        for (int i = 0; i < numberOfApples; i++) {
            System.out.println("Enter apple weight in gram(-1 to stop):");
            appleWeights.add(scanner.nextInt());
        }

        // Get the amounts paid by Ram, Sham, and Rahim
        System.out.println("Enter the amount paid by Ram:");
        int ramPaid = scanner.nextInt();
        System.out.println("Enter the amount paid by Sham:");
        int shamPaid = scanner.nextInt();
        System.out.println("Enter the amount paid by Rahim:");
        int rahimPaid = scanner.nextInt();

        int totalAmount = ramPaid + shamPaid + rahimPaid;

        // Calculate the proportion each person should get
        double totalWeight = appleWeights.stream().mapToInt(Integer::intValue).sum();
        double ramShare = (ramPaid / (double) totalAmount) * totalWeight;
        double shamShare = (shamPaid / (double) totalAmount) * totalWeight;
        double rahimShare = (rahimPaid / (double) totalAmount) * totalWeight;

        // Sort apple weights in descending order
        appleWeights.sort(Collections.reverseOrder());

        // Initialize the distribution
        Map<String, List<Integer>> distribution = new HashMap<>();
        distribution.put("Ram", new ArrayList<>());
        distribution.put("Sham", new ArrayList<>());
        distribution.put("Rahim", new ArrayList<>());

        // Initialize current weights for each person
        Map<String, Double> currentWeight = new HashMap<>();
        currentWeight.put("Ram", 0.0);
        currentWeight.put("Sham", 0.0);
        currentWeight.put("Rahim", 0.0);

        // Distribute the apples
        for (int weight : appleWeights) {
            String personToAllocate = getPersonToAllocate(currentWeight, ramShare, shamShare, rahimShare);
            distributeApple(distribution, currentWeight, personToAllocate, weight);
        }

        // Print the results
        printDistribution(distribution);
        scanner.close();
    }

    private static String getPersonToAllocate(Map<String, Double> currentWeight, double ramShare, double shamShare, double rahimShare) {
        double ramRatio = currentWeight.get("Ram") / ramShare;
        double shamRatio = currentWeight.get("Sham") / shamShare;
        double rahimRatio = currentWeight.get("Rahim") / rahimShare;

        if (ramRatio < shamRatio) {
            if (ramRatio < rahimRatio) {
                return "Ram";
            } else {
                return "Rahim";
            }
        } else {
            if (shamRatio < rahimRatio) {
                return "Sham";
            } else {
                return "Rahim";
            }
        }
    }

    private static void distributeApple(Map<String, List<Integer>> distribution, Map<String, Double> currentWeight, String person, int weight) {
        distribution.get(person).add(weight);
        currentWeight.put(person, currentWeight.get(person) + weight);
    }

    private static void printDistribution(Map<String, List<Integer>> distribution) {
        for (String person : distribution.keySet()) {
            List<Integer> apples = distribution.get(person);
            int totalWeight = apples.stream().mapToInt(Integer::intValue).sum();
            System.out.println(person + ": " + apples + " Total Weight: " + totalWeight + "g");
        }
    }
}
