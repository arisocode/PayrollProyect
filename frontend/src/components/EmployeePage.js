import { useCallback, useEffect, useState } from 'react';
import {
  FaArrowLeft, FaBirthdayCake, FaEnvelope, FaHome, FaIdCard,
  FaPhone, FaRegCalendarAlt, FaSave, FaSearch,
  FaTrash,
  FaUser
} from 'react-icons/fa';
import { useNavigate } from 'react-router-dom';
import '../App.css';

function EmployeePage() {

  const initialFormState = {
    id: '',
    primerNombre: '',
    segundoNombre: '',
    primerApellido: '',
    segundoApellido: '',
    nombre: '',
    estado: '',
    fechaNacimiento: '',
    nit: '',
    fechaInicio: '',
    tipoDocumento: '',
    telefono: '',
    email: '',
    direccion: '',
    bancoId: '',
    tipoCuenta: '',
    numeroCuenta: ''
  };

  const [form, setForm] = useState(initialFormState);

  const API_URL = process.env.REACT_APP_API_URL;
  const [empleadoEncontrado, setEmpleadoEncontrado] = useState(false);
  const navigate = useNavigate();

  const [bancos, setBancos] = useState([]);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const fetchBancos = useCallback(async () => {
    try {
      const response = await fetch(`${API_URL}/banks`);
      const data = await response.json();
      setBancos(data);
      console.log("📦 Bancos recibidos:", data);
    } catch (error) {
      console.error("Error al cargar bancos:", error);
    }
  }, [API_URL]);

  useEffect(() => {
    fetchBancos();
  }, [API_URL, fetchBancos]);

  const buscarEmpleado = async () => {
    try {
      const response = await fetch(`${API_URL}/employees/${form.nit}`);

      if (!response.ok) {
        const errorData = await response.json();
        setEmpleadoEncontrado(false);
        alert((errorData.message || "No se encontró el empleado") + " Cree uno nuevo.");
        return;
      }
  
      const data = await response.json();
      setForm(data);
      setEmpleadoEncontrado(true);
    } catch (error) {
      console.error("Error al buscar empleado:", error);
      alert("Error al buscar empleado.");
    }
  };

  const guardarEmpleado = async () => {

    const esCampoInvalido = (campo) => {
      if (typeof campo === 'string') return campo.trim() === '';
      if (typeof campo === 'number') return isNaN(campo);
      return campo === null || campo === undefined;
    };

    const camposObligatorios = [
      { campo: form.primerNombre, nombre: 'Primer Nombre' },
      { campo: form.primerApellido, nombre: 'Primer Apellido' },
      { campo: form.estado, nombre: 'Estado' },
      { campo: form.fechaNacimiento, nombre: 'Fecha de Nacimiento' },
      { campo: form.nit, nombre: 'NIT' },
      { campo: form.fechaInicio, nombre: 'Fecha de Inicio' },
      { campo: form.tipoDocumento, nombre: 'Tipo de Documento' },
      { campo: form.bancoId, nombre: 'Banco' },
      { campo: form.tipoCuenta, nombre: 'Tipo de Cuenta' },
      { campo: form.numeroCuenta, nombre: 'Número de Cuenta' }
    ];
  
    const camposFaltantes = camposObligatorios
      .filter(c => esCampoInvalido(c.campo))
      .map(c => c.nombre);

    if (camposFaltantes.length > 0) {
      alert(`Los siguientes campos son obligatorios:\n\n${camposFaltantes.join('\n')}`);
      return;
    }

    try {
      const response = await fetch(`${API_URL}/employees/${form.nit}`);
      if (response.ok) {
        const data = await response.json();
        if (data) {
          alert(`Ya existe un empleado con el NIT ${form.nit}. No se puede guardar.`);
          return;
        }
      }
    } catch (error) {
      console.error("Error al verificar NIT:", error);
      alert("Error al verificar el NIT.");
      return;
    }
  
    // Concateno nombre completo
    const nombreCompleto = [
      form.primerNombre,
      form.segundoNombre,
      form.primerApellido,
      form.segundoApellido
    ].filter(Boolean).join(' ');
  
    const payload = {
      ...form,
      nombre: nombreCompleto,
      id: undefined,
      estado: form.estado === 'true' || form.estado === true,
      tipoDocumento: Number(form.tipoDocumento),
      bancoId: Number(form.bancoId),
      tipoCuenta: form.tipoCuenta === 'Ahorros',
    };

    console.log("Payload enviado:", JSON.stringify(payload, null, 2));

    try {
      const response = await fetch(`${API_URL}/employees`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload),
      });

      if (!response.ok) {
        alert("Error al guardar el empleado.");
        return;
      }

      alert("Empleado guardado con éxito.");
      setForm(initialFormState);
      setEmpleadoEncontrado(true);
      fetchBancos()
    } catch (error) {
      console.error("Error al guardar empleado:", error);
      alert("Error al guardar empleado.");
    }
  };

  const eliminarEmpleado = async () => {
    if (!form.id) {
      alert("No hay empleado cargado para eliminar.");
      return;
    }

    const confirmacion = window.confirm("¿Estás seguro de que deseas eliminar este empleado?");
    if (!confirmacion) return;

    try {
      const response = await fetch(`${API_URL}/employees/${form.nit}`, {
        method: 'DELETE',
      });

      if (!response.ok) {
        alert("Error al eliminar empleado.");
        return;
      }

      alert("Empleado eliminado correctamente.");
      setForm(initialFormState);
      setEmpleadoEncontrado(false);
    } catch (error) {
      console.error("Error al eliminar empleado:", error);
      alert("Error al eliminar empleado.");
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
            <h2 className="form-title">Formulario de Empleado</h2>

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
                <button className="primary-button" onClick={buscarEmpleado}>
                  <FaSearch /> Buscar
                </button>
              </div>
            </div>

            {/* Primer y Segundo Nombre */}
            <div className="form-row">
              <div className="form-group">
                <label><FaUser /> Primer Nombre</label>
                <input
                  name="primerNombre"
                  value={form.primerNombre}
                  onChange={handleChange}
                  className="input-field"
                />
              </div>
              <div className="form-group">
                <label>Segundo Nombre</label>
                <input
                  name="segundoNombre"
                  value={form.segundoNombre}
                  onChange={handleChange}
                  className="input-field"
                />
              </div>
            </div>

            {/* Primer y Segundo Apellido */}
            <div className="form-row">
              <div className="form-group">
                <label>Primer Apellido</label>
                <input
                  name="primerApellido"
                  value={form.primerApellido}
                  onChange={handleChange}
                  className="input-field"
                />
              </div>
              <div className="form-group">
                <label>Segundo Apellido</label>
                <input
                  name="segundoApellido"
                  value={form.segundoApellido}
                  onChange={handleChange}
                  className="input-field"
                />
              </div>
            </div>

            {/* Estado y Tipo Documento */}
            <div className="form-row">
              <div className="form-group">
                <label>Estado</label>
                <select
                  name="estado"
                  value={form.estado}
                  onChange={handleChange}
                  className="input-field"
                >
                  <option value="">Seleccione</option>
                  <option value="true">Activo</option>
                  <option value="false">Inactivo</option>
                </select>
              </div>
              <div className="form-group">
                <label><FaIdCard /> Tipo de Documento</label>
                <select
                  name="tipoDocumento"
                  value={form.tipoDocumento}
                  onChange={handleChange}
                  className="input-field"
                >
                  <option value="">Seleccione</option>
                  <option value="1">Cédula de Ciudadanía</option>
                  <option value="2">Cédula de Extranjería</option>
                  <option value="3">Pasaporte</option>
                  <option value="4">NIT</option>
                </select>
              </div>
            </div>

            {/* Fecha Nacimiento y Fecha Inicio */}
            <div className="form-row">
              <div className="form-group">
                <label><FaBirthdayCake /> Fecha de Nacimiento</label>
                <input
                  type="date"
                  name="fechaNacimiento"
                  value={form.fechaNacimiento}
                  onChange={handleChange}
                  className="input-field"
                />
              </div>
              <div className="form-group">
                <label><FaRegCalendarAlt /> Fecha de Inicio</label>
                <input
                  type="date"
                  name="fechaInicio"
                  value={form.fechaInicio}
                  onChange={handleChange}
                  className="input-field"
                />
              </div>
            </div>

            {/* Teléfono y Email */}
            <div className="form-row">
              <div className="form-group">
                <label><FaPhone /> Teléfono</label>
                <input
                  name="telefono"
                  value={form.telefono}
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

            {/* Dirección y Banco */}
            <div className="form-row">
              <div className="form-group">
                <label><FaHome /> Dirección</label>
                <input
                  name="direccion"
                  value={form.direccion}
                  onChange={handleChange}
                  className="input-field"
                />
              </div>
              <div className="form-group">
              <label>Banco</label>
              <select
                name="bancoId"
                value={form.bancoId}
                onChange={handleChange}
              >
                <option value="">Seleccione</option>
                {bancos.map(banco => (
                  <option key={banco.id} value={banco.id}>
                    {banco.name}
                  </option>
                ))}
              </select>
              </div>
            </div>
            
            {/* Tipo de Cuenta y Número de Cuenta */}
            <div className="form-row">
              <div className="form-group">
                <label>Tipo de Cuenta</label>
                <select
                  name="tipoCuenta"
                  value={form.tipoCuenta}
                  onChange={handleChange}
                  className="input-field"
                >
                  <option value="">Seleccione</option>
                  <option value="Ahorros">Ahorros</option>
                  <option value="Corriente">Corriente</option>
                </select>
              </div>
              <div className="form-group">
                <label>Número de Cuenta</label>
                <input
                  name="numeroCuenta"
                  value={form.numeroCuenta}
                  onChange={handleChange}
                  className="input-field"
                />
              </div>
            </div>

            {/* Acciones */}
            <div className="form-actions">
              <button className="primary-button" onClick={guardarEmpleado}>
                <FaSave /> Guardar Empleado
              </button>

              {empleadoEncontrado && (
                <button className="danger-button" onClick={eliminarEmpleado}>
                  <FaTrash /> Eliminar Empleado
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

export default EmployeePage;
