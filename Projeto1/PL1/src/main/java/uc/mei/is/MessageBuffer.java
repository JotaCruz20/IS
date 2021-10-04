package uc.mei.is;

import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.msgpack.*;
import org.msgpack.annotation.Message;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MessageBuffer {
    @Message
    public static class Owner{
        private long id;
        private String name;
        private String address;
        private Date birthdate;
        private String telephone;
        private ArrayList<Pet> pets;

    }

    @Message
    public static class Pet {
        private long id;
        private String name;
        private String specie;
        private String gender;
        private Date birthdate;
        private long weight;
        private String description;
    }

    public static ArrayList<Long> function(String file){
        try {
            //load from json
            JSONParser parser = new JSONParser();
            JSONArray data = (JSONArray) parser.parse(new FileReader(file));
            String json = data.toJSONString();
            Gson gson = new Gson();
            Owner[] owners = gson.fromJson(json, Owner[].class);

            //Message Pack
            MessagePack msgpack = new MessagePack();

            OutputStream outputStream = new FileOutputStream("outputs/message.bin");

            // Serialize
            long startTimeSerialize = System.currentTimeMillis();
            byte[] bytes = msgpack.write(owners);
            outputStream.write(bytes);
            long endTimeSerialize = System.currentTimeMillis();

            // Deserialize
            long startTimeDeserialize = System.currentTimeMillis();
            byte[] bytes1 = Files.readAllBytes(Paths.get("outputs/message.bin"));
            Owner[] dst = msgpack.read(bytes1, Owner[].class);
            long endTimeDeserialize = System.currentTimeMillis();

            return new ArrayList<>(Arrays.asList((endTimeSerialize-startTimeSerialize), (endTimeDeserialize-startTimeDeserialize)));

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Long> serializeTimes = new ArrayList<>();
        ArrayList<Long> deserializeTimes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Long> list = function("jsons/owners_10_1000.json");
            serializeTimes.add(list.get(0));
            deserializeTimes.add(list.get(1));
        }
        System.out.println(serializeTimes);
        System.out.println(deserializeTimes);
    }
}
