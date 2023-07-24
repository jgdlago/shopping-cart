import React from 'react';
import './customButton.css';

const CustomButton = ({ onClick, text}) => {
  return (
    <button className="CustomButton" onClick={onClick}>
      {text}
    </button>
  );
};

export default CustomButton;
