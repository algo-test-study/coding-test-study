function solution(priorities, location) {
  let len = priorities.length;
  let count = 0;
  while(len>0){
      let tail = priorities.shift();
      let isBiggerExist = priorities.some( e => e > tail );
      if(isBiggerExist) {
        priorities.push(tail);
        location = location === 0 ? priorities.length - 1 : location - 1;
      }
      else{
          count++;
          if(location===0) return count;
          location--;
      }
  }
  return count;
}

console.log(solution([2, 1, 3, 2], 2));
