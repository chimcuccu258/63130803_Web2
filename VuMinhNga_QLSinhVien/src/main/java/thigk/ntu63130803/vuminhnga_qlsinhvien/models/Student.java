package thigk.ntu63130803.vuminhnga_qlsinhvien.models;
/*
 * @created 26/03/2024 - 15:24
 * @project VuMinhNga_QLSinhVien
 * @author TaosDev
 */

public class Student {
    private String studentId;
    private String name;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    private Double gpa;

    public Student(String studentId, String name, Double gpa) {
        this.studentId = studentId;
        this.name = name;
        this.gpa = gpa;
    }
}
