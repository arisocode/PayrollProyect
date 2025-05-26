import { useState } from 'react';
import { FaArrowLeft, FaBuilding, FaSave } from 'react-icons/fa';
import { useNavigate } from 'react-router-dom';
import '../App.css';

function NoveltyTypePage() {
    const [form, setForm] = useState({
        id: '',
        description: '',
        isCalculated: ''
    });

    const API_URL = process.env.REACT_APP_API_URL;
    const navigate = useNavigate();

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const guardarTipo = async () => {
        if (!form.description) {
            alert("El campo tipo de novedad es obligatorios.");
            return;
        }
        if (form.isCalculated === "") {
            alert("Debe seleccionar si el novedad es calculada o no.");
            return;
        }

        try {
        const response = await fetch(`${API_URL}/noveltyTypes`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
            id: form.id,
            description: form.description,
            isCalculated: form.isCalculated === 'true' || form.isCalculated === true,
            }),
        });
        
        if (!response.ok) {
            alert("Error al guardar tipo de novedad.");
            return;
        }

        await response.json();
        alert("Tipo de novedad guardado con éxito.");
        setForm({ id: '', description: '', isCalculated: '' });
        } catch (err) {
        console.error(err);
        alert("Error al guardar tipo de novedad.");
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
                <h2 className="form-title">Formulario de Tipos de Novedad</h2>

                {/* Tipo de Contrato */}
                <div className="form-row">
                <div className="form-group">
                    <label><FaBuilding /> Agregue un tipo de Novedad</label>
                    <input name="description" value={form.description} onChange={handleChange} className="input-field" />
                </div>
                <div className="form-group">
                    <label>Es Campo Calculado?</label>
                    <select name="isCalculated" value={form.isCalculated} onChange={handleChange} className="input-field">
                    <option value="">Seleccione</option>
                    <option value="true">Si</option>
                    <option value="false">No</option>
                    </select>
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

export default NoveltyTypePage;
