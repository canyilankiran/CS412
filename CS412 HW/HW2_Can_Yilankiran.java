import java.util.ArrayList;
import java.util.Arrays;

public class hw2 {

    public static void main(String[] args) {
        ArrayList<String> testcase = new ArrayList<>(Arrays.asList("ATA", "ACA", "AGA","AAT", "AAC"
        )
        );
        String result = medianString(testcase,3);
        System.out.println(result);
    }


    public static String medianString(ArrayList<String> setOfDna, int k){
        int d = Integer.MAX_VALUE;
        String medianStr = "";
        ArrayList<String> possibleKmers = new ArrayList<>();
        for(int i = 0; i < setOfDna.size(); i++){
            possibleKmers.addAll(kmers(setOfDna.get(i),k));
        }
        possibleKmers = removeDuplicates(possibleKmers);
        for (String x : possibleKmers) {
           if( d > distance(x,setOfDna)){
               d = distance(x,setOfDna);
               medianStr = x;
           }
        }
        return medianStr;
    }

    public static int distance(String x,ArrayList<String> setOfDna){
        int k = x.length();
        int totalDistance = 0;
        for (String pattern :setOfDna ) {
            int minDistance = Integer.MAX_VALUE;
            ArrayList<String> possibleKmers = kmers(pattern,k);
            for(int i = 0; i<possibleKmers.size(); i++){
                if (minDistance > difference(x,possibleKmers.get(i))){
                    minDistance = difference(x,possibleKmers.get(i));
                }
            }
            totalDistance = totalDistance + minDistance;

        }
        return totalDistance;

    }

    public static int difference(String x, String y){
        int diff = 0;
        for(int i=0; i<x.length(); i++ ){
            if(x.charAt(i) != y.charAt(i)){
                diff++;
            }
        }
        return diff;
    }
    public static ArrayList<String> kmers(String str, int k) {
        ArrayList<String> possible_kmers = new ArrayList<>();
        for (int i = 0; i < str.length() - k + 1; i++) {
            String x = str.substring(i, k + i);
            possible_kmers.add(x);
        }
        return possible_kmers;
    }
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


}
