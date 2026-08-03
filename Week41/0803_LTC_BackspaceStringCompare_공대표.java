class Solution {
    public boolean backspaceCompare(String s, String t) {
        int sIndex = s.length() - 1;
        int tIndex = t.length() - 1;

        while (sIndex >= 0 || tIndex >= 0) {
            int sSkip = 0;

            while (sIndex >= 0) {
                if (s.charAt(sIndex) == '#') {
                    sSkip++;
                    sIndex--;
                } else if (sSkip > 0) {
                    sSkip--;
                    sIndex--;
                } else {
                    break;
                }
            }

            int tSkip = 0;

            while (tIndex >= 0) {
                if (t.charAt(tIndex) == '#') {
                    tSkip++;
                    tIndex--;
                } else if (tSkip > 0) {
                    tSkip--;
                    tIndex--;
                } else {
                    break;
                }
            }

            if (sIndex < 0 && tIndex < 0) {
                return true;
            }

            if (sIndex < 0 || tIndex < 0) {
                return false;
            }

            if (s.charAt(sIndex) != t.charAt(tIndex)) {
                return false;
            }

            sIndex--;
            tIndex--;
        }

        return true;
    }
}
