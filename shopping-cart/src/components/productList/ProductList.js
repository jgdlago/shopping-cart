import React, { useState, useEffect } from 'react';
import './ProductListStyle.css';
import ProductForm from '../productForm/ProductForm';
import ProductFormDelete from '../productForm/ProductFormDelete';
import CustomButton from '../customButton/customButton';
import { fetchProductList, addProduct, deleteProductByCodigo, addProductToCart, fetchCartList } from '../../utils/ApiUtils';

const ProductList = () => {
  const [products, setProducts] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [showDeleteForm, setShowDeleteForm] = useState(false);
  const [selectedProduct, setSelectedProduct] = useState(null);

  useEffect(() => {
    fetchProductListData();
  }, []);

  const fetchProductListData = async () => {
    try {
      const productList = await fetchProductList();
      setProducts(productList);
    } catch (error) {
      console.error(error.message);
    }
  };

  const handleAddProduct = async (newProduct) => {
    try {
      await addProduct(newProduct);
      setShowForm(false);
      fetchProductListData();
    } catch (error) {
      console.error(error.message);
    }
  };

  const handleDeleteProduct = async (codigo) => {
    try {
      await deleteProductByCodigo(codigo);
      setShowDeleteForm(false);
      fetchProductListData();
    } catch (error) {
      console.error(error.message);
    }
  };

  const handleAddToCart = async (productId) => {
    try {
      await addProductToCart(productId);
      fetchProductListData();
      fetchCartList();
      window.location.reload();
    } catch (error) {
      console.error(error.message);
    }
  };
  

  return (
    <div className="ProductList">
      <h2>Produtos disponíveis</h2>
      <table>
        <thead>
          <tr>
            <th>Código</th>
            <th>Produto</th>
            <th>U.M.</th>
            <th>Valor</th>
            <th>Ação</th>
          </tr>
        </thead>
        <tbody>
          {products.map(product => (
            <tr key={product.id}>
              <td>{product.codigo}</td>
              <td>{product.nomeProduto}</td>
              <td>{product.unidadeMedida}</td>
              <td>{product.valor}</td>
              <td>
                <CustomButton onClick={() => handleAddToCart(product.id)} text="Adicionar ao Carrinho" />
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <CustomButton onClick={() => setShowForm(true)} text="Novo produto" />
      {showForm && <ProductForm onSubmit={handleAddProduct} />}

      <CustomButton onClick={() => setShowDeleteForm(true)} text="Remover produto" />
      {showDeleteForm && <ProductFormDelete onSubmit={handleDeleteProduct} />}
    </div>
  );
};

export default ProductList;
