package algorithm.tanxin;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集
 * 1.一开始所有样本都看作在单独集合
 * 设计集合，使查询元素在集合中和并集的效率较低
 *
 * P1(a，b,c) P2(x,y,z) 三个条件有1对应则为同一人，就连一块儿
 */
public class UnionFind {
    private static class Node<V>{
        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class UnionSet<V>{
        HashMap<V,Node<V>> nodes;
        HashMap<Node<V>,Node<V>> parents;
        //只有一个点，是代表点，才有大小
        HashMap<Node<V>,Integer> sizeMap;

        public UnionSet(List<V> values) {
            for (V value : values) {
                Node<V> node = new Node<>(value);
                nodes.put(value,node);
                parents.put(node,node);
                sizeMap.put(node,1);
            }
        }

        /**
         * 从点cur点开始向上找，
         * @param cur
         * @return
         */
        public Node<V> findFather(Node<V> cur){
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)){
                path.push(cur);
                cur = parents.get(cur);
            }
            // cur头节点
            while (!path.isEmpty()){
                parents.put(path.pop(),cur);
            }
            return cur;
        }

        public boolean isSameSet(V a,V b){
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return false;
            }
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a,V b){
            if (!nodes.containsKey(a)||!nodes.containsKey(b)){
                return;
            }
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead){
                Integer aSetSize = sizeMap.get(aHead);
                Integer bSetSize = sizeMap.get(bHead);
                if (aSetSize>=bSetSize){
                    parents.put(bHead,aHead);
                    sizeMap.put(aHead,aSetSize+bSetSize);
                    sizeMap.remove(bHead);
                } else {
                    parents.put(aHead,bHead);
                    sizeMap.put(bHead,aSetSize+bSetSize);
                    sizeMap.remove(aHead);
                }
            }
        }

        public int getSetNum() {
            return sizeMap.size();
        }
    }

    private static class User{
        String a;
        String b;
        String c;

        public User(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    /**
     * 合并Users,返回合并后的用户数
     */
    public static int mergeUsers(List<User> users){
        UnionSet<User> userUnionSet = new UnionSet<>(users);
        HashMap<String, User> mapA = new HashMap<>();
        HashMap<String, User> mapB = new HashMap<>();
        HashMap<String, User> mapC = new HashMap<>();
        for (User user : users) {
            if (mapA.containsKey(user.a)){
                userUnionSet.union(user,mapA.get(user.a));
            } else {
                mapA.put(user.a,user);
            }
            if (mapB.containsKey(user.b)){
                userUnionSet.union(user,mapB.get(user.b));
            } else {
                mapB.put(user.b,user);
            }
             if (mapC.containsKey(user.c)){
                userUnionSet.union(user,mapC.get(user.c));
            } else {
                 mapC.put(user.c,user);
            }

        }
        return userUnionSet.getSetNum();
    }
}
