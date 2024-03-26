package thigk.ntu63130803.vuminhnga_qlsinhvien.service;
/*
 * @created 26/03/2024 - 15:38
 * @project VuMinhNga_QLSinhVien
 * @author TaosDev
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thigk.ntu63130803.vuminhnga_qlsinhvien.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
   private List<Student> students = new ArrayList<>();

//   public Page<Student> getAllStudents(Pageable pageable) {
//      int start = (int) pageable.getOffset();
//      int end = Math.min((start + pageable.getPageSize()), students.size());
//      return new PageImpl<>(students.subList(start, end), pageable, students.size());
//   }

   public void initStudents() {
      for (int i = 1; i <= 10; i++) {
         Student student = new Student();
         student.setStudentId("S" + String.format("%03d", i));
         student.setName("Student " + i);
         double gpa = 3.5 + Math.random() * 1.5;
         String formattedGpa = String.format("%.2f", gpa);
         student.setGpa(Double.parseDouble(formattedGpa));
         students.add(student);
      }
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
//   public List<Student> getAllStudents(int page, int size) {
//      int start = page * size;
//      int end = Math.min(start + size, students.size());
//      return students.subList(start, end);
//   }

   public Student addStudent(Student student) {
      students.add(student);
      return student;
   }
}
