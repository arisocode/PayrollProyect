import { useCallback, useEffect, useState } from 'react';
import { FaArrowLeft, FaIdCard, FaSave } from 'react-icons/fa';
import { useNavigate } from 'react-router-dom';
import '../App.css';

function NoveltyPage() {

    const initialFormState = {
        id: '',
        date: '',
        hours: '',
        days: '',
        status: '',
        noveltyTypeId: '',
        contractId: '',
        employeeId: '',
        description: ''
    };
    
    const [form, setForm] = useState(initialFormState);

    const [novedadEncontrada, setNovedadEncontrada] = useState(false);
    const API_URL = process.env.REACT_APP_API_URL;
    const navigate = useNavigate();

    const [empleados, setEmpleados] = useState([]);
    const [tipos, setTipos] = useState([]);

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const fetchEmployees = useCallback(async () => {
        try {
            const res = await fetch(`${API_URL}/employees`);
            const data = await res.json();
            setEmpleados(data);
        } catch (err) {
            console.error('Error al cargar empleados:', err);
        }
    }, [API_URL]);

    const fetchTipos = useCallback(async () => {
        try {
            const response = await fetch(`${API_URL}/noveltyTypes`);
            const data = await response.json();
            setTipos(data);
        } catch (error) {
            console.error("Error al cargar los tipos de novedades:", error);
        }
    }, [API_URL]);

    useEffect(() => {
        fetchEmployees();
        fetchTipos();
    }, [fetchEmployees, fetchTipos]);

    const confirmarNovedad = async () => {
        const esCampoInvalido = (campo) => {
            if (typeof campo === 'string') return campo.trim() === '';
            if (typeof campo === 'number') return isNaN(campo);
            return campo === null || campo === undefined;
        };

        const camposObligatorios = [
            { campo: form.date, nombre: 'Fecha' },
            { campo: form.hours, nombre: 'Horas' },
            { campo: form.status, nombre: 'Estado' },
            { campo: form.noveltyTypeId, nombre: 'Tipo de novedad' },
            { campo: form.employeeId, nombre: 'Empleado' }
        ];
    
        const camposFaltantes = camposObligatorios
            .filter(c => esCampoInvalido(c.campo))
            .map(c => c.nombre);

        if (camposFaltantes.length > 0) {
            alert(`Los siguientes campos son obligatorios:\n\n${camposFaltantes.join('\n')}`);
            return;
        }

        const payload = {
        ...form,
        id: undefined,
        status: form.status === 'true' || form.status === true,
        hours: parseFloat(form.hours),
        days: parseFloat(form.days),
        noveltyTypeId: Number(form.noveltyTypeId),
        contractId: 0,
        employeeId: Number(form.employeeId)
        };

        console.log("Payload enviado:", JSON.stringify(payload, null, 2));

        try {
        const response = await fetch(`${API_URL}/novelties`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload),
        });

        if (!response.ok) {
            alert("Error al guardar novedad.");
            return;
        }

        alert("Novedad guardado con éxito.");
        setForm(initialFormState);
        setNovedadEncontrada(true);
        fetchEmployees()
        fetchTipos()
        } catch (error) {
        console.error("Error al guardar novedad:", error);
        alert("Error al guardar novedad.");
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
                <h2 className="form-title">Formulario de Novedades</h2>

                {/* Código y búsqueda */}
                <div className="form-row" style={{ display: 'flex', flexWrap: 'wrap', gap: '20px', marginBottom: '20px' }}>
                <div className="form-group" style={{ flex: '1 1 250px', display: 'flex', flexDirection: 'column' }}>
                    <label>Fecha de la novedad</label>
                    <input
                    type="date"
                    name="date"
                    value={form.date}
                    onChange={handleChange}
                    className="input-field"
                    style={{ width: '265px' }}
                    />
                </div>
                <div className="form-group" style={{ flex: '1 1 250px', display: 'flex', flexDirection: 'column' }}>
                    <label>Tipo de Novedad</label>
                    <div style={{ display: 'flex', alignItems: 'center' }}>
                    <select
                        name="noveltyTypeId"
                        value={form.noveltyTypeId}
                        onChange={handleChange}
                        className="input-field"
                        style={{ width: '200px' }}
                    >
                        <option value="">Seleccione</option>
                        {tipos.map(j => (
                        <option key={j.id} value={j.id}>
                            {j.description}
                        </option>
                        ))}
                    </select>
                    <button
                        type="button"
                        className="add-button"
                        onClick={() => navigate('/typesNovelty')}
                        style={{ marginLeft: '10px', width: '80px' }}
                    >
                        +
                    </button>
                    </div>
                </div>
                </div>

                {/* Nombre y Estado */}
                <div className="form-row">
                <div className="form-group" style={{ flex: '1 1 250px', display: 'flex', flexDirection: 'column' }}>
                    <label>Horas</label>
                    <input
                    type="number"
                    name="hours"
                    value={form.hours}
                    onChange={handleChange}
                    className="input-field"
                    step="0.01"
                    placeholder="0.00"
                    style={{ width: '263px' }}
                    />
                </div>
                <div className="form-group" style={{ flex: '1 1 250px', display: 'flex', flexDirection: 'column' }}>
                    <label>Dias</label>
                    <input
                    type="number"
                    name="days"
                    value={form.days}
                    onChange={handleChange}
                    className="input-field"
                    step="0.01"
                    placeholder="0.00"
                    style={{ width: '263px' }}
                    />
                </div>
                </div>

                {/* Tipo de banco y país */}
                <div className="form-row">
                    <div className="form-group">
                    <label>Estado</label>
                    <select
                    name="status"
                    value={form.status}
                    onChange={handleChange}
                    className="input-field"
                    >
                    <option value="">Seleccione</option>
                    <option value="true">Activo</option>
                    <option value="false">Inactivo</option>
                    </select>
                </div>
                <div className="form-group" style={{ flex: '1 1 250px', display: 'flex', flexDirection: 'column' }}>
                    <label><FaIdCard /> Descripción</label>
                    <input
                        name="description"
                        value={form.description}
                        onChange={handleChange}
                        className="input-field"
                    />
                    </div>
                </div>

                {/* Empleado */}
                <div className="form-row" style={{ display: 'flex', flexWrap: 'wrap', gap: '20px', marginBottom: '20px' }}>
                <div className="form-group" style={{ flex: '1 1 250px', display: 'flex', flexDirection: 'column' }}>
                    <label>Empleado</label>
                    <select
                    name="employeeId"
                    value={form.employeeId}
                    onChange={handleChange}
                    className="input-field"
                    >
                    <option value="">Seleccione</option>
                    {empleados.map(e => (
                        <option key={e.id} value={e.id}>
                        {e.nombre}
                        </option>
                    ))}
                    </select>
                </div>
                </div>

                {/* Botones */}
                <div className="form-row">
                <button className="primary-button" onClick={confirmarNovedad}><FaSave /> Confirmar Novedad</button>
                <button className="secondary-button" onClick={() => navigate('/')}><FaArrowLeft /> Menú</button>
                </div>
            </div>
            </div>
        </main>
        </div>
    );
    }

    export default NoveltyPage;
