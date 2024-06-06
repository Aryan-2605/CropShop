import React from 'react';
import { AppBar, Toolbar, Typography, Button, Grid } from '@mui/material';
import LogoutIcon from '@mui/icons-material/Logout';
import AdminCards from './Components/AdminCards'; 
import { useNavigate } from 'react-router-dom';


function AdminDB() {
  const navigate = useNavigate();

  const handleSignOut = () => {
    navigate('/admin', { replace: true });
  };


  return (
    <div style={{ height: '100vh', backgroundColor: '#121212', color: 'white' }}>
      <AppBar position="static" style={{ backgroundColor: '#333' }}>
        <Toolbar>
          <Typography variant="h6" style={{ flexGrow: 1 }}>
            <img src="/images/CropShop.png" alt="Company Logo" style={{ maxWidth: '90px' }} />
          </Typography>
          <Button color="inherit" startIcon={<LogoutIcon />} onClick={handleSignOut}>
            Sign Out
          </Button>
        </Toolbar>
      </AppBar>

      <Grid container spacing={3} style={{ padding: '20px', justifyContent: 'center' }}>
        <AdminCards
          imageUrl="https://dytvr9ot2sszz.cloudfront.net/wp-content/uploads/2016/04/ELASTICSEARCH-QUERIES-2.jpg"
          title="Queries"
          description="Support Queries."
          navigateTo="/admin/requests" 
        />
        <AdminCards
          imageUrl="https://t3.ftcdn.net/jpg/02/96/76/92/360_F_296769207_nAde3pZ3IFkhM6X0rVXSAGhZ8pkTmAIP.jpg"
          title="Create an Admin"
          description="Admin Panel"
          navigateTo="/admin/create"
        />
        {/*if you need to add more admin cards just extend from here. make sure u update the document if u add more.
        Also all admin redirection should be with /admin/* or security will not work. thx :) */ }
      </Grid>
    </div>
  );
}

export default AdminDB;
