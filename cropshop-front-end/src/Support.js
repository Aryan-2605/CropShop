// src/Support.js

import React, { useState } from 'react';
import './CSS/SupportStyles.css';
import axios from 'axios';


const Support = () => {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    title: '',
    query: '',
  });

  const [submitted, setSubmitted] = useState(false);
  const [errors, setErrors] = useState({
    firstName: '',
    lastName: '',
    email: '',
    title: '',
    query: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
    validateField(name, value);
  };

  const validateField = (fieldName, value) => {
    let error = '';

    if (fieldName === 'email') {
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if ((!emailRegex.test(value) || value.length > 50) && value.trim() !== '') {
        error = 'Invalid email address';
      }
    }

    if (fieldName === 'firstName') {
      if (value.length > 25 && value.trim() !== '') {
        error = 'First Name cannot exceed 25 characters';
      }
    }

    if (fieldName === 'lastName' && value.trim() !== '') {
      if (value.length > 25) {
        error = 'Last Name cannot exceed 25 characters';
      }
    }

    if (fieldName === 'title' && value.trim() !== '') {
      if (value.length > 50) {
        error = 'Title cannot exceed 50 characters';
      }
    }

    if (fieldName === 'query' && value.trim() !== '') {
      if (value.length > 4000) {
        error = 'Query cannot exceed 4,000 characters';
      }
    }

    setErrors((prevErrors) => ({
      ...prevErrors,
      [fieldName]: error,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log('Form submission attempted');
  
    e.target.querySelector('input[type="submit"]').disabled = true;
  
    validateAllFields();
  
    if (isFormValid()) {
      console.log('Form submitted:', formData);
      try {
        const response = await axios.post('http://localhost:8080/requests', formData);
      /*  axios({
          method: 'post',
          url: 'http://localhost:8080/requests',
          data: formData
        }); */
        console.log('Server response:', formData);
          setSubmitted(true);
        setFormData({
          firstName: '',
          lastName: '',
          email: '',
          title: '',
          query: '',
        });
      } catch (error) {
        console.error('Error submitting form:', error);
      } finally {
        e.target.querySelector('input[type="submit"]').disabled = false;
      }
    }
  };
  

  const resetSuccess = () => {
    if (isFormValid()) {
      setSubmitted(false);
    }
  };

  const validateAllFields = () => {
    for (const fieldName in formData) {
      validateField(fieldName, formData[fieldName]);
    }
  };

  const isFormValid = () => {
    for (const fieldName in errors) {
      if (errors[fieldName]) {
        return false;
      }
    }
    return true;
  };







  //HTML Return 
  return (
    <div>
      <div className="header">
      <div className="s1">
        <div className="bar">
        <img src="/images/CropShop.png" alt="" className="logo" />
        </div>
        <div className="f1">
          <a href="/Support" className="cats">Home</a>
        </div>
        <div className="f1">
          <a href="/Support" className="cats">Why CropShop</a>
        </div>
        <div className="f1">
          <a href="/Support" className="cats">Produce</a>
        </div>
        <div className="f1">
          <a href="/Support" className="cats">Blog</a>
        </div>
        <div className="f1">
          <a href="/Support" className="cats">About</a>
        </div>
        <button className="login">Login</button>
      </div>
      <div className="support">
        <h1 className="s1">Support</h1>
    </div>
      </div>
      <div className="test">
      <div className="top-center">
        
      </div>
      <div className="content">
      <h1 className="q1"> Have a Query?</h1>
        <h2 className="t1"> Ask how we can help you:</h2>
        <h3 className="t2">See our platform in action</h3>
        <p className="text"> CropShop is a peer-to-peer platform that directly safely links consumers to farmers.</p>
        <h3 className="t2">Our mission statement</h3>
        <p className="text"> 
          Our goal is to allow farmers to sell their produce allowing farms to maximize profits, lower prices for consumers and advocating the
          end of monopsonist produce markets that financially exploit both farmers and consumers. 
        </p>
        <h3 className="t2">What can our staff help with?</h3>
        <ul className="bp">
          <li>Account login issues</li>
          <li>Purchase disputes</li>
          <li>Repeat transactions</li>
          <li>Spoilt Goods</li>
          <li>Reporting a Farmer</li>
          <li>Verifying your identity to become a farmer</li>
          <li>Refunds</li>
        </ul>
        <h3 className="t2">Want to become a Farmer?</h3>
        <p className="text">
          To become a Farmer, you must verify your business information in the website's dashboard. Be prepared to fill out information limited but not exclusive to:
          Form of ID, Business Address, Business Name, Account details. 
          If you are having any issues with the dashboard, please contact us by filling out the support form on the right side.
        </p>
      </div>
        <div className="content2">
          <p className="ps"> Please note: all fields are required.</p>

          {submitted && Object.values(errors).every(error => !error) ? <p className='success'>We have received your message. Please check your Email.</p> : null}
          
          {errors.firstName && <p className={`error ${errors.firstName && 'show'}`}>{errors.firstName}</p>}
          {errors.lastName && <p className={`error ${errors.lastName && 'show'}`}>{errors.lastName}</p>}
          {errors.email && <p className={`error ${errors.email && 'show'}`}>{errors.email}</p>}
          {errors.title && <p className={`error ${errors.title && 'show'}`}>{errors.title}</p>}
          {errors.query && <p className={`error ${errors.query && 'show'}`}>{errors.query}</p>}


          <form classname='form' onSubmit={handleSubmit}>
          <label htmlFor="firstName">First Name:</label>
          <input
            type="text"
            id="firstName"
            name="firstName"
            value={formData.firstName}
            onChange={handleChange}
            onFocus={resetSuccess}
            required
          />

          <label htmlFor="lastName">Last Name:</label>
          <input
            type="text"
            id="lastName"
            name="lastName"
            value={formData.lastName}
            onChange={handleChange}
            onFocus={resetSuccess}
            required
          />
      

          <label htmlFor="email">Email:</label>
          <input
            type="email"
            id="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            onFocus={resetSuccess}
            required
          />
          <label htmlFor="title">Title:</label>
          <input
            type="text"
            id="title"
            name="title"
            value={formData.title}
            onChange={handleChange}
            onFocus={resetSuccess}
            required
          />
          <label htmlFor="query">Query:</label>
          <textarea
            id="query"
            name="query"
            rows="4"
            value={formData.query}
            onChange={handleChange}
            onFocus={resetSuccess}
            required
          ></textarea>
          <input type="submit" value="Submit"/>
        </form>
        </div>
      </div>
      <footer>
        <p>&copy; 2024 CropShop. Group 4 Projects.</p>
      </footer>
    </div>
  );
};

export default Support;
