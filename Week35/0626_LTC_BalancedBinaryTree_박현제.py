"""
오른쪽과 왼쪽 서브트리 높이가 1이하 차이나면 True / 아니면 False
O(N)
"""

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def isBalanced(self, root):
        """
        :type root: Optional[TreeNode]
        :rtype: bool
        """
        if not root:
           return True 

        def dfs(curr):
            if curr is None:
                return 0

            left_h = dfs(curr.left)
            if left_h == -1:
                return -1

            right_h = dfs(curr.right)
            if right_h == -1:
                return -1

            if abs(left_h-right_h) > 1:
                return -1

            return max(left_h, right_h) + 1

        return False if dfs(root) == -1 else True
