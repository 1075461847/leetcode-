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

