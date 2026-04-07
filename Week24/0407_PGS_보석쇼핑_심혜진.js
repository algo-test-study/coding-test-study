function solution(gems){
  const kindCount = new Set(gems).size;
  const counts = new Map();
  let answer = [0,gems.length-1];
  let left=0;

  for(let right=0;right<gems.length;right++){
    const gem = gems[right];
    counts.set(gem,(counts.get(gem)||0)+1);

    while(counts.size === kindCount&&left<=right){
      if(right-left<answer[1]-answer[0]){
        answer = [left,right];
      }
      const leftGem = gems[left];
      counts.set(leftGem,counts.get(leftGem)-1);
      if(counts.get(leftGem) === 0){
        counts.delete(leftGem);
      }
      left++;
    }
  }

  return [answer[0]+1,answer[1]+1];
}