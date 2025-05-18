import { useState } from 'react';
import { FaArrowLeft, FaBuilding, FaHome, FaSave } from 'react-icons/fa';
import { useNavigate } from 'react-router-dom';
import '../App.css';

function SchedulePage() {
  const [form, setForm] = useState({
    id: '',
    name: '',
    startHour: '',
    endHour: '',
    description: ''
  });

  const API_URL = process.env.REACT_APP_API_URL;
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const guardarHorario = async () => {
    if (!form.name || !form.startHour || !form.endHour) {
      alert("Los campos Nombre, Hora de inicio y Hora fin son obligatorios.");
      return;
    }

    try {
      const response = await fetch(`${API_URL}/schedules`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          id: form.id,
          name: form.name,
          startHour: parseFloat(form.startHour) || 0.0,
          endHour: parseFloat(form.endHour) || 0.0,
          description: form.description
        }),
      });
      
      if (!response.ok) {
        alert("Error al guardar horario.");
        return;
      }

      await response.json();
      alert("Horario guardado con éxito.");
      setForm({ id: '', name: '', startHour: '', endHour: '', description: '' });
    } catch (err) {
      console.error(err);
      alert("Error al guardar horario.");
    }
  };

  return (
    <div className="App">
      <header className="header">
        <h1>Gestión de Bancos</h1>
      </header>
      <main className="main-content">
        <div className="form-container">
          <div className="card">
            <h2 className="form-title">Formulario de Banco</h2>

            {/* Nombre y descripcion */}
            <div className="form-row">
              <div className="form-group">
                <label><FaBuilding /> Nombre del Horario</label>
                <input name="name" value={form.name} onChange={handleChange} className="input-field" />
              </div>
              <div className="form-group" style={{ width: '100%' }}>
                <label><FaHome /> Descripción </label>
                <input name="description" value={form.description} onChange={handleChange} className="input-field" />
              </div>
            </div>

            {/* Hora incio y hora fin */}
            <div className="form-row">
              <div className="form-group">
                <label><FaHome /> Hora inicio</label>
                <input
                  name="startHour"
                  value={form.startHour}
                  onChange={handleChange}
                  className="input-field"
                  type="number"
                />
              </div>
              <div className="form-group">
                <label><FaHome /> Hora fin</label>
                <input
                  name="endHour"
                  value={form.endHour}
                  onChange={handleChange}
                  className="input-field"
                  type="number"
                />
              </div>
            </div>

            {/* Botones */}
            <div className="form-row">
              <button className="primary-button" onClick={guardarHorario}><FaSave /> Guardar</button>
              <button className="secondary-button" onClick={() => navigate('/')}><FaArrowLeft /> Regresar</button>
            </div>
          </div>
        </div>
      </main>
    </div>
  );
}

export default SchedulePage;
