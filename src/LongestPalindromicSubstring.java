import org.junit.Test;

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
