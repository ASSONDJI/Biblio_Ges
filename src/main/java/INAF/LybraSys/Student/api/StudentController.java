package INAF.LybraSys.Student.api;

import INAF.LybraSys.Student.api.Dto.StudentRequestDto;
import INAF.LybraSys.Student.api.Dto.StudentResponseDto;
import INAF.LybraSys.Student.application.Service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@RequestBody StudentRequestDto dto) {
        StudentResponseDto createdStudent = studentService.createStudent(dto);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }




    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        List<StudentResponseDto> students = studentService.getStudent();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(
            @PathVariable int id,
            @RequestBody StudentRequestDto dto) {
        StudentResponseDto updatedStudent = studentService.updateStudent(id, dto);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable int id) {
        boolean deleted = studentService.deleteStudent(id);
        return new ResponseEntity<>(deleted, deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
