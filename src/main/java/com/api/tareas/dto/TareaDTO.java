package com.api.tareas.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class TareaDTO implements Serializable {

    @Serial  private static final long serialVersionUID = 1L;
    private long tareaId;
    private Long userId;
    private String encabazado_tarea;
    private String detalles_tarea;
    private Date fecha_vencimiento;
    private Boolean estado_tarea;
}
