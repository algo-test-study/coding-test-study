function dfs(visited, computers, node) {
  visited[node] = true;
  for (let j = 0; j < computers[node].length; j++) {
    if (computers[node][j] && !visited[j]) {
      dfs(visited, computers, j);
    }
  }
}

function solution(n, computers) {
  let answer = 0;

  let visited = Array(n).fill(false);

  for (let i = 0; i < n; i++) {
    if (!visited[i]) {
      dfs(visited, computers, i);
      answer++;
    }
  }
  return answer;
}
/*
//모든 경로를 탐색하고 한 갈래 길에서 깊게 탐색하는 전형적인 DFS
//O(n^2) 또는 O(N+E) 노드수*간선수
//제약조건은 노드가 200 이하로, 정방행렬로 조건이 주어질 것이므로 O(n^2) 면 10^4

function dfs(visited, computers, node){
    visited[node] = true;
    for(let i=0; i<computers[node].length; i++){
        if(computers[node][i] && !visited[i]){
             dfs(visited, computers, i);
        }
    }
}

function solution(n, computers) {
    let answer = 0;
    
    let visited = Array(n).fill(false);
    
    for(let i=0; i<n; i++){
        if(!visited[i]){
            dfs(visited, computers, i);
            answer++;
        }   
    }
    
    return answer;
}
*/