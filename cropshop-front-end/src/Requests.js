import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { Button } from '@mui/material'; 
import './CSS/Requests.css';

const Request = () => {
  const navigate = useNavigate();
  const handleDelete = async (id) => {
    console.log('Request with the ID ' + id + ' is now archived');
    try {
      setRequests(prevRequests => prevRequests.filter(request => request.id !== id));
  
      await axios.delete(`http://localhost:8080/requests?id=${id}`);
      
      console.log('Request deleted successfully');
    } catch (error) {
      console.error('Could not delete data error: ', error);
      fetchData(); 
    }
  }
  
  


  const [requests, setRequests] = useState([]);

 
    const fetchData = async () => {
      try {
        const response = await axios.get('http://localhost:8080/requests');
        setRequests(response.data);
      } catch (error) {
        console.error('Error fetching requests:', error);
      }
    };


 useEffect(() => {
    fetchData();
  },[]); 


  
  return (
    <div>
      <header>
        <h1>Support Dashboard</h1>
        <Button variant="contained" style={{ marginTop: '20px', backgroundColor: 'green'}} onClick={() => navigate(-1)}>Back</Button>
      </header>
      <div className="dashboard">
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Email</th>
              <th>Title</th>
              <th>Query</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {requests.map(request => (
              <tr key={request.id}>
                <td>{request.id}</td>
                <td>{request.firstName}</td>
                <td>{request.lastName}</td>
                <td>{request.email}</td>
                <td>{request.title}</td>
                <td>{request.query}</td>
                <td>
                  <button onClick={() => handleDelete(request.id)}>Delete</button> {/* THis has some latency for some reason I need help figuring it out. */}
                </td> 
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Request;
