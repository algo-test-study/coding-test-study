function solution(board, moves) {
    const basket = [];
    let answer = 0;

    for (const move of moves) {
        const col = move - 1;

        for (let row = 0; row < board.length; row++) {
            if (board[row][col] === 0) continue;

            const doll = board[row][col];
            board[row][col] = 0;

            if (basket[basket.length - 1] === doll) {
                basket.pop();
                answer += 2;
            } else {
                basket.push(doll);
            }

            break;
        }
    }

    return answer;
}

/*
//시간복잡도 계산
// moves 배열 최대 1000, board 배열 행 개수 최대 30
// for문 n이라고 했을 때 내부 while 문으로 처리하는 루프가 최대 30번으로 상수 취급해도 되는 값이 되어 O(n)
function solution(board, moves) {
    let answer = 0;
    
    let stack = [];

    //크레인 열
    for(let i=0; i<moves.length; i++){
        //게임 화면 행을 표시할 변수
        let crainHgt = 0;
        
        //크레인 행
        while(crainHgt < board.length && board[crainHgt][moves[i]-1]==0){
            crainHgt++;
        }
        if (crainHgt === board.length) continue;
        
        //행이 확정된 후 변수화
        const item = board[crainHgt][moves[i]-1];
        
        //집을 인형이 없을 경우
        if(item === 0) continue;
        
        //집은 인형 제거
        board[crainHgt][moves[i]-1]=0;

        
        //행이 확정되고 집은 인형을 바구니로
        if(stack[stack.length-1] === item){
            answer+=2;
            stack.pop();
        }else{
            stack.push(item);                    
        }
    }
    
    return answer;
}
*/

/*
function solution(board, moves){
    let answer = 0;
    
    let stack = [];
    
    for(let i=0; i<moves.length; i++){
        let column = moves[i] - 1;
        for(let j=0; j<board.length; j++){
            let row = board[j][column];
            
            if(row !== 0){
                if(stack.length>0 && stack[stack.length-1] === row){
                    stack.pop();
                    answer += 2;
                }else{
                    stack.push(row);
                }
                board[j][column]=0;
                break;
            }
        }
    }
    
    return answer;
}
*/