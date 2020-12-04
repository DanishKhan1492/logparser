package com.elixirtask.parselogs.service.impl;

import com.elixirtask.parselogs.dao.LogParserDao;
import com.elixirtask.parselogs.dto.LogDto;
import com.elixirtask.parselogs.entity.LogEntity;
import com.elixirtask.parselogs.service.LogParserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Muhammad Danish Khan
 * @created 4/12/20 - 12:20 AM
 */

@Service
public class LogParserServiceImpl implements LogParserService {

    @Autowired
    private LogParserDao logParserDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveLogs(List<LogDto> logs) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setSkipNullEnabled(true);
        List<LogEntity> logEntities = logs.stream().map(log -> modelMapper.map(log, LogEntity.class)).collect(Collectors.toList());
        logParserDao.saveAll(logEntities);
    }

    @Override
    public List<LogDto> getLogByDate(String date){
        List<LogEntity> logEntities = logParserDao.findLogEntitiesByDate(date);
        return logEntities.stream().map(logEntity -> modelMapper.map(logEntity, LogDto.class)).collect(Collectors.toList());
    }
}
