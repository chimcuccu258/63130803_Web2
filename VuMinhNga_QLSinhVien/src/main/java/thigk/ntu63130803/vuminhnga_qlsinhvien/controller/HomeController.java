package thigk.ntu63130803.vuminhnga_qlsinhvien.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import thigk.ntu63130803.vuminhnga_qlsinhvien.service.StudentService;

/*
 * @created 26/03/2024 - 15:01
 * @project VuMinhNga_QLSinhVien
 * @author TaosDev
 */
@Controller
public class HomeController {

   StudentService studentService = new StudentService();
   @RequestMapping("/")
   public String home() {
      return "index";
   }

   @GetMapping("/students")
   public String getStudents(Model model) {
      studentService.initStudents();
      model.addAttribute("students", studentService.getAllStudents());
      return "students";
   }



}
