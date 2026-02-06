function solution(n, stations, w) {
  let answer = 0;
  let start = 1;//아직 체크 안된 아파트 번호
  let mod = 2*w+1;//구간이 전파하는 아파트 개수
  for(const station of stations){
      if(start < station-w){
          answer += Math.ceil((station-w-start)/mod)
      } 
      start = station+w+1;
  }
  
  if(start <= n){
      answer += Math.ceil((n-start+1)/mod)
  }
  
  return answer;
}