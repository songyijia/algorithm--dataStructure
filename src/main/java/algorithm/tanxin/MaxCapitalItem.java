package algorithm.tanxin;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 有一定启动资金，可以做k个项目，每个项目有成本和利润，
 * 求最大利润
 */
public class MaxCapitalItem {
    private static class Program{
        int c;
        int p;

        public Program(int c, int p) {
            this.c = c;
            this.p = p;
        }
    }
    public static int findMaxCapital(int k,int w,int[] profits,int[] Capital){
        PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProComparator());
        for (int i = 0; i < profits.length; i++) {
            minCostQ.add(new Program(profits[i],Capital[i]));
        }
        for (int i = 0; i < k; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c <=w){
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()){
                return w;
            }
            w+=maxProfitQ.poll().p;
        }
        return w;
    }

    private static class MinCostComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.c-o2.c;
        }
    }

    private static class MaxProComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o2.p-o2.p;
        }
    }
}
