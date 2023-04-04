package com.Nia.electronic.store.helper;

import com.Nia.electronic.store.dtos.PageableResponse;
import com.Nia.electronic.store.dtos.UserDto;
import com.Nia.electronic.store.entites.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class Helper<U , V> {

    public static<U, V> PageableResponse<V> getPageableResponse(Page<U> page,Class<V> type){

        List<U> entity = page.getContent();
        List<V> dtoList =entity.stream().map(object -> new ModelMapper().map(object,type)).collect(Collectors.toList());
        PageableResponse<V> response = new PageableResponse<>();
        response.setContent(dtoList);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());
        Helper.getPageableResponse(page,UserDto.class);
        return response;



    }


    }
