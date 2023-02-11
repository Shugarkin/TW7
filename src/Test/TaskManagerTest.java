package Test;

import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import service.InMemoryTaskManager;
import service.TaskManager;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {
    TaskManager taskManager = new InMemoryTaskManager();
    private Executable generateExecutableForEmptyMapTask() {//метод для создания ошибки с пустым списком таска
        return () -> taskManager.getTasks();
    }

    private Executable generateExecutableForEmptyMapEpic() {//метод для создания ошибки с пустым списком эпика
        return () -> taskManager.getEpics();
    }
    private Executable generateExecutableForEmptyMapSubtask() {//метод для создания ошибки с пустым списком сабтаска
        return () -> taskManager.getSubTasks();
    }

    private Executable generateExecutablePrintAllTaskTest() {
        return () -> taskManager.printAllTask();
    }

    private Executable generateExecutablePrintAllEpicTest() {
        return () -> taskManager.printAllEpic();
    }

    private Executable generateExecutablePrintAllSubtaskTest() {
        return () -> taskManager.printAllSubTask();
    }

    private Executable generateExecutablePrintTask(Integer id) {
        return () -> taskManager.printTask(id);
    }

    private Executable generateExecutablePrintEpic(Integer id) {
        return () -> taskManager.printEpic(id);
    }

    private Executable generateExecutablePrintSubTask(Integer id) {
        return () -> taskManager.printSubTask(id);
    }

    private Executable generateExecutableDeleteForIdTask(Integer id) {
        return () -> taskManager.deleteTaskForId(id);
    }

    private Executable generateExecutableDeleteForIdEpic(Integer id) {
        return () -> taskManager.deleteEpicForId(id);
    }

    private Executable generateExecutableDeleteForIdSubtask(Integer id) {
        return () -> taskManager.deleteSubTaskForId(id);
    }



    @Test
    void addTaskTest() { //тест для проверки метода создания таска

        Task task = new Task("Задача", "Для проверки", "NEW");
        taskManager.addTask(task);
        final int taskId = task.getId();

        final Task savedTask = taskManager.getTask(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");


        final Map<Integer, Task> tasks = taskManager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(1), "Задачи не совпадают.");
        assertEquals(tasks.get(3), null, "Неверный идентификатором задачи");

        //проверка на пустой список
        taskManager.deleteTask();
        NullPointerException e = Assertions.assertThrows(
                NullPointerException.class,
                generateExecutableForEmptyMapTask()
        );
        assertEquals(e.getMessage(), "Список задач пуст");
    }

    @Test
    void addEpicTest() { //тест для проверки метода создания эпика (тесты с проверкой статусов в отдельном классе)
        Epic epic = new Epic("Эпик", "Для проверки");
        taskManager.addEpic(epic);
        final int epicId = epic.getId();

        final Epic savedEpic = taskManager.getEpic(epicId);

        assertNotNull(savedEpic, "Задача не найдена.");
        assertEquals(epic, savedEpic, "Задачи не совпадают.");

        final Map<Integer, Epic> epics = taskManager.getEpics();

        assertNotNull(epics, "Задачи не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество задач.");
        assertEquals(epic, epics.get(1), "Задачи не совпадают.");
        assertEquals(epics.get(3), null, "Неверный идентификатором задачи");

        //проверка на пустой список
        taskManager.deleteEpic();
        NullPointerException e = Assertions.assertThrows(
                NullPointerException.class,
                generateExecutableForEmptyMapEpic()
        );
        assertEquals(e.getMessage(), "Список задач пуст");
    }

    @Test
    void addSubTaskTest() { //метод для проверки добавления сабтаска
        Epic epic = new Epic("Эпик", "Для проверки");
        SubTask subTask = new SubTask("Подзадача", "Для проверки", "NEW", 1);
        taskManager.addEpic(epic);
        taskManager.addSubTask(subTask);

        final  int epicId = epic.getId();
        final int subTaskId = subTask.getId();


        final Epic savedEpic = taskManager.getEpic(epicId);
        final SubTask savedSubtask = taskManager.getSubTask(subTaskId);

        assertNotNull(savedEpic, "Задача не найдена.");
        assertNotNull(savedSubtask, "Задача не найдена.");
        assertEquals(subTask, savedSubtask, "Задачи не совпадают.");

        final Map<Integer, SubTask> subTasks = taskManager.getSubTasks();

        assertNotNull(subTasks, "Задачи не возвращаются.");
        assertEquals(1, subTasks.size(), "Неверное количество задач.");
        assertEquals(subTask, subTasks.get(2), "Задачи не совпадают.");
        assertEquals(subTasks.get(3), null, "Неверный идентификатором задачи");

        //проверка на пустой список эпиков
        taskManager.deleteEpic();
        NullPointerException ex = Assertions.assertThrows(
                NullPointerException.class,
                generateExecutableForEmptyMapEpic()
        );
        assertEquals(ex.getMessage(), "Список задач пуст");

        //проверка на пустой список
        taskManager.deleteSubTask();
        NullPointerException e = Assertions.assertThrows(
                NullPointerException.class,
                generateExecutableForEmptyMapSubtask()
        );
        assertEquals(e.getMessage(), "Список задач пуст");
    }

    @Test
    public void printAllTaskTest() {//тест для проверки метода печати всех тасков
        NullPointerException e = Assertions.assertThrows(
                NullPointerException.class,
                generateExecutablePrintAllTaskTest()
        );
        Assertions.assertEquals(e.getMessage(), "Список задач пуст");
    }

    @Test
    public void printAllEpicTest() {//тест для проверки метода печати всех эпиков
        NullPointerException e = Assertions.assertThrows(
                NullPointerException.class,
                generateExecutablePrintAllEpicTest()
        );
        Assertions.assertEquals(e.getMessage(), "Список эпиков пуст");
    }

    @Test
    public void printAllSubtaskTest() {//тест для проверки метода печати всех сабтасков
        NullPointerException e = Assertions.assertThrows(
                NullPointerException.class,
                generateExecutablePrintAllSubtaskTest()
        );
        Assertions.assertEquals(e.getMessage(), "Список подзадач пуст");
    }

    @Test
    public void printTaskTest() {
        NullPointerException e = Assertions.assertThrows(
                NullPointerException.class,
                generateExecutablePrintTask(1)
        );
        Assertions.assertEquals(e.getMessage(), "Список задач пуст");

        Task task = new Task("Задача", "Для проверки", "NEW");
        taskManager.addTask(task);

        Assertions.assertEquals(taskManager.printTask(1), task.toString());

        NullPointerException ex = Assertions.assertThrows(
                NullPointerException.class,
                generateExecutablePrintTask(2)
        );
        Assertions.assertEquals("Возможно вы неверно ввели id или задача удалена", ex.getMessage());
    }

    @Test
    public void printEpicTest() {
        NullPointerException e = Assertions.assertThrows(
                NullPointerException.class,
                generateExecutablePrintEpic(1)
        );
        Assertions.assertEquals(e.getMessage(), "Список эпиков пуст");

        Epic epic = new Epic("Эпик", "Для проверки");
        taskManager.addEpic(epic);

        Assertions.assertEquals(taskManager.printEpic(1), epic.toString());

        NullPointerException ex = Assertions.assertThrows(
                NullPointerException.class,
                generateExecutablePrintEpic(2)
        );
        Assertions.assertEquals("Возможно вы неверно ввели id или задача удалена", ex.getMessage());
    }

    @Test
    public void printSubTaskTest() {
        NullPointerException e = Assertions.assertThrows(
                NullPointerException.class,
                generateExecutablePrintSubTask(1)
        );
        Assertions.assertEquals(e.getMessage(), "Список подзадач пуст");

        taskManager.addEpic(new Epic("Эпик", "Для проверки"));
        SubTask subTask = new SubTask("Задача", "Для проверки", "NEW", 1);
        taskManager.addSubTask(subTask);

        Assertions.assertEquals(taskManager.printSubTask(2), subTask.toString());

        NullPointerException ex = Assertions.assertThrows(
                NullPointerException.class,
                generateExecutablePrintSubTask(1)
        );
        Assertions.assertEquals("Возможно вы неверно ввели id или задача удалена", ex.getMessage());
    }

    @Test
    public void deleteTaskTest() {
        taskManager.addTask(new Task("Задача", "Для проверки", "NEW"));
        taskManager.deleteTask();

        NullPointerException e = Assertions.assertThrows(
                NullPointerException.class,
                generateExecutableForEmptyMapTask()
        );
        Assertions.assertEquals(e.getMessage(), "Список задач пуст", "Задача не удаленна");
    }

    @Test
    public void deleteTaskForIdTest() {
        taskManager.addTask(new Task("Задача", "Для проверки", "NEW"));
        taskManager.deleteTaskForId(2);

        NullPointerException ex = Assertions.assertThrows(
                NullPointerException.class,
                generateExecutableForEmptyMapTask()
        );
        Assertions.assertEquals(ex.getMessage(),"Список задач пуст", "Задача не удаленна по id");

        taskManager.addTask(new Task("Задача", "Для проверки", "NEW"));
        taskManager.deleteTaskForId(2);

        NullPointerException exp = Assertions.assertThrows(
                NullPointerException.class,
                generateExecutableDeleteForIdTask(2)
        );
        Assertions.assertEquals(exp.getMessage(), "Задачи с таким id нет");
    }



}