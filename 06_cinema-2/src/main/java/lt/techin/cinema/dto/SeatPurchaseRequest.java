package lt.techin.cinema.dto;


import lombok.Data;

@Data
public class SeatPurchaseRequest {

    private int row;
    private int column;
}
