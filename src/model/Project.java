package model;

import java.util.ArrayList;
import java.util.List;

public class Project {
    // Fields
    private int id;
    private String name;
    private String description;
    private User owner;

    private List<Task> tasks = new ArrayList<>();

    // Methods to manage tasks
    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    // Constructor
    public Project(int id, String name, String description, User owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
    }

    // Getters and Setters
    // id
    public int getId() {
        return id;
    }

    // name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // description
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // owner
    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }

    // toString method
    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + (owner != null ? owner.getName() : "No owner") +
                ", tasks=" + tasks.size() +
                '}';
    }
}
