package co.za.pawpal.backend.dto;

import lombok.Data;

@Data
public class AuthResponsDto {
    private String accessToken;
    private String tokenType = "Bearer ";

    public AuthResponsDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
