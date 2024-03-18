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
        fillCinemaWithPricedSeats(rows, columns, 5);
    }

    public List<Seat> fillCinemaWithPricedSeats(int row, int column, int price) {
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                Seat seat = new Seat(i, j, price);
                seat.calculateTicketPrice(i);
                seats.add(seat);
            }
        }
        return seats;
    }

    public Seat getSeat(int row, int column) {
        return seats.stream()
                .filter(seat -> seat.getRow() == row && seat.getColumn() == column)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No seat was found"));
    }
}
