package Test;

import model.Epic;
import model.SubTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.InMemoryTaskManager;
import service.TaskManager;

class EpicStatusTest {
    TaskManager taskManager = new InMemoryTaskManager();
        @Test
        public void emptyListOfSubtasks() {
            Epic epic = new Epic("Эпик", "Для проверки");
            taskManager.addEpic(epic);
            Assertions.assertEquals("NEW", epic.getStatus(), "Неверно определяет статус Epic при создании без подзадач");
        }

        @Test
        public void allSubtasksWithStatusNew() {
            Epic epic = new Epic("Эпик", "Для проверки");
            taskManager.addEpic(epic);
            taskManager.addSubTask(new SubTask("Подзадача 1", "Для теста", "NEW", 1));
            taskManager.addSubTask(new SubTask("Подзадача 2", "Для теста", "NEW", 1));
            Assertions.assertEquals("NEW", epic.getStatus(), "Неверно определяет статус Epic при создании когда подзадачи с статусом NEW");
        }

        @Test
        public void allSubtasksWithStatusDone() {
            Epic epic = new Epic("Эпик", "Для проверки");
            taskManager.addEpic(epic);
            taskManager.addSubTask(new SubTask("Подзадача 1", "Для теста", "DONE", 1));
            taskManager.addSubTask(new SubTask("Подзадача 2", "Для теста", "DONE", 1));
            Assertions.assertEquals("DONE", epic.getStatus(), "Неверно определяет статус Epic при создании когда подзадачи с статусом DONE");
        }

        @Test
        public void allSubtasksWithStatusDifferent() {
        Epic epic = new Epic("Эпик", "Для проверки");
            taskManager.addEpic(epic);
            taskManager.addSubTask(new SubTask("Подзадача 1", "Для теста", "NEW", 1));
            taskManager.addSubTask(new SubTask("Подзадача 2", "Для теста", "DONE", 1));
            Assertions.assertEquals("IN_PROGRESS", epic.getStatus(), "Неверно определяет статус Epic при создании когда подзадачи с разным статусом ");
        }

        @Test
        public void allSubtasksWithStatusInProgress() {
            Epic epic = new Epic("Эпик", "Для проверки");
            taskManager.addEpic(epic);
            taskManager.addSubTask(new SubTask("Подзадача 1", "Для теста", "IN_PROGRESS", 1));
            taskManager.addSubTask(new SubTask("Подзадача 2", "Для теста", "IN_PROGRESS", 1));
            Assertions.assertEquals("IN_PROGRESS", epic.getStatus(), "Неверно определяет статус Epic при создании когда подзадачи с статусом IN PROGRESS");
        }
}