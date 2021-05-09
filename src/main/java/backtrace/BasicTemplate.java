package backtrace;

import java.util.ArrayList;
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
        System.out.println(t.allSub(arr));
    }

    // 1. 返回 数组的所有子集
    List<String> allSub;

    public List<String> allSub(int[] arr) {
        allSub = new ArrayList<>();
        dfs(arr, new ArrayList<>());
        return allSub;
    }

    public void dfs(int[] arr, List<Integer> path) {
        System.out.println(",path="+path);
        //退出条件
        if (path.size() == arr.length) {
            allSub.add(path.toString());
            System.out.println("add path="+path);
            return;
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
}
