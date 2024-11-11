import React from 'react';
import { FaSync } from 'react-icons/fa';
import { useNavigate } from 'react-router-dom';

const DiscountInfoHeader = () => {
    const navigate = useNavigate();
    //Hàm quay về trang tất cả sản phẩm
    const handleReturnProducts = () => {
        navigate('/products/discountProducts');
    };
    return (
        <div className="discount-header">
            <h1 className="discount-title">GIẢM GIÁ SẢN PHẨM</h1>
            <div className="header-actions">
                <button className="reload-button">
                    <FaSync />
                </button>
                <button className='return-product' onClick={handleReturnProducts}>Quay lại</button>
            </div>
        </div>
    );
};

export default DiscountInfoHeader;