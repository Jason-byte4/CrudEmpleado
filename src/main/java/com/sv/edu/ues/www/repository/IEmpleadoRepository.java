package com.sv.edu.ues.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sv.edu.ues.www.models.Empleado;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado,Long>{

}
