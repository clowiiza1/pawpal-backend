package co.za.pawpal.backend.dto;

import lombok.Data;

@Data
public class VolunteerInfoDto {
   private String preferredRoles;
   private Integer volunteerHours;
   private String emergencyContactName;
   private String emergencyContactNumber;
}
