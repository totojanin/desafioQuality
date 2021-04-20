package com.desafioQuality.desafioQuality.dtos;

import lombok.Data;

import java.util.List;

@Data
public class BookingHotelDTO extends BookingHotelNoPaymentDTO {
    public BookingHotelDTO(String dateFrom, String dateTo, String destination, String hotelCode, String peopleAmount, String roomType, List<PersonDTO> people, PaymentMethodDTO paymentMethod) {
        super(dateFrom, dateTo, destination, hotelCode, peopleAmount,roomType, people);

        this.paymentMethod = paymentMethod;
    }

    private PaymentMethodDTO paymentMethod;
}
