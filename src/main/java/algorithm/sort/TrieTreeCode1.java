package algorithm.sort;

/**
 * 这个代码存在bug,需要用对数器找出来
 */
public class TrieTreeCode1 {
    static class Node1{
        int pass;
        int end;
        Node1[] nexts;

        public Node1() {
            nexts = new Node1[26];
        }
    }

    static class Trie1{
        Node1 root;

        public Trie1() {
            root = new Node1();
        }

        public void insert(String word){
            if (word == null){
                return;
            }
            char[] chars = word.toCharArray();
            Node1 node = root;
            node.pass++;
            int index = 0;
            for (char c : chars) {
                index = c - 'a';
                if (node.nexts[index]==null){
                    node.nexts[index] = new Node1();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        public void delete(String str){
            if (str==null){
                return;
            }
            if (search(str)!=0){
                char[] chars = str.toCharArray();
                Node1 node = root;
                int index = 0;
                for (char aChar : chars) {
                    index = aChar - 'a';
                    if (--node.nexts[index].pass == 0){
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }

        public int search(String word){
            if (word == null){
                return 0;
            }
            char[] chars = word.toCharArray();
            Node1 node = root;
            int index = 0;
            for (char aChar : chars) {
                index = aChar - 'a';
                if (node.nexts[index]==null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        public int prefixNumber(String pre) {
            if (pre == null){
                return 0;
            }
            char[] chars = pre.toCharArray();
            Node1 node = root;
            int index = 0;
            for (char c : chars) {
                index = c - 'a';
                if (node.nexts[index]==null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }
}
