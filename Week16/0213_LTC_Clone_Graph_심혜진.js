var cloneGraph = function (node) {
  if (!node) return null;

  const visited = new Map();

  function dfs(curr) {
    if (visited.has(curr)) {
      return visited.get(curr);
    }

    const copy = new Node(curr.val);
    visited.set(curr, copy);

    for (let neighbor of curr.neighbors) {
      copy.neighbors.push(dfs(neighbor));
    }

    return copy;
  }

  return dfs(node);
};
