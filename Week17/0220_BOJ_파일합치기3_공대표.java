import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            PriorityQueue<Long> pq = new PriorityQueue<>();

            for (int i = 0; i < K; i++) {
                pq.offer(Long.parseLong(st.nextToken()));
            }

            long total = 0;

            while (pq.size() > 1) {
                long a = pq.poll();
                long b = pq.poll();
                long sum = a + b;
                total += sum;
                pq.offer(sum);
            }

            sb.append(total).append("\n");
        }

        System.out.print(sb);
    }
}
