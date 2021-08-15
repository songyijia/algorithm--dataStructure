package algorithm.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 手写堆结构
 */
public class MyHeap2<T> {
//    T[] values; //数组就是大小固定的堆
    ArrayList<T> values;
    HashMap<T,Integer> indexMap; //记录value的下标的map
    int size;  //堆大小用单独变量控制！！！
    Comparator<T> comparator;

//    public MyHeap2(T[] values, int size) {
//        this.values = values;
//        this.size = size;
//    }


    public MyHeap2(Comparator<T> comparator) {
        values = new ArrayList<>();
        indexMap = new HashMap<>();
        this.comparator = comparator;
    }

    public void push(T value){
//        int index = values.size();
        indexMap.put(value,size);
        values.add(value);
//        size++;
        heapInsert(size++);
    }

    //后面的比前面大则交换：大根堆
    private void heapInsert(int index) {
        while ((index-1)/2>=0 && comparator.compare(values.get(index),values.get((index-1)/2))>0){
            swap(index,(index-1)/2);
            index = (index-1)/2;
        }
    }

    private void swap(int index, int i) {
        T t1 = values.get(index);
        T t2 = values.get(i);
        indexMap.put(t1,i);
        indexMap.put(t2,index);
        values.set(i,t1);
        values.set(index,t2);
    }

    public T pop(){
        T res = values.get(0);
        int index = --size;
        swap(0,index);
        indexMap.remove(res);
        values.remove(res);
        heapify(0,--index);
        return res;
    }

    private void heapify(int index, int size) {
        int left = 2*index+1;
        while (left<=size){
            int largest = left;
            if (left+1 < size){
                largest = comparator.compare(values.get(left+1) , values.get(left))>0 ? left+1:left;
            }
            if (comparator.compare(values.get(largest),values.get(index))<0){
                largest = index;
            }
            if (largest==index){
                //提前比较完，跳出
                break;
            }
            //孩子大
            swap(largest,index);
            //2个指针要跟着变
            index=largest;
            left=2*index+1;

        }
    }

    public boolean isEmpty(){
        return size==0;
    }

    public static void main(String[] args) {
        Student a = new Student("a", 11);
        Student b = new Student("b", 24);
        Student c = new Student("c", 4);
        MyHeap2<Student> heap2 = new MyHeap2<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.age - o2.age;
            }
        });
        heap2.push(a);
        heap2.push(b);
        heap2.push(c);
        while (!heap2.isEmpty()){
            System.out.println(heap2.pop());
        }
    }

}
class Student{
    String name;
    int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}