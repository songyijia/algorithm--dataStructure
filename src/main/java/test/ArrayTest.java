package test;

public class ArrayTest {
    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=i;
        }
        Thread.sleep(Integer.MAX_VALUE);
    }
}
