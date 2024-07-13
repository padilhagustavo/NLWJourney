package com.project.planner.link;

import com.project.planner.activity.ActivityData;
import com.project.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LinkService {

    @Autowired
    private LinkRepository repository;

    public LinkResponse registerLink(LinkRequestPayload payload, Trip trip) {
        Link newLink = new Link(payload.title(), payload.url(), trip);

        this.repository.save(newLink);

        if (newLink.getTrip().getIsConfirmed()) {
        return new LinkResponse(newLink.getId());
        }

        throw new NullPointerException();

    }
    public List<LinkData> getAllLinksFromTrip(UUID tripId) {
        return this.repository.findByTripId(tripId).stream().map(link -> new LinkData(link.getId(), link.getTitle(), link.getUrl())).toList();
    }
}
