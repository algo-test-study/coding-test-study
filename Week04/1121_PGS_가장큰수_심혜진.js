function solution(numbers) {
  const strs = numbers.map(String);

  strs.sort((a, b) => {
    const ab=a+b;
    const ba=b+a;
    if (ab>ba) return -1;
    if (ab<ba) return 1;
    return 0;
  });
  const result=strs.join('');
  if (result[0]==='0') return "0";
  return result;
}
