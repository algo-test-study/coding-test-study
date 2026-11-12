class Solution {
    public String[] solution(String[] expressions) {
        boolean[] possible = new boolean[10];

        for (int base = 2; base <= 9; base++) {
            possible[base] = true;
        }

        for (String expression : expressions) {
            String[] parts = expression.split(" ");

            String first = parts[0];
            String operator = parts[1];
            String second = parts[2];
            String result = parts[4];

            for (int base = 2; base <= 9; base++) {
                if (!possible[base]) {
                    continue;
                }

                boolean valid = true;

                for (int i = 0; i < first.length(); i++) {
                    if (first.charAt(i) - '0' >= base) {
                        valid = false;
                    }
                }

                for (int i = 0; i < second.length(); i++) {
                    if (second.charAt(i) - '0' >= base) {
                        valid = false;
                    }
                }

                if (!result.equals("X")) {
                    for (int i = 0; i < result.length(); i++) {
                        if (result.charAt(i) - '0' >= base) {
                            valid = false;
                        }
                    }
                }

                if (!valid) {
                    possible[base] = false;
                    continue;
                }

                if (!result.equals("X")) {
                    int a = Integer.parseInt(first, base);
                    int b = Integer.parseInt(second, base);
                    int c = Integer.parseInt(result, base);

                    int calculated = operator.equals("+") ? a + b : a - b;

                    if (calculated != c) {
                        possible[base] = false;
                    }
                }
            }
        }

        String[] answer = new String[expressions.length];
        int index = 0;

        for (String expression : expressions) {
            String[] parts = expression.split(" ");

            String first = parts[0];
            String operator = parts[1];
            String second = parts[2];
            String result = parts[4];

            if (!result.equals("X")) {
                continue;
            }

            String calculatedResult = null;
            boolean same = true;

            for (int base = 2; base <= 9; base++) {
                if (!possible[base]) {
                    continue;
                }

                int a = Integer.parseInt(first, base);
                int b = Integer.parseInt(second, base);

                int calculated = operator.equals("+") ? a + b : a - b;

                String converted = Integer.toString(calculated, base);

                if (calculatedResult == null) {
                    calculatedResult = converted;
                } else if (!calculatedResult.equals(converted)) {
                    same = false;
                }
            }

            answer[index++] = first + " " + operator + " " + second
                    + " = " + (same ? calculatedResult : "?");
        }

        return java.util.Arrays.copyOf(answer, index);
    }
}
