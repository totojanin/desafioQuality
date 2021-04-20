package com.desafioQuality.desafioQuality.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingFlightDTO extends BookingFlightNoPaymentDTO {
    public BookingFlightDTO(String dateFrom, String dateTo, String origin, String destination, String flightNumber, String seats, String seatType, List<PersonDTO> people, PaymentMethodDTO paymentMethod) {
        super(dateFrom, dateTo, origin, destination, flightNumber, seats, seatType, people);

        this.paymentMethod = paymentMethod;
    }

    private PaymentMethodDTO paymentMethod;
}
