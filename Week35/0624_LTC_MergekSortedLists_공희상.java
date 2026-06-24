import java.util.PriorityQueue;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> priorityQueue =
                new PriorityQueue<>((firstNode, secondNode) ->
                        Integer.compare(firstNode.val, secondNode.val)
                );

        for (ListNode listHead : lists) {
            if (listHead != null) {
                priorityQueue.offer(listHead);
            }
        }

        ListNode dummyHead = new ListNode(0);
        ListNode currentNode = dummyHead;

        while (!priorityQueue.isEmpty()) {
            ListNode smallestNode = priorityQueue.poll();

            currentNode.next = smallestNode;
            currentNode = currentNode.next;

            if (smallestNode.next != null) {
                priorityQueue.offer(smallestNode.next);
            }
        }

        return dummyHead.next;
    }
}
