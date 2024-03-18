package lt.techin.tasklist.repository;

import lt.techin.tasklist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TaskRepository extends JpaRepository<Task,Long> {
}
