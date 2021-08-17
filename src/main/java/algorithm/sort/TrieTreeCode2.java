package algorithm.sort;

import java.util.HashMap;
import java.util.Random;

public class TrieTreeCode2 {
    static class Node2{
        int pass;
        int end;
        HashMap<Integer,Node2> nexts;

        public Node2() {
            nexts = new HashMap<>();
        }
    }

    static class Trie2{
        Node2 root = new Node2();

        void insert(String word){
            if (word == null){
                return;
            }
            char[] chars = word.toCharArray();
            Node2 node = root;
            node.pass++;
            int index = 0;
            for (char c : chars) {
                index = (int)c;
                if (!node.nexts.containsKey(index)){
                    node.nexts.put(index,new Node2());
                }
                node = node.nexts.get(index);
                node.pass++;
            }
            node.end++;
        }
        
        void delete(String word){
            if (search(word) != 0){
                char[] chars = word.toCharArray();
                Node2 node = root;
                node.pass--;
                int index = 0;
                for (char c : chars) {
                    index = (int)c;
                    if (--node.nexts.get(index).pass == 0){
                        node.nexts.remove(index);
                        return;
                    }
                    node = node.nexts.get(index);
                }
                node.end--;
            }
        }

        private int search(String word) {
            if (word == null){
                return 0;
            }
            char[] chars = word.toCharArray();
            Node2 node  = root;
            int index = 0;
            for (char c : chars) {
                index = (int)c;
                if (!node.nexts.containsKey(index)){
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.end;
        }

        int prefixNumber(String pre){
            if (pre == null){
                return 0;
            }
            char[] chars = pre.toCharArray();
            Node2 node = root;
            int index = 0;
            for (char c : chars) {
                index = (int)c;
                if (!node.nexts.containsKey(index)){
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.pass;
        }

        public void print() {
            Node2 cur = root;
            for (Integer integer : cur.nexts.keySet()) {
                System.out.print(integer+","+getTrie(cur.nexts.get(integer)));
            }
        }

        private String getTrie(Node2 cur) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("pass=").append(cur.pass)
                    .append("end=").append(cur.end);
            if (cur.nexts.values().size()!=0){
                for (Node2 node2 : cur.nexts.values()) {
                    stringBuilder.append(getTrie(node2));
                }
            }
            return stringBuilder.toString();
        }
    }


    static class Right{
        HashMap<String,Integer> box = new HashMap<>();

        void insert(String word){
            if (!box.containsKey(word)){
                box.put(word,1);
            } else {
                box.put(word,box.get(word)+1);
            }
        }

        void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                }else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        int search(String word){
            if (!box.containsKey(word)){
                return 0;
            } else {
                return box.get(word);
            }
        }

        int prefixNumber(String pre){
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)){
                    count+=box.get(cur);
                }
            }
            return count;
        }


    }

    public static void main(String[] args) {
        TrieTreeCode2.Trie2 trie2 = new TrieTreeCode2.Trie2();
//        Right right = new Right();
//        char[] chars = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n'};
//        Random random = new Random();
//        for (int n = 0; n < 100; n++) {
//            int length = random.nextInt(chars.length);
//            char[] strs = new char[length];
//            for (int i = 0; i < strs.length; i++) {
//                strs[i] = chars[random.nextInt(chars.length)];
//            }
//            String randomWord = new String(strs);
//            System.out.println(randomWord);
//            trie2.insert(randomWord);
//            right.insert(randomWord);
//        }
//        //生成若干随机字符串，并计算随机前缀出现的次数
//        for (int m = 0; m < 100; m++) {
//
//            char[] pre = new char[3];
//            for (int i = 0; i < 3; i++) {
//                pre[i] = chars[random.nextInt(chars.length)];
//            }
//            if (trie2.prefixNumber(new String(pre))==right.prefixNumber(new String(pre))){
//
//            }else {
//                System.err.println("不匹配："+new String(pre)+", trie2="+trie2.prefixNumber(new String(pre))+", right="+right.prefixNumber(new String(pre)));
//            }
//        }
        trie2.insert("abcd");
        trie2.insert("abc");
        trie2.insert("bc");
        trie2.insert("cd");
//        System.out.println(trie2.print(trie2.root));
        trie2.delete("abc");
//        System.out.println(trie2.print(trie2.root));
//        trie2.delete("abcd");
//        System.out.println(trie2.print(trie2.root));
//        trie2.delete("abcd");
//        System.out.println(trie2.print(trie2.root));
    }
}
