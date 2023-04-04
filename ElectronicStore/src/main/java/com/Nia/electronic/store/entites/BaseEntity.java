package com.Nia.electronic.store.entites;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
@Data
@MappedSuperclass
public class BaseEntity {
@CreationTimestamp
@Column(name = "create_Date",updatable = false )
private LocalDate create;
@Column(name = "update_Date",insertable  = false)
@UpdateTimestamp
private LocalDate update;





}
