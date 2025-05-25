import React, { useState, useEffect } from "react";
import {
    FaCalendarAlt,
    FaClock,
    FaUser,
    FaFileContract,
    FaFlag,
    FaSave,
    FaArrowLeft,
} from "react-icons/fa";

const LiquidacionFinalForm = () => {
    const [fechaLiquidacion, setFechaLiquidacion] = useState("");
    const [estado, setEstado] = useState(0);
    const [diasTrabajados, setDiasTrabajados] = useState(0);
    const [salario, setSalario] = useState(0); // Este puede venir de base de datos si lo deseas
    const [horasTrabajadas, setHorasTrabajadas] = useState(0);
    const [vacacionesPendientes, setVacacionesPendientes] = useState(0);
    const [idUsuario, setIdUsuario] = useState("");
    const [usuarios, setUsuarios] = useState([]);
    const [diasFaltantesSueldo,setDiasFaltantes]= useState(0);

    // Opcionales: Contrato y motivo de terminación
    const [contrato, setContrato] = useState("");
    const [motivoTerminacion, setMotivoTerminacion] = useState("");

    const [resultado, setResultado] = useState(null);

    useEffect(() => {
        fetch("http://localhost:8080/api/employees")
            .then((res) => res.json())
            .then((data) => setUsuarios(data))
            .catch((err) => console.error("Error cargando empleados:", err));
    }, []);

    const calcularLiquidacion = async () => {
        if (!idUsuario) {
            alert("Debe seleccionar un empleado.");
            return;
        }

        const queryParams = new URLSearchParams({
            idUsuario,
            diasTrabajados,
            salario,
            diasFaltantesSueldo,
            diasVacacionesPendientes: vacacionesPendientes
        }).toString();

        try {
            const response = await fetch(`http://localhost:8080/api/liquidation/calcular?${queryParams}`);
            const data = await response.json();
            setResultado(data);
            console.log("Resultado de la liquidación:", data);
        } catch (error) {
            console.error("Error al calcular liquidación:", error);
        }
    };

    return (
        <main className="main-content">
            <div className="form-container">
                <div className="card">
                    <h2 className="form-title">Formulario de Liquidación Final</h2>

                    <div className="form-row">
                        <div className="form-group">
                            <label><FaCalendarAlt/> Fecha de Liquidación</label>
                            <input
                                type="date"
                                className="input-field"
                                value={fechaLiquidacion}
                                onChange={(e) => setFechaLiquidacion(e.target.value)}
                            />
                        </div>
                        <div className="form-group">
                            <label><FaClock/> Días Faltantes Sueldo</label>
                            <input
                                type="number"
                                className="input-field"
                                value={diasFaltantesSueldo}
                                onChange={(e) => setDiasFaltantes(parseInt(e.target.value))}
                            />
                        </div>
                    </div>

                    <div className="form-row">
                        <div className="form-group">
                            <label><FaClock /> Días Trabajados</label>
                            <input
                                type="number"
                                className="input-field"
                                value={diasTrabajados}
                                onChange={(e) => setDiasTrabajados(parseInt(e.target.value))}
                            />
                        </div>
                    </div>

                    <div className="form-row">
                        <div className="form-group">
                            <label>Salario</label>
                            <input
                                type="number"
                                className="input-field"
                                value={salario}
                                onChange={(e) => setSalario(parseFloat(e.target.value))}
                            />
                        </div>
                        <div className="form-group">
                            <label>Vacaciones Pendientes</label>
                            <input
                                type="number"
                                className="input-field"
                                value={vacacionesPendientes}
                                onChange={(e) => setVacacionesPendientes(parseInt(e.target.value))}
                            />
                        </div>
                    </div>

                    <div className="form-row">
                        <div className="form-group">
                            <label><FaUser /> Empleado</label>
                            <select
                                className="input-field"
                                value={idUsuario}
                                onChange={(e) => setIdUsuario(e.target.value)}
                            >
                                <option value="">Seleccione</option>
                                {usuarios.map((usuario) => (
                                    <option key={usuario.id} value={usuario.id}>
                                        {usuario.nombre}
                                    </option>
                                ))}
                            </select>
                        </div>

                        <div className="form-group">
                            <label><FaFileContract /> Contrato</label>
                            <select
                                className="input-field"
                                value={contrato}
                                onChange={(e) => setContrato(e.target.value)}
                            >
                                <option value="">Seleccione</option>
                                {/* Aquí puedes agregar opciones reales si las tienes */}
                                <option value="1">Contrato A</option>
                                <option value="2">Contrato B</option>
                            </select>
                        </div>
                    </div>

                    <div className="form-row">
                        <div className="form-group">
                            <label><FaFlag /> Motivo de Terminación</label>
                            <select
                                className="input-field"
                                value={motivoTerminacion}
                                onChange={(e) => setMotivoTerminacion(e.target.value)}
                            >
                                <option value="">Seleccione</option>
                                <option value="renuncia">Renuncia</option>
                                <option value="despido">Despido</option>
                            </select>
                        </div>
                    </div>

                    <div className="form-actions">
                        <button className="primary-button" onClick={calcularLiquidacion}>
                            <FaSave /> Guardar Liquidación
                        </button>
                    </div>

                    <div className="form-actions" style={{ marginTop: "20px" }}>
                        <button className="secondary-button">
                            <FaArrowLeft /> Volver al Menú
                        </button>
                    </div>

                    {resultado && (
                        <div className="resultado-container">
                            <h3>Resultado de Liquidación</h3>
                            <pre>{JSON.stringify(resultado, null, 2)}</pre>
                        </div>
                    )}
                </div>
            </div>
        </main>
    );
};

export default LiquidacionFinalForm;
