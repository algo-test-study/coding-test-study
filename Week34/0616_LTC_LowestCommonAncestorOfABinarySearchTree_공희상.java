class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode currentNode = root;

        while (currentNode != null) {
            if (p.val < currentNode.val && q.val < currentNode.val) {
                currentNode = currentNode.left;
            } else if (p.val > currentNode.val && q.val > currentNode.val) {
                currentNode = currentNode.right;
            } else {
                return currentNode;
            }
        }

        return null;
    }
}
