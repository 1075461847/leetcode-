/**
 * 2.两数相加
 *
 * @author 刘淳
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode=new ListNode(-1);  //保存结果的链表的头节点
        ListNode currentNode=listNode;  //当前节点
        int carry=0;  //保存进位
        while (l1!=null||l2!=null||carry!=0){
            int l1Val=l1!=null?l1.val:0;  // 根据l1是否为null得到了l1当前节点的值
            int l2Val=l2!=null?l2.val:0;  // 根据l2是否为null得到了l2当前节点的值
            int sumVal=l1Val+l2Val+carry; //两节点值与上一节点进位值的和
            carry=sumVal/10;  //当前节点进位值
            //节点后移
            if (l1!=null){
                l1=l1.next;
            }
            if (l2!=null){
                l2=l2.next;
            }
            currentNode.next=new ListNode(sumVal%10);  //用和作为val创建下一结果节点
            currentNode=currentNode.next;  //当前节点后移
        }
        return listNode.next;
    }
}
