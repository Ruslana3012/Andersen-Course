package org.example.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.entity.TicketType;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class PostgreSQLTicketTypeEnumConverter implements AttributeConverter<TicketType, String> {

    @Override
    public String convertToDatabaseColumn(TicketType ticketType) {
        if (ticketType == null) {
            return null;
        }
        return ticketType.name();
    }

    @Override
    public TicketType convertToEntityAttribute(String ticketTypeName) {
        if (ticketTypeName == null) {
            return null;
        }

        return Stream.of(TicketType.values())
                .filter(t -> t.name().equals(ticketTypeName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}