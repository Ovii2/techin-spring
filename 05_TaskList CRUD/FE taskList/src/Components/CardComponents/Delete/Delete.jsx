import { useState } from 'react';
import Button from '@mui/joy/Button';
import DialogContent from '@mui/joy/DialogContent';
import DialogActions from '@mui/joy/DialogActions';
import Modal from '@mui/joy/Modal';
import ModalDialog from '@mui/joy/ModalDialog';
import DeleteForever from '@mui/icons-material/DeleteForever';
import { deleteData } from '../../../services/delete';

function Delete({ tasks, setUpdate }) {
  const [open, setOpen] = useState(false);

  const handleDelete = async (id) => {
    try {
      await deleteData(id);
      setUpdate((update) => update + 1);
      setOpen(false);
    } catch (error) {
      console.error('Error deleting task:', error);
    }
  };

  return (
    <>
      <Button
        variant='contained'
        color='danger'
        endDecorator={<DeleteForever />}
        onClick={() => setOpen(true)}
        sx={{ color: 'red' }}
      ></Button>
      <Modal open={open} onClose={() => setOpen(false)}>
        <ModalDialog variant='outlined' role='alertdialog'>
          <DialogContent>Are you sure you want to delete this task?</DialogContent>
          <DialogActions sx={{ display: 'flex', justifyContent: 'center' }}>
            <Button
              variant='outlined'
              color='neutral'
              onClick={() => setOpen(false)}
              sx={{ padding: '0.5rem 2rem' }}
            >
              Cancel
            </Button>
            <Button
              variant='solid'
              onClick={() => handleDelete(tasks.id)}
              sx={{
                backgroundColor: '#713FFF',
                marginRight: '0.5rem',
                padding: '0.5rem 2rem',
                '&:hover': { backgroundColor: '#713FFF' },
              }}
            >
              Delete
            </Button>
          </DialogActions>
        </ModalDialog>
      </Modal>
    </>
  );
}
export default Delete;
