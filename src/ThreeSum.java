import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    @Test
    public void test(){
        int[] nums={-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums));
    }
}
