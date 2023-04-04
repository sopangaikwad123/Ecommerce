package com.Nia.electronic.store.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
@Id
private String productId;
private String title;

@Column(length = 1000)
private String description;

private int price;

private int discount;

private int quantity;

private Date addedDate;

private boolean live;

private boolean stock;


}
