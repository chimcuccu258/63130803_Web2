package thigk.ntu63130803.vuminhnga_qlsinhvien.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * @created 26/03/2024 - 15:01
 * @project VuMinhNga_QLSinhVien
 * @author TaosDev
 */
@Controller
public class HomeController {
   @RequestMapping("/")
    public String home() {
        return "index";
    }
}
