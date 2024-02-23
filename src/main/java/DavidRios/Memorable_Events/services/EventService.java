package DavidRios.Memorable_Events.services;

import DavidRios.Memorable_Events.DTOs.NewEvent;
import DavidRios.Memorable_Events.DTOs.NewUser;
import DavidRios.Memorable_Events.entities.Event;
import DavidRios.Memorable_Events.entities.User;
import DavidRios.Memorable_Events.exceptions.BadRequestException;
import DavidRios.Memorable_Events.exceptions.NotFoundException;
import DavidRios.Memorable_Events.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserService userService;


    public Page<Event> getEvents(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return eventRepository.findAll(pageable);
    }


    public Event findById(UUID id) {
        return eventRepository.findById(id).orElseThrow(() -> new NotFoundException("Event identified as: " + id + " was not found."));
    }

    public Event saveEvent(NewEvent newEvent) {
        return new Event(newEvent.title(), newEvent.description(), newEvent.date(), newEvent.location(), newEvent.capacity());
    }

    public Event update(UUID id, Event modifiedEvent) {
        Event found = this.findById(id);
        found.setTitle(modifiedEvent.getTitle());
        found.setDescription(modifiedEvent.getDescription());
        found.setDate(modifiedEvent.getDate());
        found.setLocation(modifiedEvent.getLocation());
        found.setCapacity(modifiedEvent.getCapacity());
        return eventRepository.save(found);
    }

    public Event addParticipant(UUID eventId, UUID participantId) {
        Event event = this.findById(eventId);
        User user = userService.findById(participantId);
        event.setParticipant(user);
        return event;
    }

    public void removeParticipant(UUID eventId, UUID participantId) {
        Event event = this.findById(eventId);
        User user = userService.findById(participantId);
        event.removeParticipant(user);
    }

    public void delete(UUID id) {
        Event found = this.findById(id);
        eventRepository.delete(found);
    }
}
