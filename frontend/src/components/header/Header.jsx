import React from 'react';
import './Header.css'; // Import file CSS
import { FaSearch, FaUser, FaCog, FaSignOutAlt  } from 'react-icons/fa';
import logo from '../../assets/LogoWeb-removebg.png';
import avt from '../../assets/avt1.jpg';

const Header = () => {
    return (
        <header className="header">
            <div className="logo">
                <img src={logo}alt="Logo" />
            </div>
            <div className="search-box">
                <input type="text" placeholder="Tìm kiếm..." />
                <FaSearch className="search-icon" />
            </div>
            <div className="avatar-dropdown">
                <img 
                    src={avt}
                    alt="Avatar" 
                    className="avatar" 
                    onClick={() => toggleDropdown()} // Gọi hàm để toggle dropdown
                />
                <div className="dropdown-content-header">
                    <a href="#profile"><FaUser />Thông tin cá nhân</a>
                    <a href="#settings"><FaCog />Cài đặt</a>
                    <a href="#logout"><FaSignOutAlt />Đăng xuất</a>
                </div>
            </div>
        </header>
    );
};
const toggleDropdown = () => {
  const dropdown = document.querySelector('.dropdown-content-header');
  dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
};

export default Header;
