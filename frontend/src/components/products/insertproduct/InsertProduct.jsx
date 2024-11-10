import React, { useState } from 'react';
import ReactQuill from 'react-quill';
import {TiDelete} from 'react-icons/ti';
import 'react-quill/dist/quill.snow.css';
import './InsertProduct.css';
import RightSidebar from './RightSidebar'; // Đảm bảo đường dẫn chính xác


const InsertProduct = () => {
    const [name, setName] = useState('');
    const [price, setPrice] = useState('');
    const [size, setSize] = useState('');
    const [description, setDescription] = useState('');
    const [details, setDetails] = useState('');
    const [image, setImage] = useState(null);
    const handleImageChange = (e) => {
        const file = e.target.files[0];
        if (file) {
            setImage(URL.createObjectURL(file)); // Preview the image
        }
    };
    // Handle image removal
    const handleImageRemove = () => {
        setImage(null); // Clear the image
    };
    const handleSubmit = (e) => {
        e.preventDefault();
        // Handle form submission, including image upload
        // In a real application, you'd typically send the image to a server here
        console.log('Product Data:', {
            name,
            price,
            size,
            description,
            details,
            image
        });
    };

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
                <div className="form-group">
                    <label>Hình ảnh:</label>
                    <input 
                        type="file" 
                        accept="image/*" 
                        onChange={handleImageChange} 
                    />
                    {image && (
                        <div className="image-preview-container">
                            <img src={image} alt="Product Preview" className="image-preview" />
                            <button type="button" className="remove-image-button" onClick={handleImageRemove}>
                                <TiDelete />
                            </button>
                        </div>
                    )}
                </div>
                <button type="submit" className="submit-button" onClick={handleSubmit}>
                    Thêm sản phẩm
                </button>
            </div>       
                <RightSidebar />
        </div>
    );
};

export default InsertProduct;
