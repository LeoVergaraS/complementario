package com.app.complementarioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.complementarioservice.entity.Atraso;

@Repository
public interface AtrasoRepository extends JpaRepository<Atraso, Long> {
    @Query(value = "SELECT * FROM atrasos a WHERE a.rut_empleado = :rut AND a.mes = :mes AND a.anio = :anio",
         nativeQuery = true)
    Atraso findAtrasoEmpleadoByFecha(@Param("rut") String rut, @Param("mes") int mes, @Param("anio") int anio);
}
    
