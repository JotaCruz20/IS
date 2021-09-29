package uc.mei.is;
import org.msgpack.annotation.Message;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


// 1 a)
// @XmlRootElement
//public class Student {
//    String name;
//    int age;
//
//    public Student(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//
//    public Student(){}
//
//    @XmlElement
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @XmlElement
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//}

// 1 b)
//@XmlRootElement
//public class Student {
//    String name;
//    int age;
//    long id;
//
//    public Student(String name, int age, long id) {
//        this.name = name;
//        this.age = age;
//        this.id = id;
//    }
//
//    public Student(){}
//
//    @XmlElement
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @XmlElement
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    @XmlAttribute
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//}

// 1 c)
//@XmlRootElement(namespace="")
//public class Student {
//    String name;
//    int age;
//    long id;
//
//    public Student(String name, int age, long id) {
//        this.name = name;
//        this.age = age;
//        this.id = id;
//    }
//
//    public Student(){}
//
//    @XmlElement
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @XmlElement
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    @XmlAttribute
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//}

@Message
public class Student {
    public String name;
    public int age;
    public long id;

    public Student(String name, int age, long id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }
}