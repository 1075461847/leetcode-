# leetcode刷题笔记

## 1.TwoSum 两数之和

### 1. 题目描述

```
给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
你可以按任意顺序返回答案。

示例 1：
    输入：nums = [2,7,11,15], target = 9
    输出：[0,1]
    解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
    
示例 2：
    输入：nums = [3,2,4], target = 6
    输出：[1,2]
    
示例 3：
    输入：nums = [3,3], target = 6
    输出：[0,1]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/two-sum
```

### 2. 初始解法

#### 1. 思路

```
穷举每一种情况，将符合的结果返回
```

#### 2. 代码

```java
/**
 * 1. 两数之和
 * @author 刘淳
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        //双重循环遍历数组，拿到每一种组合
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                //两数之和等于target时将两数下标赋给结果数组
                if (nums[i] + nums[j] == target) {
                    //返回结果
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];
    }
}
```

#### 3. 结果

![image-20211116114056256](https://img.lccyj.ltd/img/image-20211116114056256.png)

#### 4. 分析

```
时间复杂度过高，最坏情况下数组中任意两个数都匹配一次
使用哈希表解决第二层遍历时间复杂度过高问题，每个数a都判断哈希表中是否存在键b=target-a，
不存在将a作为键，a的下标作为值存入表中，存在则返回a的下标及表中b的值
```

#### 5. 改进代码

```java
import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 * @author 刘淳
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> hashmap=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 判断表中是否存在与该数相加等于需要的和的键
            if (hashmap.containsKey(target-nums[i])){
                //存在，将该数的下标与该键的值存入
                return new int[]{i,hashmap.get(target-nums[i])};
            }
            //不存在，将该数和该数下标存入表中
            hashmap.put(nums[i],i );
        }
        return new int[0];
    }
}
```

#### 6. 改进结果

![image-20211116120351372](https://img.lccyj.ltd/img/image-20211116120351372.png)

## 2. AddTwoNumbers 两数相加

### 1. 题目描述

```
给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
请你将两个数相加，并以相同形式返回一个表示和的链表。
你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
```

#### 示例1：

![](https://img.lccyj.ltd/img/addtwonumber1.jpg)

```
输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[7,0,8]
解释：342 + 465 = 807.
```

#### 示例2：

```
输入：l1 = [0], l2 = [0]
输出：[0]
```

#### 示例3：

```
输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
输出：[8,9,9,9,0,0,0,1]
```

### 2. 解题思路

```
创建一个节点作为结果节点，同时创建一个节点作为结果节点的当前节点
用一个遍历两链表，并用一个变量贮存进位值，当两链表的当前节点有一个不为null或进位值不为0时
循环继续，在循环内部对两链表当前节点进行判null，若为null则该节点值取0，不为null取当前节
点的val属性，将两个当前节点的值与进位值相加的和取10的余数作为结果链表的值，进位值变为和
/10，然后再次判断l1，l2是否为null，不为null则后移节点，结果节点同时后移。
```

### 3. 初始代码

```java
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode=new ListNode();  //保存结果的链表
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
            currentNode.val=sumVal%10;  // 将和赋值给当前结果节点
            currentNode.next=new ListNode();  //创建下一结果节点
            currentNode=currentNode.next;  //结果节点后移
        }
        return listNode;
    }
}
```

####  3.1 存在问题

```
结果链表最后会多出一个值为0的节点
```

![image-20211117165105242](https://img.lccyj.ltd/img/image-20211117165105242.png)

#### 3.2 问题分析

```
当计算最后一个节点时，仍会创建一个新的节点作为结果节点的下一节点导致
```

#### 3.3 解决思路

```
为结果链表创建一个不用于储存数据的头节点，在存储和的时候用和创建一个新节点，并将该节点作为
当前节点的下一节点，这样，当存储最后一个数值时，由于值储存在下一节点，就不会出现多余节点
```

#### 3.4 注意事项

```
由于头节点不用于存储结果数据，返回时应返回头节点的下一节点
```

### 4. 改进代码

```java
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
```

### 5. 最终结果

![image-20211117165850994](https://img.lccyj.ltd/img/image-20211117165850994.png)

