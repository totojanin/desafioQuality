package com.desafioQuality.desafioQuality.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingFlightResponseDTO {
    private String userName;
    private double amount;
    private double interest;
    private double total;
    private BookingFlightNoPaymentDTO booking;
    private StatusCodeDTO statusCode;

    public void setBooking(BookingFlightNoPaymentDTO booking) {
        this.booking = booking;
    }

    public void setBooking(BookingFlightDTO booking) {
        this.booking = new BookingFlightNoPaymentDTO();

        this.booking.setDateFrom(booking.getDateFrom());
        this.booking.setDateTo(booking.getDateTo());
        this.booking.setOrigin(booking.getOrigin());
        this.booking.setDestination(booking.getDestination());
        this.booking.setFlightNumber(booking.getFlightNumber());
        this.booking.setSeats(booking.getSeats());
        this.booking.setSeatType(booking.getSeatType());
        this.booking.setPeople(booking.getPeople());
    }
}
