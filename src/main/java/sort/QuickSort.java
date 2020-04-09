package sort;

/**
 * author Xianfeng <br/>
 * date 19-6-14 下午2:43 <br/>
 * Desc:
 * 关键："递归分治" "找到中间点p"
 *
 * 1.找到position:
 * 选择基准值c,遍历数组找到位置p,并交换数组元素和arr[p],让p左边小于c，右边大于c
 * 最后交换arr[p]和c
 * 2.递归：
 * 然后从p将数组分成两份递归执行这个步骤
 */
public class QuickSort implements Sort{

    @Override
    public void sort(Integer[] arr) {
        if(arr.length<=1){
            return;
        }
        quickSort(0,arr.length-1,arr);
    }
    private void quickSort(int l, int r, Integer[] arr){
        if (l<r){
            int p=getPosition(l,r,arr);
            quickSort(l,p-1,arr);
            quickSort(p+1,r,arr);
        }
    }
    /**
     *  循环不变式A：
     *  p = getPosition 返回值
     *  对于任何 i < p: arr[i]<=a[p] , i>=p arr[i]>a[p]
     *
     */
    private int getPosition(int l,int r,Integer [] arr){
        //基准值
        int compare = arr[l];
        int p = l + 1;
        //循环不变式A1：
        // 1. l<i<=r
        // 2. 对于i < p , arr[i] < compare ,i >=p arr[i]>=compare
        for(int i=p;i<=r;i++){
            if (arr[i] < compare) {
                //交换:把小于基准值的堆到0-p这一侧，每次交换p+1
                swap(arr,i,p);
                p++;
            }
        }
        //因为循环不变式A1，把compare放到p-1后,循环不变式A得到满足
        //根据循环不变式A1 arr[p-1]<compare
        swap(arr, l, p - 1);
        return p-1;
    }
}
