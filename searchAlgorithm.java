import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
public class searchAlgorithm {

    // Method to rank fibers by popularity that month
    public static String[] rank(Map<String, Integer> fibers) {
            String[] popularityRanking = new String[fibers.size()];
            int index = 0;
            for (Map.Entry<String, Integer> entry : fibers.entrySet()) {
                popularityRanking[index] = entry.getKey();
                index++;
            }
            Arrays.sort(popularityRanking);
            /* TEST
            for (int i = 0; i < popularityRanking.length; i++) {
                System.out.println(popularityRanking[i]);
            }
             */
            return popularityRanking;
        }
    // Method to read 50 search results from a file and get their dominant fiber content
    public static Map<String, String> generateResults(Map<String, Integer> fibers) {
            Map<String, String> results = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("searchResults.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(", ");
                String result = parts[0];
                String fiber = parts[1];
                results.put(result, fiber);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return results;
    }
    // Method to sort results by popularity ranking
    public static Map<String, String> sortResults(Map<String, String> results, String[] popularityRanking) {
        Map<String, String> sorted = new LinkedHashMap<>();

        for (String value : popularityRanking) {
            for (Map.Entry<String, String> entry : results.entrySet()) {
                if (entry.getValue().equals(value)) {
                    sorted.put(entry.getKey(), entry.getValue());
                }
            }
    }
        return sorted;
    }
    public static void main(String[] args) {
        // Read input from user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press ENTER to view search results: ");
        String input = scanner.nextLine();

        // Create a map to store the fiber content of different fabrics and their popularity that month based on purchase history
        Map<String, Integer> fibers = new HashMap<>();
        fibers.put("Cotton", 20);
        fibers.put("Hemp", 40);
        fibers.put("Bamboo", 10);
        fibers.put("Polyester", 80);
        // Call method to rank fibers by their popularity that month based on purchase history
        String[]popularityRanking = rank(fibers);

        if (input.isEmpty()) {
        // Call method to read 50 search results from a file and get their dominant fiber content
        Map<String, String> results = generateResults(fibers);

        // Call method to sort results by popularity ranking
        Map<String, String> sorted = sortResults(results, popularityRanking);

        // Print sorted results
        for (Map.Entry<String, String> entry : sorted.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue() + "\n");
        }
    }
    scanner.close();
}
}

