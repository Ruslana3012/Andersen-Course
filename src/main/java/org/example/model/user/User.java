package org.example.model.user;

import lombok.Getter;
import lombok.Setter;
import org.example.api.ID;

@Getter
@Setter
public abstract class User extends ID {
    public User() {
    }

    public User(Long ID) {
        super(ID);
    }

    public void printRole() {
        System.out.println("Print role in console");
    }
}
