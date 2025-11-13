/*
- 동시에 필요한 최소 회의실 개수

알고리즘
- 시작 시간으로 배열 정렬
- 시작 시간에 add. 끝 시간으로 정렬 게속함 > 우선순위 큐 사용
- 시작 시간 >= 큐 끝시간: 모두 큐에서 제거

시간복잡도
- O(nlogn)
*/

import java.util.*;
import java.io.*;
public class Main {
    
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));     
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        List<int []> times = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            times.add(new int [] {start, end});
        }

        Collections.sort(times, (a, b) -> a[0] - b[0]);


        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> a - b);

        int ans = 0;
        for(int i = 0; i < n; i++){
            int [] curr = times.get(i);
            int start = curr[0];
            int end = curr[1];

            while(!pq.isEmpty() && start >= pq.peek()){
                pq.poll();
            }

            pq.add(end);

            ans = Math.max(ans, pq.size());
        }


        System.out.println(ans);
    }
}
