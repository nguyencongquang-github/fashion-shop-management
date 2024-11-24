import React, { useState} from 'react';
import Sidebar from '../../../components/sidebar/Sidebar';
import Header from '../../../components/header/Header';
import DiscountProductsHeader from '../../../components/products/discount/DiscountProductsHeader';
import DiscountProductsHeader1 from '../../../components/products/discount/DiscountProductsHeader1';
import DiscountProductsTable from '../../../components/products/discount/DiscountProductsTable';
import PaginationDiscount from '../../../components/products/discount/PaginationDiscount';
const DiscountProducts = () => {
    const [products] = useState([
        {id: '1', name: 'Sản phẩm A', quantity: 50, price: '500,000₫', sold: 20, fixed_price: '500,000', discount_price: '10,000', sold_price: '490,0000', status_discount: 'Chưa hết hạn', category: 'Danh mục 1' },
        {id: '2', name: 'Sản phẩm B', quantity: 30, price: '300,000₫', sold: 10, fixed_price: '500,000', discount_price: '10,000', sold_price: '490,0000', status_discount: 'Chưa hết hạn', category: 'Danh mục 2' },
        {id: '3', name: 'Sản phẩm C', quantity: 20, price: '700,000₫', sold: 5, fixed_price: '500,000', discount_price: '10,000', sold_price: '490,0000', status_discount: 'Chưa hết hạn', category: 'Danh mục 1' },
        {id: '4', name: 'Sản phẩm D', quantity: 15, price: '450,000₫', sold: 7, fixed_price: '500,000', discount_price: '10,000', sold_price: '490,0000', status_discount: 'Chưa hết hạn', category: 'Danh mục 2' },
        {id: '5', name: 'Sản phẩm E', quantity: 25, price: '600,000₫', sold: 15, fixed_price: '500,000', discount_price: '10,000', sold_price: '490,0000', status_discount: 'Chưa hết hạn', category: 'Danh mục 1' },
        {id: '6', name: 'Sản phẩm F', quantity: 10, price: '800,000₫', sold: 3, fixed_price: '500,000', discount_price: '10,000', sold_price: '490,0000', status_discount: 'Chưa hết hạn', category: 'Danh mục 3' },
    ]);
    const [currentPage, setCurrentPage] = useState(1);
    const [itemsPerPage, setItemsPerPage] = useState(5);

    const discountedProducts = products.filter(product => parseFloat(product.discount_price) > 0);

    const handlePageChange = (page) => {
        setCurrentPage(page);
    };

    const handleItemsPerPageChange = (items) => {
        setItemsPerPage(items);
        setCurrentPage(1); // Reset về trang đầu khi thay đổi số sản phẩm trên trang
    };

    // Tính toán các sản phẩm được hiển thị trên trang hiện tại
    const indexOfLastProduct = currentPage * itemsPerPage;
    const indexOfFirstProduct = indexOfLastProduct - itemsPerPage;
    const currentProducts = discountedProducts.slice(indexOfFirstProduct, indexOfLastProduct);

    return (
        <div style={{ display: 'flex', flexDirection: 'column', height: '100vh', background: '#f0f2f5' }}>
            <Header />
            <div style={{ background: '#f0f2f5' }}>
                <div style={{ display: 'flex', flex: 1, paddingTop: '85px' }}>
                    <Sidebar />
                    <div style={{ marginLeft: '250px', padding: '20px', width: '100%' }}>
                        <DiscountProductsHeader />
                        <DiscountProductsHeader1 productCount={products.length}/>
                        <DiscountProductsTable products={currentProducts} />
                        <PaginationDiscount 
                            totalItems={products.length} // Tổng số sản phẩm
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

export default DiscountProducts;