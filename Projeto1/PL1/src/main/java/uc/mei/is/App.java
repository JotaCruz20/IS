package uc.mei.is;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;


public class App 
{
    public static void main( String[] args ) throws Exception {
        JAXBContext contextObj = JAXBContext.newInstance(Class.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Student student1 = new Student("Alberto",21,201134441110L);
        Student student2 = new Student("Patricia",22,201134441116L);
        Student student3 = new Student("Luis",21,201134441210L);


        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);

        Class newClass = new Class(students);

        marshallerObj.marshal(newClass, new FileOutputStream("employee.xml"));
    }
}
