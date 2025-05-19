import { useState } from 'react';
import { FaArrowLeft, FaBuilding, FaSave } from 'react-icons/fa';
import { useNavigate } from 'react-router-dom';
import '../App.css';

function ContractTypePage() {
  const [form, setForm] = useState({
    id: '',
    description: ''
  });

  const API_URL = process.env.REACT_APP_API_URL;
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const guardarTipo = async () => {
    if (!form.description) {
      alert("El campo tipo de contrato es obligatorios.");
      return;
    }

    try {
      const response = await fetch(`${API_URL}/contractTypes`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          id: form.id,
          description: form.description
        }),
      });
      
      if (!response.ok) {
        alert("Error al guardar tipo de contrato.");
        return;
      }

      await response.json();
      alert("Tipo de contrato guardado con éxito.");
      setForm({ id: '', description: '' });
    } catch (err) {
      console.error(err);
      alert("Error al guardar tipo de contrato.");
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
            <h2 className="form-title">Formulario de Tipos de Contrato</h2>

            {/* Tipo de Contrato */}
            <div className="form-row">
              <div className="form-group">
                <label><FaBuilding /> Agregue un tipo de Contrato</label>
                <input name="description" value={form.description} onChange={handleChange} className="input-field" />
              </div>
            </div>

            {/* Botones */}
            <div className="form-row">
              <button className="primary-button" onClick={guardarTipo}><FaSave /> Guardar</button>
              <button className="secondary-button" onClick={() => navigate('/')}><FaArrowLeft /> Regresar</button>
            </div>
          </div>
        </div>
      </main>
    </div>
  );
}

export default ContractTypePage;
