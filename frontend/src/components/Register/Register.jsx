import React, { useState } from 'react';
import './Register.css';
import bgImage from '../../assets/bg1-login.jpg';
import { useNavigate } from 'react-router-dom';

const Register = () => {
  const navigate = useNavigate();
  const [fade, setFade] = useState(false);

  const handleSignIn = () => {
    setFade('fade-out');
    setTimeout(() => {
      navigate('/login');
    }, 500);
  };

  return (
    <div className={`register-container ${fade ? 'fade-out' : ''}`}>
      <div className="background">
        <img src={bgImage} alt="Background" className="background-image" />
      </div>
      <div className="register-box">
        <h2>Sign up</h2>
        <form>
          <label>Email</label>
          <input type="email" placeholder="Enter your email" required />
          
          <label>Password</label>
          <input type="password" placeholder="Enter your password" required />
          
          <label>Confirm Password</label>
          <input type="password" placeholder="Confirm your password" required />
          
          <button type="submit">Sign up</button>
        </form>
        <div className="or-divider">or</div>
        <div className="login">
          <button className="sign-in" onClick={handleSignIn}>Sign in</button>
        </div>
      </div>
    </div>
  );
};

export default Register;
