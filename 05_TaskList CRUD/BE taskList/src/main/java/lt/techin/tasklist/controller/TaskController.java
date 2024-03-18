package lt.techin.tasklist.controller;

import lt.techin.tasklist.model.Task;
import lt.techin.tasklist.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tasks")

public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("")
    public Task createNewTask(@RequestBody Task task) {
        return taskService.createNewTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id) {
        taskService.deleteTask(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @PatchMapping("/{id}")
    public Task updateTaskPartially(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTaskPartially(id, task);
    }
}
