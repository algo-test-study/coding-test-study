import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] count = new int[d + 1];

        int kind = 0;

        for (int i = 0; i < k; i++) {
            if (count[arr[i]] == 0) kind++;
            
            count[arr[i]]++;
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            int cur = kind;
            
            if (count[c] == 0) cur++;

            answer = Math.max(answer, cur);

            int remove = arr[i];
            count[remove]--;
            if (count[remove] == 0) kind--;

            int add = arr[(i + k) % n];
            if (count[add] == 0) kind++;
            
            count[add]++;
        }

        System.out.print(answer);
    }
}
