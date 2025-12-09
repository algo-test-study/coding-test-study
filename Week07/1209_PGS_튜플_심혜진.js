function solution(s) {
    const sets=s
      .slice(2, -2)
      .split("},{")
      .map(str => str.split(",").map(Number))
      .sort((a,b) => a.length-b.length);

    const result = [];
    const seen = new Set();

    for (const arr of sets) {
      for (const num of arr) {
        if (!seen.has(num)) {
          seen.add(num);
          result.push(num);
        }
      }
    }

    return result;
  }
