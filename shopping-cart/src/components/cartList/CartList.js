import React, { useState, useEffect } from 'react';
import './cartListStyle.css';
import { fetchCartList, calcularValorTotalCarrinho, removeProductFromCart } from '../../utils/ApiUtils';
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
      // Fazer a requisição DELETE para remover o produto do carrinho
      await removeProductFromCart(carrinhoId, produtoCodigo);
      // Atualizar a lista de produtos do carrinho após a remoção
      updateCartAfterRemovingProduct();
    } catch (error) {
      console.error('Erro ao remover produto do carrinho:', error);
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
            <tr key={item.id}>
              <td>
                {/* Mapear a lista de produtos e exibir o nome e a unidade de medida */}
                {item.produtos && item.produtos.map(produto => (
                  <div key={produto.id}>
                    {produto.nomeProduto} ({produto.unidadeMedida})
                  </div>
                ))}
              </td>
              <td>
                {/* Mapear a lista de produtos e exibir o valor */}
                {item.produtos && item.produtos.map(produto => (
                  <div key={produto.id}>
                    {produto.valor}
                  </div>
                ))}
              </td>
              <td>
                {/* Mapear a lista de produtos e exibir a quantidade */}
                {item.produtos && item.produtos.map(produto => (
                  <div key={produto.id}>
                    {produto.quantidade}
                  </div>
                ))}
              </td>
              <td>
                {/* Mapear a lista de produtos e exibir o botão de remover */}
                {item.produtos && item.produtos.map(produto => (
                  <div key={produto.id}>
                    <CustomButton onClick={() => handleRemoveProduct(item.id, produto.codigo)} text={"Remover"} />
                  </div>
                ))}
              </td>
            </tr>
          ))}
        </tbody>
        <tfoot>
          <tr>
            <th>Total do Carrinho</th>
            <td>R$ {cart.reduce((total, item) => total + calcularValorTotalCarrinho(item), 0)}</td>
            <td></td>
          </tr>
        </tfoot>
      </table>
    </div>
  );
};

export default CartList;
