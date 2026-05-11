import java.util.*;

class Solution {

    public int solution(int k, int[][] dungeons) {
        int answer = 0;

        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(k, 0, 0));

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            answer = Math.max(answer, cur.count);

            for (int i = 0; i < dungeons.length; i++) {

                if ((cur.visited & (1 << i)) != 0) {
                    continue;
                }

                int need = dungeons[i][0];
                int cost = dungeons[i][1];

                if (cur.fatigue >= need) {
                    queue.offer(new State(
                        cur.fatigue - cost,
                        cur.visited | (1 << i),
                        cur.count + 1
                    ));
                }
            }
        }

        return answer;
    }

    static class State {
        int fatigue;
        int visited;
        int count;

        State(int fatigue, int visited, int count) {
            this.fatigue = fatigue;
            this.visited = visited;
            this.count = count;
        }
    }
}
