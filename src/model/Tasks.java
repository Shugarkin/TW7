package model;

import java.time.Duration;
import java.time.LocalDateTime;

abstract public class Tasks {
    private int id;
    private String title;
    private String description;
    private Enum status;

    private LocalDateTime startTime ;

    private long duration;

    public Tasks(int id, String title, String description, Enum status, LocalDateTime startTime, long duration) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.startTime = startTime;
        this.duration = duration;
    }

    public Tasks(String title, String description, Enum status, LocalDateTime startTime, long duration) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.startTime = startTime;
        this.duration = duration;
    }

    public Tasks(int id, String title, String description, Enum status, LocalDateTime startTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.startTime = startTime;
    }

    public Tasks(int id, String title, String description, Enum status, LocalDateTime startTime, long duration, LocalDateTime endTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.startTime = startTime;
        this.duration = duration;
    }


    public LocalDateTime getEndTime() {
        if(startTime == null){
            return null;
        }
        return startTime.plus(Duration.ofMinutes(duration));
    }

    public Tasks(int id, String title, String description, Enum status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Tasks(String title, String description, Enum status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Tasks(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

//    public void setEndTime(LocalDateTime endTime) {
//        this.endTime = endTime;
//    }
}
