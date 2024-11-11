
import React, { useEffect, useState } from 'react'
import { ShopContext } from '../../context/ShopContext'
import { useContext } from 'react'
import { assets } from '../../assets/assets';
import { useLocation } from 'react-router-dom';
import './SearchBar.css';
const SearchBar = () => {

const { search, setSearch, showSearch, setShowSearch } = useContext(ShopContext);
const [visible, setVisible] = useState(false);
const location = useLocation();

useEffect(() => {
    if(location.pathname.includes('/shop')){
        setVisible(true);
    }
    else{
        setVisible(false);
    }
}, [location])

return showSearch && visible ? (
  <div className="search-container">
    <div className="search-input-wrapper">
      <input 
        value={search} 
        onChange={(e) => setSearch(e.target.value)} 
        className="search-input" 
        type="text" 
        placeholder="Search"
      />
      <img className="search-icon" src={assets.search_icon} alt="" />
    </div>
    <img 
      onClick={() => setShowSearch(false)} 
      className="close-icon" 
      src={assets.cross_icon} 
      alt="" 
    />
  </div>
) : null;
}

export default SearchBar
