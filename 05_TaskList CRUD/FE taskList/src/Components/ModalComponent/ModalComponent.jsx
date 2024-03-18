import { useState } from 'react';
import Button from '@mui/joy/Button';
import FormControl from '@mui/joy/FormControl';
import FormLabel from '@mui/joy/FormLabel';
import Input from '@mui/joy/Input';
import Modal from '@mui/joy/Modal';
import ModalDialog from '@mui/joy/ModalDialog';
import DialogTitle from '@mui/joy/DialogTitle';
import ModalClose from '@mui/joy/ModalClose';
import Stack from '@mui/joy/Stack';
import Add from '@mui/icons-material/Add';
import './ModalComponent.css';
import { useForm } from 'react-hook-form';
import { postData } from '../../services/post';
import { toast } from 'react-toastify';

function ModalComponent({ setUpdate, setTasks }) {
  const [open, setOpen] = useState(false);

  const { register, handleSubmit, reset, setValue, watch } = useForm({
    defaultValues: {
      title: '',
      priority: '',
    },
  });

  // const formSubmitHandler = async (data) => {
  //   try {
  //     if (data.title.trim() === '') {
  //       toast.error('Title cannot be empty or contain only whitespaces.');
  //       return;
  //     }
  //     const newTask = {
  //       ...data,
  //       progress: 0,
  //       status: 'To do',
  //     };
  //     await postData(newTask);
  //     setTasks((prevTasks) => [...prevTasks, newTask]);
  //     setUpdate((update) => update + 1);
  //     reset({ title: '', priority: '' });
  //     setOpen(false);
  //   } catch (error) {
  //     toast.error(error.message);
  //   }
  // };
  const formSubmitHandler = async (data) => {
    try {
      if (data.title.trim() === '') {
        toast.error('Title cannot be empty or contain only whitespaces.');
        reset({ title: '', priority: '' });
        return;
      }
      await postData({
        ...data,
        progress: 0,
        status: 'To do',
      });
      setUpdate((update) => update + 1);
      reset({ title: '', priority: '' });
      setOpen(false);
    } catch (error) {
      toast.error(error.message);
    }
  };

  return (
    <>
      <Button
        variant='outlined'
        color='neutral'
        startDecorator={<Add />}
        onClick={() => setOpen(true)}
        sx={{
          backgroundColor: '#703EFF',
          color: '#fff',
          '&:hover': {
            backgroundColor: '#3b42a399',
          },
        }}
      >
        Add task
      </Button>
      <Modal open={open} onClose={() => setOpen(false)}>
        <ModalDialog>
          <ModalClose variant='plain' sx={{ m: 1 }} />
          <DialogTitle>Add Task</DialogTitle>
          <form onSubmit={handleSubmit(formSubmitHandler)} noValidate>
            <Stack spacing={2}>
              <FormControl>
                <FormLabel>Task</FormLabel>
                <Input
                  maxLength={20}
                  id='title'
                  autoFocus
                  required
                  placeholder='Type your task here...'
                  {...register('title', { required: 'Title is required' })}
                />
              </FormControl>
              <FormControl>
                <FormLabel>Priority</FormLabel>
                <Stack direction='row' spacing={1}>
                  <Button
                    id='priority'
                    variant='outlined'
                    sx={{
                      color: 'red',
                      borderColor: 'red',
                      '&:hover': { backgroundColor: 'red', color: '#fff' },
                      '&:focus': { backgroundColor: 'red', color: '#fff' },
                    }}
                    {...register('priority', { value: 'High', required: 'Priority is required' })}
                    onClick={() => setValue('priority', 'High')}
                  >
                    High
                  </Button>

                  <Button
                    id='priority'
                    variant='outlined'
                    sx={{
                      color: '#FFD572',
                      borderColor: '#FFD572',
                      '&:hover': { backgroundColor: '#FFD572', color: '#fff' },
                      '&:focus': { backgroundColor: '#FFD572', color: '#fff' },
                    }}
                    {...register('priority', { value: 'Medium', required: 'Priority is required' })}
                    onClick={() => setValue('priority', 'Medium')}
                  >
                    Medium
                  </Button>

                  <Button
                    id='priority'
                    variant='outlined'
                    sx={{
                      color: '#0AC947',
                      borderColor: '#0AC947',
                      '&:hover': { backgroundColor: '#0AC947', color: '#fff' },
                      '&:focus': { backgroundColor: '#0AC947', color: '#fff' },
                    }}
                    {...register('priority', { value: 'Low', required: 'Priority is required' })}
                    onClick={() => setValue('priority', 'Low')}
                  >
                    Low
                  </Button>
                </Stack>
              </FormControl>
              <FormControl sx={{ display: 'flex', flexDirection: 'row-reverse' }}>
                <Button
                  className='submit-button'
                  type='submit'
                  sx={{
                    padding: '0.5rem 2rem',
                    boxShadow: '0 6px 12px #713fff3f',
                    backgroundColor: watch('title').trim() === '' && '#7D8592',
                    '&:hover': {
                      cursor: watch('title').trim() === '' ? 'not-allowed' : 'pointer',
                      backgroundColor: watch('title').trim() === '' ? '#7D8592' : '#0b6bcb',
                    },
                  }}
                >
                  Add
                </Button>
              </FormControl>
            </Stack>
          </form>
        </ModalDialog>
      </Modal>
    </>
  );
}

export default ModalComponent;
