package org.example.model.user;

import lombok.Getter;
import org.example.api.Printable;
import org.example.model.Ticket;

public class Client extends User implements Printable {
    final Role role;
    @Getter
    private Ticket ticket;

    public Client() {
        this.role = Role.CLIENT;
    }

    @Override
    public void printRole() {
        System.out.println("Print role in console: " + role);
    }
}
