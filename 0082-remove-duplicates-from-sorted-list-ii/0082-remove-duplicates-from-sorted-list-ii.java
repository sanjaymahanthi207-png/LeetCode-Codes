class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        int[] hash = new int[201];
        ListNode temp = head;
        while(temp != null){
            hash[temp.val + 100]++;
            temp = temp.next;
        }
        ListNode newHead = new ListNode(0);
        ListNode tail = newHead;
        temp = head;
        while(temp != null){
            if(hash[temp.val + 100] == 1){
                tail.next = temp;
                tail = tail.next;
            }
            temp = temp.next;
        }
        tail.next = null;
        return newHead.next;
    }
}