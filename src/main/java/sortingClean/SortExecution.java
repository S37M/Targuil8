package sortingClean;

public class SortExecution {
    private long executionTime;
    private int executionCount;
    private long startTime;
    private long endTime;

    public SortExecution() {
        this.executionTime = 0L;
        this.executionCount = 0;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void endExecution() {
        executionTime += System.currentTimeMillis() - startTime;
        executionCount++;
    }

    public int getExecutionCount() {
        return executionCount;
    }
}
