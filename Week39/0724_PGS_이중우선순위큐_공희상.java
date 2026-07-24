import java.util.TreeMap;

class Solution {

    public int[] solution(String[] operations) {
        TreeMap<Integer, Integer> numberCountMap = new TreeMap<>();

        for (String operation : operations) {
            String[] command = operation.split(" ");

            String operationType = command[0];
            int number = Integer.parseInt(command[1]);

            if (operationType.equals("I")) {
                numberCountMap.put(
                    number,
                    numberCountMap.getOrDefault(number, 0) + 1
                );
                continue;
            }


            if (numberCountMap.isEmpty()) {
                continue;
            }

            int targetNumber;

            if (number == 1) {
                targetNumber = numberCountMap.lastKey();
            } else {
                targetNumber = numberCountMap.firstKey();
            }

            removeOne(numberCountMap, targetNumber);
        }

        if (numberCountMap.isEmpty()) {
            return new int[]{0, 0};
        }

        return new int[]{
            numberCountMap.lastKey(),
            numberCountMap.firstKey()
        };
    }

    private void removeOne(
        TreeMap<Integer, Integer> numberCountMap,
        int targetNumber
    ) {
        int count = numberCountMap.get(targetNumber);

        if (count == 1) {
            numberCountMap.remove(targetNumber);
            return;
        }

        numberCountMap.put(targetNumber, count - 1);
    }
}
