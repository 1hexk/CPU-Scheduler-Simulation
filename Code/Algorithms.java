
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Algorithms {
	
	Object lock = new Object();
	Queue<Process> jobQueue = new LinkedList<>();
	Queue<Process> readyQueue = new LinkedList<>();
	Queue<Process> Finished = new LinkedList<>();

	
	
	public static int maxMemory = 1024;

	int waitingTime = 0;
	int turnAroundTime = 0;

	


	public Algorithms(Queue<Process> jobQueue) {
		this.jobQueue = jobQueue;
	}

	public static void GannttChart(Process p, int firstNum, int NextNum) {
	    System.out.printf("|| p%-2d[ %d --> %d ] ", p.id, firstNum, NextNum);
	}

	public static void Table(Queue<Process> Final, int counter) {
	    Process[] p = Final.toArray(new Process[Final.size()]);


	    int TotalWaitTime = 0;
	    int TotalTurnAround = 0;
	    int actualCounter = 0;
	    for (int i = 0; i < counter; i++) {
	        actualCounter += 1;
	        TotalWaitTime += p[i].getWaitingTime();
	        TotalTurnAround += p[i].getTurnAroundTime();
	    }

	    System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	    System.out.printf("Average Waiting Time: %.2f\n", (TotalWaitTime / (double) actualCounter));
	    System.out.printf("Average Turnaround Time: %.2f\n", (TotalTurnAround / (double) actualCounter));
	}

	
	public void FCFS() {
		synchronized (lock) {
			JobLoader JL = new JobLoader(lock,jobQueue,readyQueue);
			int TotalTime = 0;

			while (!jobQueue.isEmpty() || !readyQueue.isEmpty()) {
				
				Thread JobLoader = new Thread(JL);
				JobLoader.run();
				
				for (Process process : readyQueue) {
					int waitingTime = TotalTime - process.getArrivalTime();
					process.setWaitingTime(Math.max(0, waitingTime));
					GannttChart(process, TotalTime, TotalTime + process.getRemainingTime());
					TotalTime += process.getBurstTime();
					int turnAroundTime = TotalTime - process.getArrivalTime();
					process.setTurnAroundTime(turnAroundTime);
					JL.updateAvailableMemory(process.getMemory());
					Finished.add(process);
				}
				readyQueue.clear();
			}
			
			Table(Finished, Finished.size());
		}
	}

	
	public void RR_10() {
		synchronized (lock) {
			JobLoader JL = new JobLoader(lock,jobQueue,readyQueue);
			int quantum = 10;
			int TotalTime = 0;
			int actual_counter=0;
			Queue<Process> Finished = new LinkedList<>();

			while (!jobQueue.isEmpty() || !readyQueue.isEmpty()) {
				
					Thread JobLoader = new Thread(JL);
					JobLoader.run();

					boolean term = false;
					while (!term && !readyQueue.isEmpty()) {
						Process process = readyQueue.poll();
						if (process.getRemainingTime() > 0) {
							if (process.getRemainingTime() > quantum) {
								GannttChart(process, TotalTime, TotalTime + Math.min(quantum, process.getRemainingTime()));
								TotalTime += Math.min(quantum, process.getRemainingTime());
								process.setRemainingTime(process.getRemainingTime() - quantum);
								readyQueue.add(process);
							} else {
								GannttChart(process, TotalTime, TotalTime + Math.min(quantum, process.getRemainingTime()));
								TotalTime += process.getRemainingTime();
								process.setWaitingTime(TotalTime - process.getBurstTime());
								process.setRemainingTime(0);
								process.setTurnAroundTime(TotalTime - process.getArrivalTime());
								Finished.add(process);
								actual_counter++;
								JL.updateAvailableMemory(process.getMemory());
								term = true;
							}
	
						}
					}
				}
			Table(Finished, actual_counter);
			}
		}
	
	public void multiLevelFeedbackQueue() {
	    synchronized (lock) {
	    	JobLoader JL = new JobLoader(lock,jobQueue,readyQueue);
	        int TotalTime = 0;
	        int actual_counter = 0;
	        Queue<Process> Finished = new LinkedList<>();

	        int[] quanta = {8, 16, 100000};
	        List<Queue<Process>> priorityQueues = new ArrayList<>();
	        for (int i = 0; i < quanta.length; i++) {
	            priorityQueues.add(new LinkedList<>());
	        	 }
	       

	        while (!jobQueue.isEmpty() || !readyQueue.isEmpty()) {	
	        	
	        	Thread JobLoader = new Thread(JL);
				JobLoader.run();
				
				while(!readyQueue.isEmpty() && !priorityQueues.isEmpty()) {
				    priorityQueues.get(0).add(readyQueue.poll());
				}
				
	            for (int i = 0; i < priorityQueues.size(); i++) {
	            	
	            	Queue<Process> currentQueue = priorityQueues.get(i);
	                

	                while (!currentQueue.isEmpty()) {
	                	Process process = currentQueue.poll();
	                    
	                    
	                    if (process.getRemainingTime() > 0) {
	                    	
	                        int quantum = quanta[i];
	                        int timeSlice = Math.min(quantum, process.getRemainingTime());
	                        
	                        GannttChart(process, TotalTime, TotalTime + timeSlice);
	                        TotalTime += timeSlice;
	                        process.setRemainingTime(process.getRemainingTime() - timeSlice);

	                        if (process.getRemainingTime() <= 0) {
	      
	                            process.setWaitingTime(TotalTime - process.getBurstTime());
	                            process.setTurnAroundTime(TotalTime - process.getArrivalTime());
	                            Finished.add(process);
	                            actual_counter++;
	                            JL.updateAvailableMemory(process.getMemory());
	                        } else {
	                            if (i + 1 < priorityQueues.size()) {
	                                priorityQueues.get(i + 1).add(process);
	                            } else {
	                                currentQueue.add(process);
	                            }
	                        }
	                    }
	                }
	            }
	        }

	        Table(Finished, actual_counter);
	    }
	}
}