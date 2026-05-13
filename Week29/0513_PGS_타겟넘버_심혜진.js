function solution(numbers, target) {
  let answer = 0;

  function dfs(candidateTarget, currentIndex){
      if(currentIndex === numbers.length){
        if(candidateTarget === target){
          answer++;
        }
        return;
      }

      dfs(candidateTarget+numbers[currentIndex], currentIndex+1);
      dfs(candidateTarget-numbers[currentIndex], currentIndex+1);

  }

  dfs(0, 0);
  return answer;
}
