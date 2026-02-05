import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        List<FileInfo> list = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            list.add(parse(files[i], i));
        }

        Collections.sort(list, (a, b) -> {
            int headCompare = a.head.toLowerCase().compareTo(b.head.toLowerCase());
            if (headCompare != 0) return headCompare;

            int numCompare = Integer.compare(a.number, b.number);
            if (numCompare != 0) return numCompare;

            return Integer.compare(a.index, b.index);
        });

        String[] answer = new String[files.length];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i).original;
        }

        return answer;
    }

    private FileInfo parse(String file, int index) {
        int i = 0;
        while (i < file.length() && !Character.isDigit(file.charAt(i))) i++;

        int j = i;
        while (j < file.length() && Character.isDigit(file.charAt(j)) && j - i < 5) j++;

        String head = file.substring(0, i);
        int number = Integer.parseInt(file.substring(i, j));

        return new FileInfo(head, number, index, file);
    }

    static class FileInfo {
        String head;
        int number;
        int index;
        String original;

        FileInfo(String head, int number, int index, String original) {
            this.head = head;
            this.number = number;
            this.index = index;
            this.original = original;
        }
    }
}
