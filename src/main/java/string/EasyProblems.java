package string;

import org.junit.Test;

public class EasyProblems {
    @Test
    public void t() {
        System.out.println(countAndSay(30));
    }

    /**
     * 报数
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/39/
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {

        return count(1, n, "1");
    }

    public String count(int start, int target, String last) {
        if (start == target) {
            return last;
        } else {
            char[] arr = last.toCharArray();
            int count = 1;
            int i = 0;
            StringBuilder s = new StringBuilder();
            while (i < arr.length) {
                while (i < arr.length - 1 && arr[i] == arr[i + 1]) {
                    count++;
                    i++;
                }
                s.append(count).append(arr[i]);
                count = 1;
                i++;
            }
            return count(start + 1, target, s.toString());
        }
    }
}
