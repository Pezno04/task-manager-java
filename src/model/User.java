package model;

public class User {
    // Fields
    private int id;
    private String name;



    // Constructor
    public User(int id, String name) {
        this.id = id;
        this.name = name;
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
}