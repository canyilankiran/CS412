import java.util.ArrayList;
import java.util.Arrays;

public class HW5_Can_Yilankiran {
    public static void main(String[] args){
        ArrayList<Integer> arrayOfCoins = new ArrayList<>(Arrays.asList(24,13,12,7,5,3,1));
        int q1answer = ChangeProblem(8074,arrayOfCoins);
        System.out.println(q1answer);

        String x = "AACCTTGG";
        String y = "ACACTGTGA";
        String q2answer = CommonSubsequence(x,y);
        System.out.println(q2answer);
        //int q3answer = GlobalAlign() i did the question but i cannot tested because of scores_matrix
    }

    public static Integer ChangeProblem(int money, ArrayList<Integer> COINS){
        ArrayList<Integer> resultTable = new ArrayList<>();
        resultTable.add(0); // we add the first result as 0 in case of input money is 0

        for( int i = 1 ; i <= money; i++){
            resultTable.add(999999999);        // I tried to set all results as infinity
        }

        for(int i = 1; i <= money; i++){
            for (int coin: COINS) {
                if(coin <= i){
                    if(resultTable.get(i-coin)+1 <  resultTable.get(i)){
                        resultTable.set(i,resultTable.get(i-coin)+1);
                }
            }

            }
        }
        return resultTable.get(money);
    }


    public static String CommonSubsequence(String first,String second){
        String result = "";
        int firstLength = first.length();
        int secondLength = second.length();
        int[][] resultTable = new int[firstLength + 1][secondLength + 1];
        if(first.length() != 0 && second.length() != 0) {
            for (int i = 1; i <= firstLength; i++) {
                for (int j = 1; j <= secondLength; j++) {
                    if(i == 0 || j == 0) {
                        resultTable[i][j] = 0;
                    }
                    else if (first.charAt(i - 1) == second.charAt(j - 1)) {
                        resultTable[i][j] = resultTable[i - 1][j - 1] + 1;
                    } else {
                        resultTable[i][j] = get_max(resultTable[i - 1][j], resultTable[i][j - 1]);
                    }
                }
            }
        }
        return StringConversion(first,second,first.length(),second.length(),resultTable);
    }

    public static String StringConversion(String first,String second, int length1, int length2 ,int[][] resultTable){

        if(length1 == 0 || length2 == 0){
            return "";
        }

        if(first.charAt(length1-1) == second.charAt(length2-1)) {
            return StringConversion(first, second, length1 - 1, length2 - 1, resultTable) + first.charAt(length1 - 1);
        }
            if (resultTable[length1 - 1][length2] > resultTable[length1][length2 - 1]) {
                return StringConversion(first, second, length1 - 1, length2, resultTable);
            } else {
                return StringConversion(first, second, length1, length2 - 1, resultTable);
            }

    }
    public static int GlobalAlign(String first, String second , int[][] score_matrix){
        int[][] allignment_matrix= new int[first.length()+1][second.length()+1];
        int gap = 5; //I set the gap penalty to 5.
        for (int i = 1; i <= first.length(); i++){
            for (int j = 1; j <= second.length(); j++){
                allignment_matrix[i][j] = get_max(allignment_matrix[i-1][j] + gap,
                                                  allignment_matrix[i][j-1] + gap,
                                                  allignment_matrix[i-1][j-1] + getCost(score_matrix,first.charAt(i-1),second.charAt(j-1)));
            }
        }
        return allignment_matrix[first.length()+1][second.length()+1];
    }

    public static int getCost(int[][] score_matrix, char A, char B){ // getting the cost of the allignment of char a and char b from scoring matrix
        int indexOfA = 0;
        int indexOfB = 0;
        for(int i = 0; i < score_matrix[0].length; i++){
            if(score_matrix[0][i] == B){
                indexOfB = i;
            }
            if(score_matrix[0][i] == A){
                indexOfA = i;
            }
        }
        return score_matrix[indexOfA][indexOfB]; // return the score according to the indexes of char A and B
    }

    public static int get_max(int ... integers){ // max operation(the count of elements doesnt matter
        int max = 0;
        for (int x: integers) {
            if(x > max){
                max = x;
            }

        }
        return max;

    }


}
