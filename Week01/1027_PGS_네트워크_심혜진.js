//https://school.programmers.co.kr/learn/courses/30/lessons/43162?language=javascript
function dfs(visited, computers, node){
  visited[node] = true;
  for(let j=0; j<computers[node].length; j++){
      if(computers[node][j]&&!visited[j]){
          dfs(visited, computers,j);
      }
  }
}

function solution(n, computers) {
  var answer = 0;

  let visited = Array(n).fill(false);

  for(let i=0; i<n; i++){
      if(!visited[i]){
          dfs(visited, computers, i);
          answer++;
      }
  }
  return answer;
}