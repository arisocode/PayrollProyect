package co.edu.unbosque.db2.payroll_proyect.mapper.interfaces;

//Interfac dataMapper
public interface DataMapper<S,T> {

    T toDTO(S entity);
    S toEntity(T dto);
}
