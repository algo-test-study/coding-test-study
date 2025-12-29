function solution(maps) {
    const move = [[-1, 0], [0, -1], [0,1], [1,0]];//상방향은 row(행)인덱스가 -1, 좌방향은 column(열)인덱스가 -1
                 // 상      //좌      //우   //하
    const n = maps.length;//x축 길이 5
    const m = maps[0].length;//y축 길이 5

    const dist = Array.from({length: n}, ()=>Array(m).fill(-1));

    function bfs(start){
        //큐를 만든다
        const q = new Queue();
        //큐에 시작 노드를 넣는다.
        q.push(start);
        // 시작노드가 방문한 곳에 방문 처리
        dist[start[0]][start[1]] = 1;

        while(!q.isEmpty()){
            const here = q.pop();

            for(const [dx, dy] of move){//상하좌우가 정의된 배열
                //행
                const row = here[0] + dx; // 0 // 1
                //열
                const column = here[1] + dy; // 0 // 0

                // 범위를 벗어나는지 체크
                if(row < 0 || row >= n || column < 0 || column >= m){
                    continue;
                }

                // 0은 벽이므로 패스
                if(maps[row][column] === 0){
                    continue;
                }

                // 방문한게 아니라면 방문 처리
                if(dist[row][column] === -1 ){
                    q.push([row, column]);
                    dist[row][column] = dist[here[0]][here[1]]+1;
                }
            }
        }
        return dist;
    }

    bfs([0,0]);

    return dist[n-1][m-1];

console.log(solution([[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,1],[0,0,0,0,1]]));