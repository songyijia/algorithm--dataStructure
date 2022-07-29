package algorithm;


//2、设计和实现一个Least Recently Used (LRU) cache  的数据结构，它要支持 get 和put 操作，
//get(key) - 获取key对应的值，key不存在则返回-1， put(key, value)
//-写入key的值(包括新增或更新)，当缓存达到其容量时，它应该在插入新项之前使最近最少使用的项失效。
/**
 *  LRF LRU 动态规划
 */

import java.util.HashMap;

public class LRU {
    //链表节点，存键值对
    private class Node {
        int key;
        int val;
        Node next = null;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    //维护链表长度
    private int length = 0;
    //表头
    private Node list = new Node(0, 0);
    //LRU最大长度
    private int limit;
    //哈希表，存放键以及键对应的节点地址
    private HashMap<Integer, Node> map = new HashMap<>();

    LRU(int limit) {
        this.limit = limit;
    }

    //将链表节点移至表头
    private void moveNodeToFirst(Node node) {
        Node p = list;
        while (p.next != node) {
            p = p.next;
        }
        p.next = node.next;
        node.next = list.next;
        list.next = node;
    }

    //设置值，先用哈希表索引，若存在键则将节点移至表头，否则直接新建节点放在
    //表头，并判断长度
    public void set(int key, int value) {
        if (map.containsKey(key)) {
            map.get(key).val = value;
            moveNodeToFirst(map.get(key));
        } else {
            Node tmp = new Node(key, value);
            map.put(key, tmp);
            tmp.next = list.next;
            list.next = tmp;
            length++;
            if (length > limit) {
                length--;
                Node p = list;
                Node r = list.next;
                while (r.next != null) {
                    r = r.next;
                    p = p.next;
                }
                p.next = null;
                map.remove(r.key);
            }
        }
    }

    //取值，取完移至表头
    public int get(int key) {
        if (map.containsKey(key)) {
            moveNodeToFirst(map.get(key));
            return list.next.val;
        } else {
            return -1;
        }
    }

   public void print(){
        Node p = list;
        while (p.next != null){
            System.out.print(p.next.val+" ");
            p = p.next;
        }
       System.out.println();
   }

    public static void main(String[] args) {
        LRU lru = new LRU(3);
        lru.set(1,1);
        lru.set(2,2);
        lru.set(3,3);
        lru.print();
        lru.set(4,4);
        lru.print();
        System.out.println(lru.get(3));
        lru.print();
        System.out.println(lru.get(6));
        lru.print();
        lru.set(5,5);
        lru.print();
    }
}
