package com.api.tareas.repository;

import com.api.tareas.models.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea,Long> {
}
