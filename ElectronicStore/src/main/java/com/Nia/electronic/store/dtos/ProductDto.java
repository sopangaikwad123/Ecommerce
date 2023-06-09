package com.Nia.electronic.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String productId;
    private String title;

    private String description;

    private int price;

    private int discount;

    private int quantity;

    private Date addedDate;

    private boolean live;

    private boolean stock;
}
