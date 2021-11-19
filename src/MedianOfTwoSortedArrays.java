import com.sun.deploy.util.ArrayUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 4.寻找两个正序数组的中位数
 * @author 刘淳
 */
//TODO 二分法优化时间复杂度为log(m+n)
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1=nums1.length;  // 数组1的长度
        int length2=nums2.length;  //数组2的长度
        int length=length1+length2;  //总长度
        int nums1Index=0;  //num1索引
        int nums2Index=0;  //num2索引
        int[] nums=new int[length1+length2];  //用于合并的数组
        //双指针合并有序数组
        for (int i = 0; i < nums.length; i++) {
            if (nums1Index<length1&&nums2Index<length2) {
                //两数组都未到边界
                if (nums1[nums1Index]<nums2[nums2Index]){
                    //nums1当前数值小
                    nums[i]=nums1[nums1Index]; //nums1当前数值加入nums
                    nums1Index++;  //nums1指针右移
                }else if (nums1[nums1Index]>=nums2[nums2Index]){
                    //num2当前数值小或两数相等
                    nums[i]=nums2[nums2Index]; //nums2当前数值加入nums
                    nums2Index++;  //nums2指针右移
                }
            }else if (nums1Index==length1){
                //nums1到达边界
                nums[i]=nums2[nums2Index]; //nums2当前数值加入nums
                nums2Index++;  //nums2指针右移
            }else if (nums2Index==length2){
                //nums2到达边界
                nums[i]=nums1[nums1Index]; //nums1当前数值加入nums
                nums1Index++;  //nums1指针右移
            }
        }
        if (length%2==0){
            //长度为偶数
            return (nums[length/2]+nums[(length/2)-1])/2.0;
        }else {
            //长度为奇数
            return nums[length/2];
        }
    }
}
