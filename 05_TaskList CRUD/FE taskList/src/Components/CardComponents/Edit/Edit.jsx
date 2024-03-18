import { useState, useEffect } from 'react';
import Button from '@mui/joy/Button';
import FormControl from '@mui/joy/FormControl';
import FormLabel from '@mui/joy/FormLabel';
import Input from '@mui/joy/Input';
import Modal from '@mui/joy/Modal';
import ModalDialog from '@mui/joy/ModalDialog';
import DialogTitle from '@mui/joy/DialogTitle';
import ModalClose from '@mui/joy/ModalClose';
import Stack from '@mui/joy/Stack';
import { updateData } from '../../../services/update';
import { Edit as EditIcon } from '@mui/icons-material';
import { useForm } from 'react-hook-form';
import { toast } from 'react-toastify';
import './Edit.css';

function Edit({ tasks, setUpdate }) {
  const [open, setOpen] = useState(false);
  const { register, handleSubmit, setValue, reset, watch } = useForm({
    defaultValues: {
      title: '',
      priority: '',
    },
  });

  useEffect(() => {
    if (tasks) {
      setValue('title', tasks.title);
      setValue('priority', tasks.priority);
    }
  }, [open, tasks, setValue]);

  const formSubmitHandler = async (data) => {
    try {
      await updateData(tasks.id, { ...data });
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
        className='edit-btn'
        variant='contained'
        color='neutral'
        endDecorator={<EditIcon />}
        onClick={() => {
          setOpen(true);
        }}
        sx={{ color: '#000' }}
      ></Button>
      <Modal open={open} onClose={() => setOpen(false)}>
        <ModalDialog>
          <ModalClose variant='plain' sx={{ m: 1 }} />
          <DialogTitle>Edit Task</DialogTitle>
          <form noValidate onSubmit={handleSubmit(formSubmitHandler)}>
            <Stack spacing={2}>
              <FormControl>
                <FormLabel>Task</FormLabel>
                <Input
                  autoFocus
                  required
                  placeholder='Edit your task here...'
                  {...register('title', { required: 'Title is required' })}
                  onChange={(e) => setValue('title', e.target.value)}
                />
              </FormControl>
              <FormControl>
                <FormLabel>Priority</FormLabel>
                <Stack direction='row' spacing={1}>
                  <Button
                    className={watch('priority') === 'High' ? 'high-btn' : ''}
                    value='High'
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
                    className={watch('priority') === 'Medium' ? 'medium-btn' : ''}
                    value='Medium'
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
                    className={watch('priority') === 'Low' ? 'low-btn' : ''}
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
                    color: '#fff',
                    width: '6rem',
                    boxShadow: '0 6px 12px #713fff3f',
                    backgroundColor: watch('title').trim() === '' ? '#7D8592' : '#713FFF',
                    '&:hover': {
                      cursor: watch('title').trim() === '' ? 'not-allowed' : 'pointer',
                      backgroundColor: watch('title').trim() === '' ? '#7D8592' : '#713FFF',
                    },
                  }}
                >
                  Edit
                </Button>
              </FormControl>
            </Stack>
          </form>
        </ModalDialog>
      </Modal>
    </>
  );
}

export default Edit;
