package lt.techin.cinema.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lt.techin.cinema.dto.SeatPurchaseRequest;
import lt.techin.cinema.exceptions.TicketAlreadyPurchasedException;
import lt.techin.cinema.exceptions.WrongRowOrColumnNumberException;
import lt.techin.cinema.model.Cinema;
import lt.techin.cinema.model.Seat;
import lt.techin.cinema.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Seat purchaseTicket(@RequestBody SeatPurchaseRequest purchaseRequest) throws WrongRowOrColumnNumberException,
            TicketAlreadyPurchasedException {
        return cinemaService.purchaseTicket(purchaseRequest);
    }

}