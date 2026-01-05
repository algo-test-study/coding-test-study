function solution(routes) {
  routes.sort((a, b) => a[1] - b[1]);

  let answer = 0;
  let cameraPos = -Infinity;

  for (const [start, end] of routes) {
    if (start > cameraPos) {
      answer++;
      cameraPos = end;
    }
  }

  return answer;
}
