function solution(jobs) {
  jobs.sort((a,b) => a[0]-b[0]); 
  const n = jobs.length;
  const wait = [];
  let time=0;
  let idx=0;
  let sum=0;

  while(idx<n||wait.length){
    while(idx<n&&jobs[idx][0]<=time){
      wait.push(jobs[idx++]);
    }
    if(!wait.length){
      time = jobs[idx][0];
      continue;
    }
    wait.sort((a,b) => a[1]-b[1]);
    const [start,dur] = wait.shift();
    time+=dur;
    sum+=time-start;
  }
  return Math.floor(sum/n);
}