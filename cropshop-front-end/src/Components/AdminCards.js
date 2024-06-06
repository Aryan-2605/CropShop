// AdminCards.js
import React from 'react';
import { Grid, Card, CardActionArea, CardContent, CardMedia, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';

// Add 'navigateTo' to the function parameters
function AdminCards({ imageUrl, title, description, navigateTo }) {
  const navigate = useNavigate();

  // Define the handleClick function to use 'navigate' with 'navigateTo'
  const handleClick = () => {
    navigate(navigateTo);
  };
    

  return (
    <Grid item xs={12} sm={6} md={4} lg={3}>
      <Card style={{ backgroundColor: '#424242' }}>
        <CardActionArea onClick={handleClick}>
          <CardMedia
            component="img"
            height="140"
            image={imageUrl}
            alt="Abstract Image"
          />
          <CardContent>
            <Typography gutterBottom variant="h5" component="div" style={{ color: 'white' }}>
              {title}
            </Typography>
            <Typography variant="body2" style={{ color: 'grey' }}>
              {description}
            </Typography>
          </CardContent>
        </CardActionArea>
      </Card>
    </Grid>
  );
}

export default AdminCards;
