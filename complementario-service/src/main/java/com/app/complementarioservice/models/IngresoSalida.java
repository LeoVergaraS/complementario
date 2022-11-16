package com.app.complementarioservice.models;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngresoSalida {
    private Date fecha;
    private Time hora;
    private String rutEmpleado;
}
