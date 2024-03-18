package lt.techin.cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class Seat {

    private int row;
    private int column;
    private int price;
    @JsonIgnore
    private boolean taken;

    public Seat() {
    }

    public Seat(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }

    public boolean isSeatTaken() {
        return taken;
    }

    public void calculateTicketPrice(int row) {
        int price = row <= 4 ? 10 : 8;
        this.setPrice(price);
    }
}

