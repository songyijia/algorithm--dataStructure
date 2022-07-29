package algorithm;

public class MyLRUTest {
    class Node{
        String key;
        Object value;
        Node next;

        public Node(String key, Object value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    Node head = new Node(null,null,null);

    public void put(String key,Object value){
        Node _head = head;
        while (_head.next != null){
            if (!_head.next.key.equals(key)){
                _head = _head.next;
            }else {
                //有重复key 删掉旧的node
                _head.next = _head.next.next;

            }
        }
        //新节点放在前面
        Node newNode = new Node(key, value, head.next);
        head.next = newNode;

    }

    public Object get(String key){
        Node _head = head;
        while (_head.next != null){
            if (!_head.next.key.equals(key)){
                _head = _head.next;
            } else {
                Object value = _head.next.value;
                //将新元素放前面
                Node newNode = _head.next;
                _head.next = _head.next.next; // _head是上一个节点，指向下一个节点 （连上旧链）
                newNode.next = head.next;    // 新节点连上原头节点
                head.next = newNode;         // 重新设置新头
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        Node _head = head;
        StringBuilder stringBuilder = new StringBuilder();
        while (_head.next != null){
            stringBuilder.append(" "+_head.next.value);
            _head = _head.next;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        MyLRUTest myLRUTest = new MyLRUTest();
        myLRUTest.put("a","a");
        myLRUTest.put("b","b");
        myLRUTest.put("c","c");
        System.out.println(myLRUTest);
        myLRUTest.put("a","a");
        System.out.println(myLRUTest);
        Object b = myLRUTest.get("b");
        System.out.println("get b="+b);
        System.out.println(myLRUTest);
        Object w = myLRUTest.get("w");
        System.out.println("get w="+w);
        myLRUTest.put("d","d");
        System.out.println(myLRUTest);
    }
}
