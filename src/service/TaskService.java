package service;

import model.Task;
import model.Project;
import model.User;

import repository.InMemoryTaskRepository;
import repository.InMemoryProjectRepository;

import java.util.List;

public class TaskService {

    private InMemoryTaskRepository taskRepository = new InMemoryTaskRepository();
    private InMemoryProjectRepository projectRepository = new InMemoryProjectRepository();

    // task crud operations
    public void createTask(Task task){
        taskRepository.save(task);
    }

    public Task getTaskById(int id){
        return taskRepository.findById(id);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public void updateTask(Task task){
        taskRepository.update(task);
    }

    public void deleteTask(int id){
        taskRepository.delete(id);
    }

    // search methods
    public List<Task> getTasksByProject(Project project) {
        return project.getTasks();
    }

    public List<Task> getTasksByUser(User user) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getOwner().equals(user))
                .toList();
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getStatus() == status)
                .toList();
    }

    public List<Task> getTasksByPriority(Priority priority) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getPriority() == priority)
                .toList();
    }

    // business logic
    public void setTaskStatus(int taskId, TaskStatus newStatus) {
        Task task = taskRepository.findById(taskId);
        if (task != null) {
            if (task.getStatus() == TaskStatus.COMPLETED) {
                task.uncompleteTask();
            }
            task.setStatus(newStatus);
            taskRepository.update(task);
        }
    }

    public void markTaskAsCompleted(int taskId) {
        Task task = taskRepository.findById(taskId);
        if (task != null) {
            task.completeTask();
            taskRepository.update(task);
        }
    }

    public void setTaskPriority(int taskId, Priority newPriority) {
        Task task = taskRepository.findById(taskId);
        if (task != null) {
            task.setPriority(newPriority);
            taskRepository.update(task);
        }
    }
}
