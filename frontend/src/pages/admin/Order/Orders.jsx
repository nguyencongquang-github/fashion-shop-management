import React, { useState} from 'react';
import Sidebar from '../../../components/sidebar/Sidebar';
import Header from '../../../components/header/Header';
import OrdersHeader from '../../../components/order/OrdersHeader';
import OrdersHeader1 from '../../../components/order/OrdersHeader1';
import OrdersTable from '../../../components/order/OrdersTable';
import PaginationOrders from '../../../components/order/PaginationOrders';
const Orders = () => {
    const [orders] = useState([
            {id: '1', name: 'Khách hàng A', password: '123456', phone_number: '0123456789', email: 'abcd@gmail.com', address: "HCM"},
            {id: '2', name: 'Khách hàng B', password: '123456', phone_number: '0123456789', email: 'abcd@gmail.com', address: "HCM"},
            {id: '3', name: 'Khách hàng C', password: '123456', phone_number: '0123456789', email: 'abcd@gmail.com', address: "HCM"},
            {id: '4', name: 'Khách hàng D', password: '123456', phone_number: '0123456789', email: 'abcd@gmail.com', address: "HCM"},
            {id: '5', name: 'Khách hàng E', password: '123456', phone_number: '0123456789', email: 'abcd@gmail.com', address: "HCM"},
            {id: '6', name: 'Khách hàng F', password: '123456', phone_number: '0123456789', email: 'abcd@gmail.com', address: "HCM"},
    ]); 
    const [currentPage, setCurrentPage] = useState(1);
    const [itemsPerPage, setItemsPerPage] = useState(5);


    const handlePageChange = (page) => {
        setCurrentPage(page);
    };

    const handleItemsPerPageChange = (items) => {
        setItemsPerPage(items);
        setCurrentPage(1); // Reset về trang đầu khi thay đổi số sản phẩm trên trang
    };

    // Tính toán các sản phẩm được hiển thị trên trang hiện tại
    const indexOfLastOrder = currentPage * itemsPerPage;
    const indexOfFirstOrder = indexOfLastOrder - itemsPerPage;
    const currentOrders = orders.slice(indexOfFirstOrder, indexOfLastOrder);  
    return (
        <div style={{ display: 'flex', flexDirection: 'column', height: '100vh', background: '#f0f2f5' }}>
            <Header /> {/* Header chiếm chiều rộng đầy đủ */}
            <div style={{ background: '#f0f2f5' }}>
                <div style={{ display: 'flex', flex: 1, paddingTop: '85px' }}>
                    <Sidebar />
                    <div style={{ marginLeft: '250px', padding: '20px', width: '100%' }}>
                        <OrdersHeader />
                        <OrdersHeader1 orderCount={orders.length}/>
                        <OrdersTable orders={currentOrders} />
                        
                        <PaginationOrders
                            totalItems={orders.length} // Tổng số sản phẩm
                            itemsPerPage={itemsPerPage} 
                            onPageChange={handlePageChange} // Hàm thay đổi trang
                            onItemsPerPageChange={handleItemsPerPageChange}  // Hàm thay đổi số lượng sản phẩm hiển thị
                        />
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Orders;
