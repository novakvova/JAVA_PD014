import React from 'react';
import logo from './logo.svg';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import DefaultLayout from './components/containers/default';
import Home from './components/home';
import LoginPage from './components/auth/login';
import ProductListPage from './components/products/list';
import ProductItemPage from './components/products/item/ProductItemPage';
import AdminLayout from './components/containers/admin';
import AdminCategoryCreatePage from './components/admin/categories/create';
import AdminProductCreatePage from './components/admin/products/create';
import AdminProductListPage from './components/admin/products/list';
import AdminProductEditPage from './components/admin/products/edit';
import AdminHome from './components/admin/categories/list';
import Loader from './components/common/Loader/Loader';

const App = () => {
  return (
    <>
      <Loader />
      <Routes>
        <Route path="/" element={<DefaultLayout />}>
          <Route index element={<Home />} />
          <Route path="products/list" element={<ProductListPage />} />
          <Route path="products/view/:id" element={<ProductItemPage/> }/>
          <Route path="login" element={<LoginPage />} />
        </Route>

        <Route path="/admin" element={<AdminLayout />}>
          <Route index element={<AdminHome />} />
          <Route path="categories/create" element={<AdminCategoryCreatePage />} />
          <Route path="products/create" element={<AdminProductCreatePage />} />
          <Route path="products/list" element={<AdminProductListPage />} />
          <Route path="products/edit/:id" element={<AdminProductEditPage/> }/>
        </Route>
      </Routes>
    </>
  );
}

export default App;
