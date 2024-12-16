package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// AllUsers DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDto {
    private String name;
    private int score;
}
