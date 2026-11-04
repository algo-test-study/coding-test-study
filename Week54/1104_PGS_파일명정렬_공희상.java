import java.util.ArrayList;
import java.util.List;

class Solution {

    static class File {
        String originalFileName;
        String head;
        int number;

        public File(String originalFileName, String head, int number) {
            this.originalFileName = originalFileName;
            this.head = head;
            this.number = number;
        }
    }

    public String[] solution(String[] files) {

        List<File> parsedFiles = new ArrayList<>();

        for (String fileName : files) {

            int currentIndex = 0;

            while (currentIndex < fileName.length()
                    && !Character.isDigit(fileName.charAt(currentIndex))) {
                currentIndex++;
            }

            String head = fileName.substring(0, currentIndex);

            int numberStartIndex = currentIndex;

            while (currentIndex < fileName.length()
                    && Character.isDigit(fileName.charAt(currentIndex))
                    && currentIndex - numberStartIndex < 5) {
                currentIndex++;
            }

            int number = Integer.parseInt(
                    fileName.substring(numberStartIndex, currentIndex)
            );

            parsedFiles.add(
                    new File(fileName, head, number)
            );
        }

        parsedFiles.sort((firstFile, secondFile) -> {

            int headCompareResult =
                    firstFile.head.toLowerCase()
                            .compareTo(secondFile.head.toLowerCase());

            if (headCompareResult != 0) {
                return headCompareResult;
            }

            return Integer.compare(
                    firstFile.number,
                    secondFile.number
            );
        });

        String[] sortedFiles = new String[files.length];

        for (int index = 0; index < parsedFiles.size(); index++) {
            sortedFiles[index] =
                    parsedFiles.get(index).originalFileName;
        }

        return sortedFiles;
    }
}
