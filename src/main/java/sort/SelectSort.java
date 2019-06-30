package sort;

import java.awt.SystemTray;
import java.util.Arrays;

/**
 * author Xianfeng <br/>
 * date 19-6-14 下午2:28 <br/>
 * Desc: 选择排序，每次从右边(未排序)选择一个最小的放到左边末尾(已排序)
 */
public class SelectSort implements Sort {

    @Override
    public void sort(Integer[] arr) {
        for(int i=0;i<arr.length;i++){
            int indexMin=i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[indexMin]){
                    indexMin=j;
                }
            }
            if(indexMin!=i){
                int t=arr[i];
                arr[i]=arr[indexMin];
                arr[indexMin]=t;
            }
            System.out.println(Arrays.asList(arr));
        }
    }
}
