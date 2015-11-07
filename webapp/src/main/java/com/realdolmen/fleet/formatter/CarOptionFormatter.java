package com.realdolmen.fleet.formatter;

import com.realdolmen.fleet.model.CarOption;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by JVDAX31 on 29/10/2015.
 */
public class CarOptionFormatter implements Formatter<CarOption> {
    @Override
    public CarOption parse(String id, Locale locale) throws ParseException {
        CarOption carOption = new CarOption();
        carOption.setId(Long.valueOf(id));
        return carOption;
    }

    @Override
    public String print(CarOption carOption, Locale locale) {
        return String.valueOf(carOption.getId());
    }
}
