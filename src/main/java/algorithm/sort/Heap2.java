package algorithm.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**【重点掌握】
 * 自定义业务堆，存放业务对象，并保持堆结构
 * 包含比较器，特殊对象值变化后，自动更新堆
 */
public class Heap2 {
    /**
     * 自己实现的业务小根堆，排序规则依靠比较器。
     * @param <T>
     */
    static class MyHeap<T>{
        private ArrayList<T> heap;
        // Java 的对象全都只能通过引用操作 上下容器里都存的引用
        private HashMap<T,Integer> indexMap;
        private int heapSize;
        private Comparator<? super T> comparator;

        public MyHeap(Comparator<? super T> comparator) {
            this.heap = new ArrayList<>();
            this.indexMap = new HashMap<>();
            this.heapSize = 0;
            this.comparator = comparator;
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
            heapify(0,--heapSize);
            return ans;
        }

        public void resign(T value){
            Integer valueIndex = indexMap.get(value);
            heapInsert(valueIndex);
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

    static class Student{
        public int classNo;
        public int age;
        public int id;

        public Student(int classNo, int age, int id) {
            this.classNo = classNo;
            this.age = age;
            this.id = id;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "classNo=" + classNo +
                    ", age=" + age +
                    ", id=" + id +
                    '}';
        }
    }

    public static void main(String[] args) {
        Student s1 = new Student(1, 22, 111);
        Student s2 = new Student(3, 24, 222);
        Student s3 = new Student(5, 26, 44);
        MyHeap heap2 = new MyHeap(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.age - o2.age;
            }
        });
        heap2.push(s1);
        heap2.push(s2);
        heap2.push(s3);
        s1.age = 90;
        s3.age = 4;
        heap2.resign(s1);
        heap2.resign(s3);
        while (!heap2.isEmpty()){
            System.out.println(heap2.pop());
        }
    }
}
