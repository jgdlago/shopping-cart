// CartList.js

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './cartListStyle.css';

const CartList = () => {
  const [cart, setCart] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/carrinho')
      .then(response => {
        setCart(response.data);
      })
      .catch(error => {
        console.error('Erro ao obter lista de carrinho:', error);
      });
  }, []);

  // Função para calcular o valor total do carrinho
  const calcularValorTotalCarrinho = (carrinho) => {
    let total = 0;
    carrinho.listaProdutos.forEach(produto => {
      total += produto.valor;
    });
    return total;
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
