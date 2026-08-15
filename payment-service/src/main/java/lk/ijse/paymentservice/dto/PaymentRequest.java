package lk.ijse.paymentservice.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long bookingId;
    private Long userId;
    private Double amount;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
}