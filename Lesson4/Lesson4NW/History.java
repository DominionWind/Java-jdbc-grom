package Lesson4.Lesson4NW;

public class History {
    private Long id;
    private OperationType operationType;
    private Long timeProcessed;
    private Status status;

    public History(Long id, OperationType operationType, Long timeProcessed, Status status) {
        this.id = id;
        this.operationType = operationType;
        this.timeProcessed = timeProcessed;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public Long getTimeProcessed() {
        return timeProcessed;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", operationType=" + operationType +
                ", timeProcessed=" + timeProcessed +
                ", status=" + status +
                '}';
    }
}
