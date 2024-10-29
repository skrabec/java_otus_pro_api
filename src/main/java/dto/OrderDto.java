package dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private Boolean completeStatus;

}
