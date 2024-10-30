import React from 'react';
import Sidebar from '../../components/sidebar/Sidebar';

const Customers = () => {
    return (
        <div style={{ display: 'flex' }}>
            <Sidebar />
            <div style={{ marginLeft: '300px', padding: '20px', width: '100%' }}>
                <h1>Welcome to Customers</h1>
                <p>This is the customers page.</p>
            </div>
        </div>
    );
};

export default Customers;
