package sort;

/**
 * 插入排序算法
 * 作者的灵感来自于斗地主，左手的牌都是有序的
 * 从左向右遍历，循环内从右边依次取新牌，往左插入合适位置
 * （注意越界）
 */
class InsertSort implements Sort {
    @Override
    public void sort(Integer[] arr) {
        if (arr.length < 2) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            //注意先判断j，否则越界
            while (j > 0 && arr[j - 1] > temp) {
                //j-1右移
                arr[j] = arr[j - 1];
                j--;
            }
            //插入到j
            arr[j] = temp;
        }
    }
}
