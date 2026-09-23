import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        int answer = 0;
        
        int rowCount = relation.length;
        int columnCount = relation[0].length;

        List<Integer> candidateKeys = new ArrayList<>();

        for (int mask = 1; mask < (1 << columnCount); mask++) {
            boolean minimal = true;

            for (int key : candidateKeys) {
                if ((mask & key) == key) {
                    minimal = false;
                    break;
                }
            }

            if (!minimal) {
                continue;
            }

            Set<String> values = new HashSet<>();

            for (int row = 0; row < rowCount; row++) {
                StringBuilder value = new StringBuilder();

                for (int column = 0; column < columnCount; column++) {
                    if ((mask & (1 << column)) != 0) {
                        value.append(relation[row][column]).append(",");
                    }
                }

                values.add(value.toString());
            }

            if (values.size() == rowCount) {
                candidateKeys.add(mask);
                answer++;
            }
        }

        return answer;
    }
}
