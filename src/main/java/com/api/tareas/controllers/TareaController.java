package com.api.tareas.controllers;

import com.api.tareas.dto.TareaDTO;
import com.api.tareas.dto.TareaResponse;
import com.api.tareas.services.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/")
public class TareaController {

    private final TareaService tareaService;

    @Autowired
    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    //Obtener todas las tareas con una paginacion de 2
    @GetMapping("tareas")
    public ResponseEntity<TareaResponse> listTareas(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false)
            int pageNumber,//Numero de la pagina
            @RequestParam(value = "pageSize", defaultValue = "5", required = false)
            int pageSize//Numero de elementos
    ) {
        return new ResponseEntity<>(tareaService.findAllTareas(pageNumber,pageSize),HttpStatus.OK);
    }

    //Obtener una tarea con id especifico
    @GetMapping("tareas/{tareaId}")
    public ResponseEntity<TareaDTO> getTareaById(@PathVariable Long tareaId) {
        return new ResponseEntity<>(tareaService.findTareaById(tareaId), HttpStatus.OK);
    }

    //Crear tarea
    @PostMapping("tarea/create")
    public ResponseEntity<TareaDTO> createTarea(@RequestBody TareaDTO tareaDTO) {
        return new ResponseEntity<>(tareaService.createTarea(tareaDTO),HttpStatus.OK);
    }

    //Editar tarea
    @PutMapping("tareas/{tareaId}")
    public TareaDTO updateTarea(@PathVariable Long tareaId, @RequestBody TareaDTO tareaDTO) {
        return tareaService.updateTarea(tareaId, tareaDTO);
    }

    //Eliminar tarea
    @DeleteMapping("tareas/{tareaId}")
    public ResponseEntity<TareaDTO> deleteTarea(@PathVariable Long tareaId) {
        tareaService.deleteTarea(tareaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
