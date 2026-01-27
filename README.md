# Poblado Nómina – Sistema de Gestión de Nómina

<img width="1600" height="937" alt="image" src="https://github.com/user-attachments/assets/5c921e77-dcc6-431c-9908-276a9375002d" />

**Poblado Nómina** es un sistema integral de nómina desarrollado para gestionar empleados, contratos, liquidaciones, cesantías, novedades y terceros asociados de manera eficiente.

Lo más interesante es que **todas las operaciones en la base de datos se realizan mediante PL/SQL**: procedimientos almacenados, funciones, vistas y triggers, sin depender de entidades JPA. Esto garantiza integridad, seguridad y automatización en todas las transacciones.

---

## Características principales

- Gestión de **empleados**, incluyendo información personal y bancaria.  
- Registro y control de **contratos laborales**, tipos de contrato y horarios.  
- **Liquidaciones de nómina**, incluyendo deducciones legales, horas extras y total a pagar.  
- Manejo de **cesantías** y liquidaciones definitivas.  
- Gestión de **novedades** y **terceros asociados**.  
- Integración con **frontend React** y **backend Spring Boot**.  

---

<img width="1600" height="846" alt="image" src="https://github.com/user-attachments/assets/5caab927-4872-4870-8277-7ec4bfdcda6e" />

## Arquitectura del proyecto

Frontend: React
Backend: Spring Boot (Java)
Base de Datos: MySQL con PL/SQL

- Servicios: EmployeeService, ContractService, PayrollService, BankService, etc.
- Operaciones: CRUD mediante procedimientos almacenados, funciones y triggers.
- Persistencia: SimpleJdbcCall en Java para ejecutar procedimientos.

<img width="1600" height="847" alt="image" src="https://github.com/user-attachments/assets/10fbba4c-2179-4aca-b2bb-bf57e8a7f990" />

## Tecnologías utilizadas

- **Frontend:** React, React Router, React Icons  
- **Backend:** Java 21, Spring Boot, Spring Data JPA, JDBC  
- **Base de datos:** MySQL + PL/SQL (Procedimientos almacenados, funciones, vistas y triggers)  
- **Gestión de dependencias:** Maven  
- **Control de versiones:** Git  

---

## Estructura del proyecto

**Frontend:**

<img width="424" height="746" alt="image" src="https://github.com/user-attachments/assets/9925a64f-0f30-4b18-b744-b4036db82ba2" />

**Backend:**

<img width="426" height="870" alt="image" src="https://github.com/user-attachments/assets/ae64caf6-cf9d-48cd-ab1c-43d719df9906" />

---

## Funcionalidades principales

1. **Gestión de Empleados**  
   - Crear, consultar, eliminar y o modificar empleados usando procedimientos almacenados.  
   - Manejo de información bancaria asociada.  

2. **Gestión de Contratos**  
   - Registro de contratos laborales, tipos de contrato y horarios.  
   - Actualización y modificación de contratos mediante procedimientos.  

3. **Liquidaciones de Nómina**  
   - Generación de liquidaciones individuales o masivas.  
   - Cálculo de horas extras, deducciones y total a pagar.  

4. **Cesantías y Liquidaciones Definitivas**  
   - Control y cálculo automático de cesantías.  
   - Generación de liquidaciones finales al terminar un contrato.  

5. **Gestión de Novedades y Terceros**  
   - Registro de novedades en la nómina.  
   - Manejo de terceros asociados a contratos y liquidaciones.  

---

<img width="1600" height="840" alt="image" src="https://github.com/user-attachments/assets/28f62909-87c8-4516-bcff-f4cc76dff935" />


## Detalles técnicos

- Todas las operaciones de base de datos son manejadas por **PL/SQL**, garantizando consistencia y eficiencia.  
- Uso de **SimpleJdbcCall** en Spring Boot para invocar procedimientos y funciones.  
- Estructura modular y escalable para añadir nuevas funcionalidades fácilmente.  
- Frontend limpio y responsivo con React y CSS modular.  

---

## Cómo correr el proyecto

1. Clonar el repositorio
2. Configurar la base de datos MySQL y ejecutar los scripts SQL y PL/SQL. (Aunque se puede hacer mapeo de las entidades, se necesita de las vista, funciones, sp y triggers para funcionar asi que contactame y te los paso!)
3. Configurar el archivo application.properties con tus credenciales de base de datos.
4. Ejecutar el backend con Spring Boot
5. Ejecutar el frontend (npm start)

<img width="1187" height="916" alt="image" src="https://github.com/user-attachments/assets/ed2bb1ca-b546-4df5-91ba-03b75eb4a1c3" />

---

## Aporte del proyecto

Este proyecto demuestra cómo un sistema de nómina completo puede ser construido sin depender de entidades JPA, 
usando únicamente PL/SQL para manejar la lógica de base de datos, y una arquitectura moderna con Spring Boot + React para la interfaz y servicios.

---

<img width="1600" height="845" alt="image" src="https://github.com/user-attachments/assets/c5a35f29-0ec9-48f8-a530-665ec2b278e1" />


**Autor:** Equipo Poblado Nómina


