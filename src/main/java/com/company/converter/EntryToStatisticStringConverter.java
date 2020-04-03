package com.company.converter;

import com.company.model.LogEvent;

import java.util.Map;

public class EntryToStatisticStringConverter implements Converter<Map.Entry<LogEvent, Long>, String> {
    @Override
    public String convert(Map.Entry entry) {
        LogEvent logEvent = (LogEvent) entry.getKey();
        return logEvent.getDateEvent() + ", "
                + logEvent.getBeginHourPeriod() + ":00 - "
                + logEvent.getEndHourPeriod() + ":00, "
                + logEvent.getTypeEvent() + "(s): " + entry.getValue();
    }
}
