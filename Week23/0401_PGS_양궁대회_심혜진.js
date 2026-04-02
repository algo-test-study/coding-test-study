function combinationWithRepitition(arr, n) {
  if (n === 1) return arr.map((v) => [v]);
  let result = [];

  arr.forEach((fixed, idx) => {
    let rest = arr.slice(idx)
    let combis = combinationWithRepitition(rest, n - 1)
    let combine = combis.map((v) => [fixed, ...v])
    result.push(...combine)
  });

  return result;
}

function solution(n, info) {
  let maxDiff = 0;
  let maxScore = {};
  let answer = Array(11).fill(0);

  function calculateScore(lionArr) {
    let apeachScore = 0;
    let lionScore = 0;

    for (let i = 1; i <= 10; i++) {

      //1점을 맞힌 화살부터 계산, 0은 계산 안함
      if (info[10 - i] < lionArr.filter(x => x === i).length) {
        lionScore += i;
      } else if (info[10 - i] > 0) {
        apeachScore += i;
      }
    }

    return [apeachScore, lionScore];
  }

  function switchDiff(diff, cnt) {
    maxDiff = diff;
    maxScore = cnt;
  }

  for (const lionArr of combinationWithRepitition([...Array(11).keys()], n)) {
    //라이언의 양궁 점수 계산할 수 있는 배열이 준비가 됨 객체를 저장해둠...
    let cnt = lionArr.reduce((acc, cur) => {
      acc[cur] = (acc[cur] || 0) + 1;
      return acc;
    }, {});

    //둘의 점수를 계산해봄
    let [apeachScore, lionScore] = calculateScore(lionArr);

    if (apeachScore >= lionScore) continue;


    let diff = lionScore - apeachScore;

    //maxDiff보다 크면? maxDiff를 교체함
    if (diff > maxDiff) {
      switchDiff(diff, cnt);
    }
  }

  if (maxDiff > 0) {
    for (const key in maxScore) {
      answer[10 - key] = maxScore[key] || 0;
    }
  } else {
    answer = [-1];
  }

  return answer;
}