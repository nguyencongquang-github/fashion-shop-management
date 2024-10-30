import React from 'react';
import Sidebar from '../../components/sidebar/Sidebar';

const Products = () => {
    return (
        <div style={{ display: 'flex' }}>
            <Sidebar />
            <div style={{ marginLeft: '300px', padding: '20px', width: '100%' }}>
                <h1>Welcome to the Products</h1>
                <p>This is the products page.</p>
            </div>
        </div>
    );
};

export default Products;
