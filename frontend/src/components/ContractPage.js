import { useCallback, useEffect, useState } from 'react';
import {
  FaArrowLeft,
  FaIdCard,
  FaSave, FaSearch,
  FaTrash
} from 'react-icons/fa';
import { useNavigate } from 'react-router-dom';
import '../App.css';

function ContractPage() {

  const initialFormState = {
    id: '',
    code: '',
    contractTypeId: '',
    startDate: '',
    endDate: '',
    salary: '',
    modificationDate: new Date().toISOString().slice(0, 16),
    status: '',
    paymentPeriod: '',
    paymentHour: '',
    employeeId: '',
    scheduleId: '',
    jobPositionId: '',
    contractId: ''
  };

  const [form, setForm] = useState(initialFormState);

  const API_URL = process.env.REACT_APP_API_URL;
  const [contratoEncontrado, setContratoEncontrado] = useState(false);
  const navigate = useNavigate();

  const [contractTypes, setContractTypes] = useState([]);
  const [schedules, setSchedules] = useState([]);
  const [jobPositions, setJobPositions] = useState([]);
  const [employees, setEmployees] = useState([]);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const fetchContractTypes = useCallback(async () => {
    try {
        const res = await fetch(`${API_URL}/contractTypes`);
        const data = await res.json();
        setContractTypes(data);
    } catch (err) {
        console.error('Error al cargar tipos de contrato:', err);
    }
  }, [API_URL]);

  const fetchSchedules = useCallback(async () => {
    try {
        const res = await fetch(`${API_URL}/schedules`);
        const data = await res.json();
        setSchedules(data);
    } catch (err) {
        console.error('Error al cargar horarios:', err);
    }
  }, [API_URL]);

  const fetchJobPositions = useCallback(async () => {
    try {
        const res = await fetch(`${API_URL}/positions`);
        const data = await res.json();
        setJobPositions(data);
    } catch (err) {
        console.error('Error al cargar cargos:', err);
    }
  }, [API_URL]);

  const fetchEmployees = useCallback(async () => {
    try {
        const res = await fetch(`${API_URL}/employees`);
        const data = await res.json();
        setEmployees(data);
    } catch (err) {
        console.error('Error al cargar empleados:', err);
    }
  }, [API_URL]);

  useEffect(() => {
    fetchContractTypes();
    fetchSchedules();
    fetchJobPositions();
    fetchEmployees();
  }, [fetchContractTypes, fetchSchedules, fetchJobPositions, fetchEmployees]);

   const buscarContrato = async () => {
   };
//     try {
//       const response = await fetch(`${API_URL}/employees/${form.nit}`);

//       if (!response.ok) {
//         const errorData = await response.json();
//         setEmpleadoEncontrado(false);
//         alert((errorData.message || "No se encontró el empleado") + " Cree uno nuevo.");
//         return;
//       }
  
//       const data = await response.json();
//       setForm(data);
//       setEmpleadoEncontrado(true);
//     } catch (error) {
//       console.error("Error al buscar empleado:", error);
//       alert("Error al buscar empleado.");
//     }
//   };

   const guardarContrato = async () => {
   };

//     const esCampoInvalido = (campo) => {
//       if (typeof campo === 'string') return campo.trim() === '';
//       if (typeof campo === 'number') return isNaN(campo);
//       return campo === null || campo === undefined;
//     };

//     const camposObligatorios = [
//       { campo: form.primerNombre, nombre: 'Primer Nombre' },
//       { campo: form.primerApellido, nombre: 'Primer Apellido' },
//       { campo: form.estado, nombre: 'Estado' },
//       { campo: form.fechaNacimiento, nombre: 'Fecha de Nacimiento' },
//       { campo: form.nit, nombre: 'NIT' },
//       { campo: form.fechaInicio, nombre: 'Fecha de Inicio' },
//       { campo: form.tipoDocumento, nombre: 'Tipo de Documento' },
//       { campo: form.bancoId, nombre: 'Banco' },
//       { campo: form.tipoCuenta, nombre: 'Tipo de Cuenta' },
//       { campo: form.numeroCuenta, nombre: 'Número de Cuenta' }
//     ];
  
//     const camposFaltantes = camposObligatorios
//       .filter(c => esCampoInvalido(c.campo))
//       .map(c => c.nombre);

//     if (camposFaltantes.length > 0) {
//       alert(`Los siguientes campos son obligatorios:\n\n${camposFaltantes.join('\n')}`);
//       return;
//     }

//     try {
//       const response = await fetch(`${API_URL}/employees/${form.nit}`);
//       if (response.ok) {
//         const data = await response.json();
//         if (data) {
//           alert(`Ya existe un empleado con el NIT ${form.nit}. No se puede guardar.`);
//           return;
//         }
//       }
//     } catch (error) {
//       console.error("Error al verificar NIT:", error);
//       alert("Error al verificar el NIT.");
//       return;
//     }
  
//     // Concateno nombre completo
//     const nombreCompleto = [
//       form.primerNombre,
//       form.segundoNombre,
//       form.primerApellido,
//       form.segundoApellido
//     ].filter(Boolean).join(' ');
  
//     const payload = {
//       ...form,
//       nombre: nombreCompleto,
//       id: undefined,
//       estado: form.estado === 'true' || form.estado === true,
//       tipoDocumento: Number(form.tipoDocumento),
//       bancoId: Number(form.bancoId),
//       tipoCuenta: form.tipoCuenta === 'Ahorros',
//     };

//     console.log("Payload enviado:", JSON.stringify(payload, null, 2));

//     try {
//       const response = await fetch(`${API_URL}/employees`, {
//         method: 'POST',
//         headers: { 'Content-Type': 'application/json' },
//         body: JSON.stringify(payload),
//       });

//       if (!response.ok) {
//         alert("Error al guardar el empleado.");
//         return;
//       }

//       alert("Empleado guardado con éxito.");
//       setForm(initialFormState);
//       setEmpleadoEncontrado(true);
//       fetchBancos()
//     } catch (error) {
//       console.error("Error al guardar empleado:", error);
//       alert("Error al guardar empleado.");
//     }
//   };

   const otroSiContrato = async () => {
   };
//     if (!form.id) {
//       alert("No hay empleado cargado para eliminar.");
//       return;
//     }

//     const confirmacion = window.confirm("¿Estás seguro de que deseas eliminar este empleado?");
//     if (!confirmacion) return;

//     try {
//       const response = await fetch(`${API_URL}/employees/${form.nit}`, {
//         method: 'DELETE',
//       });

//       if (!response.ok) {
//         alert("Error al eliminar empleado.");
//         return;
//       }

//       alert("Empleado eliminado correctamente.");
//       setForm(initialFormState);
//       setEmpleadoEncontrado(false);
//     } catch (error) {
//       console.error("Error al eliminar empleado:", error);
//       alert("Error al eliminar empleado.");
//     }
//   };

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
            <h2 className="form-title">Formulario de Contratos</h2>

            {/* Codigo y Buscar */}
            <div className="form-row">
              <div className="form-group">
                <label><FaIdCard /> Codigo</label>
                <input
                  name="code"
                  value={form.code}
                  onChange={handleChange}
                  placeholder="Ingrese codigo"
                  className="input-field"
                />
              </div>
              <div className="form-group">
                <label>&nbsp;</label>
                <button className="primary-button" onClick={buscarContrato}>
                  <FaSearch /> Buscar
                </button>
              </div>
            </div>

            {/* Tipo de Contrato, Horario y Cargo */}
            <div className="form-row">
            {/* Tipo de Contrato */}
            <div className="form-group">
                <label>Tipo de Contrato</label>
                <div style={{ display: 'flex', alignItems: 'center' }}>
                <select
                    name="contractTypeId"
                    value={form.contractTypeId}
                    onChange={handleChange}
                    className="input-field"
                    style={{ width: '200px' }}
                >
                    <option value="">Seleccione</option>
                    {contractTypes.map(ct => (
                    <option key={ct.id} value={ct.id}>
                        {ct.description}
                    </option>
                    ))}
                </select>
                <button
                    type="button"
                    className="add-button"
                    onClick={() => navigate('/tipos')}
                    style={{ marginLeft: '10px', width: '90px' }}
                >
                    +
                </button>
                </div>
            </div>

            {/* Horario */}
            <div className="form-group">
                <label>Horario</label>
                <div style={{ display: 'flex', alignItems: 'center' }}>
                <select
                    name="scheduleId"
                    value={form.scheduleId}
                    onChange={handleChange}
                    className="input-field"
                    style={{ width: '200px' }}
                >
                    <option value="">Seleccione</option>
                    {schedules.map(s => (
                    <option key={s.id} value={s.id}>
                        {s.name}
                    </option>
                    ))}
                </select>
                <button
                    type="button"
                    className="add-button"
                    onClick={() => navigate('/Horarios')}
                    style={{ marginLeft: '10px', width: '90px' }}
                >
                    +
                </button>
                </div>
            </div>

            {/* Cargo */}
            <div className="form-group">
                <label>Cargo</label>
                <div style={{ display: 'flex', alignItems: 'center' }}>
                <select
                    name="jobPositionId"
                    value={form.jobPositionId}
                    onChange={handleChange}
                    className="input-field"
                    style={{ width: '200px' }}
                >
                    <option value="">Seleccione</option>
                    {jobPositions.map(j => (
                    <option key={j.id} value={j.id}>
                        {j.name}
                    </option>
                    ))}
                </select>
                <button
                    type="button"
                    className="add-button"
                    onClick={() => navigate('/cargoss')}
                    style={{ marginLeft: '10px', width: '90px' }}
                >
                    +
                </button>
                </div>
            </div>
            </div>

            {/* Empleado */}
            <div className="form-row">
            <div className="form-group">
                <label>Empleado</label>
                <select
                name="employeeId"
                value={form.employeeId}
                onChange={handleChange}
                className="input-field"
                >
                <option value="">Seleccione</option>
                {employees.map(e => (
                    <option key={e.id} value={e.id}>
                    {e.nombre}
                    </option>
                ))}
                </select>
            </div>
            </div>

            {/* Fechas y Salario */}
            <div className="form-row">
            <div className="form-group">
                <label>Fecha de Inicio</label>
                <input
                type="date"
                name="startDate"
                value={form.startDate}
                onChange={handleChange}
                className="input-field"
                />
            </div>
            <div className="form-group">
                <label>Fecha de Fin</label>
                <input
                type="date"
                name="endDate"
                value={form.endDate}
                onChange={handleChange}
                className="input-field"
                />
            </div>
            <div className="form-group">
                <label>Salario</label>
                <input
                type="number"
                name="salary"
                value={form.salary}
                onChange={handleChange}
                className="input-field"
                step="0.01"
                placeholder="0.00"
                />
            </div>
            </div>

            {/*estado, periodo de pago y pago por hora */}
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
                <option value="1">Activo</option>
                <option value="2">Inactivo</option>
                <option value="3">Liquidado</option>
                </select>
            </div>
            <div className="form-group">
                <label>Periodo de Pago</label>
                <select
                name="paymentPeriod"
                value={form.paymentPeriod}
                onChange={handleChange}
                className="input-field"
                >
                <option value="">Seleccione</option>
                <option value="1">Mensual</option>
                <option value="2">Quincenal</option>
                </select>
            </div>
            <div className="form-group">
                <label>Pago por Hora</label>
                <input
                type="number"
                name="paymentHour"
                value={form.paymentHour}
                onChange={handleChange}
                className="input-field"
                step="0.01"
                placeholder="0.00"
                />
            </div>
            </div>

            {/* Acciones */}
            <div className="form-actions">
              <button className="primary-button" onClick={guardarContrato}>
                <FaSave /> Guardar Contrato
              </button>

              {contratoEncontrado && (
                <button className="danger-button" onClick={otroSiContrato}>
                  <FaTrash /> OtroSi
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

export default ContractPage;
