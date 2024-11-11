import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router-dom";
import Login from "../components/Login/Login";
import Dashboard from '../pages/admin/Dashboard';
import AllProducts from '../pages/admin/AllProducts';
import Order from '../pages/admin/Orders';
import Customer from '../pages/admin/Customers';
import Voucher from '../pages/admin/Vouchers';
import Setting from '../pages/admin/Setting';
import Register from "../components/Register/Register";
import HomeCustomer from "../pages/customers/HomeCustomer";
import CartCustomer from "../pages/customers/CartCustomer";
import ProductsCustomer from "../pages/customers/ProductsCustomer";
import Blog from "../pages/customers/Blog";
import About from "../pages/customers/About";
import Contact from "../pages/customers/Contact";
import ProductsDetails from "../pages/customers/ProductsDetails";
const AppRoutes = () => {
  return (
    <Router>     
      <Routes>
      <Route path="/" element={<Navigate to="/home" replace />} />       
        <Route path="/home" element={<HomeCustomer />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/products/all" element={<AllProducts />} />
        <Route path="/product/:productId" element={<ProductsDetails />} />
        <Route path="/customers" element={<Customer />} />
        <Route path="/vouchers" element={<Voucher />} />
        <Route path="/orders" element={<Order />} />
        <Route path="/setting" element={<Setting/>} />
        <Route path="/cart" element={<CartCustomer />} />
        <Route path="/shop" element={<ProductsCustomer />} />
        <Route path="/blog" element={<Blog />} />
        <Route path="/about" element={<About />} />
        <Route path="/contact" element={<Contact />} />
      </Routes>
    </Router>
  );
};

export default AppRoutes;
