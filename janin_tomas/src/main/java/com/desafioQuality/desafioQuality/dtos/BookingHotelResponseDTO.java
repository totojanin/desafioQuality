package com.desafioQuality.desafioQuality.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingHotelResponseDTO {
    private String userName;
    private double amount;
    private double interest;
    private double total;
    private BookingHotelNoPaymentDTO booking;
    private StatusCodeDTO statusCode;

    public void setBooking(BookingHotelNoPaymentDTO booking) {
        this.booking = booking;
    }

    public void setBooking(BookingHotelDTO booking) {
        this.booking = new BookingHotelNoPaymentDTO();

        this.booking.setDateFrom(booking.getDateFrom());
        this.booking.setDateTo(booking.getDateTo());
        this.booking.setDestination(booking.getDestination());
        this.booking.setHotelCode(booking.getHotelCode());
        this.booking.setPeopleAmount(booking.getPeopleAmount());
        this.booking.setRoomType(booking.getRoomType());
        this.booking.setPeople(booking.getPeople());
    }
}
