package com.realdolmen.fleet.model;

import com.realdolmen.fleet.enums.EmployeeType;
import org.junit.*;

import javax.validation.ConstraintViolationException;
import java.util.Date;

/**
 * Created on 10/11/2015.
 * Persistence test for {@link HistoryRecord}
 * @author Robin D'Haese
 */

public class HistoryRecordPersistenceTest extends AbstractPersistenceTest{

    private Employee employee;

    @Before
    public void setUp(){
        employee =new Employee("name", "email", "password", EmployeeType.ROLE_FLEET, 4, new Date());
        em.persist(employee);
    }

    @Test
    public void canHistoryRecordBePersisted(){
        HistoryRecord historyRecord = new HistoryRecord(employee, new Date(), 1000);
        em.persist(historyRecord);
        assertNotNull(historyRecord.getId());
    }

    @Test (expected = ConstraintViolationException.class)
    public void historyRecordCannotBePersistedWithoutEmployee(){
        HistoryRecord historyRecord = new HistoryRecord(null, new Date(), 1000);
        em.persist(historyRecord);
    }

    @Test (expected = ConstraintViolationException.class)
    public void historyRecordCannotBePersistedWithoutDrivenUntil(){
        HistoryRecord historyRecord = new HistoryRecord(employee, null, 1000);
        em.persist(historyRecord);
    }

    @Test (expected = ConstraintViolationException.class)
    public void historyRecordCannotBePersistedWithoutLastKm(){
        HistoryRecord historyRecord = new HistoryRecord(employee, new Date(), null);
        em.persist(historyRecord);
    }

    @Test (expected = ConstraintViolationException.class)
    public void historyRecordCannotBePersistedWithLastKmLessThan1(){
        HistoryRecord historyRecord = new HistoryRecord(employee, new Date(), 0);
        em.persist(historyRecord);
    }

}
