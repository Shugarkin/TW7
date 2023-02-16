package service;

import model.Epic;
import model.SubTask;
import model.Task;
import model.Tasks;

import java.util.List;
import java.util.TreeSet;

public interface TaskManager {
    HistoryManager getHistoryManager();
    List<Task> getTasks();

    List<Epic> getEpics();

    List<SubTask> getSubTasks();

    void addTask(Task task);

    void addEpic(Epic epic);

    void addSubTask(SubTask subTask);

    Task printAllTask();

    Epic printAllEpic();

    void printAllSubTaskForEpic(int epicId);

    SubTask printAllSubTask();

    Task printTask(Integer taskId);

    Epic printEpic(Integer epicId);

    SubTask printSubTask(Integer subTaskId);

    void deleteTask();

    void deleteEpic();

    void deleteSubTask();

    void newTask(int id, Task task);

    void updateTask(int id, Task task);

    void updateTask(Task task);

    void newEpic(int id, Epic epic);

    void updateEpic(Epic epic);

    void newSubTask(int id, SubTask subTask);

    void updateSubTask(SubTask subTask);

    void deleteTaskForId(int id);

    void deleteEpicForId(int id);

    void deleteSubTaskForId(int id);

    void checkStatus(int epicId);

    List<Tasks> getHistory();

    Task getTask(Integer id);

    Epic getEpic(Integer id);

    SubTask getSubTask(Integer id);

    void durationEpicCheck(int epicId);

    Epic dateEpicCheck(int epicId);

    TreeSet getPrioritizedTasks();
}
