package algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 快乐值计算：公司part邀请员工参加，每个员工参加带来的快乐值不同，
 * 若员工的直属领导来，则他下级员工不来，
 * 若员工的直属领导不来，则他下级可来或不来
 * 求最大快乐值
 */
public class MaxHappy {

    public static int maxHappy(Employee boss){
        if (boss == null){
            return 0;
        }
        HappyInfo all = process(boss);
        return Math.max(all.yes,all.no);
    }

    private static HappyInfo process(Employee boss) {
        if (boss.nexts.isEmpty()){
            return new HappyInfo(boss.happy,0);
        }
        int yes = boss.happy;
        int no = 0;
        for (Employee next : boss.nexts) {
            HappyInfo nextInfo = process(next);
            yes += nextInfo.no;
            no += Math.max(nextInfo.yes,nextInfo.no);
        }
        return new HappyInfo(yes,no);
    }

    private static class Employee {
        int happy;
        List<Employee> nexts = new ArrayList<>();
    }

    private static class HappyInfo {
        //来或不来的快乐值
        int yes;
        int no;

        public HappyInfo(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }
}
