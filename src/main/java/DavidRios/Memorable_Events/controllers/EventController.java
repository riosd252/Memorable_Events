package DavidRios.Memorable_Events.controllers;

import DavidRios.Memorable_Events.entities.Event;
import DavidRios.Memorable_Events.entities.User;
import DavidRios.Memorable_Events.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public Page<Event> getAllEvents(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.eventService.getEvents(page, size, orderBy);
    }


    @GetMapping("/{id}")
    public Event findById(@PathVariable UUID id) {
        return this.eventService.findById(id);
    }

    @PatchMapping("/me/{eventUuid}")
    public Event setParticipant(@PathVariable UUID eventUuid, @AuthenticationPrincipal User currentAuthenticatedUser) {
        return eventService.addParticipant(eventUuid, currentAuthenticatedUser.getId());
    }

    @PatchMapping("/me/{eventUuid}")
    public Event removeParticipant(@PathVariable UUID eventUuid, @AuthenticationPrincipal User currentAuthenticatedUser) {
        return eventService.addParticipant(eventUuid, currentAuthenticatedUser.getId());
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Event update(@PathVariable UUID id, @RequestBody Event updatedEvent) {
        return this.eventService.update(id, updatedEvent);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.eventService.delete(id);
    }
}
