import java.io.File;
import java.io.FileNotFoundException;
import java.util.Queue;
import java.util.Scanner;

public class Process_reader {

	Queue<Process> jobQueue;

	public Process_reader(Queue<Process> jobQueue) {
		this.jobQueue = jobQueue;
	}

	public void readFile() {
		synchronized (jobQueue) {
			try {
				File file = new File("input.txt");
				Scanner scanner = new Scanner(file);
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					String[] parts = line.split(":");
					String strProcessId = parts[0].trim();
					if (!strProcessId.isEmpty()) {
						int processId = Integer.parseInt(strProcessId);
						String[] burstAndMemory = parts[1].split(";");
						String strBurstTime = burstAndMemory[0].trim();
						if (!strBurstTime.isEmpty()) {
							int burstTime = Integer.parseInt(strBurstTime);
							String strMemory = burstAndMemory[1].trim();
							if (!strMemory.isEmpty()) {
								int memory = Integer.parseInt(strMemory);
								if (memory <= Algorithms.maxMemory) {
									jobQueue.add(new Process(processId, burstTime, memory));
								}
							} else {
								System.out.println("Error: empty string for memory");
							}
						} else {
							System.out.println("Error: empty string for burst time");
						}
					} else {
						System.out.println("Error: empty string for process ID");
					}
				}
				scanner.close();
			} catch (FileNotFoundException e) {
				System.out.println("File not found.");
			}
		}
	}

}