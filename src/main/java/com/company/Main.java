package com.company;

import com.company.converter.Converter;
import com.company.converter.EntryToStatisticStringConverter;
import com.company.converter.LogFilePathToLogEventConverter;
import com.company.model.LogEvent;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        Converter<Path, List<LogEvent>> toListLogEventConverter = new LogFilePathToLogEventConverter();
        Converter<Map.Entry<LogEvent, Long>, String> toStatisticStringConverter = new EntryToStatisticStringConverter();
        Comparator<Map.Entry<LogEvent, Long>> dateTimeComparator = Map.Entry.comparingByKey(Comparator.comparing(LogEvent::getDateEvent)
                                                                                                      .thenComparing(LogEvent::getBeginHourPeriod));
        Path logFolder = Paths.get("src/main/resources/myLogs");
        Path resultStatisticsFile = Paths.get("src/main/resources/statisticsResult/statistics_log");
        Files.write(resultStatisticsFile, ("").getBytes());
        Files
                .walk(logFolder)
                .filter(Files::isRegularFile)
                .map(toListLogEventConverter::convert)
                .flatMap(n -> n.stream())
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(dateTimeComparator)
                .map(toStatisticStringConverter::convert)
                .forEach(n -> Main.writeStringToFile(resultStatisticsFile, n));
    }

    private static void writeStringToFile(Path pathFile, String string) {
        try (BufferedWriter writer = Files.newBufferedWriter(pathFile, StandardOpenOption.APPEND)) {
            writer.write(string);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
