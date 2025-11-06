function solution(A, B) {
    A.sort((a,b)=>a-b);
    B.sort((a,b)=>a-b);

    let a=0;
    let b=0;
    let win=0;
    for(let i=0; i<A.length;i++){
        if(A[a]<B[b]){
            win++;
            a++;
            b++;
        }else{
            b++;
        }
    }
  return win;
}
