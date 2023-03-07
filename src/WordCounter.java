import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

public class WordCounter {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("This program will count the amount of words in an input file and write the results to an output file.");
        System.out.println("Please enter the amount of words you would like to count. Afterwards you may enter your choice of words. (NOT case-sensitive)");
        int numOfWords = scanner.nextInt();
        String[] words = new String[numOfWords];

        for (int i = 0; i < numOfWords; i ++) {
            words[i] = scanner.next();
        }

        System.out.println("The words you just entered will be randomly assigned to different lines in the \"words.txt\" file. How many lines of this file would you like to be generated?");
        int numOfLines = scanner.nextInt();
        System.out.println("Counting is complete. Check \"count.txt\" to see the results!");
        String fileName = "words.txt";
        Random random = new Random();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < numOfLines; i ++) {
                bufferedWriter.write(words[random.nextInt(words.length)] + "\n");
            }
        }

        String line;
        Map<String, Integer> counts = new TreeMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                line = line.toLowerCase();
                if (counts.containsKey(line)) {
                    counts.put(line, counts.get(line) + 1);
                } else {
                    counts.put(line, 1);
                }
            }
        }

        String outFileName = "count.txt";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outFileName))) {
            for (Entry<String, Integer> entry: counts.entrySet()) {
                bufferedWriter.write(entry.getKey() + " " + entry.getValue() + "\n");
            }
        }
        scanner.close();
    }
}