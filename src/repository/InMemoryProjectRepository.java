package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Project;

public class InMemoryProjectRepository implements ProjectRepository {

    // In-memory storage for projects
    private Map<Integer, Project> projects = new HashMap<>();

    // CRUD operations
    // create
    @Override
    public void save(Project project) {
        projects.put(project.getId(), project);
    }

    // read by id
    @Override
    public Project findById(int id) {
        return projects.get(id);
    }

    // read all
    @Override
    public List<Project> findAll() {
        return new ArrayList<>(projects.values());
    }

    // update
    @Override
    public void update(Project project) {
        if (!projects.containsKey(project.getId())) {
            throw new IllegalArgumentException("Project with id " + project.getId() + " does not exist.");
        }
        projects.put(project.getId(), project);
    }

    // delete
    @Override
    public void delete(int id) {
        projects.remove(id);
    }
}