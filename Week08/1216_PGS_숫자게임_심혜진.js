function solution (A,B) {
  let answer = 0;

  A.sort((a,b) => a-b);
  B.sort((a,b) => a-b);

  let a=0;
  let b=0;
  for(let i=0; i<A.length; i++){
    if(A[a]<B[b]){
      a++;
      b++;
      answer++
    }else {
      b++;
    };
  }

  return answer;
}