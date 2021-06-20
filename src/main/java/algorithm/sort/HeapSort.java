package algorithm.sort;

public class HeapSort {
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
