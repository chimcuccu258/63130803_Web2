package thigk.ntu63130803.vuminhnga_qlsinhvien.service;
/*
 * @created 26/03/2024 - 15:38
 * @project VuMinhNga_QLSinhVien
 * @author TaosDev
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thigk.ntu63130803.vuminhnga_qlsinhvien.models.Student;
import thigk.ntu63130803.vuminhnga_qlsinhvien.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

   @Autowired
   private StudentRepository studentRepository;
   public void initStudents() {
      List<Student> students = new ArrayList<>();
      for (int i = 1; i <= 20; i++) {
         Student student = new Student();
         student.setStudentId("S" + String.format("%03d", i));
         student.setName("Student " + i);
         student.setGpa(3.5 + Math.random() * 1.5);
         students.add(student);
      }
      studentRepository.saveAll(students);
   }

   public Optional<Student> findStudentById(String id) {
      return studentRepository.findById(id);
   }

   public List<Student> getAllStudents() {
      return studentRepository.findAll();
   }

   public Student addStudent(Student student) {
      return studentRepository.save(student);
   }
}
