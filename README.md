# CPU Scheduler Simulation in Java

## Project Overview

The study of CPU scheduling algorithms is a critical aspect of computer science and systems programming. In this project, we have developed a Java program to simulate the behavior of a CPU Scheduler. The simulation considers a simple system with a single CPU serving multiple jobs arriving at time 0. Three scheduling algorithms are implemented: First-Come-First-Serve (FCFS), Round-Robin (RR) with a quantum of 10ms, and Multi-level queue with two Round-Robins (quantum = 8ms for the first and quantum = 16ms for the second) and a First-Come-First-Serve.

## Software and Hardware Tools Used

The program is built using the Java Development Kit (JDK) and the Eclipse Integrated Development Environment (IDE).

## Strengths and Weaknesses

### Strengths
- The program reads process data from a file and implements three scheduling algorithms, enabling users to compare results.
- Users can easily test various scenarios by changing input data.
- Results of each algorithm are printed, allowing for a comprehensive analysis.

### Weaknesses
- The program lacks an aging mechanism, potentially leading to processes with long waiting times not being executed promptly.

## Threads Created

Two threads are implemented in the program:

1. **Read Thread:**
   - Reads the input file, parses the data, and stores it in a data structure for further processing.

2. **Algorithm Thread:**
   - Implements the chosen scheduling algorithm, schedules jobs, and manages resources efficiently.

## Multithreading Speed-Up Performance

Multithreading enhances application performance by allowing multiple tasks to run concurrently, optimizing resource usage. However, it introduces complexity and potential issues such as race conditions and deadlocks.

## Output Method

The program offers flexibility in output format preferences. A table format is suggested for educational purposes, presenting all relevant data for each operation concisely.

## Simulating the Operating System

Simulating an operating system involves emulating key features such as I/O operations, memory management, and other functionalities. This requires additional classes and functions in the program to handle specific tasks associated with operating systems.

## Structure of the Input File

A sample input file of three jobs is given as follows (Process ID: burst time in ms; Memory required in MB):

```
[Begin of job.txt] 
1:25;500 
2:13;700 
3:20;100 
[End of job.txt]
```

## Conclusion

In conclusion, this project simulates CPU scheduling behavior using various algorithms, providing insights into their functioning and impact on system performance. The practical experience gained in building Java programs and incorporating multi-threading concepts is invaluable. The resulting simulation can be used to compare and evaluate different CPU scheduling algorithms.