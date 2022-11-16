package com.app.complementarioservice.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "atrasos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Atraso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private int mes;
    private int anio;
    private int atraso10min;
    private int atraso25min;
    private int atraso45min;
    private String rutEmpleado;
}
