package com.example.controller;
 
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.model.Tickets;
import com.example.service.TicketsService;
 
@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:3003")
public class TicketsController {
 
    @Autowired
    private TicketsService ticketService;
 
 
    @GetMapping("/get")
    public List<Tickets> getAllTickets() {
        return ticketService.getAllTickets();
    }
    
    @GetMapping("/open")
    public List<Tickets> getOpenTickets() {
        return ticketService.getOpenTickets();
    }
    
    @GetMapping("/closed")
    public List<Tickets> getCloseTickets() {
        return ticketService.getCloseTickets();
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Tickets> getTicketById(@PathVariable Long id) {
        Optional<Tickets> ticket = ticketService.getTicketById(id);
        return ticket.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/create")
    public void add(@RequestBody Tickets t)
    {
    	ticketService.add(t);
    }
    
//    @PutMapping("/update/{id}")
//    public void updateTicket(@PathVariable Long id, @RequestBody Tickets ticketDetails)
//    {
//            ticketService.updateTicket(id, ticketDetails);
//            //ticketService.setResponseAt(LocalDateTime.now());
//            
//    }

    @GetMapping("/user/{userId}")
    public List<Tickets> getTicketsByUserId(@PathVariable Long userId) {
        return ticketService.getTicketsByUserId(userId);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Tickets> updateTicket(@PathVariable Long id, @RequestBody Tickets ticketDetails) {
        Optional<Tickets> ticketOptional = ticketService.getTicketById(id);
 
        if (ticketOptional.isPresent()) {
            Tickets ticket = ticketOptional.get();
            ticket.setResponseTxt(ticketDetails.getResponseTxt());
            ticket.setResponseAt(LocalDateTime.now());
            ticketService.updateTicket(id, ticketDetails);
            return ResponseEntity.ok(ticket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
 
 
}
 