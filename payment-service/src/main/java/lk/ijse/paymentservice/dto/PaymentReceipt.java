package lk.ijse.paymentservice.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentReceipt {
    private String transactionId;
    private Long bookingId;
    private Long userId;
    private Double amount;
    private String status;
    private LocalDateTime paymentDate;
    private String message;
}
