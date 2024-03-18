package lt.techin.cinema.services;

import lt.techin.cinema.dto.SeatPurchaseRequest;
import lt.techin.cinema.exceptions.TicketAlreadyPurchasedException;
import lt.techin.cinema.exceptions.WrongRowOrColumnNumberException;
import lt.techin.cinema.exceptions.WrongTokenException;
import lt.techin.cinema.model.Cinema;
import lt.techin.cinema.model.Seat;
import lt.techin.cinema.model.Token;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CinemaService {

    private Cinema cinema = new Cinema();

    public Cinema createNewCinema() {
        return cinema;
    }


    public Map<String, Object> purchaseTicket(SeatPurchaseRequest purchaseRequest) throws WrongRowOrColumnNumberException,
            TicketAlreadyPurchasedException {
        checkRowOrColumnNumber(purchaseRequest.getRow(), purchaseRequest.getColumn());
        Seat seat = cinema.getSeat(purchaseRequest.getRow(), purchaseRequest.getColumn());
        checkIfSeatIsTaken(seat);
        seat.setTaken(true);
        Token token = new Token(UUID.randomUUID());
        Map<String, Object> purchaseInfo = new LinkedHashMap<>();
        purchaseInfo.put("token", token.getToken());
        purchaseInfo.put("ticket", seat);
        seat.setToken(token);
        return purchaseInfo;
    }

    public Seat returnTicket(UUID token) throws WrongTokenException {
        Seat seat = findSeatByToken(token);
        seat.setToken(null);
        seat.setTaken(false);
        return seat;
    }


    public Seat findSeatByToken(UUID token) throws WrongTokenException {
        return cinema.getSeats().stream()
                .filter(seat -> seat.getToken() != null && seat.getToken().getToken().equals(token))
                .findFirst()
                .orElseThrow(() -> new WrongTokenException("Wrong token!"));
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