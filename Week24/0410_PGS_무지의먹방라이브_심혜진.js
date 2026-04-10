function solution(food_times, k) {
  // 모든 음식을 다 먹는 시간 <= k 이면 방송 중단
  const total = food_times.reduce((sum, time) => sum + time, 0);
  if (total <= k) return -1;

  // [음식시간, 원래번호]
  const foods = food_times.map((time, index) => ({
    time,
    index: index + 1,
  }));

  // 음식 시간을 기준으로 오름차순 정렬
  foods.sort((a, b) => a.time - b.time);

  let prevTime = 0;              // 이전 단계까지 공통으로 먹은 시간
  let remainCount = foods.length; // 남은 음식 개수
  let i = 0;                     // 지금까지 제거한 음식 수

  while (i < foods.length) {
    const currentTime = foods[i].time;
    const diff = currentTime - prevTime; // 이번에 한 번에 더 깎을 수 있는 시간

    // diff가 0이면 같은 시간 음식이므로 바로 넘김
    if (diff !== 0) {
      const spend = diff * remainCount;

      if (k >= spend) {
        k -= spend;
        prevTime = currentTime;
      } else {
        // 여기서 더는 한 바퀴를 다 못 돎
        // 남은 음식들을 원래 번호 순으로 정렬해서
        // k % remainCount 번째 음식 찾기
        const rest = foods.slice(i).sort((a, b) => a.index - b.index);
        return rest[k % remainCount].index;
      }
    }

    // 현재 time과 같은 음식들은 다 먹은 상태가 됨
    while (i < foods.length && foods[i].time === currentTime) {
      i++;
      remainCount--;
    }
  }

  return -1;
}