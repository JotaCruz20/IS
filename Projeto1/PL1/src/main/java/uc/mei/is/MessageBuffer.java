package uc.mei.is;

import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.msgpack.*;
import org.msgpack.annotation.Message;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
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

    public static void main(String[] args) throws Exception {
        try {
            JSONParser parser = new JSONParser();
            //Use JSONObject for simple JSON and JSONArray for array of JSON.
            JSONArray data = (JSONArray) parser.parse(
                    new FileReader("owners.json"));//path to the JSON file.

            String json = data.toJSONString();
            Gson gson = new Gson();
            Owner[] owners = gson.fromJson(json, Owner[].class);

            MessagePack msgpack = new MessagePack();
            // Serialize
            byte[] bytes = msgpack.write(owners);
            OutputStream outputStream = new FileOutputStream("message.bin");
            outputStream.write(bytes);
            // Deserialize
                Owner[] dst = msgpack.read(bytes, Owner[].class);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


    }
}
