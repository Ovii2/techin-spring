import Delete from "../CardComponents/Delete/Delete";
import Edit from "../CardComponents/Edit/Edit";
import InProgress from "../CardComponents/InProgress/InProgress";
import "./Card.css";

function Card({ task, setTasks, setUpdate }) {
  const { title, priority, progress } = task;

  return (
    <article className="card">
      <div className="task">
        <h4>Task</h4>
        <p>{title}</p>
      </div>
      <div className="priority">
        <h4>Priority</h4>
        <p
          className={
            priority === "High"
              ? "priority-high"
              : priority === "Medium"
              ? "priority-medium"
              : priority === "Low"
              ? "priority-low"
              : ""
          }
        >
          <b>{priority}</b>
        </p>
      </div>
      <div className="to-do-progress">
        <InProgress value={progress} task={task} setTasks={setTasks} setUpdate={setUpdate} />
      </div>
      <div className="actions">
        <Edit tasks={task} setTasks={setTasks} setUpdate={setUpdate} />
        <Delete tasks={task} setUpdate={setUpdate} />
      </div>
    </article>
  );
}

export default Card;
