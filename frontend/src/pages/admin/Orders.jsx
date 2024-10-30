import React from 'react';
import Sidebar from '../../components/sidebar/Sidebar';

const Orders = () => {
    return (
        <div style={{ display: 'flex' }}>
            <Sidebar />
            <div style={{ marginLeft: '300px', padding: '20px', width: '100%' }}>
                <h1>Welcome to the Orders</h1>
                <p>This is the orders page.</p>
            </div>
        </div>
    );
};

export default Orders;
