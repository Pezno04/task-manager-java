package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Task;

public class InMemoryTaskRepository implements TaskRepository {

    // In-memory storage for tasks
    private Map<Integer, Task> tasks = new HashMap<>();

    // CRUD operations
    // create
    @Override
    public void save(Task task) {
        tasks.put(task.getId(), task);
    }

    //read by id
    @Override
    public Task findById(int id) {
        return tasks.get(id);
    }

    // read all
    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    // update
    @Override
    public void update(Task task) {
        if (!tasks.containsKey(task.getId())) {
            throw new IllegalArgumentException("Task with id " + task.getId() + " does not exist.");
        }
        tasks.put(task.getId(), task);
    }

    // delete
    @Override
    public void delete(int id) {
        task = tasks.get(id);
        if (task != null) {
            task.removeTask();
            tasks.remove(id);
        }
    }
}
