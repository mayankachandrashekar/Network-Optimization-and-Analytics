import java.util.*;

public class Search {

    private static final String[] START = {"1","1","1","1","1","1","1","2","2","2","2","2","2","3","3","3","3","3","4","4","4","4","5","5","5","6","6","7"};
    private static final String[] END ={"2","3","4","5","6","7","8","3","4","5","6","7","8","4","5","6","7","8","5","6","7","8","6","7","8","7","8","8"};
    int countj=1;
    int counti=8;
    static String[] results= new String[18];
    int pi=0;
    public static HashMap<String,String> hm=new HashMap<String,String>();
    public static void main(String[] args) {
        // this graph is directional
        Graph graph = new Graph();
        graph.addEdge("1", "2");
        hm.put("1 2","1");
        graph.addEdge("2", "1");
        hm.put("2 1","1");
        graph.addEdge("1", "6");
        hm.put("1 6","2");
        graph.addEdge("6", "1");
        hm.put("6 1","2");
        graph.addEdge("1", "8");
        hm.put("1 8","3");
        graph.addEdge("8", "1");
        hm.put("8 1","3");
        graph.addEdge("2", "3");
        hm.put("2 3","4");
        graph.addEdge("3", "2");
        hm.put("3 2","4");
        graph.addEdge("2", "7");
        hm.put("2 7","5");
        graph.addEdge("7", "2");
        hm.put("7 2","5");
        graph.addEdge("3", "4");
        hm.put("3 4","6");
        graph.addEdge("4", "3");
        hm.put("4 3","6");
        graph.addEdge("3", "8");
        hm.put("3 8","7");
        graph.addEdge("8", "3");
        hm.put("8 3","7");
        graph.addEdge("4", "5");
        hm.put("4 5","8");
        graph.addEdge("5", "4");
        hm.put("5 4","8");
        graph.addEdge("4", "7");
        hm.put("4 7","9");
        graph.addEdge("7", "4");
        hm.put("7 4","9");
        graph.addEdge("5", "6");
        hm.put("5 6","10");
        graph.addEdge("6", "5");
        hm.put("6 5","10");
        graph.addEdge("5", "8");
        hm.put("5 8","11");
        graph.addEdge("8", "5");
        hm.put("8 5","11");
        graph.addEdge("6", "7");
        hm.put("6 7","12");
        graph.addEdge("7", "6");
        hm.put("7 6","12");
        for(int k=0;k<START.length;k++) {
            Arrays.fill(results," ");
            LinkedList<String> visited = new LinkedList();
            visited.add(START[k]);
            new Search().breadthFirst(graph, visited,END[k]);
            Collections.sort(Arrays.asList(results), new comp());
            int cj=0;
            for(int j=0;cj<10;j++) {
                if (results[j].equals(" "))
                {

                }
                else {
                    System.out.print("set Routes[" + (k + 1) + "," + (cj + 1) + "] := ");
                    System.out.println(results[j] + " ;");
                    cj++;
                }
            }
        }
    }

    private void breadthFirst(Graph graph, LinkedList<String> visited,String end) {
        LinkedList<String> nodes = graph.adjacentNodes(visited.getLast());
        // examine adjacent nodes
        for (String node : nodes) {
            if (visited.contains(node)) {
                continue;
            }
            if (node.equals(end)) {
                visited.add(node);
                printPath(visited);
                visited.removeLast();
                break;
            }
        }
        // in breadth-first, recursion needs to come after visiting adjacent nodes
        for (String node : nodes) {
            if (visited.contains(node) || node.equals(end)) {
                continue;
            }
            visited.addLast(node);
            breadthFirst(graph, visited,end);
            visited.removeLast();
        }
    }

    private void printPath(LinkedList<String> visited) {
        String str="";
        for (String node : visited) {
//            System.out.print(node);
//            System.out.print(" ");
               str+=node+" ";
        }

        char[] s=str.toCharArray();
        String ss="";
        for (int i=0;i+2<str.length();i=i+2)
        {
           String key= String.valueOf(s[i])+String.valueOf(s[i+1])+String.valueOf(s[i+2]);
          //  System.out.print(hm.get(key) + " ");
            ss+=hm.get(key) + " ";
        }
        results[pi]=ss;
        pi++;
        //System.out.println(";");
        countj++;
    }
}

class comp implements Comparator<String> {
    public int compare(String o1, String o2) {
        return Integer.compare(o1.length(), o2.length());
    }
}