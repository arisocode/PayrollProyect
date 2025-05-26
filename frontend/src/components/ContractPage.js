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
    thirdParties: []
  };

  const [form, setForm] = useState(initialFormState);

  const API_URL = process.env.REACT_APP_API_URL;
  const [contratoEncontrado, setContratoEncontrado] = useState(false);
  const navigate = useNavigate();

  const [contractTypes, setContractTypes] = useState([]);
  const [schedules, setSchedules] = useState([]);
  const [jobPositions, setJobPositions] = useState([]);
  const [employees, setEmployees] = useState([]);
  const [terceros, setTerceros] = useState([]);
  const [tercerosAsociados, setTercerosAsociados] = useState([]);
  const [selectedThirdPartyId, setSelectedThirdPartyId] = useState('');
  const [thirdPartyPercentage, setThirdPartyPercentage] = useState('');
  const [contratoOriginal, setContratoOriginal] = useState(null);

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

  const fetchTerceros = useCallback(async () => {
    try {
      const res = await fetch(`${API_URL}/thirdparty`);
      const data = await res.json();
      setTerceros(data);
    } catch (err) {
      console.error('Error al cargar terceros:', err);
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
    fetchTerceros();
  }, [fetchContractTypes, fetchSchedules, fetchJobPositions, fetchEmployees, fetchTerceros]);

  const addThirdParty = () => {
    if (!selectedThirdPartyId || !thirdPartyPercentage) return;

    const exists = form.thirdParties.find(tp => tp.nit === selectedThirdPartyId);
    if (exists) {
      alert('Este tercero ya fue agregado');
      return;
    }

    const nuevoTercero = {
      nit: selectedThirdPartyId,
      percentage: parseFloat(thirdPartyPercentage)
    };

    setForm(prev => ({
      ...prev,
      thirdParties: [...prev.thirdParties, nuevoTercero]
    }));

    setSelectedThirdPartyId('');
    setThirdPartyPercentage('');
  };

  const deleteThirdParty = (nit) => {
    setForm(prev => ({
      ...prev,
      thirdParties: prev.thirdParties.filter(tp => tp.nit !== nit)
    }));
  };

  const buscarContrato = async () => {
    if (!form.code || form.code.trim() === '') {
      alert("Ingrese el código del contrato para buscar.");
      return;
    }

    try {
      const response = await fetch(`${API_URL}/contracts/${form.code}`);

      if (!response.ok) {
        const errorData = await response.json();
        alert(errorData.message || "Contrato no encontrado");
        setContratoEncontrado(false);
        return;
      }

      const data = await response.json();

      setForm({
        ...initialFormState,
        ...data,
        startDate: data.startDate ? data.startDate.split("T")[0] : '',
        endDate: data.endDate ? data.endDate.split("T")[0] : '',
        modificationDate: data.modificationDate ? data.modificationDate.split("T")[0] : '',
        thirdParties: data.thirdParties || [],
        contractTypeId: String(data.contractTypeId),
        scheduleId: String(data.scheduleId),
        jobPositionId: String(data.jobPositionId),
        employeeId: String(data.employeeId),
        status: String(data.status),
        paymentPeriod: String(data.paymentPeriod),
        paymentHour: String(data.paymentHour),
        salary: String(data.salary),
      });

      setContratoOriginal({
        ...data,
        startDate: data.startDate ? data.startDate.split("T")[0] : '',
        endDate: data.endDate ? data.endDate.split("T")[0] : '',
        modificationDate: data.modificationDate ? data.modificationDate.split("T")[0] : '',
        thirdParties: data.thirdParties || [],
        contractTypeId: String(data.contractTypeId),
        scheduleId: String(data.scheduleId),
        jobPositionId: String(data.jobPositionId),
        employeeId: String(data.employeeId),
        status: String(data.status),
        paymentPeriod: String(data.paymentPeriod),
        paymentHour: String(data.paymentHour),
        salary: String(data.salary),
      });

      setContratoEncontrado(true);
    } catch (error) {
      console.error("Error al buscar contrato:", error);
      alert("Error al buscar contrato.");
      setContratoEncontrado(false);
    }
  };

  const guardarContrato = async () => {
    const camposObligatorios = [
      { campo: form.code, nombre: 'Código del contrato' },
      { campo: form.contractTypeId, nombre: 'Tipo de contrato' },
      { campo: form.startDate, nombre: 'Fecha de inicio' },
      { campo: form.status, nombre: 'Estado' },
      { campo: form.paymentPeriod, nombre: 'Periodo de pago' },
      { campo: form.paymentHour, nombre: 'Pago por hora' },
      { campo: form.employeeId, nombre: 'Empleado' },
      { campo: form.scheduleId, nombre: 'Horario' },
      { campo: form.jobPositionId, nombre: 'Cargo' },
    ];

    const esCampoInvalido = (campo) => {
      if (typeof campo === 'string') return campo.trim() === '';
      if (typeof campo === 'number') return isNaN(campo);
      return campo === null || campo === undefined;
    };

    let camposFaltantes = camposObligatorios
      .filter(c => esCampoInvalido(c.campo))
      .map(c => c.nombre);

    if (form.thirdParties.length === 0) {
      camposFaltantes.push('Terceros asociados');
    }

    if (camposFaltantes.length > 0) {
      alert(`Los siguientes campos son obligatorios:\n\n${camposFaltantes.join('\n')}`);
      return;
    }

    const cleanDate = (dateStr) => {
      return dateStr ? dateStr.split("T")[0] : null;
    };

    const payload = {
      ...form,
      startDate: cleanDate(form.startDate),
      endDate: cleanDate(form.endDate),
      modificationDate: cleanDate(form.modificationDate),
      salary: form.salary?.trim() === "" ? 0.0 : parseFloat(form.salary || 0.0),
      contractTypeId: Number(form.contractTypeId),
      scheduleId: Number(form.scheduleId),
      jobPositionId: Number(form.jobPositionId),
      employeeId: Number(form.employeeId),
      status: Number(form.status),
      paymentPeriod: Number(form.paymentPeriod),
      paymentHour: parseFloat(form.paymentHour),
      thirdParties: form.thirdParties.map(tp => ({
        nit: tp.nit,
        percentage: parseFloat(tp.percentage)
      }))
    };

    console.log('Contrato a enviar:', JSON.stringify(payload, null, 2));

    try {
      const response = await fetch(`${API_URL}/contracts`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });

      if (!response.ok) {
        const errorData = await response.json();
        console.error("Error al guardar contrato:", errorData);
        alert(`Error al guardar contrato: ${errorData.message || 'Error desconocido'}`);
        return;
      }

      alert('Contrato guardado con éxito.');
      setForm(initialFormState);
      setContratoEncontrado(true);
    } catch (error) {
      console.error("Error al guardar contrato:", error);
      alert("Error al guardar contrato.");
    }
  };

  const otroSiContrato = async () => {
    if (!contratoOriginal) {
    alert("No hay contrato original para comparar.");
    return;
    }

    const haCambiado = (
      contratoOriginal.salary !== form.salary ||
      contratoOriginal.startDate !== form.startDate ||
      contratoOriginal.endDate !== form.endDate ||
      contratoOriginal.contractTypeId !== form.contractTypeId ||
      contratoOriginal.scheduleId !== form.scheduleId ||
      contratoOriginal.jobPositionId !== form.jobPositionId ||
      contratoOriginal.employeeId !== form.employeeId ||
      contratoOriginal.status !== form.status ||
      contratoOriginal.paymentPeriod !== form.paymentPeriod ||
      contratoOriginal.paymentHour !== form.paymentHour ||
      JSON.stringify(contratoOriginal.thirdParties) !== JSON.stringify(form.thirdParties)
    );

    if (!haCambiado) {
      alert("No se han realizado modificaciones al contrato.");
      return;
    }

    const cleanDate = (dateStr) => {
      return dateStr ? dateStr.split("T")[0] : null;
    };

    const payload = {
      ...form,
      startDate: cleanDate(form.startDate),
      endDate: cleanDate(form.endDate),
      modificationDate: new Date().toISOString().split('T')[0],
      salary: form.salary?.trim() === "" ? 0.0 : parseFloat(form.salary || 0.0),
      contractTypeId: Number(form.contractTypeId),
      scheduleId: Number(form.scheduleId),
      jobPositionId: Number(form.jobPositionId),
      employeeId: Number(form.employeeId),
      status: Number(form.status),
      paymentPeriod: Number(form.paymentPeriod),
      paymentHour: parseFloat(form.paymentHour),
      thirdParties: form.thirdParties.map(tp => ({
        nit: tp.nit,
        percentage: parseFloat(tp.percentage)
      }))
    };

    console.log('Contrato modificado (OtroSí):', JSON.stringify(payload, null, 2));

    try {
      const response = await fetch(`${API_URL}/contracts/otrosi`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });

      if (!response.ok) {
        const errorData = await response.json();
        alert(`Error al aplicar OtroSí: ${errorData.message || 'Error desconocido'}`);
        return;
      }

      alert("Modificación del contrato registrada con éxito (OtroSí).");
      setContratoOriginal(payload);
      setContratoEncontrado(true);
    } catch (error) {
      console.error("Error en OtroSí:", error);
      alert("Error al aplicar OtroSí.");
    }
  };

  const volverAlMenu = () => {
    navigate('/');
  };

  return (
    <div className="App">
      <a href="/" className="header" style={{ display: 'flex', alignItems: 'center', marginBottom: '20px', textDecoration: 'none', color: 'inherit' }}>
        <img src="payroll.png" alt="Logo" className="logo" style={{ height: '50px', marginRight: '15px' }} />
        <h1 className="title" style={{ fontSize: '2rem', fontWeight: 'bold' }}>Poblado Nómina SAS</h1>
      </a>

      <main className="main-content" style={{ display: 'flex', gap: '30px', flexWrap: 'wrap' }}>
        
        {/* Contenedor Formulario */}
        <div className="form-container" style={{
          borderRadius: '8px',
          boxShadow: '0 4px 10px rgba(0,0,0,0.1)',
          padding: '25px',
          flex: '1 1 600px',
          minWidth: '320px'
        }}>
          
          <h2 style={{ marginBottom: '20px', fontWeight: '600', fontSize: '1.5rem' }}>Formulario de Contratos</h2>
          
          {/* Codigo y Buscar */}
            <div className="form-row" style={{ display: 'flex', flexWrap: 'wrap', gap: '20px', marginBottom: '20px' }}>
              <div className="form-group" style={{ flex: '1 1 250px', display: 'flex', flexDirection: 'column' }}>
                <label><FaIdCard /> Codigo</label>
                <input
                  name="code"
                  value={form.code}
                  onChange={handleChange}
                  placeholder="Ingrese codigo"
                  className="input-field"
                />
              </div>
              <div className="form-group" style={{ flex: '1 1 250px', display: 'flex', flexDirection: 'column' }}>
                <label>&nbsp;</label>
                <button className="primary-button" onClick={buscarContrato}
                  style={{ marginLeft: '200px', width: '250px'}}
                >
                  <FaSearch /> Buscar
                </button>
              </div>
            </div>

          {/* Tipo de Contrato, Horario y Cargo */}
            <div className="form-row" style={{ display: 'flex', flexWrap: 'wrap', gap: '20px', marginBottom: '20px' }}>
            {/* Tipo de Contrato */}
            <div className="form-group" style={{ flex: '1 1 250px', display: 'flex', flexDirection: 'column' }}>
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
                    style={{ marginLeft: '10px', width: '80px' }}
                >
                    +
                </button>
                </div>
            </div>

          {/* Horario */}
            <div className="form-group" style={{ flex: '1 1 250px', display: 'flex', flexDirection: 'column' }}>
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
                    style={{ marginLeft: '10px', width: '80px' }}
                >
                    +
                </button>
                </div>
            </div>

          {/* Cargo */}
            <div className="form-group" style={{ flex: '1 1 250px', display: 'flex', flexDirection: 'column' }}>
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
                    style={{ marginLeft: '10px', width: '80px' }}
                >
                    +
                </button>
                </div>
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
                {employees.map(e => (
                    <option key={e.id} value={e.id}>
                    {e.nombre}
                    </option>
                ))}
                </select>
            </div>
            </div>

          {/* Fechas y Salario */}
            <div className="form-row" style={{ display: 'flex', flexWrap: 'wrap', gap: '20px', marginBottom: '20px' }}>
            <div className="form-group" style={{ flex: '1 1 250px', display: 'flex', flexDirection: 'column' }}>
                <label>Fecha de Inicio</label>
                <input
                type="date"
                name="startDate"
                value={form.startDate}
                onChange={handleChange}
                className="input-field"
                style={{ width: '265px' }}
                />
            </div>
            <div className="form-group" style={{ flex: '1 1 250px', display: 'flex', flexDirection: 'column' }}>
                <label>Fecha de Fin</label>
                <input
                type="date"
                name="endDate"
                value={form.endDate}
                onChange={handleChange}
                className="input-field"
                style={{ width: '265px' }}
                />
            </div>
            <div className="form-group" style={{ flex: '1 1 250px', display: 'flex', flexDirection: 'column' }}>
                <label>Salario</label>
                <input
                type="number"
                name="salary"
                value={form.salary}
                onChange={handleChange}
                className="input-field"
                step="0.01"
                placeholder="0.00"
                style={{ width: '263px' }}
                />
            </div>
            </div>

          {/*estado, periodo de pago y pago por hora */}
            <div className="form-row" style={{ display: 'flex', flexWrap: 'wrap', gap: '20px', marginBottom: '20px' }}>
            <div className="form-group" style={{ flex: '1 1 250px', display: 'flex', flexDirection: 'column' }}>
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
            <div className="form-group" style={{ flex: '1 1 250px', display: 'flex', flexDirection: 'column' }}>
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
            <div className="form-group" style={{ flex: '1 1 250px', display: 'flex', flexDirection: 'column' }}>
                <label>Pago por Hora</label>
                <input
                type="number"
                name="paymentHour"
                value={form.paymentHour}
                onChange={handleChange}
                className="input-field"
                step="0.01"
                placeholder="0.00"
                style={{ width: '263px' }}
                />
            </div>
            </div>

          {/* Acciones */}
            <div className="form-actions" style={{ marginTop: '25px', display: 'flex', gap: '15px' }}>
              {!contratoEncontrado && (
                <button
                  className="primary-button"
                  onClick={guardarContrato}
                  style={{
                    padding: '10px 20px',
                    borderRadius: '5px',
                    border: 'none',
                    cursor: 'pointer',
                    display: 'flex',
                    alignItems: 'center',
                    gap: '8px',
                  }}
                >
                  <FaSave /> Guardar Contrato
                </button>
              )}

              {contratoEncontrado && (
                <button
                  className="danger-button"
                  onClick={otroSiContrato}
                  style={{
                    padding: '10px 20px',
                    borderRadius: '5px',
                    border: 'none',
                    cursor: 'pointer',
                    display: 'flex',
                    alignItems: 'center',
                    gap: '8px',
                  }}
                >
                  <FaTrash /> OtroSí
                </button>
              )}
            </div>

            <div className="form-actions" style={{ marginTop: '20px' }}>
              <button
                className="secondary-button"
                onClick={volverAlMenu}
                style={{
                  padding: '10px 20px',
                  borderRadius: '5px',
                  border: 'none',
                  cursor: 'pointer',
                  display: 'flex',
                  alignItems: 'center',
                  gap: '8px',
                }}
              >
                <FaArrowLeft /> Volver al Menú
              </button>
            </div>
          </div>

        {/* Contenedor Tabla Terceros */}
        <aside className="terceros-container" style={{
          background: 'rgba(30, 30, 45, 0.6)',
          borderRadius: '8px',
          boxShadow: '0 4px 10px rgba(0,0,0,0.1)',
          padding: '20px',
          width: '350px',
          maxHeight: '600px',
          overflowY: 'auto'
        }}>
          <h3 style={{ marginBottom: '15px', fontWeight: '600', fontSize: '1.25rem' }}>Terceros Asociados</h3>

          <div style={{ display: 'flex', flexDirection: 'column', gap: '10px' }}>
              <label style={{ fontWeight: '500' }}>Tercero</label>
              <select
                value={selectedThirdPartyId}
                onChange={e => setSelectedThirdPartyId(e.target.value)}
                className="input-field"
                style={{
                  padding: '8px',
                  borderRadius: '4px',
                  border: '1px solid #ccc',
                  width: '100%'
                }}
              >
                <option value="">Seleccione</option>
                {terceros.map(tp => (
                  <option key={tp.nit} value={tp.nit}>
                    {tp.name}
                  </option>
                ))}
              </select>

              {/* Porcentaje + Botón en una línea */}
              <div style={{ display: 'flex', gap: '10px', alignItems: 'center' }}>
                <input
                  type="number"
                  placeholder="%"
                  value={thirdPartyPercentage}
                  onChange={e => setThirdPartyPercentage(e.target.value)}
                  className="input-field"
                  style={{
                    flex: 1,
                    padding: '8px',
                    borderRadius: '4px',
                    border: '1px solid #ccc'
                  }}
                />
                <button
                  type="button"
                  onClick={addThirdParty}
                  style={{
                    padding: '8px 12px',
                    border: 'none',
                    borderRadius: '4px',
                    backgroundColor: '#4CAF50',
                    color: 'white',
                    cursor: 'pointer',
                    width: '100px'
                  }}
                >
                  Agregar
                </button>
              </div>
            </div>

          <table style={{ marginTop: '12px', width: '100%', borderCollapse: 'collapse' }}>
            <thead>
              <tr style={{ backgroundColor: 'Black' }}>
                <th style={{ padding: '8px', borderBottom: '1px solid #ccc', textAlign: 'left' }}>Tercero</th>
                <th style={{ padding: '8px', borderBottom: '1px solid #ccc', textAlign: 'left' }}>Porcentaje</th>
              </tr>
            </thead>
            <tbody>
              {form.thirdParties.length === 0 ? (
                <tr>
                  <td colSpan="3" style={{ textAlign: 'center', padding: '20px', color: '#6b7280' }}>
                    Sin terceros asociados
                  </td>
                </tr>
              ) : (
                form.thirdParties.map((tp) => {
                  const terceroInfo = terceros.find(t => t.nit === tp.nit);
                  return (
                    <tr key={tp.nit} style={{ borderBottom: '1px solid #eee' }}>
                      <td style={{ padding: '8px' }}>{terceroInfo ? terceroInfo.nit : 'Tercero desconocido'}</td>
                      <td style={{ padding: '8px' }}>{tp.percentage}%</td>
                      <td style={{ padding: '8px', textAlign: 'center' }}>
                        <button
                          onClick={() => deleteThirdParty(tp.nit)}
                          style={{
                            backgroundColor: '#ef4444',
                            color: 'white',
                            border: 'none',
                            padding: '5px 10px',
                            borderRadius: '4px',
                            cursor: 'pointer',
                            display: 'flex',
                            alignItems: 'center',
                            gap: '5px',
                            justifyContent: 'center'
                          }}
                          title="Quitar tercero"
                        >
                          <FaTrash /> Quitar
                        </button>
                      </td>
                    </tr>
                  );
                })
              )}
            </tbody>
          </table>
        </aside>
      </main>
    </div>
  );
}

export default ContractPage;
