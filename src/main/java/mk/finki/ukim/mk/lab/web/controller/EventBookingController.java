package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.service.impl.EventServiceImpl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventBookingController {

    private final EventServiceImpl eventService;

    public EventBookingController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/eventBooking")
    public String bookEvent(
            @RequestParam("eventName") String eventName,
            @RequestParam("numTickets") String numTickets,
            Model model) {

        model.addAttribute("eventName", eventName);
        model.addAttribute("numTickets", numTickets);

        return "bookingConfirmation";
    }
}
