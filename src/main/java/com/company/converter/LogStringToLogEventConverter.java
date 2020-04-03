package com.company.converter;

import com.company.model.LogEvent;

import java.time.LocalDateTime;
import java.util.StringTokenizer;

//format: "2019-01-01T00:12:01.001;ERROR; Ошибка 1"
public class LogStringToLogEventConverter implements Converter<String, LogEvent> {

    @Override
    public LogEvent convert(String logString) {
        StringTokenizer st = new StringTokenizer(logString, ";");
        LogEvent logEvent = new LogEvent();
        while (st.hasMoreTokens()) {
            LocalDateTime dateTime = LocalDateTime.parse(st.nextToken()
                                                           .trim());
            int hour = dateTime.getHour();
            logEvent.setDateEvent(dateTime.toLocalDate())
                    .setTypeEvent(st.nextToken()
                                    .trim())
                    .setBeginHourPeriod(hour)
                    .setEndHourPeriod(hour == 23 ? 0 : hour + 1);
            st.nextToken();
        }
        return logEvent;
    }
}
