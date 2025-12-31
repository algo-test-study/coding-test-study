function solution(board){
  let n = board.length;
  let m = board[0].length;

  let side = 0;
  //board를 기반으로 dp를 재배치 해야함
  let dp = [];
  for(let i=0; i<n; i++){
      dp[i]=[];
      for(let j=0; j<m; j++){
          //dp[i][j]는 1일 때만 추가 로직을 따르고 0 이라면 0체크만 하고 넘어간다
          if(board[i][j] === 1){
              //인덱스 범위 밖 값들 가정
              let left = 0;
              let cross = 0;
              let top = 0;

              //인덱스가 범위 내라서 값을 계산할 수 있는 경우
              //좌
              if(j-1>=0){
                   left = dp[i][j-1]; //좌측 인덱스가 양수인데 값이 0이면 현재 dp[i][j]는 정사각형 후보 탈락
              }
              //좌상단
              if(i-1>=0 && j-1>=0){
                  cross =  dp[i-1][j-1] //좌상단 인덱스가 양수인데 값이 0이면 현재 dp[i][j]는 정사각형 후보 탈락
              }
              //위
              if(i-1>=0){
                  top=dp[i-1][j]; //위쪽 인덱스가 양수인데 값이 0이면 현재 dp[i][j]는 정사각형 후보 탈락
              }

              dp[i][j]=Math.min(left, cross, top)+1;

              side = Math.max(side, dp[i][j]);
          }else{
              dp[i][j]=0;
          }
      }
  }

  return side*side;
}