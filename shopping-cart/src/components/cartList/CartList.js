import React, { useState, useEffect } from 'react';
import './cartListStyle.css';
import { fetchCartList, removeProductFromCart, updateQuantity } from '../../utils/ApiUtils';
import CustomButton from '../customButton/customButton';

const CartList = () => {
  const [cart, setCart] = useState([]);

  useEffect(() => {
    fetchCartListData();
  }, []);

  const fetchCartListData = async () => {
    const cartData = await fetchCartList();
    setCart(cartData);
  };

  const updateCartAfterRemovingProduct = async () => {
    try {
      const updatedCartData = await fetchCartList();
      setCart(updatedCartData);
    } catch (error) {
      console.error('Erro ao atualizar carrinho:', error);
    }
  };

  const handleRemoveProduct = async (carrinhoId, produtoCodigo) => {
    try {
      await removeProductFromCart(carrinhoId, produtoCodigo);
      updateCartAfterRemovingProduct();
    } catch (error) {
      console.error('Erro ao remover produto do carrinho:', error);
    }
  };

  const handleQuantityChange = async (produtoId, quantidade) => {
    try {
      await updateQuantity(produtoId, quantidade);
      updateCartAfterRemovingProduct();
    } catch (error) {
      console.error('Erro ao atualizar quantidade do produto:', error);
    }
  };

  return (
    <div className="CartList">
      <h2>Carrinho</h2>
      <table>
        <thead>
          <tr>
            <th>Produto</th>
            <th>Valor Individual</th>
            <th>Quantidade</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {cart.map(item => (
            item.produtos && item.produtos.map(produto => (
              <tr key={produto.id}>
                <td>
                  {produto.nomeProduto} ({produto.unidadeMedida})
                </td>
                <td>
                  {produto.valor.toFixed(2)}
                </td>
                <td>
                  <input
                    type="number"
                    min="1"
                    value={produto.quantidade}
                    onChange={(event) => handleQuantityChange(produto.id, event.target.value)}
                  />
                </td>
                <td>
                  <div>
                    <CustomButton onClick={() => handleRemoveProduct(item.id, produto.codigo)} text={"Remover"} />
                  </div>
                </td>
              </tr>
            ))
          ))}
        </tbody>
        <tfoot>
          <tr>
            <th>Total do Carrinho</th>
            <td>R$ {cart.reduce((total, item) => total + item.valorTotal, 0)}</td>
            <td></td>
          </tr>
        </tfoot>

      </table>
    </div>
  );
};

export default CartList;
