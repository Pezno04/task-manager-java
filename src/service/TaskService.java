package service;

import model.Task;
import model.Project;
import model.User;

import repository.InMemoryTaskRepository;
import repository.InMemoryProjectRepository;

import model.enums.Priority;
import model.enums.TaskStatus;

import exceptions.TaskNotFoundException;
import exceptions.ProjectNotFoundException;

import java.util.List;

public class TaskService {

    // repositories
    private InMemoryTaskRepository taskRepository;
    private InMemoryProjectRepository projectRepository;

    // constructor
    public TaskService() {
        this.taskRepository = new InMemoryTaskRepository();
        this.projectRepository = new InMemoryProjectRepository();
    }

    // task crud operations
    //create task
    public void createTask(Task task){
        taskRepository.save(task);
    }

    // read task by id
    public Task getTaskById(int id){
        Task task = taskRepository.findById(id);
        if (task == null) throw new TaskNotFoundException("Task with id " + id + " not found.");
        return task;
    }

    // read all tasks
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    // update task
    public void updateTask(Task task){
        taskRepository.update(task);
    }

    // delete task
    public void deleteTask(int id){
        if (taskRepository.findById(id) == null) throw new TaskNotFoundException("Task with id " + id + " not found.");
        taskRepository.delete(id);
    }

    // project crud operations
    // create project
    public void createProject(Project project){
        projectRepository.save(project);
    }

    // read project by id
    public Project getProjectById(int id){
        return projectRepository.findById(id);
    }

    // read all projects
    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    // update project
    public void updateProject(Project project){
        projectRepository.update(project);
    }

    // delete project
    public void deleteProject(int id){
        projectRepository.delete(id);
    }

    // search methods
    // get tasks by project
    public List<Task> getTasksByProject(Project project) {
        return project.getTasks();
    }

    // get tasks by user
    public List<Task> getTasksByUser(User user) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getOwner().equals(user))
                .toList();
    }

    // get tasks by status
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getStatus() == status)
                .toList();
    }

    // get tasks by priority
    public List<Task> getTasksByPriority(Priority priority) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getPriority() == priority)
                .toList();
    }

    // business logic
    // set task status
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

    // mark task as completed
    public void markTaskAsCompleted(int taskId) {
        Task task = taskRepository.findById(taskId);
        if (task != null) {
            task.completeTask();
            taskRepository.update(task);
        }
    }

    // set task priority
    public void setTaskPriority(int taskId, Priority newPriority) {
        Task task = taskRepository.findById(taskId);
        if (task != null) {
            task.setPriority(newPriority);
            taskRepository.update(task);
        }
    }
}