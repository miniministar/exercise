package algorithm.link;

import algorithm.sort.Bubble;
import algorithm.sort.Insertion;
import algorithm.sort.Selection;
import algorithm.sort.Shell;
import org.junit.Test;

import java.util.Arrays;

public class RecorderListTest {

    @Test
    public void SelectSort() {
        Solution.ListNode node1 = new Solution.ListNode(1);
        Solution.ListNode node2 = new Solution.ListNode(2);
        Solution.ListNode node3 = new Solution.ListNode(3);
        Solution.ListNode node4 = new Solution.ListNode(4);
        Solution.ListNode node5 = new Solution.ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        Solution solution  = new Solution();
        solution.print(node1);
        System.out.println();
        solution.reorderList(node1);
        solution.print(node1);
    }
}
