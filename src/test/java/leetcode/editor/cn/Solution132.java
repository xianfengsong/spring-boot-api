package leetcode.editor.cn;
//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
//
// 返回符合要求的 最少分割次数 。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：s = "aab"
//输出：1
//解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
// 
//
// 示例 2： 
//
// 
//输入：s = "a"
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：s = "ab"
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 2000 
// s 仅由小写英文字母组成 
// 
// 
// 
// Related Topics 动态规划 
// 👍 348 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution132 {
    public int minCut(String s) {
        int part = 0;
        int len = s.length();
        String result = s;
        while (result.length()!=0&&len>0){
            String ans = clean(result,len);
            if(result.equals(ans)){
               len--;
            }else{
                part+=1;
                len = ans.length();
            }
            result = ans;
        }
        return Math.max(part-1,0);
    }
    public String clean(String s,int len){
        int i =0,j=i+len-1;
        char [] arr = s.toCharArray();
        while (i<=j&&j<s.length()){
            if(valid(arr,i,j)){
                String match = s.substring(i,j+1);
                return s.replaceFirst(match,"");
            }
            i++;
            j++;
        }
        return s;
    }
    public boolean valid(char [] arr,int i,int j){
        while (i<j && arr[i]==arr[j]){
            i++;
            j--;
        }
        return i>=j;
    }
    public static void main(String []args){
        Solution132 s=new Solution132();
        System.out.println(s.minCut("a"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
