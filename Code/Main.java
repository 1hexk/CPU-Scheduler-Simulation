
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Queue<Process> jobQueue = new LinkedList<>();

		Algorithms processor = new Algorithms(jobQueue);
		Process_reader PR = new Process_reader(jobQueue);

		Thread readFileThread = new Thread(new Runnable() {
			@Override
			public void run() {
				PR.readFile();

			}
		});
		
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print(
					"CHOOSE ONE OF THESE ALGORITHMS:\n1. First come first serve(FCFS)\n2. Round robin(RR)\n3. Multi-level feedback queue (RR8, RR16 and FCFS)\n0. Exit\n ==> ");
			try {
				int algorithmChoice = scanner.nextInt();

				Thread algorithmThread = null;
				switch (algorithmChoice) {
				case 0:
					break;
				case 1:
					algorithmThread = new Thread(new Runnable() {
						@Override
						public void run() {
							processor.FCFS();
						}
					});
					break;
				case 2:
					algorithmThread = new Thread(new Runnable() {
						@Override
						public void run() {
							processor.RR_10();
						}
					});
					break;
				case 3:
					algorithmThread = new Thread(new Runnable() {
						@Override
						public void run() {
							processor.multiLevelFeedbackQueue();
						}
					});
					break;
				default:
					System.out.println("Invalid choice. Please choose 1, 2, or 3.");
					return;
				}

				readFileThread.start();
				try {
					readFileThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				algorithmThread.start();
			} catch (Exception e) {
				System.out.println("Please enter an integer");
				}

			}
		}
	}
