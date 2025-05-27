import { useCallback, useEffect, useState } from 'react';
import { FaArrowLeft, FaDollarSign } from 'react-icons/fa';
import { useNavigate } from 'react-router-dom';
import '../App.css';
import '../PayrollLiquidationPage.css'; // te explico más abajo qué agregar aquí

function PayrollLiquidationPage() {
    const initialFormState = { id: '', employeeId: '' };
    const [form, setForm] = useState(initialFormState);

    const API_URL = process.env.REACT_APP_API_URL;
    const navigate = useNavigate();
    const [empleados, setEmpleados] = useState([]);
    const [cargando, setCargando] = useState(false);

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const fetchEmpleados = useCallback(async () => {
        setCargando(true);
        try {
        const response = await fetch(`${API_URL}/employees/to-liquidate`);
        const data = await response.json();
        setEmpleados(data);
        } catch (error) {
        console.error("Error al cargar empleados:", error);
        alert("No se pudieron cargar los empleados.");
        } finally {
        setCargando(false);
        }
    }, [API_URL]);

    useEffect(() => {
        fetchEmpleados();
    }, [API_URL, fetchEmpleados]);

    const generarLiquidacion = async (empleadoId) => {
        try {
        const response = await fetch(`${API_URL}/payroll/${empleadoId}`, {
            method: 'POST'
        });
        if (!response.ok) throw new Error("Error en la liquidación");
        const data = await response.json();
        alert("Liquidación generada con éxito");
        
        navigate(`/payroll/details/${empleadoId}`, { state: { liquidacion: data } });

        } catch (error) {
        console.error("Error al generar liquidación:", error);
        alert("No se pudo generar la liquidación.");
        }
    };

    const liquidarTodos = async () => {
        try {
        const ids = empleados.map(emp => emp.employeeId);
        const response = await fetch(`${API_URL}/payroll/liquidate-multiple`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(ids)
        });

        if (!response.ok) throw new Error("Error al liquidar todos los empleados");

        alert("Todos los empleados fueron liquidados correctamente.");
        fetchEmpleados();
        } catch (error) {
        console.error("Error al liquidar todos:", error);
        alert("No se pudo realizar la liquidación masiva.");
        }
    };

    const volverAlMenu = () => {
        navigate('/');
    };

    return (
        <div className="payroll">
            <div className="App">
            <a href="/" className="header">
                <img src="payroll.png" alt="Logo" className="logo" />
                <h1 className="title">Liquidación de Nómina</h1>
            </a>

            <main className="main-content centered-content">
                <h2>Empleados para Liquidar</h2>

                {cargando ? (
                <p>Cargando empleados...</p>
                ) : (
                <div className="table-container">
                    <table className="table">
                    <thead>
                        <tr>
                        <th>Nombre</th>
                        <th>Código Contrato</th>
                        <th>Última Liquidación</th>
                        <th>Salario</th>
                        <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        {empleados.length === 0 ? (
                        <tr><td colSpan="5">No hay empleados para liquidar.</td></tr>
                        ) : (
                        empleados.map(emp => (
                            <tr key={emp.employeeId}>
                            <td>{emp.name}</td>
                            <td>{emp.contractCode}</td>
                            <td>{emp.lastLiquidationDate ?? 'Sin registro'}</td>
                            <td>${emp.salary?.toLocaleString('es-CO')}</td>
                            <td>
                                <div className="action-buttons">
                                <button className="small-button" onClick={() => generarLiquidacion(emp.employeeId)}>
                                    <FaDollarSign /> Liquidar
                                </button>
                                </div>
                            </td>
                            </tr>
                        ))
                        )}
                    </tbody>
                    </table>

                    <div className="button-group">
                    <button className="small-button" onClick={volverAlMenu}>
                        <FaArrowLeft /> Volver al menú
                    </button>
                    <button className="small-button" onClick={liquidarTodos}>
                        <FaDollarSign /> Liquidar Todos
                    </button>
                    </div>
                </div>
                )}
            </main>
            </div>
        </div>
        );
}

export default PayrollLiquidationPage;
