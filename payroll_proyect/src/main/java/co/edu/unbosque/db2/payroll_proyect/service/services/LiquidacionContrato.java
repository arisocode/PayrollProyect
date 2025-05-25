package co.edu.unbosque.db2.payroll_proyect.service.services;


import co.edu.unbosque.db2.payroll_proyect.model.dto.LiquidacionResultadoDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class LiquidacionContrato {

    @PersistenceContext
    private EntityManager entityManager;

    public LiquidacionResultadoDto calcularLiquidacion(int dias, BigDecimal salario , int diasFaltantesSueldo ,int diasVacacionesPendientes) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("calcular_liquidacion");

        query.registerStoredProcedureParameter("p_dias_trabajados", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_salario_mensual", BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_dias_faltantes_sueldo", BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_dias_vacaciones_pendientes", BigDecimal.class, ParameterMode.IN);


        query.registerStoredProcedureParameter("o_cesantias", BigDecimal.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter("o_intereses_cesantias", BigDecimal.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter("o_prima_servicios", BigDecimal.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter("o_vacaciones", BigDecimal.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter("o_total_liquidacion", BigDecimal.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter("o_nomina_pendiente", BigDecimal.class, ParameterMode.OUT);

        query.setParameter("p_dias_trabajados", dias);
        query.setParameter("p_salario_mensual", salario);
        query.setParameter("p_dias_faltantes_sueldo", diasFaltantesSueldo);
        query.setParameter("p_dias_vacaciones_pendientes", diasVacacionesPendientes);

        query.execute();

        LiquidacionResultadoDto dto = new LiquidacionResultadoDto();
        dto.setCesantias((BigDecimal) query.getOutputParameterValue("o_cesantias"));
        dto.setInteresesCesantias((BigDecimal) query.getOutputParameterValue("o_intereses_cesantias"));
        dto.setPrimaServicios((BigDecimal) query.getOutputParameterValue("o_prima_servicios"));
        dto.setVacaciones((BigDecimal) query.getOutputParameterValue("o_vacaciones"));
        dto.setTotalLiquidacion((BigDecimal) query.getOutputParameterValue("o_total_liquidacion"));
        dto.setNominaPendiente((BigDecimal) query.getOutputParameterValue("o_nomina_pendiente"));


        return dto;
    }


}

