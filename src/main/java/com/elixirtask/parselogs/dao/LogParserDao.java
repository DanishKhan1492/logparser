package com.elixirtask.parselogs.dao;

import com.elixirtask.parselogs.entity.LogEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Muhammad Danish Khan
 * @created 3/12/20 - 11:22 PM
 */
public interface LogParserDao extends CrudRepository<LogEntity, Integer> {

    @Query("select le from LogEntity le where tochar(le.dateTime, 'dd/MM/yyyy')=:date")
    List<LogEntity> findLogEntitiesByDate(@Param("date") String date);

}
