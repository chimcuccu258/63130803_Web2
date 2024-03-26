package thigk.ntu63130803.vuminhnga_qlsinhvien.repository;
/*
 * @created 26/03/2024 - 15:37
 * @project VuMinhNga_QLSinhVien
 * @author TaosDev
 */

import org.springframework.data.jpa.repository.JpaRepository;
import thigk.ntu63130803.vuminhnga_qlsinhvien.models.Student;

public interface StudentRepository extends JpaRepository<Student, String> {
}
