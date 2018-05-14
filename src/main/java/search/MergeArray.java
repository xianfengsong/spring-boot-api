package search;

/**
 * 合并两个有序数组 num1可以容纳 num2
 */
public class MergeArray {

    public void merge(int []nums1,int m,int[]nums2,int n){

        for(int i=0;i<n;i++){
            int j=m;
            int move=nums2[i];
            while (j>0&&nums1[j-1]>move){
                nums1[j]=nums1[j-1];
                j--;
            }
            nums1[j]=move;
            m++;
        }
    }
}
