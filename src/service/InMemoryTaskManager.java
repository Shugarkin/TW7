package service;
import model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {

    protected HistoryManager historyManager = Managers.getDefaultHistory();


    protected HashMap<Integer, Task> tasks = new HashMap<>();
    protected HashMap<Integer, Epic> epics = new HashMap<>();
    protected HashMap<Integer, SubTask> subTasks = new HashMap<>();

    protected int nextId = 1;


    @Override
    public void addTask(Task task) { //добавление задач
        task.setId(nextId++);
        tasks.put(task.getId(), task);

    }
    @Override
    public void addEpic(Epic epic) { //добаление эпиков
        epic.setId(nextId++);
        epics.put(epic.getId(), epic);
        epic.setStatus(String.valueOf(Status.NEW));


    }
    @Override
    public void addSubTask(SubTask subTask) { //добавление сабзадач
        if(epics.isEmpty()) {
            throw  new NullPointerException("Список задач пуст");
        }
        subTask.setId(nextId++);
        subTasks.put(subTask.getId(), subTask);
        int epicId = subTask.getEpicId();
        epics.get(epicId).addSubTaskId(subTask.getId());
        checkStatus(epicId);
    }
    @Override
    public void printAllTask() { // печатает все задачи
        if(tasks.isEmpty()) {
            throw new NullPointerException("Список задач пуст");
        }
        for (Integer task : tasks.keySet()) {
            System.out.println(tasks.get(task));
        }
    }
    @Override
    public void printAllEpic() { // печатает все эпики
        if(epics.isEmpty()) {
            throw  new NullPointerException("Список эпиков пуст");
        }
        for (Integer epicx : epics.keySet()) {
            System.out.println(epics.get(epicx));
        }
    }

    @Override
    public void printAllSubTaskForEpic(int epicId) { //печатает подзадачи определенного эпика
        if(epics.isEmpty()) {
            throw  new NullPointerException("Список эпиков пуст");
        }
        if(subTasks.isEmpty()) {
            throw  new NullPointerException("Список подзадач пуст");
        }
        System.out.println(printEpic(epicId));
        Epic epic = epics.get(epicId); //определенный эпик
        if (!epic.getSubTaskId().isEmpty()) {
            for (Integer subTaskId : epic.getSubTaskId()) {
                if (epic.getSubTaskId().contains(subTaskId)) {
                    System.out.println(printSubTask(subTaskId));
                } else {
                    System.out.println("В данном EPIC нет подзадач.");
                }
            }
        }
    }

    @Override
    public void printAllSubTask() { // печатает все подзадачи
        if(subTasks.isEmpty()) {
            throw  new NullPointerException("Список подзадач пуст");
        }
        for (Integer sub : subTasks.keySet()) {
            System.out.println(subTasks.get(sub));
        }
    }

    @Override
    public String printTask(Integer taskId) { //метод для печати задач
        if(tasks.isEmpty()) {
            throw new NullPointerException("Список задач пуст");
        }
        try {
            tasks.containsKey(getTask(taskId).getId());
        } catch (NullPointerException e) {
            throw new NullPointerException("Возможно вы неверно ввели id или задача удалена");
        }
        if(tasks.containsKey(getTask(taskId).getId())) {
            return String.valueOf(tasks.get(taskId));
        } else {
            return "Упс";
        }
    }
    @Override
    public String printEpic(Integer epicId) { //метод для печати эпиков
        if(epics.isEmpty()) {
            throw  new NullPointerException("Список эпиков пуст");
        }
        try {
            epics.containsKey(getEpic(epicId).getId());
        } catch (NullPointerException e) {
            throw new NullPointerException("Возможно вы неверно ввели id или задача удалена");
        }
        if (epics.containsKey(getEpic(epicId).getId())) {
            return String.valueOf(epics.get(epicId));
        } else {
            return "Упс";
        }
    }
    @Override
    public String printSubTask(Integer subTaskId) { //метод для печати сабзадач
        if(subTasks.isEmpty()) {
            throw  new NullPointerException("Список подзадач пуст");
        }
        try {
            subTasks.containsKey(getSubTask(subTaskId).getId());
        } catch (NullPointerException e) {
            throw new NullPointerException("Возможно вы неверно ввели id или задача удалена");
        }
        if (subTasks.containsKey(getSubTask(subTaskId).getId())) {
            return String.valueOf(subTasks.get(subTaskId));
        } else {
            return "Возможно вы неверно ввели id  или задача удалена";
        }
    }
    @Override
    public void deleteTask() {//очищает задачи
        tasks.clear();
    }
    @Override
    public void deleteEpic() {//очищает эпики
        epics.clear();
        subTasks.clear();
    }
    @Override
    public void deleteSubTask() {//очищает подзадачи
        subTasks.clear();
    }

    @Override
    public void newTask(int id, Task task) {//заменяет старую задачу новой
        if (tasks.containsKey(id)) {
            task.setId(id);
            tasks.put(task.getId(), task);
        } else {
            System.out.println("Что-то пошло не так ((");
        }
    }
    @Override
    public void newEpic(int id, Epic epic) {//заменяет старый эпик новым
        if (epics.containsKey(id)) {
            epic.setId(id);
            epics.put(epic.getId(), epic);
        } else {
            System.out.println("Что-то пошло не так ((");
        }
    }
    @Override
    public void newSubTask(int id, SubTask subTask) {//заменяет старую подзадачу новой
        if (subTasks.containsKey(id)) {
            subTask.setId(id);
            subTasks.put(subTask.getId(), subTask);
            int epicId = subTask.getEpicId();
            epics.get(epicId).addSubTaskId(subTask.getId());
        } else {
            System.out.println("Что-то пошло не так ((");
        }
    }
    @Override
    public void deleteTaskForId(int id) {//удаляет задачу по id
        try {
            tasks.containsKey(id);
        } catch (NullPointerException e) {
            throw new NullPointerException("Задачи с таким id нет");
        }
        tasks.remove(id);
        historyManager.remove(id);
    }
    @Override
    public void deleteEpicForId(int id) {//удаляет эпик по id
        Epic epic = epics.get(id);
        if (!epic.getSubTaskId().isEmpty()) {
            for (Integer subTaskId : epic.getSubTaskId()) {
                if (epic.getSubTaskId().contains(subTaskId)) {
                    subTasks.remove(subTaskId);
                    if(historyManager.getHistoryMap().containsKey(subTaskId)) {
                        historyManager.getHistoryList().remove(subTaskId);
                    }
                }
            }
        }
        epics.remove(id);
        historyManager.remove(id);
    }
    @Override
    public void deleteSubTaskForId(int id) {//удаляет подзадачу по id
        subTasks.remove(id);
        historyManager.remove(id);
    }

    @Override
    public String checkStatus(int epicId) {
        ArrayList<String> statusSubTask = new ArrayList<>();
        Epic epic = epics.get(epicId); //определенный эпик
        for (Integer subTaskId : epic.getSubTaskId()) {
            statusSubTask.add(subTasks.get(subTaskId).status);
        }
        if (statusSubTask.contains("NEW") && !statusSubTask.contains("DONE") && !statusSubTask.contains("IN_PROGRESS")) { //почему-то если сравнивать через Status.*** сравнение происходит не корректно
            return epic.status = String.valueOf(Status.NEW);
        } else if (!statusSubTask.contains("NEW") && statusSubTask.contains("DONE") && !statusSubTask.contains("IN_PROGRESS")) {//тоже самое
            return epic.status = String.valueOf(Status.DONE);
        } else {
            return epic.status = String.valueOf(Status.IN_PROGRESS);
        }


    }

    @Override
    public HistoryManager getHistoryManager() {
        return historyManager;
    }
    @Override
    public Task getTask(Integer id) {
        Task task = tasks.get(id);
        historyManager.add(task);
        return tasks.get(id);
    }
    @Override
    public Epic getEpic(Integer id) {
        Epic epic = epics.get(id);
        historyManager.add(epic);
        return epics.get(id);
    }
    @Override
    public SubTask getSubTask(Integer id) {
        SubTask subTask = subTasks.get(id);
        historyManager.add(subTask);
        return subTasks.get(id);
    }

    @Override
    public HashMap<Integer, Task> getTasks() {
        if(tasks.isEmpty()) {
            throw new NullPointerException("Список задач пуст");
        }
        return tasks;

    }
    @Override
    public HashMap<Integer, Epic> getEpics() {
        if(epics.isEmpty()) {
            throw new NullPointerException("Список задач пуст");
        }
        return epics;
    }
    @Override
    public HashMap<Integer, SubTask> getSubTasks() {
        if(subTasks.isEmpty()) {
            throw new NullPointerException("Список задач пуст");
        }
        return subTasks;
    }

}
