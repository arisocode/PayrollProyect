import { useCallback, useEffect, useState } from 'react';
import {
  FaArrowLeft,
  FaEnvelope, FaHome, FaIdCard,
  FaPhone,
  FaSave, FaSearch,
  FaTrash,
  FaUser
} from 'react-icons/fa';
import { useNavigate } from 'react-router-dom';
import '../App.css';

function ThirdPartyPage() {

  const initialFormState = {
    id: '',
    nit: '',
    name: '',
    status: '',
    address: '',
    phone: '',
    email: '',
    typeId: '',
    accountNumber: '',
    accountType: '',
    bankId: ''
  };

  const [form, setForm] = useState(initialFormState);

  const API_URL = process.env.REACT_APP_API_URL;
  const [terceroEncontrado, setTerceroEncontrado] = useState(false);
  const navigate = useNavigate();

  const [bancos, setBancos] = useState([]);
  const [tipos, setTipos] = useState([]);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const fetchBancos = useCallback(async () => {
    try {
      const response = await fetch(`${API_URL}/banks`);
      const data = await response.json();
      setBancos(data);
    } catch (error) {
      console.error("Error al cargar bancos:", error);
    }
  }, [API_URL]);

  const fetchTipos = useCallback(async () => {
    try {
      const response = await fetch(`${API_URL}/types`);
      const data = await response.json();
      setTipos(data);
    } catch (error) {
      console.error("Error al cargar los tipos de tercero:", error);
    }
  }, [API_URL]);

  useEffect(() => {
    fetchBancos();
    fetchTipos();
  }, [fetchBancos, fetchTipos]);

  const buscarTercero = async () => {
    try {
      const response = await fetch(`${API_URL}/thirdparty/${form.nit}`);

      if (!response.ok) {
        const errorData = await response.json();
        setTerceroEncontrado(false);
        alert((errorData.message || "No se encontró el empleado") + " Cree uno nuevo.");
        return;
      }
  
      const data = await response.json();
      setForm(data);
      setTerceroEncontrado(true);
    } catch (error) {
      console.error("Error al buscar tercero:", error);
      alert("Error al buscar tercero.");
    }
  };

  const guardarTercero = async () => {

    const esCampoInvalido = (campo) => {
      if (typeof campo === 'string') return campo.trim() === '';
      if (typeof campo === 'number') return isNaN(campo);
      return campo === null || campo === undefined;
    };

    const camposObligatorios = [
      { campo: form.name, nombre: 'Nombre' },
      { campo: form.nit, nombre: 'NIT' },
      { campo: form.status, nombre: 'Estado' },
      { campo: form.typeId, nombre: 'Tipo de tercero' },
      { campo: form.bankId, nombre: 'Banco' },
      { campo: form.accountType, nombre: 'Tipo de Cuenta' },
      { campo: form.accountNumber, nombre: 'Número de Cuenta' }
    ];
  
    const camposFaltantes = camposObligatorios
      .filter(c => esCampoInvalido(c.campo))
      .map(c => c.nombre);

    if (camposFaltantes.length > 0) {
      alert(`Los siguientes campos son obligatorios:\n\n${camposFaltantes.join('\n')}`);
      return;
    }

    try {
      const response = await fetch(`${API_URL}/thirdparty/${form.nit}`);
      if (response.ok) {
        const data = await response.json();
        if (data) {
          alert(`Ya existe un tercero con el NIT ${form.nit}. No se puede guardar.`);
          return;
        }
      }
    } catch (error) {
      console.error("Error al verificar NIT:", error);
      alert("Error al verificar el NIT.");
      return;
    }

    const payload = {
      ...form,
      id: undefined,
      status: form.status === 'true' || form.status === true,
      typeId: Number(form.typeId),
      bankId: Number(form.bankId),
      accountType: form.accountType === 'Ahorros',
    };

    console.log("Payload enviado:", JSON.stringify(payload, null, 2));

    try {
      const response = await fetch(`${API_URL}/thirdparty`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload),
      });

      if (!response.ok) {
        alert("Error al guardar el tercero.");
        return;
      }

      alert("Tercero guardado con éxito.");
      setForm(initialFormState);
      setTerceroEncontrado(true);
      fetchBancos()
      fetchTipos()
    } catch (error) {
      console.error("Error al guardar tercero:", error);
      alert("Error al guardar tercero.");
    }
  };

  const eliminarTercero = async () => {
    if (!form.id) {
      alert("No hay tercero cargado para eliminar.");
      return;
    }

    const confirmacion = window.confirm("¿Estás seguro de que deseas eliminar este tercero?");
    if (!confirmacion) return;

    try {
      const response = await fetch(`${API_URL}/thirdparty/${form.nit}`, {
        method: 'DELETE',
      });

      if (!response.ok) {
        alert("Error al eliminar tercero.");
        return;
      }

      alert("Tercero eliminado correctamente.");
      setForm(initialFormState);
      setTerceroEncontrado(false);
    } catch (error) {
      console.error("Error al eliminar tercero:", error);
      alert("Error al eliminar tercero.");
    }
  };

  const volverAlMenu = () => {
    navigate('/');
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
            <h2 className="form-title">Formulario de Terceros</h2>

            {/* NIT y Buscar */}
            <div className="form-row">
              <div className="form-group">
                <label><FaIdCard /> NIT</label>
                <input
                  name="nit"
                  value={form.nit}
                  onChange={handleChange}
                  placeholder="Ingrese NIT"
                  className="input-field"
                />
              </div>
              <div className="form-group">
                <label>&nbsp;</label>
                <button className="primary-button" onClick={buscarTercero}>
                  <FaSearch /> Buscar
                </button>
              </div>
            </div>

            {/* Nombre y estado */}
            <div className="form-row">
              <div className="form-group">
                <label><FaUser /> Nombre del tercero </label>
                <input
                  name="name"
                  value={form.name}
                  onChange={handleChange}
                  className="input-field"
                />
              </div>
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
            </div>

            {/* Teléfono y Email */}
            <div className="form-row">
              <div className="form-group">
                <label><FaPhone /> Teléfono</label>
                <input
                  name="phone"
                  value={form.phone}
                  onChange={handleChange}
                  className="input-field"
                />
              </div>
              <div className="form-group">
                <label><FaEnvelope /> Email</label>
                <input
                  name="email"
                  value={form.email}
                  onChange={handleChange}
                  className="input-field"
                />
              </div>
            </div>

            {/* Dirección y Tipo de terceros */}
            <div className="form-row">
              <div className="form-group">
                <label><FaHome /> Dirección</label>
                <input
                  name="address"
                  value={form.address}
                  onChange={handleChange}
                  className="input-field"
                />
              </div>
              <div className="form-group">
                <label>Tipo de tercero</label>
                <div style={{ display: 'flex', alignItems: 'center' }}>
                <select
                    name="typeId"
                    value={form.typeId}
                    onChange={handleChange}
                    className="input-field"
                    style={{ width: '200px' }}
                >
                    <option value="">Seleccione</option>
                    {tipos.map(tt => (
                    <option key={tt.id} value={tt.id}>
                        {tt.name}
                    </option>
                    ))}
                </select>
                <button
                    type="button"
                    className="add-button"
                    onClick={() => navigate('/tiposTerceros')}
                    style={{ marginLeft: '10px', width: '90px' }}
                >
                    +
                </button>
                </div>
              </div>
            </div>
            
            {/* Banco y Tipo de cuenta */}
            <div className="form-row">
              <div className="form-group">
                <label>Banco</label>
                <select
                    name="bankId"
                    value={form.bankId}
                    onChange={handleChange}
                    className="input-field"
                >
                    <option value="">Seleccione</option>
                    {bancos.map(banco => (
                    <option key={banco.id} value={banco.id}>
                        {banco.name}
                    </option>
                    ))}
                </select>
              </div>
              <div className="form-group">
                <label>Tipo de Cuenta</label>
                <select
                  name="accountType"
                  value={form.accountType}
                  onChange={handleChange}
                  className="input-field"
                >
                  <option value="">Seleccione</option>
                  <option value="Ahorros">Ahorros</option>
                  <option value="Corriente">Corriente</option>
                </select>
              </div>
            </div>

            {/* Número de Cuenta */}
            <div className="form-row">
              <div className="form-group">
                <label>Número de Cuenta</label>
                <input
                  name="accountNumber"
                  value={form.accountNumber}
                  onChange={handleChange}
                  className="input-field"
                />
              </div>
            </div>

            {/* Acciones */}
            <div className="form-actions">
              <button className="primary-button" onClick={guardarTercero}>
                <FaSave /> Guardar Tercero
              </button>

              {terceroEncontrado && (
                <button className="danger-button" onClick={eliminarTercero}>
                  <FaTrash /> Eliminar Tercero
                </button>
              )}
            </div>

            <div className="form-actions" style={{ marginTop: '20px' }}>
              <button className="secondary-button" onClick={volverAlMenu}>
                <FaArrowLeft /> Volver al Menú
              </button>
            </div>
          </div>
        </div>
      </main>
    </div>
  );
}

export default ThirdPartyPage;
