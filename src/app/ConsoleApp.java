package app;

import model.Project;
import model.Task;
import model.User;

import model.enums.Priority;
import model.enums.TaskStatus;

import service.TaskService;

public class ConsoleApp {
    
    public static void main(String[] args) {

        TaskService service = new TaskService();

        // creating a user
        User user1 = new User(1, "Pedro");

        // creating a project
        Project project1 = new Project(1, "Task Management System", "Example project");
        service.createProject(project1);

        System.out.println("Projects:");
        service.getAllProjects().forEach(System.out::println);

        // creating a task
        Task task1 = new Task(
                1,
                "Implement CRUD",
                "Create basic CRUD operations in the service layer",
                user1,
                project1,
                Priority.HIGH
        );

        service.createTask(task1);

        System.out.println("\nTasks:");
        service.getAllTasks().forEach(System.out::println);

        // retrieving a task by id
        System.out.println("\nTask by ID:");
        System.out.println(service.getTaskById(1));

        // updating task status
        service.setTaskStatus(1, TaskStatus.IN_PROGRESS);
        System.out.println("\nTask updated:");
        System.out.println(service.getTaskById(1));

        // marking task as completed
        service.markTaskAsCompleted(1);
        System.out.println("\nTask completed:");
        System.out.println(service.getTaskById(1));

        // filtering tasks by priority
        System.out.println("\nTasks with HIGH priority:");
        service.getTasksByPriority(Priority.HIGH).forEach(System.out::println);

        // deleting a task
        service.deleteTask(1);
        System.out.println("\nTasks after delete:");
        service.getAllTasks().forEach(System.out::println);
        
    }

}
