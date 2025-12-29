function solution(edges){
  let biggest = 0;
  for(const[start,end]of edges){
    if(start>biggest) biggest=start;
    if(end>biggest) biggest=end;
  }

  const enter=Array(biggest+1).fill(0);
  const leave=Array(biggest+1).fill(0);

  for(const[from,to]of edges){
    leave[from]++;
    enter[to]++;
  }

  let root=0;
  for(let i = 1;i <= biggest; i++){
    if(enter[i] === 0&&leave[i] >= 2){
      root=i;
      break;
    }
  }

  let stickCount=0;
  let eightCount=0;

  for(let i=1 ;i <= biggest; i++){
    if(i===root)continue
    if(leave[i]===0)stickCount++;
    else if(leave[i]===2)eightCount++;
  }

  const donutCount=leave[root]-stickCount-eightCount;
  return[root, donutCount, stickCount, eightCount];
}
