var isBalanced = function(root) {
  function dfs(node) {
    if (!node) return 0;

    const left = dfs(node.left);
    if (left === -1) return -1;

    const right = dfs(node.right);
    if (right === -1) return -1;

    if (Math.abs(left - right) > 1) return -1;

    return Math.max(left, right) + 1;
  }

  return dfs(root) !== -1;
};