function solution(progresses, speeds) {
  var answer = [];
  const lng = progresses.length;
  let chk = 0;

  let remainedDays = [];
  let remainedProgresses = [];
  while(chk < lng){
      remainedProgresses.push(100-progresses[chk]);

      remainedDays[chk] = Math.ceil(remainedProgresses[chk]/speeds[chk])

      chk++;
  }
  console.log(remainedDays);
  //속도에 따라 다시 계산하기 몇일 걸리는지

  //남은 진행률 배열 완성
  chk = 0;
  let num = 0;
  let maxDay = remainedDays[0];
  while(chk < lng){
      if(maxDay>=remainedDays[chk]){
          num++;

      }else{
          answer.push(num);
          num = 1;
          maxDay = remainedDays[chk];
      }
      chk++;

  }

  answer.push(num);
  return answer;
}

function solution2(progresses,speeds){
  var answer = [];
  const lng = progresses.length;
  let chk = 0;

  let remainedDays = [];
  let remainedProgresses = [];
  while(chk < lng){
      remainedProgresses.push(100-progresses[chk]);

      remainedDays[chk] = Math.ceil(remainedProgresses[chk]/speeds[chk])

      chk++;
  }
  console.log(remainedDays);
  //속도에 따라 다시 계산하기 몇일 걸리는지

  let maxDay=remainedDays[0];// *****
  let result = 0;
  for(let i=0;i<remainedDays.length;i++){


      if(maxDay >= remainedDays[i]){
          result++;
      }else{
          maxDay = remainedDays[i];
          answer.push(result);
      }
  }

  if(answer.length === 0){
      answer.push(result);
  }

  return answer;
}
