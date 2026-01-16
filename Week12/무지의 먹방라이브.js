function solution(food_times, k) {
  const n = food_times.length;

  // 전체 먹는 시간 <= k 이면 다 먹고 끝
  const total = food_times.reduce((a, b) => a + b, 0);
  if (total <= k) return -1;

  // (time, index) 형태로 저장
  const foods = food_times
    .map((time, idx) => ({ time, idx: idx + 1 }))
    .sort((a, b) => a.time - b.time);

  let prev = 0;      // 이전에 제거한 층 높이
  let i = 0;         // foods에서 현재 위치
  let remain = n;    // 남은 음식 수

  while (i < n) {
    const curTime = foods[i].time;
    const diff = curTime - prev;

    if (diff !== 0) {
      const cost = diff * remain;

      if (k >= cost) {
        k -= cost;
        prev = curTime;
      } else {
        // 여기서 남은 음식들 중 (k % remain) 번째 음식 찾기
        const rest = foods.slice(i).sort((a, b) => a.idx - b.idx);
        return rest[k % remain].idx;
      }
    }

    // curTime이 prev와 같아졌다는 뜻 = 해당 음식은 0이 됨 -> 제거
    while (i < n && foods[i].time === prev) {
      i++;
      remain--;
    }
  }

  return -1;
}