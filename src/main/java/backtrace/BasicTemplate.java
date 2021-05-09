package backtrace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 回溯算法基本模板
 * 回溯本质是树的深度遍历
 * 需要处理后退操作
 * 需要处理递归退出条件
 * 需要注意对象拷贝
 */
public class BasicTemplate {

    public static void main(String[] args) {
        BasicTemplate t = new BasicTemplate();
        int[] arr = {1, 2, 3};
        List<String> allSub = t.allSub(arr);
        Collections.sort(allSub);
        System.out.println("所有子集：" + allSub);
        System.out.println("所有子集2:"+t.allSubWithOrder(arr));
        List<String> allSeq = t.allSeq(arr);
        System.out.println("全排列：" + allSeq);
    }

    List<String> allSub;

    /**
     * 模板一. 返回数组的所有子集(无重复数组)
     * 元素顺序不同，算作不同子集
     * 比如[1,2]，返回 "","1","2","12","21"
     */
    public List<String> allSub(int[] arr) {
        allSub = new ArrayList<>();
        dfs(arr, new ArrayList<>());
        return allSub;
    }

    public void dfs(int[] arr, List<Integer> path) {
        //类似前序遍历，只要遇到新路径，在向下遍历之前就保存
        if (!allSub.contains(path.toString())) {
            allSub.add(path.toString());
            System.out.println("add path=" + path);
        }
        //遍历树的所有子节点
        for (int anArr : arr) {
            //去掉已访问的节点
            if (path.contains(anArr)) {
                continue;
            }
            path.add(anArr);
            dfs(arr, path);
            path.remove(path.size() - 1);
        }
    }

    List<String> allSeq;
    List<List<Integer>> allSubWithOrder;
    /**
     * 模板二. 返回数组的所有子集(无重复数组)
     * 元素顺序不同，算作相同子集，所以子集不能有重复
     * 比如[1,2]，返回 "","1","2","12"
     */
    public List<List<Integer>> allSubWithOrder(int[] nums) {
        allSubWithOrder = new ArrayList<>();
        dfs(0, nums, new ArrayList<>());
        return allSubWithOrder;
    }

    public void dfs(int i, int[] nums, List<Integer> path) {
        allSubWithOrder.add(new ArrayList<>(path));
        for (int j = i; j < nums.length; j++) {

            path.add(nums[j]);

            dfs(j + 1, nums, path);

            path.remove(path.size() - 1);
        }
    }

    /**
     * 模板三， 返回数组的全排列(无重复数组)
     * 比如 [1,2] 返回 "12","21"
     */
    public List<String> allSeq(int[] arr) {
        allSeq = new ArrayList<>();
        dfs1(arr, new ArrayList<>());
        return allSeq;
    }

    public void dfs1(int[] arr, List<Integer> path) {
        //等path包含所有元素时再退出
        if (path.size() == arr.length) {
            allSeq.add(path.toString());
            return;
        }
        for (int i : arr) {
            if (path.contains(i)) {
                continue;
            }
            path.add(i);
            dfs1(arr, path);
            path.remove(path.size() - 1);
        }
    }
}
