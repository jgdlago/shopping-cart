import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './ProductListStyle.css'; // Importe o arquivo CSS

const ProductList = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/produto')
      .then(response => {
        setProducts(response.data);
      })
      .catch(error => {
        console.error('Erro ao obter lista de produtos:', error);
      });
  }, []);

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
          </tr>
        </thead>
        <tbody>
          {products.map(product => (
            <tr key={product.id}>
              <td>{product.codigo}</td>
              <td>{product.nomeProduto}</td>
              <td>{product.unidadeMedida}</td>
              <td>{product.valor}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};


export default ProductList;