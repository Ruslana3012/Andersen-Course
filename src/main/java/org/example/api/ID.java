package org.example.api;

import lombok.Getter;
import lombok.Setter;
import org.example.annotation.NullableWarning;

@Getter
@Setter
public abstract class ID {
    @NullableWarning
    private Long ID;

    public ID() {
    }

    public ID(Long ID) {
        this.ID = ID;
    }
}
