import java.util.Arrays;

class Solution {
    public int[][] kClosest(int[][] points, int k) {
        Arrays.sort(points, (firstPoint, secondPoint) -> {
            int firstDistance =
                    firstPoint[0] * firstPoint[0]
                    + firstPoint[1] * firstPoint[1];

            int secondDistance =
                    secondPoint[0] * secondPoint[0]
                    + secondPoint[1] * secondPoint[1];

            return Integer.compare(firstDistance, secondDistance);
        });

        return Arrays.copyOfRange(points, 0, k);
    }
}
