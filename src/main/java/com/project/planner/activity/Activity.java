package com.project.planner.activity;

import com.project.planner.trip.Trip;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "activities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "occurs_at", nullable = false)
    private LocalDateTime occursAt;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    public Activity(String title, String occurs_at, Trip trip) {
        this.title = title;
        this.occursAt = LocalDateTime.parse(occurs_at, DateTimeFormatter.ISO_DATE_TIME);
        this.trip = trip;
        validateActivityDate();
    }

    private void validateActivityDate() {
        LocalDateTime tripStart = trip.getStarstAt();
        LocalDateTime tripEnd = trip.getEndsAt();

        if (occursAt.isBefore(tripStart) || occursAt.isAfter(tripEnd)) {
            throw new IllegalArgumentException("A data da atividade deve estar dentro do período da viagem.");
        }
    }
}
