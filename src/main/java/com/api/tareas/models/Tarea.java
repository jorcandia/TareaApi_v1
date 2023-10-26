package com.api.tareas.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "tareas")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tarea implements Serializable {

    @Serial private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tareaId;
    private Long userid;
    private String encabazado_tarea;
    private String detalles_tarea;
    private Date fecha_vencimiento;
    private Boolean estado_tarea;


}