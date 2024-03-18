import { useState, useEffect } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Header from './Components/Header/Header';
import CardList from './Components/CardList/CardList';
import { getAllData } from './services/get';
import { ClipLoader } from 'react-spinners';
import Footer from './Components/Footer/Footer';

function App() {
  const [tasks, setTasks] = useState([]);
  const [isLoading, setIsloading] = useState(true);
  const [update, setUpdate] = useState(0);

  const fetchData = async () => {
    setIsloading(true);
    try {
      const data = await getAllData();
      setTasks(data);
    } catch (error) {
      toast.error(error.message);
    } finally {
      setIsloading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, [update]);

  return (
    <div className='App'>
      <ToastContainer autoClose={1500} />
      <Header setUpdate={setUpdate} setTasks={setTasks} />
      {!isLoading && tasks.length > 0 ? (
        <CardList tasks={tasks} setTasks={setTasks} setUpdate={setUpdate} />
      ) : (
        <div className='loading'>
          {tasks.length === 0 ? (
            <h2>Hooray, you can relax now!</h2>
          ) : (
            <>
              <ClipLoader />
              <p>Loading</p>
            </>
          )}
        </div>
      )}
      <Footer />
    </div>
  );
}

export default App;
