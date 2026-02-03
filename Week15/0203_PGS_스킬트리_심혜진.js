function solution(skill, skill_trees) {
  let answer = 0;

  skillArr = skill.split('');
  //['C', 'B', 'D']

  //skill_trees를 포문 돌리면서 split하는데 skillArr에 포함되지 않으면 다 버리는 쪽으로 필터링함
  for(let i=0; i<skill_trees.length; i++){
      const sArr = skill_trees[i].split('');
      skill_trees[i] = sArr.filter(e=>skillArr.includes(e));
      //[['B','C','D'], ['C','B','D'],['C','B'],['B','D']]
  }

  for(let i=0; i<skill_trees.length; i++){
      //['B','C','D']
      let s = skill_trees[i];
      let index = skillArr.indexOf(s[0]); //0
      let last = skillArr.indexOf(s[s.length-1]);//1
      while(index <= last ){
          if(skillArr[index] === s[index]){
              if(index === last){
                  answer++;
              }
          }else{
              break;
          }
          index++;
      }
  }

  return answer;
}