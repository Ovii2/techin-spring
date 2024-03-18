import Card from '../Card/Card';
import './CardList.css';

function CardList({ tasks, setTasks, setUpdate }) {
  return (
    <div className='card-list'>
      {tasks
        .map((task, index) => ({ ...task, tempIndex: index }))
        .filter((item) => item.id !== tasks.id)
        .sort((a, b) => b.tempIndex - a.tempIndex)
        .map((task) => {
          return <Card key={task.id} task={task} setTasks={setTasks} setUpdate={setUpdate} />;
        })}
    </div>
  );
}

export default CardList;
