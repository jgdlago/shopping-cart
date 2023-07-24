import React, { useState, useEffect } from 'react';
import './cartListStyle.css';
import { fetchCartList, calcularValorTotalCarrinho } from '../../utils/ApiUtils';

const CartList = () => {
  const [cart, setCart] = useState([]);

  useEffect(() => {
    fetchCartListData();
  }, []);

  const fetchCartListData = async () => {
    const cartData = await fetchCartList();
    setCart(cartData);
  };

  const updateCartAfterAddingProduct = async () => {
    try {
      const updatedCartData = await fetchCartList();
      setCart(updatedCartData);
    } catch (error) {
      console.error('Erro ao atualizar carrinho:', error);
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
          </tr>
        </thead>
        <tbody>
          {cart.map(item => (
            <tr key={item.id}>
              <td>
                <ul>
                  {item.listaProdutos.map(produto => (
                    <li key={produto.id}>
                      {produto.nomeProduto} ({produto.unidadeMedida})
                    </li>
                  ))}
                </ul>
              </td>
              <td>
                <ul>
                  {item.listaProdutos.map(produto => (
                    <li key={produto.id}>
                      {produto.valor}
                    </li>
                  ))}
                </ul>
              </td>
            </tr>
          ))}
        </tbody>
        <tfoot>
          <tr>
            <th>Total do Carrinho</th>
            <td>R$ {cart.reduce((total, item) => total + calcularValorTotalCarrinho(item), 0)}</td>
          </tr>
        </tfoot>
      </table>
    </div>
  );
};

export default CartList;
