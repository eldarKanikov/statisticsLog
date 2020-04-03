package com.company.converter;

import com.company.model.LogEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilePathToLogEventConverter implements Converter<Path, List<LogEvent>> {

    private Converter<String, LogEvent> toLogEventConverter;

    public LogFilePathToLogEventConverter() {
        this.toLogEventConverter = new LogStringToLogEventConverter();
    }

    @Override
    public List<LogEvent> convert(Path logFilePath){
        try {
            return Files
                    .readAllLines(logFilePath)
                    .stream()
                    .map(toLogEventConverter::convert)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
