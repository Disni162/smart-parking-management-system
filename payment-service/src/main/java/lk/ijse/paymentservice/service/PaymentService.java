package lk.ijse.paymentservice.service;

import lk.ijse.paymentservice.dto.PaymentReceipt;
import lk.ijse.paymentservice.dto.PaymentRequest;
import lk.ijse.paymentservice.entity.Payment;
import lk.ijse.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentReceipt processPayment(PaymentRequest request) {
        // Mock Card Validation Logic
        boolean isValidCard = request.getCardNumber() != null &&
                request.getCardNumber().length() == 16 &&
                !request.getCardNumber().startsWith("0000");

        String status = isValidCard ? "SUCCESS" : "FAILED";
        String txnId = "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        String lastFour = request.getCardNumber() != null && request.getCardNumber().length() >= 4
                ? request.getCardNumber().substring(request.getCardNumber().length() - 4)
                : "XXXX";

        Payment payment = Payment.builder()
                .bookingId(request.getBookingId())
                .userId(request.getUserId())
                .amount(request.getAmount())
                .cardLastFour(lastFour)
                .transactionId(txnId)
                .status(status)
                .paymentDate(LocalDateTime.now())
                .build();

        paymentRepository.save(payment);

        return PaymentReceipt.builder()
                .transactionId(txnId)
                .bookingId(request.getBookingId())
                .userId(request.getUserId())
                .amount(request.getAmount())
                .status(status)
                .paymentDate(payment.getPaymentDate())
                .message(isValidCard ? "Payment processed successfully" : "Payment failed due to invalid card details")
                .build();
    }
}