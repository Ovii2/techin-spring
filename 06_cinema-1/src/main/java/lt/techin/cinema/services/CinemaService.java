package lt.techin.cinema.services;

import lt.techin.cinema.model.Cinema;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {
    public Cinema createNewCinema() {
        return new Cinema();
    }
}
