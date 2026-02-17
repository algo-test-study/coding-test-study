import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> maxHeap = new PriorityQueue(Collections.reverseOrder());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();
        
        while (n --> 0) {
            int x = Integer.parseInt(br.readLine());
            
            if (x == 0) {
                if (maxHeap.isEmpty()) {
                    sb.append(0).append("\n");
                } else {
                    sb.append(maxHeap.poll()).append("\n");
                }
            } else {
                maxHeap.add(x);
            }
        }
        
        System.out.print(sb);
    }
}
