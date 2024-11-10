import React, { useState } from 'react';
import { FaEdit, FaTrash } from 'react-icons/fa';
import './ProductTable.css';

const ProductTable = ({ products }) => {
    const [selectedProducts, setSelectedProducts] = useState([]);
    const [showConfirmDelete, setShowConfirmDelete] = useState(false);
    const [editingProduct, setEditingProduct] = useState(null);  // State for the product being edited
    const [isModalOpen, setIsModalOpen] = useState(false);  // State for managing modal visibility

    // Xử lý khi checkbox chọn tất cả được bật hoặc tắt
    const handleSelectAll = (e) => {
        if (e.target.checked) {
            const allProductIds = products.map((product) => product.id);
            setSelectedProducts(allProductIds);
        } else {
            setSelectedProducts([]);
        }
    };

    // Xử lý khi checkbox cho từng sản phẩm được bật hoặc tắt
    const handleSelectProduct = (id) => {
        setSelectedProducts((prevSelected) => {
            if (prevSelected.includes(id)) {
                return prevSelected.filter((productId) => productId !== id);
            } else {
                return [...prevSelected, id];
            }
        });
    };

    const handleSelectDelete = () => {
        console.log('Sản phẩm sẽ bị xóa:', selectedProducts);
        setShowConfirmDelete(false);
        setSelectedProducts([]);
    };

    const handleConfirmDelete = () => {
        setShowConfirmDelete(true);
    };

    const handleCancelDelete = () => {
        setShowConfirmDelete(false);
    };

    // Hàm xử lý khi click vào icon sửa
    const handleEdit = (product) => {
        setEditingProduct(product);  // Set the selected product to be edited
        setIsModalOpen(true);  // Open the modal
    };

    // Hàm xử lý khi click vào icon xóa
    const handleDelete = (id) => {
        console.log('Xóa sản phẩm với ID:', id);
    };

    // Handle form submission to update the product
    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Sửa sản phẩm:', editingProduct);
        setIsModalOpen(false);  // Close the modal after submission
    };

    // Close modal without saving
    const handleCloseModal = () => {
        setIsModalOpen(false);
    };

    return (
        <div>
            {/* Modal for editing product */}
            {isModalOpen && (
                <div className="modal-overlay">
                    <div className="edit-product-modal">
                        <h2>Chỉnh sửa sản phẩm</h2>
                        <form onSubmit={handleSubmit}>
                            <div className="form-group">
                                <label>Tên sản phẩm:</label>
                                <input
                                    type="text"
                                    value={editingProduct.name}
                                    onChange={(e) => setEditingProduct({ ...editingProduct, name: e.target.value })}
                                />
                            </div>
                            <div className="form-group">
                                <label>Số lượng:</label>
                                <input
                                    type="number"
                                    value={editingProduct.quantity}
                                    onChange={(e) => setEditingProduct({ ...editingProduct, quantity: e.target.value })}
                                />
                            </div>
                            <div className="form-group">
                                <label>Giá:</label>
                                <input
                                    type="number"
                                    value={editingProduct.price}
                                    onChange={(e) => setEditingProduct({ ...editingProduct, price: e.target.value })}
                                />
                            </div>
                            <div className="form-group">
                                <label>Đã bán:</label>
                                <input
                                    type="number"
                                    value={editingProduct.sold}
                                    onChange={(e) => setEditingProduct({ ...editingProduct, sold: e.target.value })}
                                />
                            </div>
                            <div className="form-group">
                                <label>Loại sản phẩm:</label>
                                <input
                                    type="text"
                                    value={editingProduct.category}
                                    onChange={(e) => setEditingProduct({ ...editingProduct, category: e.target.value })}
                                />
                            </div>
                            <button type="submit">Lưu thay đổi</button>
                            <button type="button" onClick={handleCloseModal}>Hủy</button>
                        </form>
                    </div>
                </div>
            )}

            {/* Product Table */}
            <table className="product-table">
                <thead>
                    <tr>
                        <th>
                            <input
                                type="checkbox"
                                onChange={handleSelectAll}
                                checked={selectedProducts.length === products.length}
                            />
                        </th>
                        <th>TÊN SẢN PHẨM</th>
                        <th>SỐ LƯỢNG</th>
                        <th>GIÁ</th>
                        <th>ĐÃ BÁN</th>
                        <th>LOẠI SẢN PHẨM</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {products.map((product) => (
                        <tr key={product.id}>
                            <td>
                                <input
                                    type="checkbox"
                                    checked={selectedProducts.includes(product.id)}
                                    onChange={() => handleSelectProduct(product.id)}
                                />
                            </td>
                            <td>{product.name}</td>
                            <td>{product.quantity}</td>
                            <td>{product.price}</td>
                            <td>{product.sold}</td>
                            <td>{product.category}</td>
                            <td>
                                <FaEdit className="icon" onClick={() => handleEdit(product)} />
                                |
                                <FaTrash className="icon" onClick={() => handleDelete(product.id)} />
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ProductTable;
