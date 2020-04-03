package com.company.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Objects;

@Data
@Accessors(chain = true)
public class LogEvent {
    private LocalDate dateEvent;
    private String typeEvent;
    private int beginHourPeriod;
    private int endHourPeriod;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogEvent logEvent = (LogEvent) o;
        return beginHourPeriod == logEvent.beginHourPeriod &&
                endHourPeriod == logEvent.endHourPeriod &&
                Objects.equals(dateEvent, logEvent.dateEvent) &&
                Objects.equals(typeEvent, logEvent.typeEvent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateEvent, typeEvent, beginHourPeriod, endHourPeriod);
    }
}
