import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class kmers {


    public static void main(String[] args) {
        ArrayList<String> can =new ArrayList<>();
        can = readFile("rosalind_ba1b (3).txt");
        String genome = can.get(0);
        int k = Integer.parseInt(can.get(1));
        kmers(genome, k);
    }
    public static void kmers(String str, int k) {
        String[] possible_kmers = new String[str.length() - k + 1];
        for (int i = 0; i < str.length() - k + 1; i++) {
            String x = str.substring(i, k + i);
            possible_kmers[i] = x;
        }

        int[] countOfKmers = new int[str.length() - k + 1];
        for (int j = 0; j < possible_kmers.length; j++) {
            countOfKmers[j] = frequency(possible_kmers, possible_kmers[j]);
        }
        ArrayList<String> mostFrequentKmers = new ArrayList<>();
        for (int x = 0; x < countOfKmers.length; x++) {
            if ((countOfKmers[x] == max(countOfKmers))) {
                mostFrequentKmers.add(possible_kmers[x]);
            }
        }
        mostFrequentKmers = removeDuplicates(mostFrequentKmers);
        for (int c = 0; c < mostFrequentKmers.size(); c++) {
            System.out.print(mostFrequentKmers.get(c) + " ");
        }
        System.out.println();

    }

    public static int frequency(String[] str, String str1) {
        int count = 0;
        for (int i = 0; i < str.length; i++) {
            if (str1.equals(str[i])) {
                count++;
            }

        }
        return count;

    }

    public static int frequency(ArrayList<String> str, String str1) {
        int count = 0;
        for (int i = 0; i < str.size(); i++) {
            if (str1.equals(str.get(i))) {
                count++;
            }

        }
        return count;

    }

    public static int max(int[] counts) {
        int max = counts[0];
        for (int k = 0; k < counts.length; k++) {
            if (counts[k] > max) {
                max = counts[k];
            }
        }
        return max;
    }

    //removing duplicates from mostFrequentsKmers arraylist
    public static ArrayList<String> removeDuplicates(ArrayList<String> list) {
        ArrayList<String> removedList = new ArrayList<>();
        if (list.size() > 1) {
            removedList.add(list.get(0));
            for (int i = 0; i < list.size(); i++) {
                if (!removedList.contains(list.get(i))) {
                    removedList.add(list.get(i));
                }
            }

        }
        return removedList;
    }

    public static ArrayList<String> readFile (String filename){
        ArrayList<String> list = new ArrayList<>();
        try (FileReader reader = new FileReader(filename);
             BufferedReader br = new BufferedReader(reader)) {

            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    return list;
    }
}
