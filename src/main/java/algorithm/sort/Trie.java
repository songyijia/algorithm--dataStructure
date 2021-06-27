package algorithm.sort;

import com.sun.deploy.util.StringUtils;

/**
 * 前缀树
 */
public class Trie {
    static class Node{
        int pass;
        int end;
        char value;
        Node[] next;

        public Node(char value) {

            this.value = value;
            this.next = new Node[26]; //用hashmap扩展字符
        }
    }
    Node root = new Node('\u0000');
    public void add(String str){
        char[] chars = str.toCharArray();
        Node cur = root;
        for (char c : chars) {
            int index = c - 'a';
            if (cur.next[index] == null){
                cur.next[index] = new Node(c);
            }
            cur.next[index].pass++;
            cur = cur.next[index];
        }
        cur.end++;
    }

    public int search(String str){
        char[] chars = str.toCharArray();
        Node cur = root;
        for (char c : chars) {
            int index = c-'a';
            if (cur.next[index] == null){
                return -1;
            }
            cur = cur.next[index];
        }
        return cur.end;
    }

    public void delete(String str){
        if (str == null || "".equals(str.trim())){
            return;
        }
        if (search(str)>0){
            char[] chars = str.toCharArray();
            Node cur = root;
            for (char c : chars) {
                int index = c-'a';
                cur.next[index].pass--;
                if (cur.next[index].pass==0){
                    cur.next[index] = null;
                }
                cur = cur.next[index];
            }
            cur.end--;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("abc");
        trie.add("ab");
        trie.add("abd");
        trie.add("abcd");
        System.out.println(trie.search("abc"));
        trie.delete("abc");
        System.out.println(trie.search("abc"));
        System.out.println(trie.search("abcd"));
    }
}
