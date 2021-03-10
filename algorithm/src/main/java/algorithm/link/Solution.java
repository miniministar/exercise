package algorithm.link;

public class Solution {
    public void print(ListNode head) {
        ListNode node = head;
        while (node.next!=null) {
            System.out.print( node.value + "->");
            node = node.next;
        }
        System.out.println(node.value);
    }
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode t = head;
        //先找到中间节点
        ListNode temp = findMidNode(head);
        ListNode midNode = temp.next;
        temp.next = null;
        //反转中间节点为头节点的链表
        midNode = reverseNode(midNode);
        //按照特定顺序将原链表的前一半与反转后的链表拼接起来
        ListNode newNode = new ListNode(-1);
        ListNode n = newNode;
        int flag = 1;
        while (t != null && midNode != null) {
            if(flag % 2 == 1) {
                n.next = t;
                t = t.next;
            }else {
                n.next = midNode;
                midNode = midNode.next;
            }
            flag++;
            n = n.next;
        }
        if (t == null && midNode != null) {
            n.next = midNode;
        }else if (t != null && midNode == null) {
            n.next = t;
        } 
        head = newNode.next;
    }
    //找到链表的中间节点的上一个节点
    private ListNode findMidNode(ListNode head) {
        ListNode f = head;
        ListNode s = head;
        while(f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }
        return s;
    }
    //反转节点
    private ListNode reverseNode(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode f = head;
        ListNode s = f.next;
        while (s != null) {
            f.next = s.next;
            s.next = dummyHead.next;
            dummyHead.next = s;
            s = f.next;
        }
        return dummyHead.next;
    }

    static class ListNode {
        int value;
        ListNode next;
        public ListNode(int value){
            this.value = value;
        }
    }
}