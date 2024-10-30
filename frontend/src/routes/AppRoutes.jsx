import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Login from "../components/Login";
import Home from "../components/Home";
import Dashboard from '../pages/admin/Dashboard';
import Product from '../pages/admin/Products';
import Order from '../pages/admin/Orders';
import Customer from '../pages/admin/Customers';
import Setting from '../pages/admin/Setting';

const AppRoutes = () => {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/" element={<Home />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/products" element={<Product />} />
        <Route path="/customers" element={<Customer />} />
        <Route path="/orders" element={<Order />} />
        <Route path="/setting" element={<Setting/>} />
      </Routes>
    </Router>
  );
};

export default AppRoutes;
