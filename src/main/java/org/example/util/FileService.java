package org.example.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.Ticket;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class FileService {
    public static List<Ticket> loadTicketsFromResources(Resource resource) {
        List<Ticket> tickets = Collections.emptyList();
        try {
            String data = new String(resource.getInputStream().readAllBytes());
            tickets = new ObjectMapper().readValue(data, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
