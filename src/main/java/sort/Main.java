package sort;

/**
 * 测试 排序
 */
public class Main {
    public static void main(String []args){
        int[]arr=new int[]{2,8,3,7,1,7,0};
        InsertSort is=new InsertSort();
        is.insert(arr);
        for(int i:arr){
            System.out.print(i+" ");
        }
    }
}
