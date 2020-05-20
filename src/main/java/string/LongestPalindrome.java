package string;

/**
 * author Xianfeng <br/>
 * date 20-5-20 下午8:55 <br/>
 * Desc:
 */
public class LongestPalindrome {

    static int longestPalSubstr(String str) {
        int n = str.length();   // get length of input string

        // table[i][j] will be false if substring str[i..j]
        // is not palindrome.
        // Else table[i][j] will be true
        boolean table[][] = new boolean[n][n];

        // All substrings of length 1 are palindromes
        int maxLength = 1;
        for (int i = 0; i < n; ++i) {
            table[i][i] = true;
        }

        // check for sub-string of length 2.
        int start = 0;
        for (int i = 0; i < n - 1; ++i) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                table[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        // Check for lengths greater than 2. k is length
        // of substring
        for (int k = 3; k <= n; ++k) {

            // Fix the starting index
            for (int i = 0; i < n - k + 1; ++i) {
                // Get the ending index of substring from
                // starting index i and length k
                int j = i + k - 1;

                // checking for sub-string from ith index to
                // jth index iff str.charAt(i+1) to
                // str.charAt(j-1) is a palindrome
                if (table[i + 1][j - 1] && str.charAt(i) ==
                        str.charAt(j)) {
                    table[i][j] = true;

                    if (k > maxLength) {
                        start = i;
                        maxLength = k;
                    }
                }
            }
        }
        System.out.print("Longest palindrome substring is; ");
        System.out.println(str.substring(start, start + maxLength));

        return maxLength; // return length of LPS
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int span = s.length();
        while (span > 1) {
            int space = s.length() - span;
            int begin = 0;
            while (begin <= space) {
                int l = begin, r = begin + span - 1;
                while (s.charAt(l) == s.charAt(r) && l < r) {
                    l++;
                    r--;
                }
                if (l >= r) {
                    return s.substring(begin, begin + span);
                }
                begin++;
            }
            span--;
        }
        return null;
    }
}
