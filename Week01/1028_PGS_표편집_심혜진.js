function solution(n,k,cmd){
  const up = [...Array(n+2)].map((_,i)=> i-1);
  const down = [...Array(n+2)].map((_,i)=> i+1);

  const delArr = [];

  k += 1;//현재 위치를 나타내는 인덱스이자 가상 공간

  for(let command of cmd){
      if(command[0]==='C'){
          delArr.push(k);
          up[down[k]] = up[k];//down[0] = 2 , up[2] = 0 0번행의 아래를 2번으로 2번행의 위를 0번행으로
          down[up[k]] = down[k];
          k = n < down[k] ? up[k] : down[k];
      }else if(command[0]==='Z'){
          const restore = delArr.pop();
          up[down[restore]] = restore; //down[1] = 2, up[2] = 1 1번행의 아래를 2번행으로 2번행의 위를 1번행으로
          down[up[restore]] = restore;
      }else{
          const [action, num] = command.split(' ');
          if(command[0]==='U'){
              for(let i=0; i<parseInt(num); i++){
                  k = up[k];
              }
          }else if(command[0]==='D'){
              for(let i=0; i<parseInt(num); i++){
                  k = down[k];
              }
          }
      }
  }

  const answer = Array(n).fill('O');
  for(const i of delArr){
      answer[i-1] = 'X';
  }
  return answer.join('');
}