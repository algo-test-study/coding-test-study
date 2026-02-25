import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static int[] parent;

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        parent[b] = a;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[E];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(a, b, c);
        }

        Arrays.sort(edges);

        parent = new int[V + 1];

        for (int i = 1; i <= V; i++) {
          parent[i] = i;
        }

        long sum = 0;
        int cnt = 0;

        for (Edge e : edges) {
            if (union(e.u, e.v)) {
                sum += e.w;
                cnt++;

                if (cnt == V - 1) break;
            }
        }

        System.out.println(sum);
    }
}
