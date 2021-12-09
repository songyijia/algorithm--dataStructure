package algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.*;

public class AliTest {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        File file = new File("D:\\input.txt");
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader reader = new BufferedReader(isr);
        //目录、文件的大小结果统计 父目录及子目录分开统计
        Map<String,Long> path2Size = new ConcurrentHashMap<>();
        //目录、文件对应的大小级别
//        Map<String,String> path2Level = new ConcurrentHashMap<>();
        //大小级别相同的目录、文件出现次数
        Map<String,Integer> path2Num = new ConcurrentHashMap<>();

        String line = null;
        List<Future> futureList = new ArrayList<>();
        while ((line = reader.readLine())!=null){
            Future<Long> future = executorService.submit(new CountPathSize(line, path2Size));
            futureList.add(future);
        }
        System.out.println("统计任务提交完毕！");
        for (Future future : futureList) {
            future.get();
        }
        System.out.println("统计任务执行完毕！");
        //得到了所有文件、目录的大小，计算级别及统计次数
//        System.out.println("发令枪："+path2Size.keySet().size());
        MyHeap myHeap = new MyHeap<Result>(new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                return o2.num-o1.num;
            }
        });
        for (String path : path2Size.keySet()) {
            Long size = path2Size.get(path);
            String level = "A"+(size / 1024 +1);
            Result n = (Result)myHeap.get(level);
            if (n==null){
                myHeap.put(level,new Result(level,1));
            } else {
                n.num = n.num+1;
                myHeap.put(level,n);
            }
        }
//        PriorityQueue priorityQueue = new PriorityQueue(new Comparator<Result>() {
//
//            @Override
//            public int compare(Result o1, Result o2) {
//                return o2.num - o1.num;
//            }
//        });
//        for (String s : path2Num.keySet()) {
//            priorityQueue.add(new Result(s,path2Num.get(s)));
//        }
//        while (!priorityQueue.isEmpty()){
//            Result r = (Result)priorityQueue.poll();
//            System.out.println(r.level+" "+r.num);
//        }
        while (!myHeap.isEmpty()){
            Result r = (Result)myHeap.pop();
            System.out.println(r.level+" "+r.num);
        }

        executorService.shutdown();

    }
    private static class Result{
        String level;
        Integer num;

        public Result(String level, Integer num) {
            this.level = level;
            this.num = num;
        }
    }

    private static class CountPathSize implements Callable<Long> {
        String path;
        Map<String,Long> path2Size;
        public CountPathSize(String line, Map<String, Long> path2Size) {
            this.path = line;
            this.path2Size = path2Size;
        }

        @Override
        public Long call() {
            return count(new File(path),path2Size);
        }
        //统计所有目录及文件大小
        private long count(File path, Map<String, Long> path2Size) {
            //是文件，直接返回文件大小
            if (!path.isDirectory()){
                //在结果集中，保存第一层文件大小
                path2Size.put(path.getAbsolutePath(),path.length());
                return path.length();
            }
            //是目录，有计算结果，直接返回
            Long result = path2Size.get(path.getAbsolutePath());
            if (result != null){
                return result;
            }
            //没结果，则遍历目录下的文件，计算总和
            long size = 0;
            for (File file : path.listFiles()) {
                size+=count(file,path2Size);
            }
            //缓存已计算过目录的大小
            path2Size.put(path.getAbsolutePath(),size);
            return size;
        }
    }


    /**
     * 一种可进行计算，且随着计算值动态排序的数据结构
     */
    static class MyHeap<T>{
        private ArrayList<T> heap;
        private HashMap<T,Integer> indexMap;
        private HashMap<String,T> valueMap;
        private int heapSize;
        private Comparator<? super T> comparator;

        public MyHeap(Comparator<? super T> comparator) {
            this.heap = new ArrayList<>();
            this.indexMap = new HashMap<>();
            this.valueMap = new HashMap<>();
            this.heapSize = 0;
            this.comparator = comparator;
        }

        public T get(String level){
            return valueMap.get(level);
        }
        //get出来，修改value后，不用再set回去，因为是同一个对象，对象的引用不变
        //调用提供的put方法，将修改后的value 重新排序
        public void put(String level,T value){
            Integer index = indexMap.get(value);
            if (index == null) {
                valueMap.put(level, value);
                push(value);
            } else {
                resign(value);
            }
        }

        public boolean isEmpty(){
            return heapSize == 0;
        }

        public int size(){
            return heapSize;
        }

        public boolean contains(T key){
            return indexMap.containsKey(key);
        }

        public void push(T value){
            heap.add(value);
            indexMap.put(value,heapSize);
            heapInsert(heapSize++);
        }

        public T pop(){
            T ans = heap.get(0);
            int end = heapSize - 1;
            swap(0,end);
            heap.remove(end);
            indexMap.remove(ans);
//            valueMap.remove() valueMap 没法删！！
            heapify(0,--heapSize);
            return ans;
        }

        public void resign(T value){
            Integer valueIndex = indexMap.get(value);
            heapInsert(valueIndex);//在该索引之上的进行heapInsert操作，该索引之下的进行heapify操作
            heapify(valueIndex,heapSize);
        }

        private void heapify(int i, int heapSize) {
            int left = i*2+1;
            while (left<heapSize){
                int least = left+1<heapSize && (comparator.compare(heap.get(left+1),heap.get(left))<0) ? left+1:left;
                least = (comparator.compare(heap.get(least),heap.get(i))<0) ? least:i;
                if (least == i){
                    break;
                }
                swap(least,i);
                i = least;
                left = 2*i+1;
            }
        }

        private void swap(int i, int j) {
            T t1 = heap.get(i);
            T t2 = heap.get(j);
            heap.set(i,t2);
            heap.set(j,t1);
            indexMap.put(t1,j);
            indexMap.put(t2,i);
        }

        private void heapInsert(int i) {
            while (comparator.compare(heap.get(i),heap.get((i-1)/2))<0){
                swap(i,(i-1)/2);
                i = (i-1)/2;
            }
        }

    }
}
