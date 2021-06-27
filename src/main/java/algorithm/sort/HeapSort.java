package algorithm.sort;

public class HeapSort {
    //堆排序额外空间复杂度O(1)
    public static void heapSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        //O(N*logN)
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr,i);
        }
        //到这里已经是大根堆

        //优化方案：倒着使小节点成为大根堆
//        for (int i = arr.length-1; i >= 0 ; i--) {
//            heapify(arr,i,arr.length);
//        }


        int heapSize = arr.length;
        //一直把最大值放最后面，
        swap(arr,0,--heapSize);
        //O(N*logN)
        while (heapSize > 0){ //O(N)
            heapify(arr,0,heapSize);//O(logN)
            swap(arr,0,--heapSize); //O(1)
        }
        //完成后，数组递增
    }

    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[b];
        arr[b] = arr[a];
        arr[a] = tmp;
    }

    //下沉
    private static void heapify(int[] arr, int i, int heapSize) {
        int left = i*2+1;
        while (left<heapSize){
            int largest = left+1<heapSize && arr[left+1]>arr[left] ? left+1:left;
            largest = arr[largest] > arr[i] ? largest:i;
            if (largest == i){
                break;
            }
            swap(arr,largest,i);
            i = largest;
            left = 2*i+1;
        }

    }

    //上浮
    private static void heapInsert(int[] arr, int i) {
        while (arr[i] > arr[(i-1)/2]){
            swap(arr,i,(i-1)/2);
            i = (i-1)/2;
        }

    }

    /**
     * 堆结构
     */
    static class MyHeap{
        int[] heap;
        int heapSize = 0;
        int limit;
        public MyHeap(int heapSize) {
            heap = new int[heapSize];
            this.limit = heapSize;
            
        }
        
        public boolean isEmpty(){
            return heapSize == 0;
        }
        
        public boolean isFull(){
            return heapSize == limit;
        }
        
        public void push(int value){
            if (heapSize == limit){
                throw new RuntimeException("heap is full!");
            }
            heap[heapSize] = value;
            heapInsert(heap,heapSize++);
        }
        
        public int pop(){
            int ans = heap[0];
            swap(heap,0,--heapSize);
            heapify(heap,0,heapSize);
            return ans;
        }

        private void heapify(int[] arr, int index, int heapSize) {
            int left = index*2+1;
            while (left < heapSize){
                //有右孩子，且比左孩子大
                int largest = left+1 < heapSize && arr[left+1]>arr[left]?left+1:left;
                //孩子跟爹比
                largest = arr[largest] > arr[index] ? largest:index;
                if (largest == index){
                    break;
                }
                swap(arr,largest,index);
                index = largest;
                left = index*2+1; //下移

            }
        }

        private void heapInsert(int[] arr, int index) {
            while (arr[index]>arr[(index-1)/2]){
                swap(arr,index,(index-1)/2);
                index = (index-1)/2;
            }
        }

        private void swap(int[] arr, int a, int b) {
            int tmp = arr[b];
            arr[b] = arr[a];
            arr[a] = tmp;
            
        }
    }

    public static void main(String[] args) {
        MyHeap myHeap = new MyHeap(5);
        myHeap.push(4);
        myHeap.push(6);
        myHeap.push(2);
        myHeap.push(1);
        myHeap.push(3);
        while (!myHeap.isEmpty()){
            System.out.println(myHeap.pop());
        }
    }
}
