function solution(n, times) {
  let low = 0;
  let high = Math.max(...times) * n;
  let answer = high;

  while (low <= high) {
    const mid = Math.floor((low + high) / 2);

    let processed = 0;
    for (const t of times) {
      processed += Math.floor(mid / t);
      if (processed >= n) break;
    }

    if (processed >= n) {
      answer = mid;
      high = mid - 1;
    } else {
      low = mid + 1;
    }
  }

  return answer;
}
/*
// n과 times의 값 범위 각각 10^9, 10^9, 
// 모든 사람이 심사받는 최소 시간
// 답이 되는 후보 값의 범위가 10^18이 될 수 있음
// 브루트 포스 불가로 범위를 줄여야함 > 이분 탐색

// 시간 복잡도 O(logN*M)

function solution(n, times) {
    let start = 1;
    let end = Math.max(...times)*n;
    let answer = end;
    
    while(start<=end){
        const mid = Math.floor((start+end)/2);
        let count = 0;
        for(let i=0; i<times.length; i++){
            count  += Math.floor(mid/times[i]);
            if (count >= n) break;
        }
        
        if(count>=n){
            answer = mid;
            end = mid - 1;
        }else{
            start = mid + 1;
        }     
    }
  return answer;
}
*/