function solution(records){
    let answer = [];
    let uid = {};

    for(let record of records){
        let cmd = record.split(" ");
        if(cmd[0] === "Leave") continue;
        uid[cmd[1]] = cmd[2];
    }
    console.log(uid);

    for(let record of records){
        let cmd = record.split(" ");
        if(cmd[0] === "Leave"){
            answer.push(`${uid[cmd[1]]}님이 나가셨습니다.`);
        } else if(cmd[0] === "Enter") {
            answer.push(`${uid[cmd[1]]}님이 들어왔습니다.`);
        }
    }

    return answer;
}

console.log(solution(["Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"]));
