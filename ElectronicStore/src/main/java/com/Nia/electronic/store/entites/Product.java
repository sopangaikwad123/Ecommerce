package com.Nia.electronic.store.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long productId;
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
