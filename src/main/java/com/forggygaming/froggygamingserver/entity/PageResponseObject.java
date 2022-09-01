package com.forggygaming.froggygamingserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class PageResponseObject {
    private int page;
    private int perPage;
    private int total;
    private int totalPages;
    private Object data;
}
