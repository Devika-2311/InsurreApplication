package com.example.service;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
 
import com.example.model.Tickets;
import com.example.repo.TicketsRepo;
 
@Service
public class TicketsService {
 
    private final TicketsRepo ticketsRepo;
 
    public TicketsService(TicketsRepo ticketsRepo) {
        this.ticketsRepo = ticketsRepo;
    }
 
    // To get all tickets
    public List<Tickets> getAllTickets() {
        return ticketsRepo.findAll();
    }
 
    // To get open tickets
    public List<Tickets> getOpenTickets() {
        return ticketsRepo.findByResponseTxtIsNull();
    }
 
    // To get close tickets
    public List<Tickets> getCloseTickets() {
        return ticketsRepo.findByResponseTxtIsNotNull();
    }
 
    // To get one ticket by ticket id
    public Optional<Tickets> getTicketById(Long id) {
        return ticketsRepo.findById(id);
    }
 
    // To get count of all tickets raised
    public long getTotalTickets() {
        return ticketsRepo.count();
    }
 
    // To create tickets
    public void add(@RequestBody Tickets ticket) {
        ticketsRepo.save(ticket);
    }
    public List<Tickets> getTicketsByUserId(Long userId) {
        return ticketsRepo.findByUser_Id(userId);
    }
    // To update a ticket
    public void updateTicket(Long id, Tickets ticketDetails) {
        Optional<Tickets> ticketOptional = ticketsRepo.findById(id);
        Tickets ticket = ticketOptional.orElseThrow(() -> new IllegalArgumentException("Invalid ticket ID"));
        ticket.setResponseTxt(ticketDetails.getResponseTxt());
        ticketsRepo.save(ticket);
    }
}
 
 

 