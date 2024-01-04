
# CPU Scheduler Simulation Project

## Project Objective

The objective of this project is to develop a Java program that simulates the behavior of a CPU Scheduler, creating multiple threads to perform specific tasks in the simulation. The focus is on implementing three distinct scheduling algorithms: First-Come-First-Serve (FCFS), Round-Robin (RR) with a quantum of 10ms, and Multi-level feedback queue (RR8, RR16, and FCFS).

## System Description

Consider a simple system with a single CPU that serves multiple jobs arriving at time 0. Each job has a Process Control Block (PCB) containing essential information such as ID, state, burst time, required memory, and necessary statistics (turnaround time and waiting time). The program reads process information from a file (input.txt) in an independent thread, creating PCBs and placing them in the job queue. Another thread continuously checks memory availability and loads jobs from the queue to the ready queue, ensuring sufficient memory space. The main thread executes the selected scheduling algorithm, allowing the user to choose from the available options.

### Structure of the Input File

A sample input file format for three jobs is as follows:

```
[Begin of job.txt] 
1:25;500 
2:13;700 
3:20;100 
[End of job.txt]
```
###  Input File:
1) There are no more than 30 jobs in the input file (input.txt). 
2) Processes arrive in the order they are read from the file for FCFS and RR-10. 
## Output

The program compares the average waiting times and the average turnaround times of all jobs for each scheduling algorithm. Detailed output, including the selected jobs at specific times, starting and stopping burst values, will be displayed in Gantt chart.

## Additional Considerations

- **Software and Hardware Used:**
  - The program is developed using the Java Development Kit (JDK) and the Eclipse Integrated Development Environment (IDE).
  -  Main memory is limited to 1024 MB.
  - Context switching time is zero.



- **Threads Created:**
  - Two threads are implemented: one for reading the input file and the other for executing the chosen scheduling algorithm.