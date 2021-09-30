package uc.mei.is;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;


public class App 
{
    public static void main( String[] args ) throws Exception {
        try {
            JSONParser parser = new JSONParser();
            //Use JSONObject for simple JSON and JSONArray for array of JSON.
            JSONArray data = (JSONArray) parser.parse(
                    new FileReader("owners.json"));//path to the JSON file.

            String json = data.toJSONString();
            Gson gson = new Gson();
            Owner[] owners = gson.fromJson(json, Owner[].class);

            JAXBContext contextObj = JAXBContext.newInstance(Owner.class);

            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


            marshallerObj.marshal(owners, new FileOutputStream("pet.xml"));

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


    }
}
