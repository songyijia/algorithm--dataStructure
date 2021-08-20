package algorithm.tanxin;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 贪心算法：会议安排问题 求尽可能多的安排会议
 *
 */
public class MeetingAsign {
    static class Program{
        int start;
        int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int bestArrange1(Program[] programs){
        if (programs == null || programs.length ==0){
            return 0;
        }
        return process(programs,0,0);
    }

    /**
     *
     * @param programs  剩下的会议programs可以自由安排
     * @param done      已经安排了done多的会议
     * @param timeLine  目前来到timeLine的时间点
     * @return          返回能安排的最多会议数量
     */
    public static int process(Program[] programs,int done,int timeLine){
        if (programs.length == 0){
            return done;
        }
        // 还有会议可以选择
        int max = done;
        // 当前安排的会议是什么会，每一个都枚举
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLine){
                Program[] next = copyButExcept(programs,i);
                max = Math.max(max,process(next,done+1,programs[i].end));
            }
        }
        return max;

    }

    private static Program[] copyButExcept(Program[] programs, int i) {
        Program[] ans = new Program[programs.length-1];
        int index = 0;
        for (int k = 0; k < programs.length; k++) {
            if (k != i){
                ans[index++] = programs[k];
            }
        }
        return ans;
    }

    public static int bestArrange2(Program[] programs){
        Arrays.sort(programs,new ProgramComparator());
        int timeLine = 0;
        int result = 0;
        for (int i = 0; i < programs.length; i++) {
            if (timeLine <= programs[i].start){
                result++;
                timeLine = programs[i].end;
            }
        }
        return result;
    }

    private static class ProgramComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end-o2.end;
        }
    }

    public static void main(String[] args) {
        Program[] programs = new Program[5];
        programs[0]=  new Program(0,3);
        programs[1]=  new Program(3,5);
        programs[2]=  new Program(2,4);
        programs[3]=  new Program(5,8);
        programs[4]=  new Program(6,10);
        System.out.println(bestArrange1(programs));
        System.out.println(bestArrange2(programs));
    }
}
