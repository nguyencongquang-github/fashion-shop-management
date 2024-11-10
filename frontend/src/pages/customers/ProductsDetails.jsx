import React, { useEffect } from "react";
import { useContext, useState } from "react";
import { ShopContext } from "../../context/ShopContext";
import { useParams } from "react-router-dom";
import { assets } from "../../assets/assets";
import { useCart } from "./CartContext/CartContext";
import RelatedProducts from "./RelatedProducts";
import HeaderCustomer from "../../components/headerCustomer/HeaderCustomer";
import FooterCustomer from "../../components/footerCustomer/FooterCustomer";
const ProductsDetails = () => {
  const { productId } = useParams();
  const { products } = useContext(ShopContext);
  const [productData, setProductData] = useState(false);
  const { addToCart } = useCart();
  const [image, setImage] = useState("");
  const [size, setSize] = useState("");



  const fetchProductData = async () => {
    products.map((item) => {
      if (item.id === productId) {
        setProductData(item);
        setImage(item.image[0]);
        return null;
      }
    });
  };


  const handleAddToCart = () => {
    if (!size) {
      alert("Please select a size first");
      return;
    }
    
    // Add product with selected size to cart
    const productWithSize = {
      ...productData,
      selectedSize: size
    };
    addToCart(productWithSize);
  };



  useEffect(() => {
    fetchProductData();
  }, [productId]);

  return productData ? (
    
    <div className="border-t-2 pt-0 transition-opacity ease-in duration-500 opacity-100 ">
      <HeaderCustomer />
      {/* Product Data */}
      <div className="flex gap-4 sm:gap-4 flex-col sm:flex-row mt-5 mx-4 sm:mx-15">
        {/* Product Images */}
        <div className="flex-1 flex flex-col-reverse gap-5 sm:flex-row ml-10">
          <div className="flex sm:flex-col overflow-x-auto sm:overflow-y-scroll justify-between sm:justify-normal sm:w-[16.4%] w-full">
            {productData.image.map((item, index) => (
              <img
                onClick={() => setImage(item)}
                src={item}
                key={index}
                className="w-[24%] sm:w-full sm:mb-3 flex-shrink-0 cursor-pointer"
              />
            ))}
          </div>
          <div className="w-full sm:w-[70%]">
            <img className="w-full h-auto" src={image} alt="" />
          </div>
        </div>
        {/* Product Details */}
        <div className="flex-1 max-w-2xl mr-10">
          <h1 className="font-medium text-2xl mt-1">{productData.name}</h1>
          <div className="flex items-center gap-1 mt-1 ">
            <img src={assets.star_icon} alt="" className="w-3 5" />
            <img src={assets.star_icon} alt="" className="w-3 5" />
            <img src={assets.star_icon} alt="" className="w-3 5" />
            <img src={assets.star_icon} alt="" className="w-3 5" />
            <img src={assets.star_dull_icon} alt="" className="w-3 5" />
            <p className="pl-2">(122)</p>
          </div>
          <p className="mt-2 text-3xl font-medium">{productData.price} USD</p>
          <p className="mt-2 text-gray-500 md:w-4/5">
            {productData.description}
          </p>
          <div className="flex flex-col gap-1 my-3">
            <p>Select Size</p>
            <div className="flex gap-2">
              {productData.sizes.map((item, index) => (
                <button
                  onClick={() => setSize(item)}
                  key={index}
                  className={`border py-2.5 px-4 bg-gray-100 mt-2 ${
                    item === size ? "border-orange-500" : ""
                  }`}
                >
                  {item}
                </button>
              ))}
            </div>
          </div>
          <button onClick={handleAddToCart} className="bg-black text-white px-8 py-4 text-sm active:bg-gray-700">
            ADD TO CART{" "}
          </button>
          <hr className="mt-4 sm:w-4/5" />
          <div className="text-sm text-gray-500 mt-1 flex flex-col gap-1">
            <p>100% Original product.</p>
            <p>Cash on delivery is available on this product.</p>
            <p>Easy return and exchange policy within 7 days.</p>
          </div>
        </div>
      </div>
      {/* Description and review section  */}
      <div className="mt-10">
        <div className="flex border-b">
          <b className="border my-5 mx-1 px-6 py-4 text-sm font-medium border-b-2 border-black">Description</b>
          <p className="border my-5 px-6 py-4 text-sm font-medium text-gray-500">Review (122)</p>
        </div>
        <div className="flex flex-col gap-4 border-b px-4 py-4 text-sm text-gray-500 leading-relaxed">
          <p>
            An e-commerce website is an online platform that facilitates the
            buying and selling of products or services over the internet. It
            serves as a virual marketplace where businesses and individuals can
            showcase their products, interact with customers, and conduct
            transactions without the need for a physical presence. E-commerce
            websites have gained immense popularity due to their convenience,
            accessibility, and the global reach they offer.
          </p>
          <p>
            E-commerce websites typically display products or services along
            with detailed descriptions, images, prices, and any available
            variations. Each product usually has its own dedicated page with
            relevant information.
          </p>
        </div>
      </div>

      {/* Related products */}
      <RelatedProducts
        category={productData.category}
        subCategory={productData.subCategory}
      />
      <FooterCustomer />
    </div>
  ) : (
    <div className="opacity-0"></div>


  );
};

export default ProductsDetails;
