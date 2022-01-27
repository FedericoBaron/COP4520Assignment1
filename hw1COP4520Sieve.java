import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// many threads
public class hw1COP4520Sieve{
    public static final boolean[] compositeList = new boolean[(int)1E8];
    public static final AtomicInteger current = new AtomicInteger(3);
    public static final int numThreads = 8;

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

        int pCount = 1;
        long sumOfP = 2;
        for(int i = 3; i < compositeList.length; i = i + 2){
            if(compositeList[i] == false){
                pCount++;
                sumOfP += i;
            }
        }

        int top10Index = 9;
        int[] top10 = new int[10];
        for(int i = compositeList.length - 1; i > 0; i--){
            if(compositeList[i] == false){
                top10[top10Index--] = i;
            }
            
            if(top10Index < 0){
                break;
            }
        }

        long timeEnd = System.currentTimeMillis();
        
        System.out.println("Execution time: " + (timeEnd - timeStart) + "ms");
        System.out.println("Total number of primes found: " + pCount);
        System.out.println("Sum of all primes found: " + sumOfP);
        System.out.println("Top 10 maximum primes, listed in order from lowest to highest:");
        for(int i = 0; i < top10.length; i++){
            System.out.println(top10[i]);
        }
        
    }

    static class Primes extends hw1COP4520Sieve implements Runnable {
    
        public void run(){
            int currLocal = current.getAndAdd(2);
            while(currLocal < compositeList.length){
                doSieve(currLocal);
                currLocal = current.getAndAdd(2);
            }
        }
    
        public static void doSieve(int num){
            int newNum = num * 2;
            while(newNum < compositeList.length){
                compositeList[newNum] = true;
                newNum += num;
            }
        }
    }
}