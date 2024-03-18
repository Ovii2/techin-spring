package lt.techin.cinema.services;

import lt.techin.cinema.dto.SeatPurchaseRequest;
import lt.techin.cinema.exceptions.TicketAlreadyPurchasedException;
import lt.techin.cinema.exceptions.WrongRowOrColumnNumberException;
import lt.techin.cinema.model.Cinema;
import lt.techin.cinema.model.Seat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CinemaService {

    private Cinema cinema = new Cinema();


    public Cinema createNewCinema() {
        return cinema;
    }

    public Seat purchaseTicket(SeatPurchaseRequest purchaseRequest) throws WrongRowOrColumnNumberException,
            TicketAlreadyPurchasedException {
        checkRowOrColumnNumber(purchaseRequest.getRow(), purchaseRequest.getColumn());
        Seat seat = cinema.getSeat(purchaseRequest.getRow(), purchaseRequest.getColumn());
        checkIfSeatIsTaken(seat);
        seat.setTaken(true);
        return seat;
    }


    public void checkIfSeatIsTaken(Seat seat) throws TicketAlreadyPurchasedException {
        if (seat.isSeatTaken()) {
            throw new TicketAlreadyPurchasedException("The ticket has been already purchased!");
        }
    }

    public void checkRowOrColumnNumber(int row, int column) throws WrongRowOrColumnNumberException {
        if (row > 9 || column > 9) {
            throw new WrongRowOrColumnNumberException("The number of a row or a column is out of bounds!");
        }
    }
}