function solution(today, terms, privacies) {
  const toDays = (dateStr) => {
    const [y, m, d] = dateStr.split('.').map(Number);
    return y * 12 * 28 + m * 28 + d;
  };

  const termMap = new Map();
  for (const t of terms) {
    const [type, months] = t.split(' ');
    termMap.set(type, Number(months));
  }

  const todayDays = toDays(today);
  const answer = [];

  for (let i = 0; i < privacies.length; i++) {
    const [dateStr, type] = privacies[i].split(' ');
    const start = toDays(dateStr);
    const months = termMap.get(type);

    const expire = start + months * 28 - 1;

    if (todayDays > expire) answer.push(i + 1);
  }

  return answer;
}
