package lt.techin.cinema.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Cinema {

    private int rows;
    private int columns;
    private List<Seat> seats = new ArrayList<>();

    public Cinema() {
        this.rows = 9;
        this.columns = 9;
        fillCinemaWithSeats();
    }

    private void fillCinemaWithSeats() {
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                seats.add(new Seat(i, j));
            }
        }
    }
}
