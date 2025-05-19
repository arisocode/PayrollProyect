import React, { useState } from 'react';
import { FaArrowLeft, FaBuilding, FaGlobe, FaHome, FaListUl, FaSave, FaSearch, FaTrash } from 'react-icons/fa';
import { useNavigate } from 'react-router-dom';
import '../App.css';

function BankPage() {
  const [form, setForm] = useState({
    id: '',
    nombre: '',
    codigo: '',
    estado: '',
    tipoBanco: '',
    pais: '',
    direccion: ''
  });

  const [bancoEncontrado, setBancoEncontrado] = useState(false);
  const API_URL = process.env.REACT_APP_API_URL;
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const buscarBanco = async () => {
    try {
      const response = await fetch(`${API_URL}/banks/${form.codigo}`);
      
      if (!response.ok) {
        alert("Banco no encontrado. Cree uno nuevo.");
        setBancoEncontrado(false);
        return;
      }
      const data = await response.json();

      setForm({
        id: data.id || '',
        codigo: data.code || '',
        nombre: data.name || '',
        estado: data.status?.toString() || '',
        tipoBanco: data.bankType?.toString() || '',
        pais: data.country || '',
        direccion: data.address || ''
      });

      setBancoEncontrado(true);
    } catch (err) {
      console.error(err);
      alert("Error al buscar banco.");
    }
  };

  const guardarBanco = async () => {
    if (!form.nombre || !form.codigo || !form.estado) {
      alert("Los campos Código, Nombre y Estado son obligatorios.");
      return;
    }

    try {
      const response = await fetch(`${API_URL}/banks`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          id: form.id,
          code: form.codigo,
          name: form.nombre,
          status: form.estado === "true",
          bankType: parseInt(form.tipoBanco) || null,
          country: form.pais || null,
          address: form.direccion || null
        }),
      });

      if (response.status === 409) {
        alert("Ya existe un banco con ese código.");
        return;
      }
      
      if (!response.ok) {
        alert("Error al guardar banco.");
        return;
      }

      await response.json();
      alert("Banco guardado con éxito.");
      setForm({ id: '', nombre: '', codigo: '', estado: '', tipoBanco: '', pais: '', direccion: '' });
      setBancoEncontrado(false);
    } catch (err) {
      console.error(err);
      alert("Error al guardar banco.");
    }
  };

  const eliminarBanco = async () => {
    if (!form.codigo) {
      alert("Debe buscar un banco primero.");
      return;
    }

    const confirm = window.confirm("¿Seguro que desea eliminar el banco?");
    if (!confirm) return;

    try {
      const response = await fetch(`${API_URL}/banks/${form.codigo}`, { method: 'DELETE' });
      if (!response.ok) {
        alert("Error al eliminar banco.");
        return;
      }

      alert("Banco eliminado.");
      setForm({ id: '', nombre: '', codigo: '', estado: '', tipoBanco: '', pais: '', direccion: '' });
      setBancoEncontrado(false);
    } catch (err) {
      console.error(err);
      alert("Error al eliminar banco.");
    }
  };

  return (
    <div className="App">
      <a href="/" className="header">
        <img src="payroll.png" alt="Logo" className="logo" />
        <h1 className="title">Poblado Nómina SAS</h1>
      </a>
      <main className="main-content">
        <div className="form-container">
          <div className="card">
            <h2 className="form-title">Formulario de Banco</h2>

            {/* Código y búsqueda */}
            <div className="form-row">
              <div className="form-group">
                <label><FaBuilding /> Código</label>
                <input name="codigo" value={form.codigo} onChange={handleChange} className="input-field" />
              </div>
              <div className="form-group">
                <button className="primary-button" onClick={buscarBanco}>
                  <FaSearch /> Buscar
                </button>
              </div>
            </div>

            {/* Nombre y Estado */}
            <div className="form-row">
              <div className="form-group">
                <label>Nombre</label>
                <input name="nombre" value={form.nombre} onChange={handleChange} className="input-field" />
              </div>
              <div className="form-group">
                <label>Estado</label>
                <select name="estado" value={form.estado} onChange={handleChange} className="input-field">
                  <option value="">Seleccione</option>
                  <option value="true">Activo</option>
                  <option value="false">Inactivo</option>
                </select>
              </div>
            </div>

            {/* Tipo de banco y país */}
            <div className="form-row">
              <div className="form-group">
                <label><FaListUl /> Tipo de Banco</label>
                <select name="tipoBanco" value={form.tipoBanco} onChange={handleChange} className="input-field">
                  <option value="">Seleccione</option>
                  <option value="1">Banco Comercial</option>
                  <option value="2">Banco de Desarrollo</option>
                  <option value="3">Banco Cooperativo</option>
                  <option value="4">Banco de Inversión</option>
                  <option value="5">Banco Hipotecario</option>
                  <option value="6">Banco Digital / Neobanco</option>
                </select>
              </div>
              <div className="form-group">
                <label><FaGlobe /> País</label>
                <input name="pais" value={form.pais} onChange={handleChange} className="input-field" />
              </div>
            </div>

            {/* Dirección */}
            <div className="form-row">
              <div className="form-group" style={{ width: '100%' }}>
                <label><FaHome /> Dirección</label>
                <input name="direccion" value={form.direccion} onChange={handleChange} className="input-field" />
              </div>
            </div>

            {/* Botones */}
            <div className="form-row">
              <button className="primary-button" onClick={guardarBanco}><FaSave /> Guardar</button>
              <button className="danger-button" onClick={eliminarBanco}><FaTrash /> Eliminar</button>
              <button className="secondary-button" onClick={() => navigate('/')}><FaArrowLeft /> Menú</button>
            </div>
          </div>
        </div>
      </main>
    </div>
  );
}

export default BankPage;
