package com.realdolmen.fleet.sqlgenerator;

import java.io.*;

import java.io.IOException;

import com.realdolmen.fleet.enums.CarType;
import com.realdolmen.fleet.enums.FuelType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created on 5/11/2015.
 *
 * @author Robin D'Haese
 */
public class CarSqlGenerator {

    private static List<List<String>> packs = new ArrayList<>();

    private static PrintWriter writer;
    public static void main(String... args) throws IOException {
        writer = new PrintWriter("generated-sql.txt", "UTF-8");
        // Open the file
        readPacks();
        FileInputStream fstream = new FileInputStream("C:/cars.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        int id = 10;
        int carCounter = 0;
        //Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            System.out.println("car processed");
            //Process line
            strLine = strLine.replace(",", ".");
            process(strLine, id, carCounter);
            id++;
            carCounter++;
        }
        //Close the input stream
        br.close();
    }

    private static void readPacks() throws IOException {
        FileInputStream fstream = new FileInputStream("C:/options.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        List<String> pack = null;
        while ((strLine = br.readLine()) != null) {
            strLine.trim();
           if ("0".equals(strLine)){
               if (pack != null){
                   packs.add(pack);
               }
              pack = new ArrayList<>();
           }else {
               pack.add(strLine.replace("'", "''"));
           }
        }
        br.close();
    }

    private static void process(String strLine, int id, int carCounter) {
        String[] s = strLine.split(";");
        processPack(carCounter);
        StringBuilder carQuery = new StringBuilder("INSERT INTO car (id,  version, category, emission, fiscalhp, fuel_type, brand, model, pk, delivery_time, ideal_km, max_km, list_price, benefit, amount_upgrade, amount_downgrade, car_type, base_pack_id) VALUES(");
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
        carQuery.append(",'");
        carQuery.append(carCounter);
        carQuery.append("');");
        writer.println(carQuery.toString());
        StringBuilder imgQuery = new StringBuilder("INSERT INTO car_pictures(Car_id, pictures) VALUES('");
        imgQuery.append(id);
        imgQuery.append("','");
        imgQuery.append(s[s.length - 1]);
        imgQuery.append("');");
        writer.println(imgQuery.toString());
    }

    private static void processPack(int carCounter) {
        List<String> pack = packs.get(carCounter);
        String packName = pack.get(0);
        writer.println("INSERT INTO pack(name, price, version, id) VALUES ('" + packName + "', '500', '0', '" + carCounter + "');");
        processOptions(pack.subList(1, pack.size() - 1), carCounter);

    }
    static int optionCounter = 1;
    private static void processOptions(List<String> strings, int carCounter) {
        for (String option : strings){
            writer.println("INSERT INTO car_option(version, name ,id) VALUES ('0', '"+ option +"','" + optionCounter + "');");
            writer.println("INSERT INTO pack_car_options(pack_id, car_options_id) VALUES ('"+ carCounter +"', '"+ optionCounter +"');");
            optionCounter++;
        }
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