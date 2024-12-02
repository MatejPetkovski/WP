package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.EventBooking;

public interface EventBookingService{
    EventBooking pBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets);
}
