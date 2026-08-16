package lk.ijse.paymentservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PaymentRequest {

    @NotNull(message = "Booking ID is required")
    private Long bookingId;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Payment amount is required")
    @Positive(message = "Payment amount must be greater than zero")
    private Double amount;

    @NotBlank(message = "Card number is required")
    @Pattern(
            regexp = "\\d{16}",
            message = "Card number must contain exactly 16 digits"
    )
    private String cardNumber;

    @NotBlank(message = "Expiry date is required")
    @Pattern(
            regexp = "(0[1-9]|1[0-2])/\\d{2}",
            message = "Expiry date must be in MM/YY format"
    )
    private String expiryDate;

    @NotBlank(message = "CVV is required")
    @Pattern(
            regexp = "\\d{3}",
            message = "CVV must contain exactly 3 digits"
    )
    private String cvv;
}