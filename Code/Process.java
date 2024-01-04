
class Process {
	int id;
	final int burstTime;
	int burst;
	int memory;
	int arrivalTime;
	int priority;
	int waitingTime;
	int turnAroundTime;
	int completionTime;
	int remainingTime;

	public Process(int id, int burstTime, int memory) {
		this.id = id;
		this.burstTime = burstTime;
		this.memory = memory;
		this.arrivalTime = 0;
		this.priority = 0;
		this.waitingTime = 0;
		this.turnAroundTime = 0;
		this.completionTime = 0;
		this.remainingTime = burstTime;
		this.burst = burstTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public void setTurnAroundTime(int turnAroundTime) {
		this.turnAroundTime = turnAroundTime;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public int getBurst() {
		return burst;
	}

	public void setBurst(int i) {
		this.burst = i;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public int getId() {
		return id;
	}

	public int getMemory() {
		return memory;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public int getPriority() {
		return priority;
	}

	public int getTurnAroundTime() {
		return turnAroundTime;
	}

	public int getCompletionTime() {
		return completionTime;
	}

	public int getRemainingTime() {
		return remainingTime;
	}

	public void setCompletionTime(int completionTime) {
		this.completionTime = completionTime;
	}

	public void setRemainingTime(int i) {
		this.remainingTime = i;
	}

	public void setArrivalTime(int i) {
		this.arrivalTime = i;
	}

}