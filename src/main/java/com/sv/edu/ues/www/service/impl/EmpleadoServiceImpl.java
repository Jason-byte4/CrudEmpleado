package com.sv.edu.ues.www.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sv.edu.ues.www.models.Empleado;
import com.sv.edu.ues.www.repository.IEmpleadoRepository;
import com.sv.edu.ues.www.service.IEmpleadoService;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

	@Autowired
	private IEmpleadoRepository empRepository;

	@Override
	public void guardarEmpleado(Empleado emp) {
		// TODO Auto-generated method stub
		this.empRepository.save(emp);
	}

	@Override
	public Empleado obtenerEmpleadoById(long id) {
		Optional<Empleado> optional = this.empRepository.findById(id);
		Empleado emp = null;
		if (optional.isPresent()) {
			emp = optional.get();
		} else {
			throw new RuntimeException("Empleado no encontrado para el id: " + id);
		}
		return emp;
	}

	@Override
	public void eliminarEmpleadoById(long id) {
		this.empRepository.deleteById(id);
	}

	@Override
	public Page<Empleado> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.empRepository.findAll(pageable);
	}
}
