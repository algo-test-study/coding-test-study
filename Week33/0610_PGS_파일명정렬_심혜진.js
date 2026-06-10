function solution(files) {
  let hash = [];
  //files 배열을 iterator로 돌리면서 head, number, 그 외로 정렬한다
  for(let i = 0; i<files.length; i++){
      const fileName = files[i];
      hash[i] = {head: '', number: '', tail: ''};
      let head = false;
      let number = false;
      let tail = false;
      for(let j = 0; j<fileName.length; j++){
          if(!head && !isNumeric(fileName[j])){
             hash[i].head += fileName[j];
          }

          if(!head && isNumeric(fileName[j])){
             head = true;
          }

          if(head && isNumeric(fileName[j])){
            hash[i].number += fileName[j];
         }

          if(head && !isNumeric(fileName[j])){
             tail = true;
             hash[i].tail = fileName.slice(j);
             break;
          }

      }
  }


  hash.sort((a,b)=> {
    if( a.head.toLowerCase() !== b.head.toLowerCase()) return a.head.toLowerCase().localeCompare(b.head.toLowerCase());
    else if  (Number(a.number) !== Number(b.number)) return Number(a.number) - Number(b.number);
    else return 0;
  });

  const answer = hash.map((v) => v.head + v.number + v.tail);
  return answer;
}

function isNumeric(str) {
  if (typeof str !== "string") return false; // 문자열만 허용
  return !isNaN(str) && !isNaN(parseFloat(str));
}