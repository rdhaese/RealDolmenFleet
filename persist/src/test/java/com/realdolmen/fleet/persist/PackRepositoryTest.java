package com.realdolmen.fleet.persist;

import com.realdolmen.fleet.model.CarOption;
import com.realdolmen.fleet.model.Pack;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 5/11/2015.
 * Test class for {@link PackRepository}
 * @author Robin D'Haese
 */
public class PackRepositoryTest extends AbstractRepositoryTest {

    @Before
    public void setUp() {
        deleteAll();
        packRepository.save(createPack("name1", 500D));
        packRepository.save(createPack("name2", 500D));
        packRepository.save(createPack("name3", 500D));
        packRepository.save(createPack("name4", 500D));
        packRepository.flush();
    }

    private Pack createPack(String name, double price) {
        return new Pack(name, price, createCarOptions());
    }

    private List<CarOption> createCarOptions() {
        List<CarOption> carOptions = new ArrayList<>();
        carOptions.add(new CarOption("description1", "name1"));
        carOptions.add(new CarOption("description2", "name2"));
        carOptions.add(new CarOption("description3", "name3"));
        for (CarOption carOption : carOptions){
            carOptionsRepository.save(carOption);
        }
        return carOptions;
    }

    @Test
    public void canPackBeFoundOnName() {
        assertNotNull(packRepository.findByName("name2"));
        assertNotNull(packRepository.findByName("name3"));
        assertNotNull(packRepository.findByName("name4"));
        assertNotNull(packRepository.findByName("name1"));
        assertNull(packRepository.findByName("unknown name"));
    }

    @Test
    public void canAllPacksBeFoundExceptWithGivenName() {
        List<Pack> packs = packRepository.findByNameNot("name1");
        assertEquals(3, packs.size());
        for (Pack pack : packs){
            assertNotEquals("name1", pack.getName());
        }
        assertEquals(3, packRepository.findByNameNot("name2").size());
        assertEquals(3, packRepository.findByNameNot("name3").size());
        assertEquals(3, packRepository.findByNameNot("name4").size());
        assertEquals(4, packRepository.findByNameNot("unknown name").size());
    }

}
