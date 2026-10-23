class Solution {        
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int start = h1 * 3600 + m1 * 60 + s1;
        
        int end = h2 * 3600 + m2 * 60 + s2;

        int cnt = 0;
        
        if (start == 0 || start == 43200) { 
            cnt++;
        }

        for (int t = start; t < end; t++) {

            double s = (t * 6.0) % 360;
            double m = (t * 0.1) % 360;
            double h = (t / 120.0) % 360;

            double nextS = ((t + 1) * 6.0) % 360;
            double nextM = ((t + 1) * 0.1) % 360;
            double nextH = ((t + 1) / 120.0) % 360;

            if (nextS == 0) nextS = 360;
            if (nextM == 0) nextM = 360;
            if (nextH == 0) nextH = 360;

            boolean sm = s < m && nextS >= nextM;

            boolean sh = s < h && nextS >= nextH;

            if (sm) cnt++;
            if (sh) cnt++;

            if (t + 1 == 43200 && sm && sh) {
                cnt--;
            }
        }

        return cnt;
    }
}
