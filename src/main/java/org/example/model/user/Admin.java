package org.example.model.user;

import org.example.api.Printable;
import org.example.model.Ticket;

public class Admin extends User implements Printable {
    final Role role;

    public Admin() {
        this.role = Role.ADMIN;
    }

    @Override
    public void printRole() {
        System.out.println("Print role in console: " + role);
    }

    public void checkTicket(Ticket ticket) {
        System.out.println("Check ticket: " + ticket);
    }

    @Override
    public void print() {
        System.out.println("Print content in console by ADMIN");
    }
}
