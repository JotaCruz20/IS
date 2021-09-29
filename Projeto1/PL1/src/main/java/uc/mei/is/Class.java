package uc.mei.is;

import org.msgpack.annotation.Message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

// 1 a) e b)
// @XmlRootElement
//public class Class {
//    List<Student> students;
//
//    public Class(List<Student> students) {
//        this.students = students;
//    }
//
//    public Class(){}
//
//    @XmlElement
//    public List<Student> getStudents() {
//        return students;
//    }
//
//    public void setStudents(List<Student> students) {
//        this.students = students;
//    }
//}

// 1 c)
//@XmlRootElement(namespace="http://www.dei.uc.pt/EAI")
//public class Class {
//    List<Student> students;
//
//    public Class(List<Student> students) {
//        this.students = students;
//    }
//
//    public Class(){}
//
//    @XmlElement
//    public List<Student> getStudents() {
//        return students;
//    }
//
//    public void setStudents(List<Student> students) {
//        this.students = students;
//    }
//}

@Message
public class Class {
    public List<Student> students;

    public Class(List<Student> students) {
        this.students = students;
    }
}