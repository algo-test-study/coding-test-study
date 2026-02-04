
function solution(clothes) {
  let answer = 1;
  let len = clothes.length;

  let hash = {};
  for(let i=0; i<len; i++){
      let type = clothes[i][1];
      let name = clothes[i][0];
      if(!hash[type]) hash[type] =0;
      hash[type]++;
  }

  //2, 1 = 3*2 -1 
  for(const type in hash) answer *= hash[type]+1;
    return answer-1;

}