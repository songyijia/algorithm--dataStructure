package algorithm.tanxin;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 贪心算法：会议安排问题
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
     * 目前来到timeLine的时间点，已经安排了done多的会议，剩下的会议programs可以自由安排
     * 返回能安排的最多会议数量
     * @param programs
     * @param done
     * @param timeLine
     * @return
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
}
