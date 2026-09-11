function solution(numbers, target) {
  let answer = 0;

  function dfs(candidateTarget, currentIndex) {
    if (currentIndex === numbers.length) {
      if (candidateTarget === target) {
        answer++;
      }
      return;
    }

    dfs(candidateTarget + numbers[currentIndex], currentIndex + 1);
    dfs(candidateTarget - numbers[currentIndex], currentIndex + 1);

  }

  dfs(0, 0);
  return answer;
}
/*
//완전탐색 DFS
function solution(numbers, target) {
  let answer = 0;
    
  function dfs(currentIndex, val){
      if(currentIndex === numbers.length){
          if(val === target) answer++;
          return;
      }
      
      //두 갈래로 이진트리를 DFS로 돌기때문에 시간복잡도 O(2^n)
      dfs(currentIndex+1, val+numbers[currentIndex])
      dfs(currentIndex+1, val+-numbers[currentIndex]) 
  }

  dfs(0,0);
  return answer;
}

*/