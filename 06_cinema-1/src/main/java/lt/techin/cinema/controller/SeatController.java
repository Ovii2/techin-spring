package lt.techin.cinema.controller;

import lt.techin.cinema.model.Cinema;
import lt.techin.cinema.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seats")
public class SeatController {

    private CinemaService cinemaService;

    @Autowired
    public SeatController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("")
    public Cinema getAllSeats() {
        return cinemaService.createNewCinema();
    }
}
