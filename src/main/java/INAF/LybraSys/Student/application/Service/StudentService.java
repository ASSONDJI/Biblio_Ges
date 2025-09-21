package INAF.LybraSys.Student.application.Service;

import INAF.LybraSys.Student.api.Dto.StudentRequestDto;
import INAF.LybraSys.Student.api.Dto.StudentResponseDto;

import java.util.List;

public interface StudentService {

    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto);

    public List<StudentResponseDto> getStudent();

    public StudentResponseDto updateStudent(int id, StudentRequestDto studentRequestDto);

    public boolean deleteStudent(int id);
}
