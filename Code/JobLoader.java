import java.util.Queue;

public class JobLoader extends Thread {
    public final Object lock;
    public Queue<Process> jobQueue;
    public Queue<Process> readyQueue;
    public final int maxMemory;
    public static int availableMemory;


    public JobLoader(Object lock, Queue<Process> jobQueue, Queue<Process> readyQueue) {
        this.lock = lock;
        this.jobQueue = jobQueue;
        this.readyQueue = readyQueue;
        this.maxMemory = 1024;
        availableMemory = this.maxMemory;
    }
   
    public synchronized void updateAvailableMemory(int memory) {
        availableMemory += memory;
    }

    @Override
    public void run() {
        while (!jobQueue.isEmpty()) {
            synchronized (lock) {
                while (!jobQueue.isEmpty() && jobQueue.peek().getMemory() <= availableMemory) {
                    Process process = jobQueue.poll();
                    readyQueue.offer(process);
                    availableMemory-=process.getMemory();
                }
                return;
            }
            
        }
    }
}