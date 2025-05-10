import React from 'react';
import { FaBuilding, FaClock, FaDollarSign, FaFileContract, FaMoneyCheckAlt, FaUserTie, FaUsers } from 'react-icons/fa';
import { Route, BrowserRouter as Router, Routes, useNavigate } from 'react-router-dom';
import '../App.css';
import BankPage from './BankPage';
import EmpleadoPage from './EmployeePage';

const modules = [
  { name: 'Empleados', icon: <FaUserTie />, route: '/empleados' },
  { name: 'Terceros', icon: <FaUsers /> },
  { name: 'Contratos', icon: <FaFileContract /> },
  { name: 'Bancos', icon: <FaBuilding />, route: '/bancos' },
  { name: 'Novedades', icon: <FaClock /> },
  { name: 'Cargos', icon: <FaUserTie /> },
  { name: 'Liquidación de Nómina', icon: <FaDollarSign /> },
  { name: 'Liquidación de Cesantías', icon: <FaMoneyCheckAlt /> },
  { name: 'Liquidación Definitiva', icon: <FaMoneyCheckAlt /> },
];

function Home() {
  const navigate = useNavigate();
  const today = new Date().toLocaleDateString('es-CO', {
    weekday: 'long',
    year: 'numeric',
    month: 'long',
    day: 'numeric',
  });

  const handleClick = (mod) => {
    if (mod.route) navigate(mod.route);
  };

  const employeeStatus = [
    { name: 'Juan Pérez', status: 'Activo' },
    { name: 'Ana López', status: 'Inactivo' },
    { name: 'Carlos Méndez', status: 'Activo' },
    { name: 'Laura Romero', status: 'Incapacitada' },
  ];

  return (
    <div className="App">
      <header className="header">
        <h1>Empresa Nómina</h1>
        <img src="https://upload.wikimedia.org/wikipedia/commons/a/a7/React-icon.svg" alt="Logo" className="logo" />
      </header>
      <main className="main-content">
        <section className="modules-section">
          {modules.map((mod, index) => (
            <button className="module-button" key={index} onClick={() => handleClick(mod)}>
              {mod.icon}
              <span>{mod.name}</span>
            </button>
          ))}
        </section>
        <section className="employee-status">
          <h2 className="section-title">
            Estado de Empleados <span className="date">({today})</span>
          </h2>
          <div className="scrollable-list">
            <ul>
              {employeeStatus.map((emp, idx) => (
                <li key={idx} className={`status ${emp.status.toLowerCase()}`}>
                  <strong>{emp.name}</strong> - {emp.status}
                </li>
              ))}
            </ul>
          </div>
        </section>
      </main>
    </div>
  );
}

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/empleados" element={<EmpleadoPage />} />
        <Route path="/bancos" element={<BankPage />} />
      </Routes>
    </Router>
  );
}

export default App;
