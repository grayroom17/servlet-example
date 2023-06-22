package com.servlet.example.http.dto;

import java.util.Objects;

public class FlightDto {

    private Long id;
    private String description;

    public FlightDto(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FlightDto flightDto = (FlightDto) o;
        return Objects.equals(id, flightDto.id) && Objects.equals(description, flightDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "FlightDto{" +
               "id=" + id +
               ", description='" + description + '\'' +
               '}';
    }
}
