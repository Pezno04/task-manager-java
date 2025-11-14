package repository;

import model.Task;
import java.util.List;

public interface TaskRepository {
    void save(Task task);
    Task findById(int id);
    List<Task> findAll();
    void update(Task task);
    void delete(int id);
}