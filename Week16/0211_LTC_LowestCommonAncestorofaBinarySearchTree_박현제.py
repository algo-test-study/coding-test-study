"""
알고리즘
- dfs
- 아래 둘다 있으면 본인이 조상
- 찾은 거 반환

시간복잡도
- O(len(node))

"""

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        
        if (root is None) or root == p or root == q:
            return root

        l = self.lowestCommonAncestor(root.left, p, q)
        r = self.lowestCommonAncestor(root.right, p, q)

        if r and l:
            return root
        if r:
            return r
        else:
            return l
