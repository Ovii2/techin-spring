import { useState, useEffect } from 'react';
import Stack from '@mui/material/Stack';
import CircularProgress from '@mui/material/CircularProgress';
import Button from '@mui/material/Button';
import './InProgress.css';
import { updateData } from '../../../services/update';
import { toast } from 'react-toastify';

function InProgress({ task }) {
  const statuses = [0, 50, 100];
  const labels = ['To Do', 'In Progress', 'Done'];
  const [index, setIndex] = useState(task.progress);

  const progressHandler = () => {
    setIndex((index) => (index + 1) % statuses.length);
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        await updateData(task.id, { progress: index });
      } catch (error) {
        toast.error(error.message);
      }
    };

    fetchData();
  }, [index]);

  return (
    <div className=''>
      <Stack
        spacing={2}
        sx={{
          display: 'grid',
          gridTemplateColumns: '1fr 1fr',
          alignItems: 'center',
          justifyItems: 'center',
        }}
      >
        <Button
          className='todo-btn'
          variant='contained'
          onClick={() => {
            progressHandler();
          }}
          sx={{
            backgroundColor: '#ccc',
            '&:hover': { backgroundColor: '#ccc' },
          }}
        >
          {labels[index]}
        </Button>

        <div className='progress-circle'>
          {statuses[index] == 0 ? (
            <CircularProgress variant='determinate' value={0} sx={{ color: '#ccc' }} />
          ) : (
            <CircularProgress variant='determinate' value={statuses[index]} color='primary' />
          )}
        </div>
      </Stack>
    </div>
  );
}

export default InProgress;
