
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class hw3 {
    public static void main (String[] args){
        //question 1
        System.out.println("Answer of question 1:");
        ArrayList<String> can = stringComposiiton("CAATCCAAC",5);
        for (String x :can) {
            System.out.print(x + " ");
        }
        System.out.println();
        //question 2
        ArrayList<String> q2 = new ArrayList<>(Arrays.asList("ATGCG","GCATG","CATGC","AGGCA","GGCAT"));
        System.out.println("Answer of question 2:");
        ArrayList<ArrayList<String>> q2answer = overlapGraph(q2);
        printGraph(q2answer);

        //question 3
        ArrayList<String> q3 = new ArrayList<>(Arrays.asList("GAGG","CAGG","GGGG","GGGA","CAGG","AGGG","GGAG"));
        System.out.println("Answer of question 3:");
        ArrayList<ArrayList<String>> q3answer = DeBruijnGraph(q3);
        printGraph(q3answer);

        //question 4
        System.out.println("\nAnswer of question 4:");
        System.out.println(EulerianPath(DeBruijnGraph(q3)));
    }
    public static ArrayList<String> stringComposiiton(String genome, int k){
        ArrayList<String> result = new ArrayList<>();
        result = kmers(genome,k);
        Collections.sort(result);
//        for( int i = 0 ; i < result.size(); i++){
//            for(int j = i+1; j < result.size(); j++){
//                if(lexo(result.get(i) > lexo(result.get(j)))){
//                    String tmp = result.get(i);
//                    resul;
//
//                }
//            }
//        }

        return result;
    }
    public static ArrayList<String> kmers(String str, int k) {
        ArrayList<String> possible_kmers = new ArrayList<>();
        for (int i = 0; i < str.length() - k + 1; i++) {
            String x = str.substring(i, k + i);
            possible_kmers.add(x);
        }
        return possible_kmers;
    }
    public static ArrayList<ArrayList<String>> overlapGraph(ArrayList<String> genomes){
        int k = genomes.get(1).length();
        ArrayList<ArrayList<String>> graph = new ArrayList<>();
        for( int i =0 ; i < genomes.size(); i++){
            ArrayList<String> x = new ArrayList<>();
            x.add(genomes.get(i));
            graph.add(x);
            for (int j = 0; j < genomes.size(); j++){
                if(suffix(genomes.get(i),k).equals(prefix((genomes.get(j)),k))){
                    constructEdge(graph,i,genomes.get(j));
                }
            }
        }
        return graph;

    }
    public static ArrayList<ArrayList<String>> DeBruijnGraph(ArrayList<String> genomes){
        int k = genomes.get(1).length();
        ArrayList<ArrayList<String>> graph = new ArrayList<>();
        int counter = 0;
        for( int i =0 ; i < genomes.size(); i++) {
            ArrayList<String> x = new ArrayList<>();
            String headOfKmer = prefix(genomes.get(i),k);
            x.add(headOfKmer);
            //System.out.println(x);
            if (graphContains(graph,headOfKmer)) {
                //System.out.println("containledi");
                int index = getIndexOf(graph,headOfKmer);
                //System.out.println(index);
                constructEdge(graph,index,suffix(genomes.get(i),k));
                counter--;
            }else{
                graph.add(x);
                constructEdge(graph, counter, suffix(genomes.get(i), k));

            }
            counter++;
        }

        return graph;

    }

    public static  ArrayList<String> EulerianPath(ArrayList<ArrayList<String>> graph) {
        ArrayList<String> eulerianPath = new ArrayList<>();
        //sorted according to their degree
        Collections.sort(graph, Comparator.comparingInt(ArrayList::size));
        Collections.reverse(graph);
        String next = "";
        String current = graph.get(0).get(0);
        ArrayList<String> neighbors;
        while (!current.equals("")) {
            eulerianPath.add(current);
            neighbors = getOneNeighbor(graph, current);
            if (neighbors.size() != 0) {
                next = neighbors.get(0);
                current = next;
            } else {
                break;
            }

        }
        return eulerianPath;
    }
    public static ArrayList<String> getOneNeighbor(ArrayList<ArrayList<String>> graph,String current) {
    ArrayList<String> result = new ArrayList<>();
    int index = getIndexOf(graph,current);
    for(int i = 1 ; i <graph.get(index).size();i++){
        if(!graph.get(index).get(i).equals("")){
            result.add(graph.get(index).get(i));
            graph.get(index).set(i,"");
            break;
        }
    }


    return result;
}


    public static String suffix(String genome,int k){
        return genome.substring(k-(k-1));
    }
    public static String prefix(String genome,int k) { return genome.substring(0,k-1); }

    public static void constructEdge (ArrayList<ArrayList<String>> graph, int startGenomeIndex,  String endGenome){ graph.get(startGenomeIndex).add(endGenome); }

    public static boolean graphContains(ArrayList<ArrayList<String>> graph, String kmer){
        for (ArrayList<String> list:graph) {
            if(list.get(0).equals(kmer)){
                return true;
            }

        }
        return false;
    }
    public static int getIndexOf(ArrayList<ArrayList<String>> graph,String kmer) {
        int count = 0;
        for (ArrayList<String> list : graph) {
            if (list.get(0).equals(kmer)) {
                break;
            } else {
                count++;
            }
        }
        return count;
    }

    private static void printGraph(ArrayList<ArrayList<String>> graph)
    {
        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.get(i).size(); j++) {
                if(graph.get(i).size() < 2){
                    break;
                }
                else if (graph.get(i).size() < 3) {
                    if (j > 0) {
                        System.out.print(" -> " + graph.get(i).get(j));
                    } else {
                        System.out.print(graph.get(i).get(j));
                    }
                }else{
                    if (j > 0 && j<graph.get(i).size()-1) {
                        System.out.print(graph.get(i).get(j)+ ",");
                    }else if (j == graph.get(i).size()-1){
                        System.out.print(graph.get(i).get(j));
                    }else {
                        System.out.print(graph.get(i).get(j)+" -> ");
                    }
                }
            }
            System.out.println();
        }
    }
}

