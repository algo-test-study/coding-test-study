function solution(progresses, speeds) {
    var answer = [];
    const lng = progresses.length;
    let chk = 0;

    let remainedDays = [];
    let remainedProgresses = [];
    while (chk < lng) {
        remainedProgresses.push(100 - progresses[chk]);

        remainedDays[chk] = Math.ceil(remainedProgresses[chk] / speeds[chk])

        chk++;
    }
    console.log(remainedDays);

    chk = 0;
    let num = 0;
    let maxDay = remainedDays[0];
    while (chk < lng) {
        if (maxDay >= remainedDays[chk]) {
            num++;

        } else {
            answer.push(num);
            num = 1;
            maxDay = remainedDays[chk];
        }
        chk++;

    }

    answer.push(num);
    return answer;
}
