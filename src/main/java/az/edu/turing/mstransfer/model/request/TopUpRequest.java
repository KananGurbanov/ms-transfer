package az.edu.turing.mstransfer.model.request;

import az.edu.turing.mstransfer.model.annotations.ValidIban;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TopUpRequest(
        @ValidIban
        String fromIban,

        @Positive(message = "Transfer amount must be greater than 0")
        BigDecimal amount) {
}
