import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Login from "../components/Login/Login";
import Home from "../components/Home";
import Dashboard from '../pages/admin/Dashboard/Dashboard';
import AllProducts from '../pages/admin/Product/AllProducts';
import InsertProduct from '../pages/admin/Product/InsertProduct';
import DiscountProducts from "../pages/admin/Product/DiscountProducts";
import DiscountProductsInfo from "../pages/admin/Product/DiscountProductsInfo";
import AllVouchers from '../pages/admin/Voucher/AllVouchers';
import EditVoucher from "../pages/admin/Voucher/EditVoucher";
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
        <Route path="/products/allProducts" element={<AllProducts />} />
        <Route path="/products/insertProduct" element={<InsertProduct />} />
        <Route path="/products/discountProducts" element={<DiscountProducts />} />
        <Route path="/products/discountProducts/discountInfo" element={<DiscountProductsInfo />} />
        <Route path="/vouchers/allVouchers" element={<AllVouchers />} />
        <Route path="/vouchers/editVoucher" element={<EditVoucher />} />
        <Route path="/customers" element={<Customer />} />
        <Route path="/orders" element={<Order />} />
        <Route path="/setting" element={<Setting />} />
      </Routes>
    </Router>
  );
};

export default AppRoutes;
