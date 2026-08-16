package lk.ijse.paymentservice.service;

import lk.ijse.common.exception.BadRequestException;
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

        if (request == null) {
            throw new BadRequestException(
                    "Payment request cannot be null"
            );
        }

        if (request.getBookingId() == null) {
            throw new BadRequestException(
                    "Booking ID cannot be null"
            );
        }

        if (request.getUserId() == null) {
            throw new BadRequestException(
                    "User ID cannot be null"
            );
        }

        if (request.getAmount() == null ||
                request.getAmount().doubleValue() <= 0) {

            throw new BadRequestException(
                    "Payment amount must be greater than zero"
            );
        }

        if (request.getCardNumber() == null ||
                request.getCardNumber().isBlank()) {

            throw new BadRequestException(
                    "Card number cannot be empty"
            );
        }

        boolean isValidCard =
                request.getCardNumber().length() == 16 &&
                        request.getCardNumber().matches("\\d{16}") &&
                        !request.getCardNumber().startsWith("0000");

        if (!isValidCard) {
            throw new BadRequestException(
                    "Invalid card details"
            );
        }

        String txnId = "TXN-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();

        String lastFour =
                request.getCardNumber()
                        .substring(
                                request.getCardNumber().length() - 4
                        );

        Payment payment = Payment.builder()
                .bookingId(request.getBookingId())
                .userId(request.getUserId())
                .amount(request.getAmount())
                .cardLastFour(lastFour)
                .transactionId(txnId)
                .status("SUCCESS")
                .paymentDate(LocalDateTime.now())
                .build();

        paymentRepository.save(payment);

        return PaymentReceipt.builder()
                .transactionId(txnId)
                .bookingId(request.getBookingId())
                .userId(request.getUserId())
                .amount(request.getAmount())
                .status("SUCCESS")
                .paymentDate(payment.getPaymentDate())
                .message("Payment processed successfully")
                .build();
    }
}