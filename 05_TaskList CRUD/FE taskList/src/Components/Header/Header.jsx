import ModalComponent from '../ModalComponent/ModalComponent';
import './Header.css';

function Header({ tasks, setTasks, setUpdate }) {
  return (
    <div className='header'>
      <h1>Task list</h1>
      <ModalComponent tasks={tasks} setUpdate={setUpdate} setTasks={setTasks} />
    </div>
  );
}

export default Header;
