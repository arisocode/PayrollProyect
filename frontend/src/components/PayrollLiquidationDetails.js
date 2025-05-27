import { FaArrowLeft } from 'react-icons/fa';
import { useLocation, useNavigate } from 'react-router-dom';

function PayrollLiquidationDetails() {
    const navigate = useNavigate();
    const location = useLocation();
    const { liquidacion } = location.state || {};

    if (!liquidacion) {
        return <p>No hay información de liquidación para mostrar.</p>;
    }

    return (
        <div className="details">
            <button className="small-button" onClick={() => navigate(-1)}>
            <FaArrowLeft /> Volver
            </button>

            <h2>Detalles de Liquidación</h2>
            <ul>
            <li><strong>ID:</strong> {liquidacion._id}</li>
            <li><strong>Empleado:</strong> {liquidacion.employeeId}</li>
            <li><strong>Contrato:</strong> {liquidacion.contractId}</li>
            <li><strong>Fecha:</strong> {new Date(liquidacion.liquidationDate).toLocaleDateString('es-CO')}</li>
            <li><strong>Salario Base:</strong> ${liquidacion.basicSalary?.toLocaleString('es-CO')}</li>
            <li><strong>Horas Extras:</strong> ${liquidacion.extraHoursValue?.toLocaleString('es-CO')}</li>
            <li><strong>Deducciones Legales:</strong> ${liquidacion.legalDeductions?.toLocaleString('es-CO')}</li>
            <li><strong>Total Liquidado:</strong> ${liquidacion.totalValue?.toLocaleString('es-CO')}</li>
            <li><strong>Estado:</strong> {liquidacion.status}</li>
            </ul>
        </div>
    );
}

export default PayrollLiquidationDetails;
