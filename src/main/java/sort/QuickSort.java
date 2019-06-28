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
     *  找到p: p左边都比arr[compare]小，右边大
     */
    private int getPosition(int l,int r,Integer [] arr){
        //被比较的基准值位置
        int compare=l;
        int p=compare+1;
        for(int i=p;i<=r;i++){
            if(arr[i]<arr[compare]){
                swap(arr,i,p);
                //实现上p就是被交换到左边的所有元素的边界
                //p从l+1开始，交换n次就+n
                p++;
            }
        }
        //因为p++了,而且arr[p-1]<compare,可以交换
        swap(arr,compare,p-1);
        return p-1;
    }
}
