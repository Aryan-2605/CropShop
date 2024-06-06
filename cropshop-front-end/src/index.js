// src/index.js
import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Support from './Support'; // Support
import Requests from './Requests';
import Admin from './Admin';
import Dash from './AdminDB';
import CreateAdmin from './Create';

ReactDOM.render(
  <React.StrictMode>
    <Router>
      <Routes>
        <Route path="/support" element={<Support />} />
        <Route path='/admin/requests' element={<Requests />}/>
        <Route path='/admin' element={<Admin />}/>
        <Route path="/admin/dashboard" element={<Dash/>}/>
        <Route path="/admin/create" element={<CreateAdmin/>}/>

      </Routes>
    </Router>
  </React.StrictMode>,
  document.getElementById('root')
);
