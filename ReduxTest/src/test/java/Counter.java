import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Counter {

    private volatile int count;

    void increment(){
        count++;
    }

    int get(){
        return count;
    }

    public static void test() throws Exception{
        final Counter counter = new Counter();
        Runnable runnable = () -> {
            for(int i = 0;i < 10000; i++){
                counter.increment();
            }
        };

        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Boolean> future1 = executorService.submit(runnable,true);
        Future<Boolean> future2 = executorService.submit(runnable,true);

        if(future1.get() && future2.get()){
            System.out.println(counter.get());
        }else{
            System.err.println("FAILED");
        }

        executorService.shutdown();
    }
}
