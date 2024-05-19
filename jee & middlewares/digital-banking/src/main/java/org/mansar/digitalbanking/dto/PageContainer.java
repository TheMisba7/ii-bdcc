package org.mansar.digitalbanking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageContainer<T> {
    private List<T> content;
    private int currentPage;
    private int size;
    private int totalPages;
    private int totalElements;
}
