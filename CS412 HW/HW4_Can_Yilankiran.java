import java.util.*;

public class HW4_Can_Yilankiran {

    public static void main(String[] args) {
        HashMap<String, String> GeneticCode = new HashMap<>(); // defining the geneticCode according to the 3-mers
        GeneticCode.put("AAA", "K");
        GeneticCode.put("AAC", "N");
        GeneticCode.put("AAG", "K");
        GeneticCode.put("AAU", "N");
        GeneticCode.put("ACA", "T");
        GeneticCode.put("ACC", "T");
        GeneticCode.put("ACG", "T");
        GeneticCode.put("ACU", "T");
        GeneticCode.put("AGA", "R");
        GeneticCode.put("AGC", "S");
        GeneticCode.put("AGG", "R");
        GeneticCode.put("AGU", "S");
        GeneticCode.put("AUA", "K");
        GeneticCode.put("AUC", "I");
        GeneticCode.put("AUG", "M");
        GeneticCode.put("AUU", "I");
        GeneticCode.put("CAA", "Q");
        GeneticCode.put("CAC", "H");
        GeneticCode.put("CAG", "Q");
        GeneticCode.put("CAU", "H");
        GeneticCode.put("CCA", "P");
        GeneticCode.put("CCC", "P");
        GeneticCode.put("CCG", "P");
        GeneticCode.put("CGA", "R");
        GeneticCode.put("CGC", "R");
        GeneticCode.put("CGG", "R");
        GeneticCode.put("CGU", "R");
        GeneticCode.put("CUA", "L");
        GeneticCode.put("CUC", "L");
        GeneticCode.put("CUG", "L");
        GeneticCode.put("CUU", "L");
        GeneticCode.put("GAA", "E");
        GeneticCode.put("GAC", "D");
        GeneticCode.put("GAG", "E");
        GeneticCode.put("GAU", "D");
        GeneticCode.put("GCA", "A");
        GeneticCode.put("GCC", "A");
        GeneticCode.put("GCG", "A");
        GeneticCode.put("GCU", "A");
        GeneticCode.put("GGA", "G");
        GeneticCode.put("GGC", "G");
        GeneticCode.put("GGG", "G");
        GeneticCode.put("GGU", "G");
        GeneticCode.put("GUA", "V");
        GeneticCode.put("GUC", "V");
        GeneticCode.put("GUG", "V");
        GeneticCode.put("GUU", "V");
        GeneticCode.put("UAA", "*");
        GeneticCode.put("UAC", "Y");
        GeneticCode.put("UAG", "*");
        GeneticCode.put("UAU", "Y");
        GeneticCode.put("UCA", "S");
        GeneticCode.put("UCC", "S");
        GeneticCode.put("UCG", "S");
        GeneticCode.put("UCU", "S");
        GeneticCode.put("UGA", "*");
        GeneticCode.put("UGC", "C");
        GeneticCode.put("UGG", "W");
        GeneticCode.put("UGU", "C");
        GeneticCode.put("UUA", "L");
        GeneticCode.put("UUC", "F");
        GeneticCode.put("UUG", "L");
        GeneticCode.put("UUU", "F");

        // Question 1 answer
        String q1result = PatternTranslation("AUGGCCAUGGCGCCCAGAACUGAGAUCAAUAGUACCCGUAUUAACGGGUGA", GeneticCode);
        System.out.println("Question 1 answer: " + q1result);

        // Question 2 answer
        ArrayList<Integer> resultOfQ2;
        resultOfQ2 = sortArrayList(theoreticalSpectrum("NQEL"));
        System.out.println("Question 2 answer: " + resultOfQ2);

        // Question 3 answer
        List<Integer> Q3collectionOfInts = (Arrays.asList(0, 99, 113, 114, 128, 227, 257, 299, 355, 356, 370, 371, 484));
        int resultOfQ3 = CyclopeptideScoring("NQEL", Q3collectionOfInts);
        System.out.print("Question 3 answer: " + resultOfQ3);

        // Question 4 answer
        ArrayList<Integer> Q4spectrum = new ArrayList<>(Arrays.asList(0, 71, 113, 129, 147, 200, 218, 260, 313, 331, 347, 389, 460));
        String q4Answer = LeaderboardCycloPeptide(Q4spectrum);
        System.out.print("\nQuestion 4 answer: " + q4Answer + ". Corresponding masses " + printMassesofCylicPeptide(q4Answer));

        // Question 5 answer
        // I wanted to show the multiplicities of each masses thats why I printed q5 answer twice intentionally
        ArrayList<Integer> q5Answer;
        ArrayList<Integer> Q5spectrum = new ArrayList<>(Arrays.asList(0, 137, 186, 323));
        q5Answer = SpectralConvolutionProblem(Q5spectrum);
        System.out.println("Question 5 answer: " + q5Answer);


    }


    public static String PatternTranslation(String pattern, HashMap<String, String> geneticCode) {
        String result = "";
        if (checkPattern(pattern)) { // check that pattern is only contains 'A' 'U' 'G' 'C'
            for (int i = 0; i < pattern.length() - 3 + 1; i = i + 3) { // get 3-kmers
                String x = pattern.substring(i, 3 + i);
                String aminoAcid = geneticCode.get(x);
                if (!aminoAcid.equals("*")) {           // check that is stop codon or not, if not continue
                    result += aminoAcid;
                } else {                           // if it is stop codon stop the for loop return the result
                    break;
                }
            }
        }
        return result;
    }

    public static ArrayList<Integer> theoreticalSpectrum(String peptide) {

        ArrayList<Integer> result = new ArrayList<>();
        result.add(0); // default
        for (int i = 0; i < peptide.length(); i++) {
            for (int y = i; y < peptide.length(); y++) {
                String var = peptide.substring(i, y + 1);
                //System.out.println(var);
                int mass = calcMass(var);
                result.add(mass);
            }
        }
        for (int x = 2; x < peptide.length(); x++) {
            for (int z = 0; z < x - 1; z++) {
                String varr = peptide.substring(x) + peptide.substring(0, z + 1);
                //System.out.println(varr);
                int mass = calcMass(varr);
                result.add(mass);
            }
        }
        return result;
    }

    public static int calcMass(String peptide) {
        HashMap<String, Integer> intMass = new HashMap<>();
        intMass.put("G", 57);
        intMass.put("A", 71);
        intMass.put("S", 87);
        intMass.put("P", 97);
        intMass.put("V", 99);
        intMass.put("T", 101);
        intMass.put("C", 103);
        intMass.put("I", 113);
        intMass.put("L", 113);
        intMass.put("N", 114);
        intMass.put("D", 115);
        intMass.put("K", 128);
        intMass.put("Q", 128);
        intMass.put("E", 129);
        intMass.put("M", 131);
        intMass.put("H", 137);
        intMass.put("F", 147);
        intMass.put("R", 156);
        intMass.put("Y", 163);
        intMass.put("W", 186);
        int totalMass = 0;
        for (int i = 0; i < peptide.length(); i++) {
            String x = "";
            x += peptide.charAt(i);
            totalMass += intMass.get(x);
        }
        return totalMass;
    }

    public static Integer CyclopeptideScoring(String peptide, List<Integer> collecOfIntegers) {
        ArrayList<Integer> theoSpectrumofPeptide = theoreticalSpectrum(peptide);
        int result = 0;
        for (int i = 0; i < collecOfIntegers.size(); i++) {
            for (int j = 0; j < theoSpectrumofPeptide.size(); j++) {
                if (collecOfIntegers.get(i).equals(theoSpectrumofPeptide.get(j))) {
                    result++;
                }
            }
        }
        return result;
    }

    public static String LeaderboardCycloPeptide(ArrayList<Integer> spectrum) {
        ArrayList<String> leaderBoard = new ArrayList<>();
        String leaderPeptide = "";
        leaderBoard.add(leaderPeptide);
        ArrayList<String> removes = new ArrayList<>();
        while (leaderBoard.size() != 0) {
            leaderBoard = expand(leaderBoard);
            for (String peptide : leaderBoard) {
                if (calcMass(peptide) == spectrum.get(spectrum.size() - 1)) {
                    if (CyclopeptideScoring(peptide, spectrum) > CyclopeptideScoring(leaderPeptide, spectrum)) {
                        leaderPeptide = peptide;
                    }
                } else if (calcMass(peptide) > spectrum.get(spectrum.size() - 1)) {
                    removes.add(peptide);
                }
            }
            leaderBoard.removeAll(removes);
            if (leaderBoard.size() != 0)
                leaderBoard = cut(leaderBoard, spectrum);
        }
        return leaderPeptide;
    }

    public static ArrayList<String> cut(ArrayList<String> leaderBoard, ArrayList<Integer> spectrum) {
        HashMap<String, Integer> scores = new HashMap<>();
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> result2 = new ArrayList<>();

        for (String i : leaderBoard) {
            scores.put(i, CyclopeptideScoring(i, spectrum));
        }
        int max = 0;
        for (String i : scores.keySet()) {
            if (max < scores.get(i)) {
                max = scores.get(i);
            }
        }
        while (max >= 0) {
            for (String i : scores.keySet()) {
                if (max == scores.get(i)) {
                    result.add(i);
                }
            }
            max--;
        }
        for (int i = 0; i < 10; i++) {
            result2.add(result.get(i));
        }
        return result2;
    }


    public static ArrayList<String> expand(ArrayList<String> leaderBoard) {
        HashMap<String, Integer> intMass = new HashMap<>();
        intMass.put("G", 57);
        intMass.put("A", 71);
        intMass.put("S", 87);
        intMass.put("P", 97);
        intMass.put("V", 99);
        intMass.put("T", 101);
        intMass.put("C", 103);
        intMass.put("I", 113);
        intMass.put("L", 113);
        intMass.put("N", 114);
        intMass.put("D", 115);
        intMass.put("K", 128);
        intMass.put("Q", 128);
        intMass.put("E", 129);
        intMass.put("M", 131);
        intMass.put("H", 137);
        intMass.put("F", 147);
        intMass.put("R", 156);
        intMass.put("Y", 163);
        intMass.put("W", 186);
        ArrayList<String> temp = new ArrayList<>();
        for (String i : leaderBoard) {
            for (String j : intMass.keySet()) {
                temp.add(i + j);
            }
        }
        leaderBoard.addAll(temp);
        return temp;
    }

    public static ArrayList<Integer> SpectralConvolutionProblem(ArrayList<Integer> spectrum) {
        HashMap<Integer, Integer> mapp = new HashMap<>();  // HashMap with mass values and multiplicities
        ArrayList<Integer> result = new ArrayList<>();   // result array
        ArrayList<Integer> multiplicities = new ArrayList<>(); // multiplicities array
        for (int i = 0; i < spectrum.size(); i++) {
            for (int j = 0; j < spectrum.size(); j++) {
                if (spectrum.get(i) - spectrum.get(j) > 0) {
                    if (!mapp.containsKey(spectrum.get(i) - spectrum.get(j))) {
                        mapp.put(spectrum.get(i) - spectrum.get(j), 1);
                    } else {
                        mapp.put(spectrum.get(i) - spectrum.get(j), mapp.get(spectrum.get(i) - spectrum.get(j)) + 1);
                    }
                }
            }
        }
        //find the max value in the HashMap, then add the keys having the max values to the ArrayList, lower the max value each time.
        int max = 0;
        for (int i : mapp.keySet()) {
            if (max < mapp.get(i)) {
                max = mapp.get(i);
            }
        }
        while (max >= 0) {
            for (int i : mapp.keySet()) {
                if (max == mapp.get(i)) {
                    result.add(i);
                    multiplicities.add(mapp.get(i));
                }
            }
            max--;
        }
        System.out.println("\nQuestion 5 answer with their corresponding  multiplicities " + result + " Multiplicities=" + multiplicities);
        return result;
    }

    private static boolean checkPattern(String pattern) {
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) != 'A' && pattern.charAt(i) != 'U' && pattern.charAt(i) != 'G' && pattern.charAt(i) != 'C') {
                System.out.println(i);
                return false;
            }
        }
        return true;
    }


    // getting masses of cyclic peptides
    public static String printMassesofCylicPeptide(String cyclicpeptide) {
        HashMap<String, Integer> intMass = new HashMap<>();
        intMass.put("G", 57);
        intMass.put("A", 71);
        intMass.put("S", 87);
        intMass.put("P", 97);
        intMass.put("V", 99);
        intMass.put("T", 101);
        intMass.put("C", 103);
        intMass.put("I", 113);
        intMass.put("L", 113);
        intMass.put("N", 114);
        intMass.put("D", 115);
        intMass.put("K", 128);
        intMass.put("Q", 128);
        intMass.put("E", 129);
        intMass.put("M", 131);
        intMass.put("H", 137);
        intMass.put("F", 147);
        intMass.put("R", 156);
        intMass.put("Y", 163);
        intMass.put("W", 186);
        String result = "";
        for (int i = 0; i < cyclicpeptide.length(); i++) {
            String charAtt = String.valueOf(cyclicpeptide.charAt(i));
            if (i != 0) {
                result += "--" + intMass.get(charAtt);
            } else {
                result += intMass.get(charAtt);
            }

        }
        return result;
    }

    //method for sorting arraylists
    public static ArrayList<Integer> sortArrayList(ArrayList<Integer> sortThisArray) {
        int min = sortThisArray.get(0);
        int max = 0;
        ArrayList<Integer> sortedList = new ArrayList<>();
        for (Integer i : sortThisArray) {
            if (i < min) {
                min = i;
            }
            if (i > max){
                max = i;
            }
        }
        while (min <= max) {
            for (Integer i : sortThisArray) {
                if (min == i) {
                    sortedList.add(i);
                }
            }
            min++;
        }

        return sortedList;
    }
}
