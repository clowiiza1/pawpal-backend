package co.za.pawpal.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class AnimalFilterDto {
    private List<String> categories;
    private String species;

}
