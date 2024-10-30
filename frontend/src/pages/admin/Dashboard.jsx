// src/pages/admin/AdminDashboard.jsx
import React from 'react';
import Sidebar from '../../components/sidebar/Sidebar';

const AdminDashboard = () => {
    return (
        <div style={{ display: 'flex' }}>
            <Sidebar />
            <div style={{ marginLeft: '300px', padding: '20px', width: '100%' }}>
                <h1>Welcome to the Admin Dashboard</h1>
                <p>This is the admin home page.</p>
            </div>
        </div>
    );
};

export default AdminDashboard;
