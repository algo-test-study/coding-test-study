"""
dfs로 탐색하면서 모든 값들 ans에 담고, 정렬, 다시 연결 : NlogN
"""

# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
import headq
class Solution(object):
    1. 정렬 풀이
    def mergeKLists(self, lists):
        """
        :type lists: List[/[ListNode]]
        :rtype: Optional[ListNode]
        """
        ans = []

        def dfs(curr):
            if not curr:
                return

            ans.append(curr)

            if curr.next:
                dfs(curr.next)

        for lst in lists:
            dfs(lst)

        ans.sort(key = lambda x: x.val)

        nodes = ListNode(0)
        curr = nodes

        for node in ans:
            curr.next = node
            curr = curr.next
    
        curr.next = None
        return nodes.next
