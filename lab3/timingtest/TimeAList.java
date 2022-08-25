package timingtest;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        /* N from 1000 to 128000 */
        int[] ranges = new int[]{1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        for (int range : ranges) {
            AList<Integer> aList = new AList<>();
            // initialize a stopwatch
            Stopwatch sw = new Stopwatch();
            // run your timing code
            for (int j = 0; j < range; ++j) {
                aList.addLast(j);
            }
            // get the time
            double timeInSeconds = sw.elapsedTime();
            Ns.addLast(range);
            times.addLast(timeInSeconds);
            opCounts.addLast(range);
        }
        printTimingTable(Ns, times, opCounts);
    }
}
