package com.realdolmen.fleet.persist;

import com.realdolmen.fleet.model.HistoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 10/11/2015.
 *
 * @author Robin D'Haese
 */
@Repository
public interface HistoryRecordRepository extends JpaRepository<HistoryRecord, Long>{
}
