class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (n1, n2) -> n1.val - n2.val);
        
        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }
        
        ListNode temp = new ListNode(0);
        ListNode tail = dummy;
        
        while (!pq.isEmpty()) {
            tail.next = pq.poll();
            tail = tail.next;
            
            if (tail.next != null) {
                pq.add(tail.next);
            }
        }
        
        return temp.next;
    }
}
