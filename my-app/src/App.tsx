import React from 'react';
import logo from './logo.svg';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import DefaultLayout from './components/containers/default';
import Home from './components/home';
import LoginPage from './components/auth/login';
import CategoryCreatePage from './components/categories/create';
import ProductCreatePage from './components/products/create';
import ProductListPage from './components/products/list';
import ProductEditPage from './components/products/edit';

const App = () => {
  return (
    <>
      <Routes>
        <Route path="/" element={<DefaultLayout />}>
          <Route index element={<Home />} />
          <Route path="categories/create" element={<CategoryCreatePage />} />
          <Route path="products/create" element={<ProductCreatePage />} />
          <Route path="products/list" element={<ProductListPage />} />
          <Route path="products/edit/:id" element={<ProductEditPage/> }/>
          <Route path="login" element={<LoginPage />} />
        </Route>
      </Routes>
    </>
  );
}

export default App;
