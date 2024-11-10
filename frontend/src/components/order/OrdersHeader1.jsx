import React from 'react';

const OrdersHeader1 = ({orderCount}) => {

    return (
        <div className="order-total">
            <p>Tất cả đơn hàng({orderCount})</p>
            <div className="filter">
                <select className="sort">
                    <option>Sắp xếp theo</option>
                    <option value="price">Giá</option>
                    <option value="sold">Đã bán</option>
                    <option value="quantity">Số lượng</option>
                </select>
                <select className="choose-directory">
                    <option>Chọn danh mục</option>
                    <option value="category1">Danh mục 1</option>
                    <option value="category2">Danh mục 2</option>
                </select>
                <input className="search-order" type="text" placeholder="Tìm kiếm hoá đơn" />
            </div>
        </div>
    );
};

export default OrdersHeader1;