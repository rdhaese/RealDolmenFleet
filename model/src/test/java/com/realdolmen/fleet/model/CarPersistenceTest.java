package com.realdolmen.fleet.model;

import java.util.List;

/**
 * Created on 28/10/2015.
 *
 * @author Robin D'Haese
 */
public class CarPersistenceTest extends AbstractPersistenceTest {


    private List<Pack> extraPacks;
    private List<CarOption> extraOptions;

    @Before

    public void setUp(){
        initExtraPacks();
        initExtraOptions();
    }
    //TODO test persisting and constraints

}
