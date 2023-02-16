package model;

import java.time.LocalDateTime;

public class SubTask extends Tasks {

    protected int epicId;

    public SubTask(int id, String title, String description, Enum status, int epicId) {
        super(id, title, description, status);
        this.epicId = epicId;
    }

    public SubTask(String title, String description, Enum status, int epicId) {
        super(title, description, status);
        this.epicId = epicId;
    }

    public SubTask(String title, String description, Enum status, LocalDateTime startTime, long duration, int epicId) {
        super(title, description, status, startTime, duration);
        this.epicId = epicId;
    }

    public SubTask(int id, String title, String description, Enum status, LocalDateTime start, long duration, int epicId) {
        super(id, title, description,status, start, duration);
        this.epicId = epicId;
    }

    public SubTask(int id, String title, String description, Enum status, LocalDateTime startTime, long duration, LocalDateTime endTime, int epicId) {
        super(id, title, description, status, startTime, duration, endTime);
        this.epicId = epicId;
    }


    @Override
    public LocalDateTime getEndTime() {
        return super.getEndTime();
    }
    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return  "\n" + getId() +
                "," +
                "SubTask" +
                "," +
                getTitle() +
                "," +
                getStatus() +
                "," +
                getDescription() +
                "," +
                getStartTime() +
                "," +
                getDuration() +
                "," +
                getEpicId();
    }
}