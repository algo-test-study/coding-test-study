class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int n = a.length;
        int[] f = new int[n];

        for (int x : a) {
            f[x]++;
        }

        for(int i = 0; i < n; i++) {
            if (f[i] * 2 <= answer) continue;

            int cnt = 0;
            
            int j = 0;
            while(j < n - 1){
                if (a[j] == a[j + 1] || (a[j] != i && a[j + 1] != i)) {
                    j++;
                    continue;
                }
                cnt++;
                j += 2;
            }

            answer = Math.max(answer, cnt * 2);
        }

        return answer;
    }
}
