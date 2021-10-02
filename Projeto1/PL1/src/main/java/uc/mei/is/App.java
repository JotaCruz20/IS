package uc.mei.is;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class App 
{
    public static ArrayList<Long> function(String file){
        try {
            //load from json
            JSONParser parser = new JSONParser();
            JSONArray data = (JSONArray) parser.parse(new FileReader("jsons/owners.json"));

            String json = data.toJSONString();
            Gson gson = new Gson();
            Owner[] owners = gson.fromJson(json, Owner[].class);
            Owners owners1 = new Owners(owners);

            // Serialize
            long startTimeSerialize = System.currentTimeMillis();
            JAXBContext contextObj = JAXBContext.newInstance(Owners.class);
            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshallerObj.marshal(owners1, new FileOutputStream("outputs/pet.xml"));
            long endTimeSerialize = System.currentTimeMillis();

            // Deserialize
            long startTimeDeserialize = System.currentTimeMillis();
            JAXBContext contextObj1 = JAXBContext.newInstance(Owners.class);
            Unmarshaller unmarshaller = contextObj1.createUnmarshaller();
            Owners owners2 = (Owners) unmarshaller.unmarshal(new FileReader("outputs/pet.xml"));
            long endTimeDeserialize = System.currentTimeMillis();

            return new ArrayList<>(Arrays.asList((endTimeSerialize-startTimeSerialize), (endTimeDeserialize-startTimeDeserialize)));

        } catch (IOException | ParseException | JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main( String[] args ) throws Exception {
        ArrayList<Long> serializeTimes = new ArrayList<>();
        ArrayList<Long> deserializeTimes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Long> list = function("jsons/owners.json");
            serializeTimes.add(list.get(0));
            deserializeTimes.add(list.get(1));
        }
        System.out.println(serializeTimes);
        System.out.println(deserializeTimes);
    }
}
