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
            minCostQ.add(new Program(Capital[i],profits[i]));
        }
        int res = w;
        for (int i = 0; i < k; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c <=res){
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()){
                return w;
            }
            Program program = maxProfitQ.poll();
            res-=program.c;
            res+=program.p;
        }
        return res;
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
            return o2.p-o1.p;
        }
    }

    public static void main(String[] args) {
        int[] profits = {2,3,5,8} ,capital = {4,2,6,3};
        int k = 1,w=10;
        System.out.println(findMaxCapital(k,w,profits,capital));
    }
}
