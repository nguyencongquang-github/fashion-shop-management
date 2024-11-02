import React from 'react';
import { Link } from 'react-router-dom';
import { FaHome, FaBox, FaClipboardList, FaUsers, FaTag, FaCog } from 'react-icons/fa'; // Nhập các icon từ react-icons
import './Sidebar.css';
import { useState } from 'react';

const Sidebar = () => {
    const [isProductDropOn, setProductDrop] = useState(false);
    const toggleProductDropdown = () => {
        setProductDrop(!isProductDropOn);
    }
    return (
        <div className="sidebar">
            <ul>
                <li>
                    <Link to="/dashboard"><FaHome />Bảng điều khiển</Link>
                </li>
                <li className="sidebar-item">
                    <div class="d-flex flex-column">
                        <div onClick={toggleProductDropdown} className="sidebar-button">
                            <FaBox /> Sản phẩm
                        </div>
                        {isProductDropOn && (
                            <div>
                                <ul className={`sub-menu ${isProductDropOn ? 'show' : ''}`}>
                                    <li><Link to="/products/all">Tất cả sản phẩm</Link></li>
                                    <li><Link to="/newproducts">Thêm mới sản phẩm</Link></li>
                                    <li><Link to="/products/discount">Giảm giá sản phẩm</Link></li>
                                </ul>
                            </div>
                        )}
                    </div>
                </li>
                <li><Link to="/orders"><FaClipboardList />Đơn hàng</Link></li>
                <li><Link to="/customers"><FaUsers />Khách hàng</Link></li>
                <li><Link to="/vouchers"><FaTag />Khuyến mãi</Link></li>
                <li><Link to="/setting"><FaCog />Cài đặt</Link></li>
            </ul>
        </div>
    );
};

export default Sidebar;
