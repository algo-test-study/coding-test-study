import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};

        Map<String, Integer> genre = new HashMap<>(); // 장르별 총 재생횟수
        Map<String, HashMap<Integer, Integer>> music = new HashMap<>(); // 각 재생횟수
        for (int i = 0; i < genres.length; i++) {
            genre.put(genres[i], genre.getOrDefault(genres[i], 0) + plays[i]);

            HashMap<Integer, Integer> map = music.getOrDefault(genres[i], new HashMap<>());
            map.put(i, plays[i]);
            music.put(genres[i], map);
        }

        List<String> keys = new LinkedList<>(genre.keySet());
        Collections.sort(keys, ((o1, o2) -> genre.get(o2) - genre.get(o1)));

        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < keys.size(); i++) {
            HashMap<Integer, Integer> map = music.get(keys.get(i));

            List<Integer> list = new LinkedList<>(map.keySet());
            Collections.sort(list, ((o1, o2) -> map.get(o2) - map.get(o1)));

            for (int j = 0; j < 2; j++) {
                if (list.size() <= j) {
                    break;
                }

                arr.add(list.get(j));
            }
        }

        answer = arr.stream().mapToInt(Integer::intValue).toArray();

        return answer;
    }
}
