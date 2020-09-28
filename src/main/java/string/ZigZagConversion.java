package string;

/**
 * No.6
 * z字变换
 */
public class ZigZagConversion {
    public static void main(String[] args) {
        String result = new ZigZagConversion().convert("PAYPALISHIRING", 3);
        System.out.println(result);
    }

    public String convert(String s, int numRows) {
        if (numRows == 0) {
            return null;
        }
        if (s == null || s.length() <= 1 || numRows == 1) {
            return s;
        }
        int len = s.length();
        int t = 0, c = 0;
        while (t < len) {
            if (c % 2 == 0) {
                t += numRows;
                c++;
            } else {
                t += numRows - 2;
                c += numRows - 2;
            }
        }
        int cols = c + 1;
        String[][] arr = new String[numRows][cols];
        char[] ch = s.toCharArray();
        boolean rev = false;
        int p = 0;
        for (int j = 0; j < cols; j++) {

            if (!rev) {
                int i = 0;
                while (i < numRows && p < len) {
                    arr[i++][j] = String.valueOf(ch[p++]);
                }
                if (i == numRows) {
                    rev = true;
                } else {
                    break;
                }
            } else {
                int r = numRows - 1;
                if (p < len) {
                    while (arr[r][j - 1] == null) {
                        r--;
                    }
                    if (r - 1 <= 0) {
                        rev = false;
                        j--;
                    } else {
                        arr[r - 1][j] = String.valueOf(ch[p++]);
                    }
                }
            }
        }
        StringBuilder bd = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < cols; j++) {
                if (arr[i][j] != null) {
                    bd.append(arr[i][j]);
                }
            }
        }
        return bd.toString();
    }
}
