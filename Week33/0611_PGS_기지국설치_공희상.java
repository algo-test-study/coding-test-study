class Solution {

    public int solution(int apartmentCount, int[] existingStations, int coverageRange) {

        int requiredStationCount = 0;

        int nextUncoveredApartment = 1;

        int coverageLength = coverageRange * 2 + 1;

        for (int stationPosition : existingStations) {

            int uncoveredEndApartment =
                    stationPosition - coverageRange - 1;

            if (nextUncoveredApartment <= uncoveredEndApartment) {

                int uncoveredLength =
                        uncoveredEndApartment - nextUncoveredApartment + 1;

                requiredStationCount += calculateRequiredStations(
                        uncoveredLength,
                        coverageLength
                );
            }

            nextUncoveredApartment =
                    stationPosition + coverageRange + 1;
        }

        if (nextUncoveredApartment <= apartmentCount) {

            int remainingLength =
                    apartmentCount - nextUncoveredApartment + 1;

            requiredStationCount += calculateRequiredStations(
                    remainingLength,
                    coverageLength
            );
        }

        return requiredStationCount;
    }

    private int calculateRequiredStations(
            int uncoveredLength,
            int coverageLength
    ) {
        return (uncoveredLength + coverageLength - 1)
                / coverageLength;
    }
}
