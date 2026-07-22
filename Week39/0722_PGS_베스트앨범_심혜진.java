import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> total = new HashMap<>();
        Map<String, List<int[]>> map = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            total.put(genres[i], total.getOrDefault(genres[i], 0) + plays[i]);
            map.computeIfAbsent(genres[i], k -> new ArrayList<>())
                    .add(new int[] { i, plays[i] });
        }

        List<String> genreList = new ArrayList<>(total.keySet());
        genreList.sort((a, b) -> total.get(b) - total.get(a));

        List<Integer> answer = new ArrayList<>();

        for (String genre : genreList) {
            List<int[]> songs = map.get(genre);

            songs.sort((a, b) -> {
                if (a[1] == b[1]) {
                    return a[0] - b[0];
                }
                return b[1] - a[1];
            });

            answer.add(songs.get(0)[0]);
            if (songs.size() > 1) {
                answer.add(songs.get(1)[0]);
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}