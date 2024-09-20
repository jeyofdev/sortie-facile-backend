package com.poec.sortie_facile_backend.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ListIdsFormat {
    private int count;
    private List<Long> results;
}
