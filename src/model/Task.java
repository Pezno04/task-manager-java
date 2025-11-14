package model;

import java.time.LocalDateTime;
import model.enums.Priority;
import model.enums.TaskStatus;

public class Task {
    // Fields
    private int id;
    private String title;
    private String description;
    private Project project;
    private Priority priority;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;

    // Method to mark task as completed
    public void comleteTask() {
        this.status = TaskStatus.COMPLETED;
        this.completedAt = LocalDateTime.now();
    }

    // Constructor
    public Task(int id, String title, String description, Project project, Priority priority){
        this.id = id;
        this.title = title;
        this.description = description;
        this.project = project;
        this.priority = priority;
        this.status = TaskStatus.PENDING;
        this.createdAt = LocalDateTime.now();
        this.completedAt = null;

        if (this.project != null) {
            this.project.addTask(this);
        }
    }

    // Getters and Setters
    // id
    public int getId() {
        return id;
    }

    // title
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    // description
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // project
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        // remove from old project
        if (this.project != null) {
            this.project.removeTask(this);
        }

        this.project = project;

        // add to new project
        if (this.project != null) {
            this.project.addTask(this);            
        }
    }

    // priority
    public Priority getPriority() {
        return priority;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    // status
    public TaskStatus getStatus() {
        return status;
    }
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    // createdAt
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // completedAt
    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    // toString method
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", project=" + (project != null ? project.getName() : "No project") +
                ", priority=" + priority +
                ", status=" + status +
                ", createdAt=" + createdAt +
                (completedAt != null ? ", completedAt=" + completedAt : "") +
                '}';
    }

    // Main method for testing
    public static void main(String[] args) {
        User user = new User(1, "User1");
        Project project = new Project(1, "Project1", "Description1", user);
        Task task = new Task(1, "task", "description", project, Priority.HIGH);
        task.comleteTask();
        System.out.println(task);
    }
}