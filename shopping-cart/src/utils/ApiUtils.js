import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080';

export const fetchCartList = async () => {
  try {
    const response = await axios.get(`${API_BASE_URL}/carrinho`);
    return response.data;
  } catch (error) {
    console.error('Erro ao obter lista de carrinho:', error);
    return [];
  }
};

export const calcularValorTotalCarrinho = (carrinho) => {
  let total = 0;
  carrinho.listaProdutos.forEach(produto => {
    total += produto.valor;
  });
  return total;
};

export const fetchProductList = async () => {
  try {
    const response = await axios.get(`${API_BASE_URL}/produto`);
    return response.data;
  } catch (error) {
    console.error('Erro ao obter lista de produtos:', error);
    return [];
  }
};

export const addProduct = async (newProduct) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/produto`, newProduct);
    return response.data;
  } catch (error) {
    console.error('Erro ao adicionar produto:', error);
    return null;
  }
};

export const deleteProductByCodigo = async (codigo) => {
  try {
    await axios.delete(`${API_BASE_URL}/produto/codigo/${codigo}`);
  } catch (error) {
    console.error('Erro ao excluir produto:', error);
    throw new Error('Erro ao excluir produto');
  }
};

export const addProductToCart = async (product) => {
  try {
    // Fazer a requisição POST para adicionar o produto ao carrinho
    const response = await axios.post(`${API_BASE_URL}/carrinho`, {
      listaProdutos: [product], // Envie o produto no formato esperado pelo backend
    });
    return response.data;
  } catch (error) {
      console.error('Erro ao adicionar produto ao carrinho:', error);
    throw new Error('Erro ao adicionar produto ao carrinho');
  }
};