package com.project.planner.trip;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "trips")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String destination;

    @Column(name = "starts_at", nullable = false)
    private LocalDateTime starstAt;

    @Column(name = "ends_at", nullable = false)
    private LocalDateTime endsAt;

    @Column(name = "is_confirmed", nullable = false)
    private Boolean isConfirmed;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "owner_email", nullable = false)
    private String ownerEmail;

    public Trip(TripRequestPayload data) {

        this.destination = data.destination();
        this.isConfirmed = false;
        this.ownerEmail = data.owner_email();
        this.ownerName = data.owner_name();

        LocalDateTime startDateTime = LocalDateTime.parse(data.starts_at(), DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime endDateTime = LocalDateTime.parse(data.ends_at(), DateTimeFormatter.ISO_DATE_TIME);

       // this.startAt = startDateTime;
        // this.endsAt = endDateTime;

        if (endDateTime.isBefore(startDateTime)) {
            throw new IllegalArgumentException("A data de término deve ser maior ou igual à data de início.");
        }
        this.starstAt = startDateTime;
        this.endsAt = endDateTime;

    }
}