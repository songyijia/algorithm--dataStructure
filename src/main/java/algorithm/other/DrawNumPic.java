package algorithm.other;

import java.util.LinkedList;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

/**
 * 输入：3
 * 输出：
 * 1*2*3
 * 7*8*9
 * 4*5*6
 * 输入：4
 * 输出
 * 1*2*3*4
 * 9*10*11*12
 * 13*14*15*16
 * 5*6*7*8
 */
public class DrawNumPic {
    private int a=5;
    public static void main(String[] args) {
        int a=5;
        drawNumPic(5);
    }

    private static void drawNumPic(int n) {
        LinkedList<Integer> up = new LinkedList<>();
        LinkedList<Integer> down = new LinkedList<>();
        for (int i = 1; i <=n ; i++) {
            if (i%2==1){
                up.add(i*n);
            } else {
                down.addFirst(i*n);
            }
        }
        for (Integer end : up) {
            int start = end - n + 1;
            String line = IntStream.rangeClosed(start, end).mapToObj(String::valueOf).collect(joining("*"));
            System.out.println(line);
        }
         for (Integer end : down) {
            int start = end - n + 1;
            String line = IntStream.rangeClosed(start, end).mapToObj(String::valueOf).collect(joining("*"));
            System.out.println(line);
        }

    }
}
