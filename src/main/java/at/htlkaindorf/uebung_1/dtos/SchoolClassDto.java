package at.htlkaindorf.uebung_1.dtos;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SchoolClassDto implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
}
