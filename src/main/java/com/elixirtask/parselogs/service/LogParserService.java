package com.elixirtask.parselogs.service;

import com.elixirtask.parselogs.dto.LogDto;

import java.util.List;

/**
 * @author Muhammad Danish Khan
 * @created 4/12/20 - 12:04 AM
 */
public interface LogParserService {

    public void saveLogs(List<LogDto> logs);

    public List<LogDto> getLogByDate(String date);
}
