class MyLinkedList {
    class Node{
        int val;
        Node next;
        Node(int val){
            this.val = val;
        }
    }
    Node head;
    int size;
    public MyLinkedList() {
        head = null;
        size =0;
    }
    
    public int get(int index) {
        if(index<0||index>=size)return -1;
        if(index==0){
            return head.val;
        }
        Node cur = head;
        for(int i =0;i<index;i++){
            cur =cur.next;
        }
        return cur.val;
    }
    
    public void addAtHead(int val) {
        Node newNode = new Node(val);
        newNode.next = head;
        head = newNode;
        size++;
    }
    
    public void addAtTail(int val) {
        if(head==null){
            addAtHead(val);
            return;
        }
        Node newNode = new Node(val);
        Node cur = head;
        while(cur.next!=null){
            cur = cur.next;
        }
        cur.next = newNode;
        size++;
        return;
    }
    
    public void addAtIndex(int index, int val) {
        if(index<0||index>size)return;
        if(index==0){
            addAtHead(val);
            return;
        }
        if(index==size){
            addAtTail(val);
            return;
        }
        Node cur = head;
        for(int i =0; i<index-1;i++){
            cur = cur.next;
        }
        Node newNode = new Node(val);
        newNode.next = cur.next;
        cur.next = newNode;
        size++;
        return;
    }
    
    public void deleteAtIndex(int index) {
        if(index<0||index>=size)return;
        if(index==0){
            head = head.next;
            size--;
            return;
        }
        Node cur = head;
        for(int i =0;i<index-1;i++){
            cur = cur.next;
        }
        cur.next = cur.next.next;
        size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */