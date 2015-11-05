package com.realdolmen.fleet.sqlgenerator;

import java.io.IOException;

import java.io.IOException;

import com.realdolmen.fleet.enums.CarType;
import com.realdolmen.fleet.enums.FuelType;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created on 5/11/2015.
 *
 * @author Robin D'Haese
 */
public class CarSqlGenerator {

    public static void main(String... args) throws IOException {
        // Open the file
        FileInputStream fstream = new FileInputStream("C:/cars.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        int id = 10;
        //Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            //Process line
            strLine = strLine.replace(",", ".");
            process(strLine, id);
            id++;
        }
        //Close the input stream
        br.close();
    }

    private static void process(String strLine, int id) {
        String[] s = strLine.split(";");
        StringBuilder carQuery = new StringBuilder("INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type) VALUES(");
        carQuery.append("'").append(id).append("'").append(",").append("'").append(0).append("'");
        for (int index = 0; index < s.length - 1; index++){
            carQuery.append(",");
            carQuery.append("'");
            String toAppend = s[index].trim();
            if (index == 3){
                toAppend = fuelType(toAppend);
            } else if (index == 14){
                toAppend = carType(toAppend);
            }
            carQuery.append(toAppend);
            carQuery.append("'");
        }
        carQuery.append(");");
        System.out.println(carQuery.toString());
        StringBuilder imgQuery = new StringBuilder("INSERT INTO car_pictures(Car_id, pictures) VALUES('");
        imgQuery.append(id);
        imgQuery.append("','");
        imgQuery.append(s[s.length - 1]);
        imgQuery.append("');");
        System.out.println(imgQuery.toString());
    }

    private static String carType(String toAppend) {
        switch (toAppend){
            case "Normal":
                return CarType.NORMAL.toString();
            case "Break":
                return CarType.BREAK.toString();
            case "Monovolume":
                return CarType.MONOVOLUME.toString();
            default:
                return "FAIL";
        }
    }

    private static String fuelType(String toAppend) {
        switch (toAppend){
            case "D":
                return FuelType.DIESEL.toString();
            case "B":
                return FuelType.GASOLINE.toString();
            default:
                return "FAIL";
        }
    }

}