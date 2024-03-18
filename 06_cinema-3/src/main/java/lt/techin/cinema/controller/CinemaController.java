package lt.techin.cinema.controller;

import lt.techin.cinema.dto.SeatPurchaseRequest;
import lt.techin.cinema.exceptions.TicketAlreadyPurchasedException;
import lt.techin.cinema.exceptions.WrongRowOrColumnNumberException;
import lt.techin.cinema.exceptions.WrongTokenException;
import lt.techin.cinema.model.Cinema;
import lt.techin.cinema.model.Seat;
import lt.techin.cinema.model.Token;
import lt.techin.cinema.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CinemaController {

    private CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/seats")
    public Cinema createNewCinema() {
        return cinemaService.createNewCinema();
    }

    @PostMapping("/purchase")
    public Map<String, Object> purchaseTicket(@RequestBody SeatPurchaseRequest purchaseRequest) throws WrongRowOrColumnNumberException,
            TicketAlreadyPurchasedException {
        return cinemaService.purchaseTicket(purchaseRequest);
    }

    @PostMapping("/return")
    public Seat returnTicket(@RequestBody Token token) throws WrongTokenException {
        return cinemaService.returnTicket(token.getToken());
    }

}