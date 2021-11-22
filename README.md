# leetcode刷题笔记

## 1.TwoSum 两数之和

**1.题目描述**

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

**2.初始解法**

**2.1思路**

```
穷举每一种情况，将符合的结果返回
```

**2.2代码**

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

**2.3结果**

![image-20211116114056256](https://img.lccyj.ltd/img/image-20211116114056256.png)

**2.4分析**

```
时间复杂度过高，最坏情况下数组中任意两个数都匹配一次
使用哈希表解决第二层遍历时间复杂度过高问题，每个数a都判断哈希表中是否存在键b=target-a，
不存在将a作为键，a的下标作为值存入表中，存在则返回a的下标及表中b的值
```

**3.改进代码**

```java
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

**4.改进结果**

![image-20211116120351372](https://img.lccyj.ltd/img/image-20211116120351372.png)

## **2. AddTwoNumbers 两数相加**

**1.题目描述**

```
给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
请你将两个数相加，并以相同形式返回一个表示和的链表。
你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
```

**示例1：**

![](https://img.lccyj.ltd/img/addtwonumber1.jpg)

```
输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[7,0,8]
解释：342 + 465 = 807.
```

**示例2：**

```
输入：l1 = [0], l2 = [0]
输出：[0]
```

**示例3：**

```
输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
输出：[8,9,9,9,0,0,0,1]
```

**2.解题思路**

```
创建一个节点作为结果节点，同时创建一个节点作为结果节点的当前节点
用一个遍历两链表，并用一个变量贮存进位值，当两链表的当前节点有一个不为null或进位值不为0时
循环继续，在循环内部对两链表当前节点进行判null，若为null则该节点值取0，不为null取当前节
点的val属性，将两个当前节点的值与进位值相加的和取10的余数作为结果链表的值，进位值变为和
/10，然后再次判断l1，l2是否为null，不为null则后移节点，结果节点同时后移。
```

**3.初始代码**

```java
/**
 * 2.两数相加
 *
 * @author 刘淳
 */
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

**3.1 存在问题**

```
结果链表最后会多出一个值为0的节点
```

![image-20211117165105242](https://img.lccyj.ltd/img/image-20211117165105242.png)

**3.2 问题分析**

```
当计算最后一个节点时，仍会创建一个新的节点作为结果节点的下一节点导致
```

**3.3 解决思路**

```
为结果链表创建一个不用于储存数据的头节点，在存储和的时候用和创建一个新节点，并将该节点作为
当前节点的下一节点，这样，当存储最后一个数值时，由于值储存在下一节点，就不会出现多余节点
```

**3.4 注意事项**

```
由于头节点不用于存储结果数据，返回时应返回头节点的下一节点
```

**4.改进代码**

```java
/**
 * 2.两数相加
 *
 * @author 刘淳
 */
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

**5.最终结果**

![image-20211117165850994](https://img.lccyj.ltd/img/image-20211117165850994.png)

## 3. Longest Substring Without Repeating Characters 无重复字符的最长子串    

**1.题目描述**

```
给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:
输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

示例 2:
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

示例 3:
输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     
示例 4:
输入: s = ""
输出: 0

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
```

**2.初始思路**

```
用两重for循环遍历，将每个字符存入hashset，存入失败时即出现重复，将hashset的size存入用于
存不重复子串长度的hashset，可得到所有不重复子串的长度并筛出重复值，通过Collections的max
方法得到最大长度
```

**3.初始代码**

```java
/**
 * 3.无重复字符的最长字串
 * @author 刘淳
 */
public class LongestSubstringWithoutRepeatingCharacters {
     public int lengthOfLongestSubstring(String s) {
         //长度为0时直接返回
         if (s.length() == 0){
             return 0;
         }
         HashSet<Integer> lengthSet=new HashSet<>();
         char[] chars=s.toCharArray();
         for (int i = 0; i <s.length() ; i++) {
             HashSet<Character> hashSet=new HashSet<>();
             for (int j = i; j < s.length(); j++) {
                 //将字符加入hashset
                 if (!hashSet.add(chars[j])){
                     //加入失败，即出现重复
                     lengthSet.add(hashSet.size());  //将当前长度加入set
                     break;
                 }
             }
             lengthSet.add(hashSet.size());
         }
         return Collections.max(lengthSet);  //返回最大长度
    }
}
```

**4.初始结果**

![image-20211117221420071](https://img.lccyj.ltd/img/image-20211117221420071.png)

**5.改进思路**

```
滑动窗口思想：
	1.我们使用两个指针表示字符串中的某个子串（或窗口）的左右边界，其中左指针代表着上文中「枚举子串的起始位置」，而右指针即为遍历字符串的索引
	2.遍历字符串将字符加入hashmap中，字符为键，索引为值，添加前判断是否已存在
		存在：将左指针更新为已存在字符的索引加一，即将重复字符移出窗口，需要注意的是，由于可能该字符已被移出窗口，需要用max函数取当前边界与其的最大值进行更新
		不存在：将字符和索引加入hashmap
		注意：字符存在时，由于索引较小的重复字符已经移出窗口，我们也需要将该字符的索引更
		新
	每个字符操作完成后将最大子串长度更新为max(当前最大子串长度，当前索引-左指针+1)
```

**6.改进代码**

```java
/**
 * 3.无重复字符的最长字串
 * @author 刘淳
 */
public class LongestSubstringWithoutRepeatingCharacters {
     public int lengthOfLongestSubstring(String s) {
         int maxLength=0; // 最大长度
         int left=0;  //窗口左边界
         HashMap<Character,Integer> hashMap=new HashMap<>();
         //滑动窗口
         for (int i = 0; i < s.length(); i++) {
             //字符存在
             if (hashMap.containsKey(s.charAt(i))){
                 //窗口收缩至重复元素被移出窗口
                 left=Math.max(left,hashMap.get(s.charAt(i))+1);
             }
             //存在与不存在都要将字符和索引加入map，存在加入是为了更新索引
             hashMap.put(s.charAt(i),i);
             //比较当前子串长度是否超过最大子串长度
             maxLength=Math.max(maxLength,i-left+1);
         }
         return maxLength;
    }
}
```

**7.最终结果**

![image-20211118121727755](https://img.lccyj.ltd/img/image-20211118121727755.png)

## 4. Median of Two Sorted Arrays 寻找两个正序数组的中位数

**1.题目描述**

```
给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。

算法的时间复杂度应该为 O(log (m+n)) 。

示例 1：
输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2

示例 2：
输入：nums1 = [1,2], nums2 = [3,4]
输出：2.50000
解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5

示例 3：
输入：nums1 = [0,0], nums2 = [0,0]
输出：0.00000

示例 4：
输入：nums1 = [], nums2 = [1]
输出：1.00000

示例 5：
输入：nums1 = [2], nums2 = []
输出：2.00000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
```

**2.初始思路**

```
将两数组合并到list后排序，将合并后的数组长度加1后取半，然后向上向下取整得到两索引，用这两个索引获得数组元素取半得到中位数(不需要考虑奇偶)
```

**3.代码实现**

```java
/**
 * 4.寻找两个正序数组的中位数
 * @author 刘淳
 */
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> list=new ArrayList<>();
        for (int num:nums1){
            list.add(num);
        }
        for (int num:nums2){
            list.add(num);
        }
        Collections.sort(list);
        double medianIndex = (list.size() +1)/ 2.0;
        return (list.get((int) Math.ceil(medianIndex)-1)+ list.get((int) Math.floor(medianIndex)-1))/2.0;
    }
}
```

**5.初始结果**

![image-20211118192050161](https://img.lccyj.ltd/img/image-20211118192050161.png)

**6.改进思路**

```
这种写法没有考虑两数组为有序数组，在合并数组时可用两个指针分别遍历两数组，比较大小并加入结
果数组，然后将对应指针右移，最后根据数组长度的奇偶从合并后数组中取得中位数。
合并时要注意判断指针是否到达边界，防止数组越界
```

**7.改进代码**

```java
/**
 * 4.寻找两个正序数组的中位数
 * @author 刘淳
 */
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;  // 数组1的长度
        int length2 = nums2.length;  //数组2的长度
        int length = length1 + length2;  //总长度
        int nums1Index = 0;  //num1索引
        int nums2Index = 0;  //num2索引
        int[] nums = new int[length1 + length2];  //用于合并的数组
        //双指针合并有序数组
        for (int i = 0; i < nums.length; i++) {
            if (nums1Index < length1 && nums2Index < length2) {
                //两数组都未到边界
                if (nums1[nums1Index] < nums2[nums2Index]) {
                    //nums1当前数值小
                    nums[i] = nums1[nums1Index]; //nums1当前数值加入nums
                    nums1Index++;  //nums1指针右移
                } else if (nums1[nums1Index] >= nums2[nums2Index]) {
                    //num2当前数值小或两数相等
                    nums[i] = nums2[nums2Index]; //nums2当前数值加入nums
                    nums2Index++;  //nums2指针右移
                }
            } else if (nums1Index == length1) {
                //nums1到达边界
                nums[i] = nums2[nums2Index]; //nums2当前数值加入nums
                nums2Index++;  //nums2指针右移
            } else if (nums2Index == length2) {
                //nums2到达边界
                nums[i] = nums1[nums1Index]; //nums1当前数值加入nums
                nums1Index++;  //nums1指针右移
            }
        }
        if (length % 2 == 0) {
            //长度为偶数
            return (nums[length / 2] + nums[(length / 2) - 1]) / 2.0;
        } else {
            //长度为奇数
            return nums[length / 2];
        }
    }
}
```

**8.改进结果**

![image-20211118210511375](https://img.lccyj.ltd/img/image-20211118210511375.png)

9.注意

```
此处还可用二分法，将时间复杂度降为log(m+n)
```

## 5. Longest Palindromic Substring 最长回文子串

**1.题目描述**

```
给你一个字符串 s，找到 s 中最长的回文子串。

示例 1：
输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。

示例 2：
输入：s = "cbbd"
输出："bb"

示例 3：
输入：s = "a"
输出："a"

示例 4：
输入：s = "ac"
输出："a"

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-palindromic-substring
```

**2.解题思路**

```
循环得到每一个中心点，用双指针表示左右边界，左右相等时向两边扩展子串，即可得到最长回文子串
```

**3.代码实现**

```java
/**
 * 5.最长回文子串
 * @author 刘淳
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        int start = 0;  // 最长回文子串起始下标
        int end = 0;  // 最长回文子串结束下标
        for (int i = 0; i < s.length(); i++) {
            //遍历每一个中心点，得到最长回文子串长度
            int len1 = getLength(s, i, i);
            int len2 = getLength(s, i, i + 1);  //考虑长度为偶数时中心扩展的情况
            int len = Math.max(len1, len2);  //得到最大子串长度
            if (len > end - start) {
                //长度为偶数时，i为左边界，故此处计算时因将长度减1，因为整数除法向下取整，故对奇数情况不影响，不用分情况考虑
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    /**
     * 得到当前中心点最长回文子串的长度
     *
     * @param s     字符串
     * @param left  左指针
     * @param right 右指针
     * @return 最长回文子串长度
     */
    public int getLength(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            //左右相等时扩展子串
            left--;
            right++;
        }
        return right - left - 1;  //此处要注意扩展子串时会多扩展一次，此处长度应减2，而下标相减应加1才为长度，故全式为 right-left+1-2
    }
}
```

**4.结果**

![image-20211119141847233](https://img.lccyj.ltd/img/image-20211119141847233.png)

## 6. ContainerWithMostWater   盛最多水的容器

**1.题目描述**

> 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
>
> 说明：你不能倾斜容器。
>
> ![img](https://img.lccyj.ltd/img/question_11.jpg)
>
> 输入：[1,8,6,2,5,4,8,3,7]
> 输出：49 
> 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
>
> 示例 2：
>
> 输入：height = [1,1]
> 输出：1
>
> 示例 3：
>
> 输入：height = [4,3,2,1,4]
> 输出：16
>
> 示例 4：
>
> 输入：height = [1,2,1]
> 输出：2
>
>
> 提示：
>
> n == height.length
> 2 <= n <= 105
> 0 <= height[i] <= 104
>
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/container-with-most-water

**2.初始思路**

> 用两个指针分别置于数组highs左右，当前左右的值分别为a，b，面积为min(a,b)*highs.length，然后与当前最大容量比较，更新为较大的容量，然后将数值较小的指针移动一位，此时面积变为min(a,b)*highs.length-1，当左指针等于右指针时，返回容量

**3.代码实现**

```java
/**
 * 6.盛最多水的容器
 * @author 刘淳
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int length= height.length-1; //容量的宽初始为数组长度减一
        int left=0;  //左指针
        int right= height.length-1;  //右指针
        int container=0;  //容量
        //当左指针等于右指针时，数组遍历完成
        while(left< height.length&&right>=0&&left!=right){
            int temp;
            //计算当前面积并更新长度与指针
            if (height[left]>height[right]) {
                temp = Math.min(height[left], height[right--]) * length--;
            }else {
                temp = Math.min(height[left++], height[right]) * length--;
            }
            //更新容量
            container=Math.max(temp,container);
        }
        return container;
    }
}
```

**4.结果**

![image-20211121214826548](https://img.lccyj.ltd/img/image-20211121214826548.png)

## 7. ThreeSum  三数之和

**1.题目描述**

> 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
>
> 注意：答案中不可以包含重复的三元组。
>
> 
>
> 示例 1：
>
> 输入：nums = [-1,0,1,2,-1,-4]
> 输出：[[-1,-1,2],[-1,0,1]]
>
> 示例 2：
>
> 输入：nums = []
> 输出：[]
>
> 示例 3：
>
> 输入：nums = [0]
> 输出：[]
>
>
> 提示：
>
> 0 <= nums.length <= 3000
> -105 <= nums[i] <= 105
>
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/3sum

**2.解题思路**

> 我们用三个指针i，j，k，首先将数组排序，然后用i遍历数组，j，k分别指向i后区域的左右边界，计算三数之和，当等于0时，记录值，大于零，尾部左移，小于0，首部右移

**3.代码实现**

```java
/**
 * 7.三数之和
 * @author 刘淳
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); //排序数组
        List<List<Integer>> list=new ArrayList<>();
        //数组长度小于3时直接返回空集
        if (nums.length<3){
            return list;
        }
        //遍历数组
        for (int i = 0; i < nums.length; i++) {
            int left=i+1;  //i后区域的左边界
            int right= nums.length-1;  //i后区域的右边界
            while (left<right){
                if (nums[i]+nums[left]+nums[right]==0){
                    //相加为0，保存结果
                    List<Integer> temp=new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    //判断是否已经存在
                    if (!list.contains(temp)){
                        list.add(temp);
                    }
                    left++;
                }else if (nums[i]+nums[left]+nums[right]>0){
                    //相加大于0，右指针左移
                    right--;
                }else if (nums[i]+nums[left]+nums[right]<0){
                    //相加小于0，左指针右移
                    left++;
                }
            }
        }
        return list;
    }
}
```

**4.结果**

![image-20211122175648883](https://img.lccyj.ltd/img/image-20211122175648883.png)

**5.改进思路**

> 以下几种情况为非必要判断
>
> > 1. 当nums[i]>0时，此时后面以没有与其相加等于0的数
> > 2. 当nums[i]==nums[i-1]时，为重复判断，可直接跳过
> > 3. 当三数之和等于0时，左右边界应移动直到与当前左右边界不相等

**6.改进代码**

```java
/**
 * 7.三数之和
 * @author 刘淳
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); //排序数组
        List<List<Integer>> list=new ArrayList<>();
        //数组长度小于3时直接返回空集
        if (nums.length<3){
            return list;
        }
        //遍历数组
        for (int i = 0; i < nums.length; i++) {
            int left=i+1;  //i后区域的左边界
            int right= nums.length-1;  //i后区域的右边界
            int base=nums[i];
            if (base>0) return list;  //i所处位置大于0，说明以不存在三数和为0的情况
            if (i>0&&base==nums[i-1]) continue; //当前位置与上一位置相等时，可跳过
            while (left<right){
                if (nums[left]+nums[right]==-base){
                    //相加为0，保存结果
                    List<Integer> temp=new ArrayList<>();
                    temp.add(base);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    list.add(temp);
                    while (left<right&&nums[left+1]==nums[left]) left++;  //右移左指针直到下一个数不相等
                    while (left<right&&nums[right-1]==nums[right]) right--;  //左移右指针直到下一个数不相等
                    //移动指针
                    left++;
                    right--;
                }else if (nums[left]+nums[right]>-base){
                    //相加大于nums[i]，右指针左移
                    right--;
                }else if (nums[left]+nums[right]<-base){
                    //相加小于0，左指针右移
                    left++;
                }
            }
        }
        return list;
    }
}
```

**7.改动结果**

![image-20211122182159243](https://img.lccyj.ltd/img/image-20211122182159243.png)
