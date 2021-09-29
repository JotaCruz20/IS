package uc.mei.is;

import org.msgpack.*;
import org.msgpack.annotation.Message;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MessageBuffer {
    @Message
    public static class Student1 {
        public String name;
        public int age;
        public long id;
    }

    @Message
    public static class Class1 {
        public List<Student1> students;
    }

    public static void main(String[] args) throws Exception {
        Student1 student = new Student1();
        student.name="Andre";
        student.age=21;
        student.id=12919;

        Student1 student2 = new Student1();
        student2.name="Ma";
        student2.age=22;
        student2.id=12918;

        Student1 student3 = new Student1();
        student3.name="Me";
        student3.age=23;
        student3.id=12917;

        List<Student1> student1s = new ArrayList<>();
        student1s.add(student3);
        student1s.add(student2);
        student1s.add(student);

        Class1 class1 = new Class1();
        class1.students = student1s;


        MessagePack msgpack = new MessagePack();
        // Serialize
        byte[] bytes = msgpack.write(class1);
        OutputStream outputStream = new FileOutputStream("message.bin");
        outputStream.write(bytes);
        // Deserialize
        Class1 dst = msgpack.read(bytes, Class1.class);
    }
}
