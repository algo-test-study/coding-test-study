/*
알고리즘
- k번째 사람 삭제 처리
- visited로 방문 체크: O(N*K)

시간복잡도
- O(N*K)
*/

import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));     
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int num = 0;
        int cnt = 0;
        List<Integer> ans = new ArrayList<>();
        boolean [] visited = new boolean [n];


        while(ans.size() < n){
            
            if(!visited[num]){
                cnt++;
                if(cnt == k){  
                    visited[num] = true;
                    ans.add(num+1);
                    cnt = 0;
                }
            }
            num++;
            num %= n;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");

        for(int i=0; i<n; i++){
            if(i == n-1) sb.append(ans.get(i)).append(">");
            else sb.append(ans.get(i)).append(", ");
        }

        System.out.println(sb.toString());
    }
}
