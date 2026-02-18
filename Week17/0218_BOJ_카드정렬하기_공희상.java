import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        PriorityQueue<Integer> cardBundles = new PriorityQueue();
        while (n --> 0) {
            cardBundles.add(Integer.parseInt(br.readLine()));
        }
        
        long count = 0;
        while (cardBundles.size() > 1) {
            int a = cardBundles.poll();
            int b = cardBundles.poll();

            int sum = a + b;
            count += sum;
            cardBundles.add(sum);
        }
        
        System.out.print(count);
    }
}
