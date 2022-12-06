package com.swj9707.twittercloneadmin.user.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
@Data
public class AdminUserPageRequestDTO {
    private int page;
    private int size;
    private String username;

    public AdminUserPageRequestDTO(){
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort){
        return PageRequest.of(page -1, size, sort);
    }
}
