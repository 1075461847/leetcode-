import org.junit.Test;

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
    @Test
    public void test(){
        int[] height={1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }
}
