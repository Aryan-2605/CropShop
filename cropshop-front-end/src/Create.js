import React, { useState, useEffect } from 'react';
import { Box, Button, Grid, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function Create() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    username: '',
    confirmUsername: '',
    password: '',
    confirmPassword: ''
  });

  const [errors, setErrors] = useState({});
  const [submitDisabled, setSubmitDisabled] = useState(false); // New state for controlling submit button's disabled state

  const validateField = (name, value) => {
    let errorMsg = '';
    if (name === 'confirmUsername' && value !== formData.username) {
      errorMsg = 'Usernames do not match!';
    } else if (name === 'confirmPassword' && value !== formData.password) {
      errorMsg = 'Passwords do not match!';
    }
    setErrors(prevErrors => ({ ...prevErrors, [name]: errorMsg }));
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prevFormData => ({
      ...prevFormData,
      [name]: value,
    }));
    validateField(name, value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log('Form submission attempted');
    setSubmitDisabled(true); //This SHOULD perchance turn off button if its not valid.
  
    validateAllFields();
  
    if (isFormValid()) {
      console.log('Form submitted:', formData);
      try {


        const response = await axios.post('http://localhost:8080/create', {
          username: formData.username,
          password: formData.password
        });
        fetchData();

        console.log('Server response:', formData);
        setFormData({
          username: '',
          confirmUsername: '',
          password: '',
          confirmPassword: ''
        });
      } catch (error) {
        console.error('Error submitting form:', error);
      } finally {
        setSubmitDisabled(false); 
      }
    } else {
      setSubmitDisabled(false); 
    }
  };

  const validateAllFields = () => {
    for (const fieldName in formData) {
      validateField(fieldName, formData[fieldName]);
    }
  };

  const fetchData = async () => {
    try {
      const response = await axios.get('http://localhost:8080/admins');
      console.log(response.data); // this will need to be removed along with all console logs just to ctrl f 
      setAdmins(response.data);
    } catch (error) {
      console.error('Error fetching requests:', error);
    }
  };

  useEffect(() => {
    fetchData();
  },[]); 

  const handleDelete = async (id) =>{
    console.log('Request with the ID ' + id + ' is now archived');
    try {
      setAdmins(prevAdmin => prevAdmin.filter(admin => admin.id !== id));
  
      await axios.delete(`http://localhost:8080/admins/${id}`);
      
      console.log('Request deleted successfully');
    } catch (error) {
      console.error('Could not delete data error: ', error);
      fetchData(); 
    }

  }

  const isFormValid = () => {
    return Object.values(errors).every(error => error === '');
  };

  const [admins, setAdmins] = useState([]);

  return (
    <div>
      <header>
        <Typography variant="h4" gutterBottom style={{ textAlign: 'center', marginTop: '20px' }}>
          Admin Dashboard
        </Typography>
        <Button variant="contained" style={{ backgroundColor: 'green', display: 'block', margin: '0 auto' }} onClick={() => navigate('/admin/dashboard')}>Back</Button>
      </header>
      <Grid container spacing={2} style={{ marginTop: '20px' }}>
        <Grid item xs={6} display="flex" justifyContent="center">
          <form width="50%" component="form" onSubmit={handleSubmit}>
            <TextField 
              name="username" 
              label="Username" 
              variant="outlined" 
              fullWidth 
              margin="normal"
              value={formData.username}
              onChange={handleChange}
              error={!!errors.username}
              helperText={errors.username}
              required
            />
            <TextField 
              name="confirmUsername" 
              label="Confirm Username" 
              variant="outlined" 
              fullWidth 
              margin="normal"
              value={formData.confirmUsername}
              onChange={handleChange}
              error={!!errors.confirmUsername}
              helperText={errors.confirmUsername}
              required 
            />
            <TextField 
              name="password" 
              label="Password" 
              type="password" 
              variant="outlined"
              fullWidth 
              margin="normal" 
              value={formData.password}
              onChange={handleChange}
              error={!!errors.password}
              helperText={errors.password}
              required 
            />
            <TextField 
              name="confirmPassword" 
              label="Confirm Password" 
              type="password" 
              variant="outlined" 
              fullWidth 
              margin="normal"
              value={formData.confirmPassword}
              onChange={handleChange}
              error={!!errors.confirmPassword}
              helperText={errors.confirmPassword}
              required 
            />
            <Button type="submit" value="Submit" variant="contained" color="primary" style={{ backgroundColor: 'green', display: 'block', margin: '20px auto 0' }}>Create</Button>
          </form>
        </Grid>
        
        <Grid item xs={6} style={{ display: 'flex', justifyContent: 'center' }}>

          <Box width="75%">
            <TableContainer>
              <Table aria-label="simple table">
                <TableHead>
                  <TableRow>
                    <TableCell>ID</TableCell>
                    <TableCell>Username</TableCell>
                    <TableCell align="center">Delete</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
  
                  {admins.map(admin => (

                
                  <TableRow key={admin.id}>
                    <TableCell>{admin.id}</TableCell>
                    <TableCell>{admin.username}</TableCell>
                    <TableCell align="center">
                      <Button onClick={() => handleDelete(admin.id)} variant="contained" color="primary"style={{ backgroundColor: 'green', display: 'block', margin: '0 auto' }}>Delete</Button>
                    </TableCell>
                  </TableRow>
                    ))}
                  {/*this should automatickly add ro-s fromt eh db */}
                </TableBody>
              </Table>
            </TableContainer>
          </Box>
        </Grid>
      </Grid>
    </div>
  );
}

export default Create;
