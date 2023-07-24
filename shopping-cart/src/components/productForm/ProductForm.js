import React, { useState } from 'react';
import './ProductFormStyle.css';
import AddButton from '../customButton/customButton';

const ProductForm = ({ onSubmit }) => {
  const [produto, setProduto] = useState({
    codigo: '',
    nomeProduto: '',
    unidadeMedida: '',
    valor: '',
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setProduto({
      ...produto,
      [name]: value,
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    onSubmit(produto);
    setProduto({
      codigo: '',
      nomeProduto: '',
      unidadeMedida: '',
      valor: '',
    });
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Código:</label>
        <input type="text" name="codigo" value={produto.codigo} onChange={handleChange} />
      </div>
      <div>
        <label>Nome do Produto:</label>
        <input type="text" name="nomeProduto" value={produto.nomeProduto} onChange={handleChange} />
      </div>
      <div>
        <label>Unidade de Medida:</label>
        <input type="text" name="unidadeMedida" value={produto.unidadeMedida} onChange={handleChange} />
      </div>
      <div>
        <label>Valor:</label>
        <input type="text" name="valor" value={produto.valor} onChange={handleChange} />
      </div>
      <AddButton onClick={handleSubmit} text="Adicionar" />
    </form>
  );
};

export default ProductForm;
