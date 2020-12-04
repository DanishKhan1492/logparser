package com.elixirtask.parselogs.util;

import com.elixirtask.parselogs.dto.LogDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Muhammad Danish Khan
 * @created 4/12/20 - 12:25 AM
 */
public class LogParserUtil {

    private LogParserUtil() {
    }

    public static List<LogDto> getLogs(MultipartFile logFile) throws IOException, ParseException {
        List<LogDto> logList = new ArrayList<>();

        final String URI_REGEX = "\\/\\w+(\\/?\\/?)[^\\s]+";
        final String REGEX_FOR_OTHER_INFORMATIONS = "^(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}).*(\\d{2}\\/[A-Za-z]{3}\\/\\d{4}:\\d{2}:\\d{2}:\\d{2}).*(\"[A-Z]{3,5}).*((?<=\\s)\\d+(?=\\s))";

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(logFile.getInputStream()))) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                LogDto logDto = null;
                Pattern pattern = Pattern.compile(REGEX_FOR_OTHER_INFORMATIONS);
                Matcher matcher = pattern.matcher(line);

                if(matcher.matches()){
                    logDto = new LogDto(matcher.group(1), new SimpleDateFormat("dd/MMM/yyyy").parse(matcher.group(2).toUpperCase()), matcher.group(3), Integer.valueOf(matcher.group(4)));
                }

                pattern = pattern.compile(URI_REGEX);
                matcher = pattern.matcher(line);

                if(matcher.matches()){
                    logDto.setResourceUrl(matcher.group(0));
                }

                logList.add(logDto);
            }
        }
        return logList;
    }

    private static void saveLogFile(MultipartFile logFile) throws IOException {
        Files.copy(logFile.getInputStream(), Paths.get(System.getProperty("user.home") + "/imported_logs/").resolve(logFile.getOriginalFilename()));
    }


}
