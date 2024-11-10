// import React from "react";
// import { Link } from "react-router-dom";

// const ProductsItem = ({ id, image, name, price }) => {
//   return (
//     <Link className="text-gray-700 cursor-pointer" to={`/product/${id}`}>
//       <div className="overflow-hidden">
//         <img
//           className="hover:scale-110 transition ease-in-out"
//           src={image[0]}
//           alt={name}
//         />
//       </div>
//       <p className="pt-3 pb-1 text-sm">{name}</p>
//       <p className="text-sm font-medium">{price} USD</p>
//     </Link>
//   );
// };

// export default ProductsItem;
// ProductsItem.jsx
import React from "react";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar, faShoppingCart } from "@fortawesome/free-solid-svg-icons";
import { useCart } from "../../pages/customers/CartContext/CartContext";

const ProductsItem = ({ id, image, name, price }) => {
  const { addToCart } = useCart();

  const handleAddToCart = (e) => {
    e.preventDefault();
    addToCart({ id, image, name, price });
  };

  return (
    <div className="w-[23%] min-w-[250px] p-[10px_12px] border border-[#cce7d0] rounded-[25px] cursor-pointer shadow-[20px_20px_30px_rgba(0,0,0,0.02)] my-[15px] transition-all duration-200 ease-in-out hover:shadow-[20px_20px_30px_rgba(0,0,0,0.06)] relative">
      <Link to={`/product/${id}`}>
        <img
          src={Array.isArray(image) ? image[0] : image}
          alt={name}
          className="w-full rounded-[20px] transition-transform duration-200 hover:scale-110"
        />
        <div className="text-left py-[10px]">
          <span className="text-[#606063] text-xs">adidas</span>
          <h5 className="pt-[7px] text-[#1a1a1a] text-sm">{name}</h5>
          <div className="text-xs text-[rgb(243,141,25)]">
            <FontAwesomeIcon icon={faStar} />
            <FontAwesomeIcon icon={faStar} />
            <FontAwesomeIcon icon={faStar} />
            <FontAwesomeIcon icon={faStar} />
          </div>
          <h4 className="pt-[7px] text-[15px] mb-3 font-bold text-[#088178]">
            {price.toLocaleString()} VNĐ
          </h4>
        </div>
      </Link>
      <a href="#" onClick={handleAddToCart}>
        <FontAwesomeIcon
          icon={faShoppingCart}
          className="h-[20px] w-[20px] p-[15px] rounded-full mr-2 bg-[#e8f6ea] font-medium text-[#088178] border border-[#cce7d0] absolute bottom-[20px] right-[10px]"
        />
      </a>
    </div>
  );
};

export default ProductsItem;
