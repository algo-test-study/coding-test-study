import java.util.*;
class Solution {
    public List<String> solution(String[] expressions){
        List<String> ans = new ArrayList<>();
        List<Integer> bases = new ArrayList<>();

        int minBase = 2;

        for (String expression : expressions) {
            for (char c : expression.toCharArray()) {
                if (c >= '0' && c <= '9') minBase = Math.max(minBase, c - '0' + 1);
            }
        }

        for (int base = minBase; base <= 9; base++) {
            boolean isValid = true;

            for (String expression : expressions) {
                String[] parts = expression.split(" ");

                String a = parts[0];
                String oper = parts[1];
                String b = parts[2];
                String c = parts[4];

                if (c.equals("X")) continue;

                int numA = Integer.parseInt(a, base);
                int numB = Integer.parseInt(b, base);
                int numC = Integer.parseInt(c, base);

                int result = oper.equals("+") ? numA + numB : numA - numB;

                if (result != numC) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                bases.add(base);
            }
        }

        for (String expression : expressions) {
            String[] parts = expression.split(" ");

            if (!parts[4].equals("X")) continue;

            String exp = null;
            boolean isSame = true;

            for (int base : bases) {
                int a = Integer.parseInt(parts[0], base);
                int b = Integer.parseInt(parts[2], base);

                int result = parts[1].equals("+") ? a + b : a - b;

                String strResult = Integer.toString(result, base);

                if (exp == null) {
                    exp = strResult;
                } else if (!exp.equals(strResult)) {
                    isSame = false;
                    break;
                }
            }

            ans.add(expression.replace("X", isSame ? exp : "?"));
        }

        return ans;
    }
}
