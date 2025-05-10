import { useEffect, useState } from 'react';

function EmployeeStatus() {
  const [employees, setEmployees] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/api/employees/status')
      .then(res => res.json())
      .then(data => setEmployees(data))
      .catch(() => setEmployees([
        { name: 'Camila Ruiz', status: 'Activo' },
        { name: 'Luis Gómez', status: 'Inactivo' }
      ]));
  }, []);

  return (
    <div className="employee-status-list">
      {employees.map((emp, idx) => (
        <div
          key={idx}
          className={`employee-card ${emp.status === 'Activo' ? 'active' : 'inactive'}`}
        >
          <strong>{emp.name}</strong> — {emp.status}
        </div>
      ))}
    </div>
  );
}

export default EmployeeStatus;
