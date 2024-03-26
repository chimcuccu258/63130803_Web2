package thigk.ntu63130803.vuminhnga_qlsinhvien.service;
/*
 * @created 26/03/2024 - 15:38
 * @project VuMinhNga_QLSinhVien
 * @author TaosDev
 */

import org.springframework.stereotype.Service;
import thigk.ntu63130803.vuminhnga_qlsinhvien.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
   private List<Student> students = new ArrayList<>();

   public void initStudents() {
      List<Student> students = new ArrayList<>();
      Student student1 = new Student("1", "Nguyen Van A", 3.5);
      Student student2 = new Student("2", "Nguyen Van B", 3.0);
      Student student3 = new Student("3", "Nguyen Van C", 2.5);
      Student student4 = new Student("4", "Nguyen Van D", 2.0);
      Student student5 = new Student("5", "Nguyen Van E", 1.5);
      students.add(student1);
      students.add(student2);
      students.add(student3);
      students.add(student4);
      students.add(student5);
      this.students = students;
   }

   public Optional<Student> findStudentById(String id) {
      for (Student student : students) {
         if (student.getStudentId().equals(id)) {
            return Optional.of(student);
         }
      }
      return Optional.empty();
   }

      public List<Student> getAllStudents() {
      return students;
   }

   public void addStudent(Student student) {
      students.add(student);
   }
}
