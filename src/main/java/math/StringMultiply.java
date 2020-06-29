package math;

/**
 * No.43 字符串相乘
 */
public class StringMultiply {

    public static void main(String[] args) {
//        String a = "9", b = "1";
//        StringMultiply sm = new StringMultiply();
//        System.out.println(sm.multiply_0(a, b));
//        System.out.println(sm.multiply_1(a, b));
        char[][] grid = new char[1][0];
        System.out.println(grid.length);
        System.out.println(grid[0].length);
    }

    /**
     * 第一版：使用加法实现乘法，开始时忽略了，9x9这种需要扩展乘积字符串长度的问题
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply_0(String num1, String num2) {
        if (num1 == null || num1.length() == 0 || num2 == null || num2.length() == 0) {
            return null;
        }
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        String a, b;
        if (num1.length() > num2.length()) {
            a = num1;
            b = num2;
        } else {
            a = num2;
            b = num1;
        }
        String r = "0";
        for (int i = 0; i < b.length(); i++) {
            StringBuilder c = new StringBuilder(a);
            for (int k = b.length() - i; k > 1; k--) {
                c.append("0");
            }
            int t = b.charAt(i) - '0';
            while (t > 0) {
                r = sum(r, c.toString());
                t--;
            }
        }
        return r;
    }

    private String sum(String a, String b) {
        if (a.length() < b.length()) {
            String c = a;
            a = b;
            b = c;
        }

        //len(a)>=len(b)
        char[] arr = a.toCharArray();
        boolean cf = false;

        for (int i = b.length() - 1, j = arr.length - 1; j >= 0 || cf; i--, j--) {
            int sum = cf ? 1 : 0;
            if (i >= 0) {
                sum += b.charAt(i) - '0';
            } else if (!cf) {
                break;
            }
            if (j >= 0) {
                int ai = a.charAt(j) - '0';
                sum += ai;
                arr[j] = String.valueOf(sum % 10).charAt(0);

            } else {
                //为arr增加长度，保留进位
                char[] arrPlus = new char[arr.length + 1];
                System.arraycopy(arr, 0, arrPlus, 1, arr.length);
                arr = arrPlus;
                arr[0] = String.valueOf(sum % 10).charAt(0);
            }
            cf = sum > 9;
        }
        return new String(arr);
    }

    /**
     * 第二版：看了题解
     * 用数组res保存乘积，然后转成字符串，数组res最大长度为len(num1)+len(num2)
     * 关键是找到num1[i]*num2[j]的乘积最终会落在res[i+j]和res[i+j+1]的位置上
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply_1(String num1, String num2) {
        if (num1 == null || num1.length() == 0 || num2 == null || num2.length() == 0) {
            return null;
        }
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int res[] = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = res[i + j + 1] + n1 * n2;
                //实际写一次计算过程，不然会忘了加上原来的数
                res[i + j] += sum / 10;
                res[i + j + 1] = sum % 10;
            }
        }
        StringBuilder rb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            //去掉首位的0
            if (i == 0 && res[i] == 0) {
                continue;
            }
            rb.append(res[i]);
        }
        return rb.toString();
    }

}
