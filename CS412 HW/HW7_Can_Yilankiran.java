import java.util.ArrayList;
import java.util.Arrays;

public class HW7_Can_Yilankiran {
    public static ArrayList<ArrayList<Integer>> matrix1 = new ArrayList<>();
    public static ArrayList<Integer> row1 = new ArrayList<>(Arrays.asList(0, 13, 21, 22));
    public static ArrayList<Integer> row2 = new ArrayList<>(Arrays.asList(13, 0, 12, 13));
    public static ArrayList<Integer> row3 = new ArrayList<>(Arrays.asList(21, 12, 0, 13));
    public static ArrayList<Integer> row4 = new ArrayList<>(Arrays.asList(22, 13, 13, 0));

    public static void main(String[] args) {
        matrix1.add(row1);
        matrix1.add(row2);
        matrix1.add(row3);
        matrix1.add(row4);
        System.out.println("Answer of question 1: " + LimbLength(1, matrix1));

//        System.out.println(AdditivePhylogeny(4,matrix1));
    }

    public static int LimbLength(int j, ArrayList<ArrayList<Integer>> matrix) {
        int result = 2147483647;
        for (int i = 0; i < matrix.size(); i++) {
            for (int x = 0; x < matrix.get(i).size(); x++) {
                if (j != x && i != x && i != j) {
                    int length = (matrix.get(i).get(j) + matrix.get(j).get(x) - matrix.get(i).get(x)) / 2;
                    result = get_min(length, result);
                }

            }
        }
        return result;
    }

    public static int get_min(int... integers) { // min operation(the count of elements doesnt matter
        int min = 2147483647;
        for (int x : integers) {
            if (x < min) {
                min = x;
            }

        }
        return min;

    }

    public static ArrayList<Integer> AdditivePhylogeny(int n, ArrayList<ArrayList<Integer>> matrix) {
        ArrayList<Integer> Tree = new ArrayList<>();
        if (n == 2) {
            int distance = matrix.get(0).get(1);
            Tree.add(0);
            Tree.add(1);
            Tree.add(distance);
        }
        int limblength = LimbLength(n - 1, matrix);
        ArrayList<ArrayList<Integer>> new_matrix = matrix;
        for (int i = 0; i < n; i++) {
            new_matrix.get(n - 1).set(i, new_matrix.get(n - 1).get(i) - limblength);
            new_matrix.get(i).set(n - 1, new_matrix.get(n - 1).get(i));
        }
        ArrayList<Integer> ink = findINK(n, new_matrix);
        int i = ink.get(0);
        int k = ink.get(1);
        int nn = ink.get(2);
        int x = new_matrix.get(i).get(nn);

        new_matrix.remove(nn);
        for(int c = 0; c < new_matrix.size(); c++){
            new_matrix.get(c).remove(nn);
        }
        Tree = AdditivePhylogeny(n-1,new_matrix);
        return Tree;
    }

    public static ArrayList<Integer> findINK(int n, ArrayList<ArrayList<Integer>> matrix) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            for (int x = 0; x < matrix.get(i).size(); x++) {
                if (matrix.get(i).get(x) == matrix.get(i).get(n - 1) + matrix.get(n - 1).get(x)) {
                    result.add(i);
                    result.add(x);
                    result.add(n - 1);
                    return result;
                }
            }
        }
        return result;
    }
}