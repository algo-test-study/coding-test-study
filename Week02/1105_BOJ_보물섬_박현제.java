/*
- 두 곳을 최단거리로 이동
- 두 육지 사이의 최단 거리

알고리즘
- 모든 육지에서 최단거리 구하기 > BFS
- 그중 최댓값

시간복잡도
- O(nm*nm)
*/

import java.util.*;
import java.io.*;
public class Main {

    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,1,-1};
    static int n;
    static int m;
    static String [][] map;
    static int ans = 0;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));     
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new String[n][m];
        for(int i=0; i<n; i++){
            map[i] = br.readLine().split("");
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j].equals("W")) continue;

                bfs(i, j);
            }
        }

        System.out.println(ans);
    }


    static void bfs(int sx, int sy){

        Queue<int []> q = new LinkedList<>();

        q.offer(new int [] {sx, sy, 0});

        boolean [][] visited = new boolean[n][m];

        visited[sx][sy] = true;

        int max = 0;

        while(!q.isEmpty()){
            int [] curr = q.poll();
            int cx = curr[0];
            int cy = curr[1];
            int cnt = curr[2];

            max = Math.max(cnt, max);

            for(int i=0; i<4; i++){

                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx >= n || ny >= m || nx < 0 || ny < 0) continue;
                if(visited[nx][ny] || map[nx][ny].equals("W")) continue;

                visited[nx][ny] = true;
                q.offer(new int [] {nx, ny, cnt+1});
            }
        }

        ans = Math.max(ans, max);
    }
}
