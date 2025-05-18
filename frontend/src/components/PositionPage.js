import { useState } from 'react';
import { FaArrowLeft, FaBuilding, FaHome, FaSave, FaSearch, FaTrash } from 'react-icons/fa';
import { useNavigate } from 'react-router-dom';
import '../App.css';

function PositionPage() {
  const [form, setForm] = useState({
    id: '',
    code: '',
    name: '',
    baseSalary: '',
    description: ''
  });

  const [cargoEncontrado, setCargoEncontrado] = useState(false);
  const API_URL = process.env.REACT_APP_API_URL;
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

   const buscarCargo = async () => {

    try {
      const response = await fetch(`${API_URL}/positions/${form.code}`);
      
      if (!response.ok) {
        alert("Cargo no encontrado. Cree uno nuevo.");
        setCargoEncontrado(false);
        return;
      }
      const data = await response.json();

      setForm({
        id: data.id || '',
        code: data.code || '',
        name: data.name || '',
        baseSalary: data.baseSalary !== undefined && data.baseSalary !== null ? parseFloat(data.baseSalary) : '',
        description: data.description || ''
      });

      setCargoEncontrado(true);
    } catch (err) {
      console.error(err);
      alert("Error al buscar cargo.");
    }
  };

  const guardarCargo = async () => {
    if (!form.name || !form.code || !form.baseSalary) {
      alert("Los campos Código, Nombre y Salario Base son obligatorios.");
      return;
    }

    try {
      const response = await fetch(`${API_URL}/positions`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          id: form.id,
          code: form.code,
          name: form.name,
          baseSalary: parseFloat(form.baseSalary) || 0.0,
          description: form.description || null
        }),
      });

      if (response.status === 409) {
        alert("Ya existe un cargo con ese código.");
        return;
      }
      
      if (!response.ok) {
        alert("Error al guardar el nuevo cargo.");
        return;
      }

      await response.json();
      alert("Cargo guardado con éxito.");
      setForm({ id: '', code: '', name: '', baseSalary: '', description: '' });
      setCargoEncontrado(false);
    } catch (err) {
      console.error(err);
      alert("Error al guardar cargo.");
    }
  };

   const eliminarCargo = async () => {

    if (!form.code) {
      alert("Debe buscar un cargo primero.");
      return;
    }

    const confirm = window.confirm("¿Seguro que desea eliminar el cargo?");
    if (!confirm) return;

    try {
      const response = await fetch(`${API_URL}/positions/${form.code}`, { method: 'DELETE' });
      if (!response.ok) {
        alert("Error al eliminar cargo.");
        return;
      }

      alert("Cargo eliminado.");
      setForm({ id: '', code: '', name: '', baseSalary: '', description: '' });
      setCargoEncontrado(false);
    } catch (err) {
      console.error(err);
      alert("Error al eliminar cargo.");
    }
  };

  return (
    <div className="App">
      <header className="header">
        <h1>Cargos</h1>
      </header>
      <main className="main-content">
        <div className="form-container">
          <div className="card">
            <h2 className="form-title">Formulario de cargos</h2>

            {/* Código y búsqueda */}
            <div className="form-row">
              <div className="form-group">
                <label><FaBuilding /> Código</label>
                <input name="code" value={form.code} onChange={handleChange} className="input-field" />
              </div>
              <div className="form-group">
                <button className="primary-button" onClick={buscarCargo}>
                  <FaSearch /> Buscar
                </button>
              </div>
            </div>

            {/* Nombre y salario base */}
            <div className="form-row">
              <div className="form-group">
                <label><FaBuilding /> Nombre</label>
                <input
                  name="name"
                  value={form.name}
                  onChange={handleChange}
                  className="input-field"
                />
              </div>
              <div className="form-group">
                <label><FaHome /> Salario Base</label>
                <input
                  name="baseSalary"
                  value={form.baseSalary}
                  onChange={handleChange}
                  className="input-field"
                  type="number"
                />
              </div>
            </div>

            {/* Descripción */}
            <div className="form-row">
              <div className="form-group" style={{ width: '100%' }}>
                <label><FaHome /> Descripción </label>
                <input name="description" value={form.description} onChange={handleChange} className="input-field" />
              </div>
            </div>

            {/* Botones */}
            <div className="form-row">
              <button className="primary-button" onClick={guardarCargo}><FaSave /> Guardar</button>
              <button className="danger-button" onClick={eliminarCargo}><FaTrash /> Eliminar</button>
              <button className="secondary-button" onClick={() => navigate('/')}><FaArrowLeft /> Menú</button>
            </div>
          </div>
        </div>
      </main>
    </div>
  );
}

export default PositionPage;
