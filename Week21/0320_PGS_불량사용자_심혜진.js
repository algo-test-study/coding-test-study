function solution(user_id, banned_id) {
  const regexList = banned_id.map(b =>
    new RegExp("^" + b.replace(/\*/g, ".") + "$")
  );

  const selected = Array(user_id.length).fill(false);
  const resultSet = new Set();

  function dfs(depth, path) {
    if (depth === banned_id.length) {
      const key = path.slice().sort().join(",");
      resultSet.add(key);
      return;
    }

    for (let i = 0; i < user_id.length; i++) {
      if (selected[i]) continue;
      if (regexList[depth].test(user_id[i])) {
        selected[i] = true;
        dfs(depth + 1, path.concat(user_id[i]));
        selected[i] = false;
      }
    }
  }

  dfs(0, []);

  return resultSet.size;
}