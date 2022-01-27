import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

// many threads
public class hw1COP4520NonSieve{
    public static final AtomicLong numPrimesFound = new AtomicLong(1);
    public static final AtomicLong sumOfAllPrimes = new AtomicLong(2);
    public static final long[] top10 = new long[10];

    public static final AtomicLong current = new AtomicLong((long)(1E8 - 1));
    public static final int numThreads = 8;
    public static final AtomicInteger top10Index = new AtomicInteger(9);

    public static void main(String[] args) throws InterruptedException{
        long timeStart = System.currentTimeMillis();

        ExecutorService service = Executors.newFixedThreadPool(numThreads);

        for(int i = 0; i < numThreads; i++){
            service.submit(new Primes());
        }

        service.shutdown();

        try{service.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);

        } catch(Throwable e) {
            System.out.println(e);
        }

        long timeEnd = System.currentTimeMillis();

        System.out.println("Execution time: " + (timeEnd - timeStart) + "ms");
        System.out.println("Total number of primes found: " + numPrimesFound.get());
        System.out.println("Sum of all primes found: " + sumOfAllPrimes.get());
        System.out.println("Top 10 maximum primes, listed in order from lowest to highest:");
        for(int i = 0; i < top10.length; i++){
            System.out.println(top10[i]);
        }

    }

    static class Primes extends hw1COP4520NonSieve implements Runnable {
    
        public void run(){
            long currLocal = current.getAndAdd(-2);
            while(currLocal >= 3){
                if(checkIfPrime(currLocal)){
                    numPrimesFound.getAndIncrement();
                    sumOfAllPrimes.getAndAdd(currLocal);

                    // gets top 10
                    int currTop10Index = top10Index.getAndDecrement();
                    if(currTop10Index >= 0){
                        top10[currTop10Index] = currLocal;
                    }
                }
                
                currLocal = current.getAndAdd(-2);
            }
        }
    
        public static boolean checkIfPrime(long num){
            for(long i = (long)Math.sqrt(num); i >= 2; i--){
                if(num % i == 0){
                    return false;
                }
            }
            return true;
        }
    }
}