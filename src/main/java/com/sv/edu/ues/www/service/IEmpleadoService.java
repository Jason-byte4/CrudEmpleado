package com.sv.edu.ues.www.service;

import org.springframework.data.domain.Page;

import com.sv.edu.ues.www.models.Empleado;



public interface IEmpleadoService {
	public void guardarEmpleado(Empleado emp);
	public Empleado obtenerEmpleadoById(long id);
	public void eliminarEmpleadoById(long id);
	Page<Empleado> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
