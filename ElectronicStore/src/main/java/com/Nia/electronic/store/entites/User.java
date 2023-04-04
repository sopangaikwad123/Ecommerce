package com.Nia.electronic.store.entites;

import com.Nia.electronic.store.controllers.UserController;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="users")

public class User extends BaseEntity {
    private static Logger logger = LoggerFactory.getLogger( User.class);
    @Id

    private String userId;
    @Column(name="user_name")
    private String name;
    @Column (name="user_email",unique=true)
    private String email;
    @Column (name="user_password",length =10)
    private String password;
    private String gender;
    @Column(length = 1000)
    private String about;
    @Column(name= "user_imageName")
    private String imageName;

}
