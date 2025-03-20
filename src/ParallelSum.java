import java.util.Random;
import java.util.concurrent.*;

public class ParallelSum {
    static int SIZE = 1_000_000;
    static int[] data = new int[SIZE];

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Random rand = new Random(42);
        for (int i = 0; i < SIZE; i++) {
            data[i] = rand.nextInt(100) + 1;
        }


        long start = System.nanoTime();
        long sumSerial = serialSum();
        long tempoSerial = (System.nanoTime() - start) / 1_000_000;


        start = System.nanoTime();
        long sumParallel2 = parallelSum(2);
        long tempoParallel2 = (System.nanoTime() - start) / 1_000_000;


        start = System.nanoTime();
        long sumParallel10 = parallelSum(10);
        long tempoParallel10 = (System.nanoTime() - start) / 1_000_000;


        double speedup2 = (double) tempoSerial / tempoParallel2;
        double efficiency2 = speedup2 / 2;

        double speedup10 = (double) tempoSerial / tempoParallel10;
        double efficiency10 = speedup10 / 10;

        System.out.println("Tempo Serial = " + tempoSerial + " ms");
        System.out.println("Tempo Paralelo (2) = " + tempoParallel2 + " ms");
        System.out.println("Tempo Paralelo (10) = " + tempoParallel10 + " ms");
        System.out.println("Speedup (2) = " + speedup2);
        System.out.println("Eficiência (2 threads) = " + efficiency2);
        System.out.println("Speedup (10) = " + speedup10);
        System.out.println("Eficiência (10 threads) = " + efficiency10);
    }

    public static long serialSum() {
        long sum = 0;
        for (int num : data) {
            sum += num;
        }
        return sum;
    }

    public static long parallelSum(int numThreads) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        int chunkSize = SIZE / numThreads;
        Future<Long>[] futures = new Future[numThreads];

        for (int i = 0; i < numThreads; i++) {
            final int start = i * chunkSize;
            final int end = (i == numThreads - 1) ? SIZE : (i + 1) * chunkSize;
            futures[i] = executor.submit(() -> {
                long sum = 0;
                for (int j = start; j < end; j++) {
                    sum += data[j];
                }
                return sum;
            });
        }

        long totalSum = 0;
        for (Future<Long> future : futures) {
            totalSum += future.get();
        }

        executor.shutdown();
        return totalSum;
    }
}
