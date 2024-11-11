import React from 'react';
import './Login.css';
import bgImage from '../../assets/bg1-login.jpg';

const Login = () => {
  return (
    <div className="login-container">
      <div className="background">
        <img src={bgImage} alt="Background" class="background-image"></img>
      </div>
      <div className="login-box">
        <h2>Sign in</h2>
        <form>
          <label>Email id</label>
          <input type="email" placeholder="Enter your email" required></input>
          <label>Password</label>
          <input type="password" placeholder="Enter your password" required></input>
          <div className="forgot-password">
            <a href="#">Forgot password</a>
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