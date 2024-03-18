package lt.techin.tasklist.services;

import lt.techin.tasklist.model.Task;
import lt.techin.tasklist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createNewTask(Task task) {
        Task newTask = new Task();
        newTask.setTitle(task.getTitle());
        newTask.setPriority(task.getPriority());
        newTask.setProgress(task.getProgress());
        newTask.setStatus(task.getStatus());
        taskRepository.save(newTask);
        return newTask;
    }

    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }

    public Task updateTask(long id, Task task) {
        Task updatedTask = taskRepository.findById(id).orElse(null);
        if (updatedTask != null) {
            if (task.getTitle() != null) {
                updatedTask.setTitle(task.getTitle());
            }
            if (task.getPriority() != null) {
                updatedTask.setPriority(task.getPriority());
            }
            updatedTask.setProgress(task.getProgress());
            if (task.getStatus() != null) {
                updatedTask.setStatus(task.getStatus());
            }
            taskRepository.save(updatedTask);
        }
        return updatedTask;
    }

    public Task updateTaskPartially(Long id, Task task) {
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Task Not Found");
        }
        if (task.getTitle() != null) {
            existingTask.setTitle(task.getTitle());
        }
        if (task.getPriority() != null) {
            existingTask.setPriority(task.getPriority());
        }
        existingTask.setProgress(task.getProgress());
        if (task.getStatus() != null) {
            existingTask.setStatus(task.getStatus());
        }
        taskRepository.save(existingTask);
        return existingTask;
    }
}

