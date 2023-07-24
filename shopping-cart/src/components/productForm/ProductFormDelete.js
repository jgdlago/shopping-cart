import React, { useState } from 'react';
import './ProductFormStyle.css';
import CustomButton from '../customButton/customButton';

const ProductFormDelete = ({ onSubmit }) => {
  const [codigo, setCodigo] = useState('');

  const handleChange = (event) => {
    const { value } = event.target;
    setCodigo(value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    onSubmit(codigo);
    setCodigo('');
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Código do Produto:</label>
        <input type="text" name="codigo" value={codigo} onChange={handleChange} />
      </div>
      <CustomButton type="submit" text={"Excluir"}/>
    </form>
  );
};

export default ProductFormDelete;
