package fr.nailu.invoicegenerator.service;

import fr.nailu.invoicegenerator.model.Task;
import fr.nailu.invoicegenerator.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository _repository;

    public TaskService(TaskRepository repository) {
        this._repository = repository;
    }

    public void addAll(List<Task> tasks) {
        this._repository.saveAll(tasks);
    }

    public List<Task> getAll() {
        return this._repository.findAll();
    }
}
