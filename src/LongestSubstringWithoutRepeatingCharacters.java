import org.junit.Test;

import java.util.*;

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
