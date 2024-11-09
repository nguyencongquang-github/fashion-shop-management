import React, { useState } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import './InsertProduct.css';
import RightSidebar from './RightSidebar'; // Đảm bảo đường dẫn chính xác


const InsertProduct = () => {
    const [name, setName] = useState('');
    const [price, setPrice] = useState('');
    const [size, setSize] = useState('');
    const [description, setDescription] = useState('');
    const [details, setDetails] = useState('');

    return (
        <div className="insert-product-page d-flex flex-row">   
            <div className="form-container">
                <div className="form-group">
                    <label>Tên sản phẩm:</label>
                    <input 
                        type="text" 
                        value={name} 
                        onChange={(e) => setName(e.target.value)} 
                        placeholder="Nhập tên sản phẩm" required
                    />
                </div>

                <div className="form-row">
                    <div className="form-group">
                        <label>Giá sản phẩm:</label>
                        <input 
                            type="text" 
                            value={price} 
                            onChange={(e) => setPrice(e.target.value)} 
                            placeholder="Nhập giá sản phẩm" required
                        />
                    </div>

                    <div className="form-group">
                        <label>Kích thước:</label>
                        <input 
                            type="text" 
                            value={size} 
                            onChange={(e) => setSize(e.target.value)} 
                            placeholder="Nhập kích thước" required      
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label>Mô tả:</label>
                    <input 
                        type="text" 
                        value={description} 
                        onChange={(e) => setDescription(e.target.value)} 
                        placeholder="Nhập mô tả sản phẩm" required
                    />
                </div>

                <div className="form-group">
                    <label>Chi tiết:</label>
                    <ReactQuill 
                        value={details} 
                        onChange={setDetails} 
                        className="details-editor"
                    />
                </div>
                <button type="submit" className="submit-button">Thêm sản phẩm</button>
            </div>       
                <RightSidebar />
        </div>
    );
};

export default InsertProduct;
