package algorithm.recursive;

/**
 * 汉诺塔问题
 */
public class HannuoTower {

    public static void hanoi2(int n){
        if (n>0){
            func(n,"left","right","mid");
        }
    }

    /**
     * 要想把n移过去，得先把n-1上面的移走。
     * @param n
     * @param from
     * @param to
     * @param other
     */
    private static void func(int n, String from, String to, String other) {
        if (n ==1){
            System.out.println("move 1 from "+from+" to "+to);
        } else {
            func(n-1,from,other,to);
            System.out.println("move "+n+" from "+from+" to "+ to);
            func(n-1,other,to,from);
        }
    }

    public static void main(String[] args) {
        hanoi2(3);
    }
}
