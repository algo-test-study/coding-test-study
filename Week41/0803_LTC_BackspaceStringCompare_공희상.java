class Solution {
    public boolean backspaceCompare(String s, String t) {
        int sIndex = s.length() - 1;
        int tIndex = t.length() - 1;

        while (sIndex >= 0 || tIndex >= 0) {
            sIndex = findPreviousValidIndex(s, sIndex);
            tIndex = findPreviousValidIndex(t, tIndex);

            if (sIndex < 0 || tIndex < 0) {
                return sIndex == tIndex;
            }

            if (s.charAt(sIndex) != t.charAt(tIndex)) {
                return false;
            }

            sIndex--;
            tIndex--;
        }

        return true;
    }

    private int findPreviousValidIndex(String string, int index) {
        int backspaceCount = 0;

        while (index >= 0) {
            char currentCharacter = string.charAt(index);

            if (currentCharacter == '#') {
                backspaceCount++;
                index--;
            } else if (backspaceCount > 0) {
                backspaceCount--;
                index--;
            } else {
                break;
            }
        }

        return index;
    }
}
