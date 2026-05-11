//O(N!), 최대 던전 수가 8로 효율성 문제 X
function solution(k, dungeons) {
    let answer = 0;

    let visited = Array(dungeons.length).fill(false);

    function dfs(curK, curD, visited) {
        answer = Math.max(answer, curD);
        for (let i = 0; i < dungeons.length; i++) {
            if (!visited[i] && curK >= dungeons[i][0]) {
                //불변값 인자로 넘기기
                let futureK = curK - dungeons[i][1];
                let futureD = curD + 1;
                visited[i] = true;
                dfs(futureK, futureD, visited);
                visited[i] = false;
            }
        }
    }
    dfs(k, 0, visited);

    return answer;
}
console.log(solution(80, [[80, 20], [50, 40], [30, 10]]));
