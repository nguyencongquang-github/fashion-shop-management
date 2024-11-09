import React, { useState } from 'react';
import { FaChevronDown } from 'react-icons/fa'; // Chỉ import icon mũi tên xuống
import './RightSidebar.css';

const RightSidebar = () => {
    const [isCatalogDropOn, setCatalogDrop] = useState({
        danhMuc1: false,
        danhMuc2: false,
        danhMuc3: false,
    });

    const toggleCatalogDropdown = (category) => {
        setCatalogDrop((prevState) => ({
            ...prevState,
            [category]: !prevState[category],
        }));
    };

    return (
        <div className="right-sidebar">
            <label>Chọn danh mục</label>

            <div className="category">
                <div className="dropdown-header" onClick={() => toggleCatalogDropdown('danhMuc1')}>
                    Danh Mục 1
                    <FaChevronDown className={`chevron-icon ${isCatalogDropOn.danhMuc1}`} />
                </div>
                {isCatalogDropOn.danhMuc1 && (
                    <ul className="dropdown-content show">
                        <li><input type="checkbox" /> Mục Nhỏ 1.1</li>
                        <li><input type="checkbox" /> Mục Nhỏ 1.2</li>
                        <li><input type="checkbox" /> Mục Nhỏ 1.3</li>
                    </ul>
                )}
            </div>

            <div className="category">
                <div className="dropdown-header" onClick={() => toggleCatalogDropdown('danhMuc2')}>
                    Danh Mục 2
                    <FaChevronDown className={`chevron-icon ${isCatalogDropOn.danhMuc2}`} />
                </div>
                {isCatalogDropOn.danhMuc2 && (
                    <ul className="dropdown-content show">
                        <li><input type="checkbox" /> Mục Nhỏ 2.1</li>
                        <li><input type="checkbox" /> Mục Nhỏ 2.2</li>
                        <li><input type="checkbox" /> Mục Nhỏ 2.3</li>
                    </ul>
                )}
            </div>

            <div className="category">
                <div className="dropdown-header" onClick={() => toggleCatalogDropdown('danhMuc3')}>
                    Danh Mục 3
                    <FaChevronDown className={`chevron-icon ${isCatalogDropOn.danhMuc3}`} />
                </div>
                {isCatalogDropOn.danhMuc3 && (
                    <ul className="dropdown-content show">
                        <li><input type="checkbox" /> Mục Nhỏ 3.1</li>
                        <li><input type="checkbox" /> Mục Nhỏ 3.2</li>
                        <li><input type="checkbox" /> Mục Nhỏ 3.3</li>
                    </ul>
                )}
            </div>
        </div>
    );
};

export default RightSidebar;
