import java.util.ArrayList;
import java.util.Arrays;

public class HW6_Can_Yilankiran {

    public static void main(String[] args) {
        //question1
        ArrayList<Integer> q1 = new ArrayList<>(Arrays.asList(-3, +4, +1, +5, -2));
        int resultOfQuestion1 = greedySort(q1);
        System.out.println("And the reversal distance is " + resultOfQuestion1);

        ArrayList<Integer> q2 = new ArrayList<>(Arrays.asList(1, 9, 3, 4, 7, 8, 2, 6, 5));
        int resultOfQuestion2 = numberOfBreakPoints(q2);
        System.out.println("\nAnswer of question 2: " + resultOfQuestion2);
        //question 4

        System.out.println("\nAnswer of question 4: ");
        sharedKmers(3, "AAACTCATC", "TTTCAAATC");
    }


    public static void sharedKmers(int k, String genome1, String genome2) {
        for (int i = 0; i < genome1.length() - k + 1; i++) {
            for (int j = 0; j < genome2.length() - k + 1; j++) {
                String kmerOfFirstGenome = genome1.substring(i, k + i);
                String kmerOfSecondGenome = genome2.substring(j, k + j);

                if (kmerOfFirstGenome.equals(kmerOfSecondGenome)) {
                    System.out.println("(" + i + "," + j + ")");
                }
                if (kmerOfFirstGenome.equals(reverse_complement(kmerOfSecondGenome))) {
                    System.out.println("(" + i + "," + j + ")");
                }
            }
        }
    }

    public static int greedySort(ArrayList<Integer> list) {
        System.out.println("Answer of question 1. The steps taken on list while greedy sort operation printed down below.");
        int count = 0;
        if (!isItSignedPermutation(list)) {             // if is not a singed permutation I will use following approach
            for (int i = 1; i < list.size() + 1; i++) { // the goal is to find identity permutation so I started for loop from 1 .... list-1
                int j = find(i, list);                  // find where the "i" is in the list. ( i = 1, i = 2 .....)
                int temp = j + 1;                       // I added 1 to the position because loop stated from 1
                if (temp != i) {                        // this checks the position of the i is true or not
                    reversal(list, i, j);               // if it is not true the reversal function will be called.
                    System.out.println(list);
                    count++;
                }
                if (identity(list)) {                   // ıf we reach the identity permutation before for loop ends it will break the loop.
                    break;
                }
            }
        } else {                                            // if is it a signed permutation I will use following approach
            for (int i = 1; i < list.size() + 1; i++) {     // same as not signed
                int j = signedFind(i, list);                // same as not signed
                int temp = j + 1;                           // same as not signed
                if (temp != i) {                            // same as not signed
                    signedReversal(list, i, j);             // I will call the signedReversal func.
                    System.out.println(list);
                    count++;
                }
                if(list.get(i-1) == -i){                    // this corrects the if the i is at true position but not true sign such as: -2 being at true location but not having true sign. It will turn it to +2.
                    list.set(i-1,-list.get(i-1));
                    System.out.println(list);
                    count++;
                }
                if (identity(list)) {
                    break;
                }

            }
        }
    return count;
}

    public static boolean isItSignedPermutation(ArrayList<Integer> list){
        for(int i = 0; i< list.size(); i++){
            if(list.get(i) < 0 ){
                return true;
            }
        }
        return false;
    }

    public static int find(int goal, ArrayList<Integer> list) {
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == goal) {
                index = i;
                break;
            }
        }
        return index;
    }
    public static int signedFind(int goal, ArrayList<Integer> list) {
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == goal || list.get(i) == -goal) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static ArrayList<Integer> reversal(ArrayList<Integer> list, int i, int j) {
        int itemAtI = list.get(i - 1);
        int itemAtJ = list.get(j);
        list.set(i - 1, itemAtJ);
        list.set(j, itemAtI);
        return list;
    }
    public static ArrayList<Integer> signedReversal(ArrayList<Integer> list, int i, int j) {
        if ( list.get(i - 1)> 0 && list.get(j) > 0){
            int itemAtI = list.get(i - 1);
            int itemAtJ = list.get(j);
            list.set(i - 1, itemAtJ);
            list.set(j, itemAtI);
        }
        else if(i == j){
            list.set(j,-(list.get(j)));}
        else {
            int itemAtI = -(list.get(i - 1));
            int itemAtJ = -(list.get(j));
            list.set(i - 1, itemAtJ);
            list.set(j, itemAtI);
        }
        return list;
    }

    public static boolean identity(ArrayList<Integer> list) {
        for (int i = 1; i < list.size() + 1; i++) {
            if (i != list.get(i) + 1) {
                return false;
            }
        }
        return true;
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
        String complementofString = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'T') {
                complementofString += "A";
            } else if (str.charAt(i) == 'A') {
                complementofString += "T";
            } else if (str.charAt(i) == 'G') {
                complementofString += "C";
            } else {
                complementofString += "G";

            }

        }
        return complementofString;

    }

    public static int numberOfBreakPoints(ArrayList<Integer> list) {
        int breakpointCount = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) + 1 != list.get(i + 1) && list.get(i) - 1 != list.get(i + 1)) {
                breakpointCount++;
            }
        }
        return breakpointCount;
    }
}
