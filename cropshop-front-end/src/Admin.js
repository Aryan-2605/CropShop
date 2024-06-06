import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; 
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import axios from 'axios';

function LoginPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate(); 

  const Login = async () => {
    try {
      const response = await axios.post('http://localhost:8080/admin', { username, password });
     // console.log('Login Successful', response.data);

     const token = response.headers['authorization'];

      console.log(token);
      if (token) {
        navigate('/admin/dashboard'); 
      } else {
        console.log('No redirect URL provided');
      }
    } catch (error) {
      console.error('Error:', error.response ? error.response.data : 'Login failed');
    }
  };
  

  return (
    <div style={{

      display: 'flex',
      flexDirection: 'column',
      alignItems: 'center',
      justifyContent: 'center',
      height: '100vh', 
      backgroundImage: 'url(https://images.pexels.com/photos/2382325/pexels-photo-2382325.jpeg?cs=srgb&dl=pexels-suzy-hazelwood-2382325.jpg&fm=jpg)', // Adjust the path
      backgroundSize: 'auto cover', 
      backgroundPosition: 'center', 



    }}>
      <div style={{ padding: '20px', width: '400px', textAlign: 'center', backgroundColor: 'rgba(255, 255, 255, 0.8)'  }}>
        <h1>Admin Panel</h1>
        <TextField
          id="filled-username"
          label="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          variant="filled"
          margin="normal"
          fullWidth
          size="large" 
        />
        <TextField
          id="filled-password"
          label="Password"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)} 
          variant="filled"
          margin="normal"
          fullWidth
          size="large" 
        />
        <Button
          variant="contained"
          onClick={Login}
          color="primary"
          style={{ marginTop: '20px', backgroundColor: 'green'}}
          fullWidth
          size="large" 
        >
          Login
        </Button>
      </div>
    </div>
  );
}

export default LoginPage;
