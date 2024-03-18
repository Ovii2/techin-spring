package lt.techin.cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lt.techin.cinema.services.CinemaService;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Cinema {

    private int rows;
    private int columns;
    private List<Seat> seats;

    public Cinema() {
        this.rows = 9;
        this.columns = 9;
        this.seats = fillCinemaWithPricedSeats(9, 9, 5);
    }

    public List<Seat> fillCinemaWithPricedSeats(int row, int column, int price) {
        List<Seat> seats = new ArrayList<>();
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
        for (Seat seat : seats) {
            if (seat.getRow() == row && seat.getColumn() == column) {
                return seat;
            }
        }
        throw new IllegalArgumentException("No seat was found");
    }

}
