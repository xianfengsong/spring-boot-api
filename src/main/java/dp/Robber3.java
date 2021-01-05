package dp;
//在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
// 这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“
//房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
// 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
//
// 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
//
// 示例 1:
//
// 输入: [3,2,3,null,3,null,1]
//
//     3
//    / \
//   2   3
//    \   \
//     3   1
//
//输出: 7
//解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.

/**
 * 打家劫舍III
 * 暴力解决，先化简成3个节点，列举所有情况，发现规律编写代码，然后优化存储空间
 * 既然是树形结构，那么首先要考虑 dfs , bfs 遍历
 * https://leetcode-cn.com/problems/house-robber-iii
 */
public class Robber3 {
    public int rob(TreeNode root) {
        return Math.max(dfs(root, false), dfs(root, true));
    }

    private int dfs(TreeNode root, boolean skip) {
        if (root == null) {
            return 0;
        }
        //栈空间爆炸
        int l1 = dfs(root.left, true);
        int l2 = dfs(root.left, false);
        int r1 = dfs(root.right, true);
        int r2 = dfs(root.right, false);
        if (!skip) {
            //选择当前节点抢劫
            return root.val + l1 + r1;
        } else {
            //不选择当前节点抢劫，那么选择 有 （左，右）（左，非右） （非左，右）（非左，非右） 四种，可以概括成 选择左右节点要或不要的最大值再求和
            return Math.max(l1, l2) + Math.max(r1, r2);
        }
    }

    /**
     * 把递归函数的返回值改成数组，保存一个节点 抢/不抢的收入，减少递归函数调用次数
     *
     * @param root
     * @return
     */
    public int robV1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] ans = dfs(root);
        return Math.max(ans[0], ans[1]);
    }

    /**
     * @param root
     * @return [抢，不抢]
     */
    public int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int rob = root.val + right[1] + left[1];
        int skip = Math.max(left[1], left[0]) + Math.max(right[1], right[0]);
        return new int[]{rob, skip};
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
