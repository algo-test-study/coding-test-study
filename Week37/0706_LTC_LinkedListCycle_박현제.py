# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None
"""
사이클 판단, O(n)
"""
class Solution(object):
    def hasCycle(self, head):
        """
        :type head: ListNode
        :rtype: bool
        """
        visited = set()

        def is_cycle(curr):
            if not curr:
                return False

            if curr in visited:
                return True

            visited.add(curr)
            return is_cycle(curr.next)
        
        return is_cycle(head)
