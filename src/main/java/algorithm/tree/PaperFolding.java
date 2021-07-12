package algorithm.tree;

/**
 * 折纸问题：将纸向上折，记录折痕是凸凹的，N次后，从下往下，打印折痕情况。
 * 转化为树的中序遍历
 */
public class PaperFolding {
    
    public static void printAllFolds(int N){
        printProcess(1,N,true);
    }

    //递归过程，来到了某一个节点
    //i是节点层数，N一共的层数，down==true 凹 down == false 凸
    private static void printProcess(int i, int n, boolean down) {
        if (i > n){
            return;
        }
        printProcess(i+1,n,true);
        System.out.println(down?"凹":"凸");
        printProcess(i+1,n,false);
    }

    public static void main(String[] args) {
        printAllFolds(3);
    }
}
