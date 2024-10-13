import React from 'react';
import './App.css';
import {BrowserRouter, Navigate, Route, Routes} from 'react-router-dom';
import Home from './routed-pages/Home';
import About from './routed-pages/About';
import NotFound from './routed-pages/NotFound';
import MainScreen from './routed-pages/MainScreen';

function App() {
  return (
    <div className="App">
      <BrowserRouter>

        <Routes>

            <Route path="/rcf" element={<Home />} />
            <Route path="/rcf/app" element={<MainScreen />} />
            <Route path="/about" element={<About />} />
            <Route path="/notfound" element={<NotFound />} />

            <Route path="*" element={<Navigate to="/notfound" replace />} />


        </Routes>


      </BrowserRouter>

    </div>
  );
}

export default App;
