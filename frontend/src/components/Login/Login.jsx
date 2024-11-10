import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.css';
import bgImage from '../../assets/bg1-login.jpg';
import ApiService from '../../services/ApiService';

const Login = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: ''
  });

  const [message, setMessage] = useState(null);
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await ApiService.loginUser(formData);
      if (response.status === 200) {
        setMessage("User successfully logged in");
        localStorage.setItem('token', response.token);
        localStorage.setItem('role', response.role);
        setTimeout(() => {
          navigate("/profile");
        }, 2000);
      }
    } catch (error) {
      setMessage(error.response?.data.message || error.message || "Unable to login a user");
    }
  };

  return (
    <div className="login-container">
      <div className="background">
        <img src={bgImage} alt="Background" className="background-image" />
      </div>
      <div className="login-box">
        <h2>Sign in</h2>
        {message && <p className="message">{message}</p>}
        <form onSubmit={handleSubmit}>
          <label>Email</label>
          <input
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            placeholder="Enter your email"
            required
          />
          <label>Password</label>
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            placeholder="Enter your password"
            required
          />
          <div className="forgot-password">
            <a href="#">Forgot password?</a>
          </div>
          <button type="submit">Sign in</button>
        </form>
        <div className="or-divider">or</div>
        <div className="register">
          <button className="sign-up">Sign up</button>
        </div>
      </div>
    </div>
  );
};

export default Login;
