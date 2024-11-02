import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Login from "../components/Login/Login";
import Home from "../components/Home";
import Dashboard from '../pages/admin/Dashboard';
import AllProducts from '../pages/admin/AllProducts';
import Order from '../pages/admin/Orders';
import Customer from '../pages/admin/Customers';
import Voucher from '../pages/admin/Vouchers';
import Setting from '../pages/admin/Setting';

const AppRoutes = () => {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/" element={<Home />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/products/all" element={<AllProducts />} />
        <Route path="/customers" element={<Customer />} />
        <Route path="/vouchers" element={<Voucher />} />
        <Route path="/orders" element={<Order />} />
        <Route path="/setting" element={<Setting/>} />
      </Routes>
    </Router>
  );
};

export default AppRoutes;
