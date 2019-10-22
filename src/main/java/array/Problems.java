package array;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 初级算法： 数组类
 * <p>
 * 数组问题处理经验：
 * 记得考虑使用位运算
 * 写完要检查越界情况
 * 如果是查找类的问题，可以用Arrays.sort先排序,或者使用hashmap保存数据
 * <p>
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/21/
 */

public class Problems {


    /**
     * 旋转矩阵
     * <p>
     * 给定一个 n × n 的二维矩阵表示一个图像。
     * 将图像顺时针旋转 90 度。
     * <p>
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/31/
     * 第一遍遍历：用字符数组保存每一列的倒序
     * 第二遍遍历：依次把字符数组的值填充到矩阵
     * 缺点：没必要用额外的字符串数组存储
     */
    public void rotateV1(int[][] matrix) {
        int n = matrix[0].length;
        String[] lines = new String[n];
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (lines[i] == null) {
                    lines[i] = "";
                }
                lines[i] += String.valueOf(matrix[j][i]) + ",";
            }
        }
        for (int i = 0; i < n; i++) {
            String[] arr = lines[i].split(",");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.valueOf(arr[j]);
            }
        }
    }

    /**
     * 旋转矩阵V2
     * 先按链接左上角和右下角的对角线翻转，然后把矩阵的每一行倒序
     */
    public void rotateV2(int[][] matrix) {
        int n = matrix.length;
        // 对角线翻转
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
        // 倒序每一行
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
    }

    /**
     * 旋转矩阵V3
     * 思路：https://leetcode-cn.com/problems/rotate-image/solution/xuan-zhuan-tu-xiang-by-leetcode/
     * 还是O(n^2) 每次遍历同时移动四条边上的元素
     * 这里抽象出一个长方形，里面是要移动的元素，每层有4个长方形，由外向内逐层处理长方形内的元素
     * 实现方式没看懂
     */
    public void rotateV3(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < (n + 1) / 2; i++) {
            //每个长方形里的元素个数？
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }


    /**
     * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
     * <p>
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-sudoku
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //保证每个元素只读一次，所以每次循环，只能取一个元素，然后把它插入不同的位置
    public boolean isValidSudoku(char[][] board) {
        //map数组，保存9行 9列 9个单元格的数组
        HashMap<Integer, Integer>[] col = new HashMap[9];
        HashMap<Integer, Integer>[] row = new HashMap[9];
        HashMap<Integer, Integer>[] box = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            col[i] = new HashMap<>();
            row[i] = new HashMap<>();
            box[i] = new HashMap<>();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                int val = (int) c;
                //元素放入第j列
                col[j].put(val, col[j].getOrDefault(val, 0) + 1);
                //元素放入第i行
                row[i].put(val, row[i].getOrDefault(val, 0) + 1);
                //放入第index小单元格
                int index = j / 3 + i / 3 * 3;
                box[index].put(val, box[index].getOrDefault(val, 0) + 1);
                //分别检查行列和小单元格是否已经存在val
                if (row[i].get(val) > 1 || col[j].get(val) > 1 || box[index].get(val) > 1)
                    return false;
            }
        }

        return true;
    }

    /**
     * 判断一个 9x9 的数独是否有效
     * 优化 使用bit数组代替 hashmap
     * 遍历一次数独表格，
     *
     * @param board
     * @return
     */
    public boolean isValidSudokuV2(char[][] board) {
        int[] col = new int[9];
        int[] row = new int[9];
        int[] box = new int[9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                //val使用对应的bit保存数独中的数字
                // c-'1'转成 0到8的数字，然后通过左移 set对应bit值为1
                int val = 1 << c - '1';
                //index计算当前的数字属于哪个单元格
                int index = j / 3 + i / 3 * 3;
                //行列或单元格，与上val结果>0，说明第val个bit上为已经1，出现重复
                //必须在赋值前，否则永远>0
                if (((col[j] | row[i] | box[index]) & val) > 0) {
                    return false;
                }
                //通过或运算set 行/列/box中元素的左起第val个bit为1
                box[index] |= val;
                col[j] |= val;
                row[i] |= val;
            }
        }

        return true;
    }

    /**
     * 从"排序"数组中删除重复项,在原地删除重复出现的元素
     * 不要使用额外的数组空间
     *
     * @param nums 排序数组
     * @return 数组的新长度
     */
    public int removeDuplicates(int[] nums) {
        Set<String> s = new HashSet<>();
        s.clear();
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //这个简洁多了。。。
        int index = 0;
        for (int j = index + 1; j < nums.length; j++) {
            if (nums[index] != nums[j]) {
                index++;
                if (index != j) {
                    nums[index] = nums[j];
                }
            }
        }
        return index + 1;
    }

    /**
     * 买卖股票的最佳时机 II
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/22/
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        //注意处理特殊情况
        if (prices.length <= 1) {
            return profit;
        }
        int i = prices.length - 1;
        int value = prices[i--];
        while (i >= 0) {
            if (value > prices[i]) {
                profit += value - prices[i];
            }
            value = prices[i];
            i--;
        }
        return profit;
    }

    /**
     * 旋转数组
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/23/
     * 最简单的方法：每次移动向右一位，循环k次
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        if (k >= nums.length) {
            k = k % nums.length;
        }
        while (k != 0 && nums.length > 1) {
            int temp = nums[0];
            nums[0] = nums[nums.length - 1];
            for (int i = 1; i < nums.length; i++) {
                int t = nums[i];
                nums[i] = temp;
                temp = t;
            }
            k--;
        }
    }

    /**
     * 找规律：可以直接移动到制定位置，但是当数组元素是偶数个时，会出现循环
     * 所以如果又回到了开始的位置，就把指针向右移动一次
     * 直到所有元素被移动完成，旋转完成
     *
     * @param nums
     * @param k
     */
    public void rotate0(int[] nums, int k) {
        if (k >= nums.length) {
            k = k % nums.length;
        }
        for (int start = 0, count = 0; count < nums.length; start++) {
            int cur = start;
            int prev = nums[cur];
            do {
                int next = (cur + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                cur = next;
                count++;
            } while (cur != start);
        }
    }

    @Test
    public void testRotate() {
        int[] nums = new int[]{-1, -100, 3, 99};
        rotate(nums, 2);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    @Test
    public void testRemoveDuplicates() {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        Assert.assertEquals(5, removeDuplicates(nums));

    }


    //是否重复
    //先排序
    public boolean isRepeat(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 分析：
     * 异或大法好
     */
    public int theOnlyOne(int[] nums) {
        //因为其他元素都恰好出现两次，所以全部异或一遍，结果就是出现一次的数字（a^b^a=b）
        int result = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            result ^= nums[i];
        }
        return result;
    }

    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     * 要么排序，要么用hashmap，没有其他好方法？
     *
     * @return 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致
     */
    public int[] intersect(int[] nums1, int[] nums2) {

        byte[] mark = new byte[nums1.length];
        List<Integer> list = new ArrayList<>();
        for (int aNums2 : nums2) {
            for (int j = 0; j < nums1.length; j++) {
                if (mark[j] == 0 && aNums2 == nums1[j]) {
                    mark[j] = 1;
                    list.add(aNums2);
                    break;
                }
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }


    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/27/
     * 分析：
     * 用数组表示的整数，把它加一，再还原成数组
     * <p>
     * 注意越界的问题，如果转成数字可能会越界,所以选择按位一个个处理
     * int 最大值只有10个数字 21474_83647
     * long 最大值只有19个数字 9223_37203_68547_75807
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {

        int size = digits.length;
        if (size == 0) return digits;
        boolean plus = true;
        for (int i = size - 1; i >= 0 && plus; i--) {

            digits[i] += 1;
            if (digits[i] == 10) {
                digits[i] = 0;
                plus = true;
            } else {
                plus = false;
            }

        }
        //扩容
        if (plus) {
            //这样复制比较好写，代替System.arrayCopy()
            digits = new int[digits.length + 1];
            //如果还要进位，那第一位一定是1
            digits[0] = 1;
        }
        return digits;
    }
}
