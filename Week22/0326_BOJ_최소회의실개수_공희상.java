import java.io.*;
import java.util.*;

public class Main {
    static class Meet implements Comparable<Meet> {
        int startTime;
        int endTime;
        
        public Meet(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
        
        @Override
        public int compareTo(Meet other) {
            return this.startTime - other.startTime;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        List<Meet> meets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            
            Meet meet = new Meet(startTime, endTime);
            meets.add(meet);
        }
        
        Collections.sort(meets);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            Meet meet = meets.get(i);
            int startTime = meet.startTime;
            int endTime = meet.endTime;
            
            if (!pq.isEmpty() && pq.peek() <= startTime) {
                pq.poll();
            }
            
            pq.offer(endTime);
        }
        
        System.out.println(pq.size());
    }
}
