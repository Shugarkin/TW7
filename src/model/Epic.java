package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Epic extends Tasks {
    private List<Integer> subTaskIds = new ArrayList<>();//лист для id сабзадач

    private LocalDateTime endTime;

    public Epic(int id, String title, String description, Enum status) {
        super(id, title, description, status);
    }

    public Epic(String title, String description) {
        super(title, description);
    }

    public Epic(String title, String description, Enum status, LocalDateTime startTime, long duration) {
        super(title, description, status, startTime, duration);
    }

    public Epic(int id, String title, String description, Enum status, LocalDateTime start, LocalDateTime endTime) {
        super(id, title, description, status, start);
        this.endTime = endTime;
    }

    public void addSubTaskId(int epicId) {
        subTaskIds.add(epicId);
    }

    public List<Integer> getSubTaskId() {
        return subTaskIds;
    }

    public void setSubTaskIds(ArrayList<Integer> subTaskIds) {
        this.subTaskIds = subTaskIds;
    }

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "\n" +  getId() +
                "," +
                "Epic" +
                "," +
                getTitle() +
                "," +
                getStatus() +
                "," +
                getDescription() +
                "," +
                getStartTime() +
                "," +
                endTime;
    }
}

