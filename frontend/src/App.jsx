import AppRoutes from "./routes/AppRoutes";
import { CartProvider } from "./pages/customers/CartContext/CartContext";
import ShopContextProvider from "./context/ShopContext";
import "react-toastify/dist/ReactToastify.css";
function App() {
  return (
    <ShopContextProvider>
      <CartProvider>
        <AppRoutes />
      </CartProvider>
    </ShopContextProvider>
      
      
    


  );
}

export default App;
