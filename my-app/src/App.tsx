import React from 'react';
import logo from './logo.svg';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import DefaultLayout from './components/containers/default';
import Home from './components/home';
import LoginPage from './components/auth/login';

const App = () => {
  return (
    <>
      <Routes>
        <Route path="/" element={<DefaultLayout />}>
          <Route index element={<Home />} />
          <Route path="login" element={<LoginPage />} />
        </Route>
      </Routes>
    </>
  );
}

export default App;
