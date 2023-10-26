package com.api.tareas.services.impl;

import com.api.tareas.dto.TareaDTO;
import com.api.tareas.dto.TareaResponse;
import com.api.tareas.models.Tarea;
import com.api.tareas.repository.TareaRepository;
import com.api.tareas.services.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaServiceImpl implements TareaService {

    private final TareaRepository tareaRepository;

    @Autowired
    public TareaServiceImpl(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @Override
    public TareaResponse findAllTareas(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Tarea> tareas = tareaRepository.findAll(pageable);
        List<Tarea> tareasList = tareas.getContent();
        List<TareaDTO> content = tareasList.stream().map(TareaServiceImpl::mapToTareaDTO)
                .collect((Collectors.toList()));

        TareaResponse tareaResponse = new TareaResponse();

        tareaResponse.setContent(content);
        tareaResponse.setPageNumber(tareas.getNumber());
        tareaResponse.setPageSize(tareas.getSize());
        tareaResponse.setTotalElements(tareas.getTotalElements());
        tareaResponse.setTotalPages(tareas.getTotalPages());
        tareaResponse.setLast(tareas.isLast());

        return tareaResponse;
    }

    @Override
    public TareaDTO findTareaById(Long tareaId) {
        Tarea tareaEntity = tareaRepository.getReferenceById(tareaId);
        return TareaServiceImpl.mapToTareaDTO(tareaEntity);
    }

    @Override
    public TareaDTO createTarea(TareaDTO tareaDTO) {
        Tarea tareaEntity = new Tarea();

        tareaEntity.setDetalles_tarea(tareaDTO.getDetalles_tarea());
        tareaEntity.setEstado_tarea(tareaDTO.getEstado_tarea());
        tareaEntity.setEncabazado_tarea(tareaDTO.getEncabazado_tarea());
        tareaEntity.setFecha_vencimiento(tareaDTO.getFecha_vencimiento());
        tareaEntity.setUserid(tareaDTO.getUserId());

        tareaEntity = tareaRepository.save(tareaEntity);
        return mapToTareaDTO(tareaEntity);
    }

    @Override
    public TareaDTO updateTarea(Long tareaId, TareaDTO tareaDTO) {
        Tarea tarea = tareaRepository.findById(tareaId).orElse(null);
        if (tarea != null) {
            tarea.setEncabazado_tarea(tareaDTO.getEncabazado_tarea());
            tarea.setDetalles_tarea(tareaDTO.getDetalles_tarea());
            tarea.setEstado_tarea(tareaDTO.getEstado_tarea());
            tarea.setFecha_vencimiento(tareaDTO.getFecha_vencimiento());
            tarea = tareaRepository.save(tarea);
        }
        return mapToTareaDTO(tarea);
    }

    @Override
    public void deleteTarea(Long tareaId) {
        Tarea tareaEntity = tareaRepository.getReferenceById(tareaId);
        tareaRepository.delete(tareaEntity);
    }

    private static TareaDTO mapToTareaDTO(Tarea tarea){
        return TareaDTO.builder()
                .tareaId(tarea.getTareaId())
                .userId(tarea.getUserid())
                .encabazado_tarea(tarea.getEncabazado_tarea())
                .detalles_tarea(tarea.getDetalles_tarea())
                .estado_tarea(tarea.getEstado_tarea())
                .fecha_vencimiento(tarea.getFecha_vencimiento())
                .build();
    }

    private Tarea mapToTareaEntity(TareaDTO tareaDTO){
        return Tarea.builder()
                .tareaId(tareaDTO.getTareaId())
                .userid(tareaDTO.getUserId())
                .encabazado_tarea(tareaDTO.getEncabazado_tarea())
                .detalles_tarea(tareaDTO.getDetalles_tarea())
                .estado_tarea(tareaDTO.getEstado_tarea())
                .fecha_vencimiento(tareaDTO.getFecha_vencimiento())
                .build();
    }
}