package com.api.tareas.services;

import com.api.tareas.dto.TareaResponse;
import com.api.tareas.dto.TareaDTO;


public interface TareaService {

    //Definimos el metodo a implementar para buscar todos los usuarios
    TareaResponse findAllTareas(int pageNumber, int pageSize);

    //Definimos el metodo a implementar para buscar usuario por id
    TareaDTO findTareaById(Long tareaId);

    TareaDTO createTarea(TareaDTO tareaDTO);

    TareaDTO updateTarea(Long tareaId, TareaDTO tareaDTO);

    void deleteTarea(Long tareaId);

}
