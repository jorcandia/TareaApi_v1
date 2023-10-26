package com.api.tareas.dto;

import lombok.Data;

import java.util.List;

@Data
public class TareaResponse {
    private List<TareaDTO> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
