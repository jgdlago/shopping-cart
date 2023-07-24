import ProductList from '../../components/productList/ProductList';
import CartList from '../../components/cartList/CartList';
import './home.css'

function Home() {
    return (
      <div className="container-home">
        <ProductList />
        <CartList />
      </div>
    );
  }
  
export default Home;