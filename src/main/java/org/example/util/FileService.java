package org.example.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.BusTicket;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileService {
    public static List<BusTicket> readFileInList(String file) {
        List<BusTicket> tickets = Collections.emptyList();
        try {
            tickets = new ObjectMapper().readValue(
                    Files.readString(Paths.get(file)),
                    new TypeReference<>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
