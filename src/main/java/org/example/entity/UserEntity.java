package org.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class UserEntity {
    private int id;
    private String name;
    private Timestamp creationDate;

    public UserEntity(String name, Timestamp creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }
}
