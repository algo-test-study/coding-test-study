import java.util.*;

class Solution {

    public String[] solution(String[] expressions) {

        String[][] parsed = new String[expressions.length][];
        for (int i = 0; i < expressions.length; i++) {
            parsed[i] = expressions[i].split(" ");
        }

        List<Integer> validBases = new ArrayList<>();

        for (int base = 2; base <= 9; base++) {
            if (isValidBase(parsed, base)) {
                validBases.add(base);
            }
        }

        List<String> answer = new ArrayList<>();

        for (String[] parts : parsed) {

            if (!parts[4].equals("X")) {
                continue;
            }

            String candidate = null;
            boolean same = true;

            for (int base : validBases) {

                int value = calculate(parts, base);
                String result = Integer.toString(value, base);

                // 진법에 따라 결과가 달라진다면
                if (candidate != null && !candidate.equals(result)) {
                    same = false;
                    break;
                }

                candidate = result;
            }

            String result = same ? candidate : "?";

            answer.add(
                parts[0] + " " + parts[1] + " "
                + parts[2] + " = " + result
            );
        }

        return answer.toArray(new String[0]);
    }

    private boolean isValidBase(String[][] parsed, int base) {

        for (String[] parts : parsed) {

            if (!validDigits(parts[0], base)
                    || !validDigits(parts[2], base)) {
                return false;
            }

            if (parts[4].equals("X")) {
                continue;
            }

            if (!validDigits(parts[4], base)) {
                return false;
            }

            if (calculate(parts, base)
                    != Integer.parseInt(parts[4], base)) {
                return false;
            }
        }

        return true;
    }

    private boolean validDigits(String number, int base) {

        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) - '0' >= base) {
                return false;
            }
        }

        return true;
    }

    private int calculate(String[] parts, int base) {

        int a = Integer.parseInt(parts[0], base);
        int b = Integer.parseInt(parts[2], base);

        return parts[1].equals("+") ? a + b : a - b;
    }
}
