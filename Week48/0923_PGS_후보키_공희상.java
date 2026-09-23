import java.util.*;

class Solution {

    public int solution(String[][] relation) {
        int columnCount = relation[0].length;
        List<Integer> candidateKeys = new ArrayList<>();

        for (int mask = 1; mask < (1 << columnCount); mask++) {
            if (!isMinimal(mask, candidateKeys)) {
                continue;
            }

            if (isUnique(mask, relation)) {
                candidateKeys.add(mask);
            }
        }

        return candidateKeys.size();
    }

    private boolean isMinimal(int mask, List<Integer> candidateKeys) {
        for (int key : candidateKeys) {
            if ((mask & key) == key) {
                return false;
            }
        }
        return true;
    }

    private boolean isUnique(int mask, String[][] relation) {
        Set<String> set = new HashSet<>();

        for (String[] row : relation) {
            StringBuilder key = new StringBuilder();

            for (int col = 0; col < row.length; col++) {
                if ((mask & (1 << col)) != 0) {
                    key.append(row[col]).append('|');
                }
            }

            if (!set.add(key.toString())) {
                return false;
            }
        }

        return true;
    }
}
