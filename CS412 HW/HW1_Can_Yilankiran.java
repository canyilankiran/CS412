import java.util.*;

public class hw1 {
    public static void main(String[] args) {
        System.out.print("Question 1 answer: ");
        kmers("ACGTTGCATGTCGCATGATGCATGAGAGCT",4);
        String x = reverse_complement("AAAACCCGGT");
        System.out.println("Question 2 answer:" + x);
        System.out.print("Question 3 answer: ");
        patternMatch("ATAT","GATATATGCATATACTT");
        System.out.print("\nQuestion 4 answer: ");
        ClumpFinding("CGGACTCGACAGATGTGAAGAAATGTGAAGACTGAGTGAAGAGAAGAGGAAACACGACACGACATTGCGACATAATGTACGAATGTAATGTGCCTATGGC",5, 75, 4);
        System.out.print("\nQuestion 5 answer: ");
        ArrayList<Integer> skewResults = MinimumSkew("CCTATCGGTGGATTAGCATGTCCCTGTACGTTTCGCCGCGAACTAGTTCACACGGCTTGATGGCAAATGGTTTTTCCGGCGACCGTAATCGTCCACCGAG");
        for (int i: skewResults) {
            System.out.print(i + " ");
        }
        int result = 0;
        for (int i: x
             ) {
            result+= i;
        }
        System.out.println(result);

        public void resultGenerator(ArrayList x){

            for(int i = 0 ; i<x.length(); i++){
                System.out.println(i);
            }
        }

        }


    public static void kmers(String str, int k) {
        String[] possible_kmers = new String[str.length() - k + 1];
        for (int i = 0; i < str.length() - k + 1; i++) {
            String x = str.substring(i, k + i);
            possible_kmers[i] = x;
//            System.out.print(x + " ");
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
        // System.out.println("");
//        for (int c = 0; c < mostFrequentKmers.size(); c++ ){
//            System.out.print(mostFrequentKmers.get(c) + " ");
//        }
        // System.out.println("");
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


    public static String reverse_complement(String str) {
        String reversedDNA = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversedDNA += str.charAt(i);
        }
        reversedDNA = complementString(reversedDNA);
        return reversedDNA;
    }

    public static String complementString(String str) {
        String complementofString = " ";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'T') {
                complementofString += 'A';
            } else if (str.charAt(i) == 'A') {
                complementofString += 'T';
            } else if (str.charAt(i) == 'G') {
                complementofString += 'C';
            } else {
                complementofString += 'G';

            }

        }
        return complementofString;

    }

    public static void patternMatch(String pattern, String genome) {
        for (int i = 0; i < genome.length(); i++) {
            int counter = 0;
            for (int j = 0; j < pattern.length(); j++) {
                if (i + j < genome.length()) {
                    if (pattern.charAt(j) != genome.charAt(i + j)) {
                        break;
                    }
                    counter++;
                }
            }
            if (counter == pattern.length()) {
                System.out.print(i + " ");
            }

        }
    }

    public static void ClumpFinding(String genome, int k, int L, int t) {
        ArrayList<String> substringsOfGenomeWithLlength = new ArrayList<>();
        //System.out.println(genome.length());
        ArrayList<String> kmersWithFrequencyOfT = new ArrayList<>();
        ArrayList<String> resultlist = new ArrayList<>();
        if (L < genome.length()) {
            for (int i = 0; i < genome.length() - L + 1; i++) {
                String genomesWithLlength = genome.substring(i, L + i);
                substringsOfGenomeWithLlength.add(genomesWithLlength);
                kmersWithFrequencyOfT.addAll(getPossibleKmers(genomesWithLlength,k,t));
                }
            }else{
                substringsOfGenomeWithLlength.add(genome);
                kmersWithFrequencyOfT.addAll(getPossibleKmers(genome, k,t));
            }
        resultlist=removeDuplicates(kmersWithFrequencyOfT);
        for (String s : resultlist) {
            System.out.print(s + " ");
        }

//            for (int i = 0; i < kmersWithFrequencyOfT.size(); i++) {
//                System.out.println(kmersWithFrequencyOfT.get(i));
//            }
        }


        public static ArrayList<String> getPossibleKmers(String str, int k, int t){
        ArrayList<String> possibleKmers  = new ArrayList<>();
        ArrayList<String> kmers = new ArrayList<>();
            for (int i = 0; i < str.length() - k + 1; i++) {
                String x = str.substring(i, k + i);
                possibleKmers.add(x);
            }

            for (int j = 0; j< possibleKmers.size(); j++){
                if(frequency(possibleKmers,possibleKmers.get(j)) == t){
                    kmers.add(possibleKmers.get(j));
                }

            }

            return kmers;
        }
        public static ArrayList<Integer> MinimumSkew (String genome){
        int skew = 0;
        ArrayList<Integer> skewValues = new ArrayList<>();
        skewValues.add(skew);
        for (int i = 0 ; i < genome.length(); i++) {
            if (genome.charAt(i) == 'G') {
                skew = skew + 1;
            } else if (genome.charAt(i) == 'C') {
                skew = skew - 1;
            }
            skewValues.add(skew);


        }
        int min = findMin(skewValues);
        ArrayList<Integer> results = new ArrayList<>();
        for (int i = 0; i < skewValues.size(); i++){
            if(skewValues.get(i) == min){
                results.add(i);
            }
        }
        return results;
        }

        public static int findMin (ArrayList<Integer> list){
        int min = list.get(0);
        for(int i = 0; i < list.size(); i++){
            if(list.get(i) < min){
                min = list.get(i);
            }
        }
        return min;
        }

    }

