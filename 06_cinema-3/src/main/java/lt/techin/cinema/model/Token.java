package lt.techin.cinema.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Token {
    private UUID token;

    public Token(UUID token) {
        this.token = token;
    }
}
