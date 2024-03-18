package lt.techin.cinema.exceptions;

public class TicketAlreadyPurchasedException extends Exception{

    public TicketAlreadyPurchasedException(String message) {
        super(message);
    }
}
