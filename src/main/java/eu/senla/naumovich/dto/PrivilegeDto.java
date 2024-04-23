package eu.senla.naumovich.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrivilegeDto {
    Long id;
    String privilegeName;
}
