import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;

        Arrays.sort(routes, (o1, o2) -> o1[0] - o2[0]);

        int x = routes[0][1]; // 처음 차 빠져나갔을 때 위치 할당, 카메라 + 1
        /* x는 마지막으로 설치한 카메라의 위치 */
       for (int i = 1; i < routes.length; i++) {
            if (routes[i][1] < x) {
                x = routes[i][1];
            }
            if (x < routes[i][0]) {
                x = routes[i][1];
                answer++; /* 현재 카메라 위치보다 더 나중에 진입이면 카메라 위치 다시 설정 후 개수 증가 */
            }
        }
        return answer;
    }
}
