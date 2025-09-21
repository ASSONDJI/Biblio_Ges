package INAF.LybraSys.Student.application.ServiceImpl;

import INAF.LybraSys.Emprunt.infrastructure.EmpruntRepository;
import INAF.LybraSys.Student.Domain.StudentModel;
import INAF.LybraSys.Student.Infrastructure.StudentRepository;
import INAF.LybraSys.Student.api.Dto.StudentRequestDto;
import INAF.LybraSys.Student.api.Dto.StudentResponseDto;
import INAF.LybraSys.Student.application.Service.StudentService;
import INAF.LybraSys.User.Domain.UsersModel;
import INAF.LybraSys.User.Infrastructure.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional

public class StudentServiceImpl implements StudentService {

    private  final StudentRepository studentRepository;
    private final UsersRepository usersRepository;


    public StudentServiceImpl(StudentRepository studentRepository, UsersRepository usersRepository, EmpruntRepository empruntRepository) {
        this.studentRepository = studentRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {
        StudentModel student = toEntity(studentRequestDto);
        StudentModel saved = studentRepository.save(student);
        return toDto(saved);
    }

    @Override
    public List<StudentResponseDto> getStudent() {{
            return studentRepository.findAll()
                    .stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
        }

    }

    @Override
    public StudentResponseDto updateStudent(int id, StudentRequestDto studentRequestDto) {
        StudentModel student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student introuvable"));

        student.setName(studentRequestDto.name());
        student.setFirstname(studentRequestDto.firstname());
        student.setEmail(studentRequestDto.email());
        student.setPhoneNumber(studentRequestDto.phoneNumber());
        student.setMajor(studentRequestDto.major());
        student.setDepartment(studentRequestDto.department());
        student.setFaculty(studentRequestDto.faculty());

        if (studentRequestDto.idUser() != null) {
            UsersModel user = usersRepository.findById(studentRequestDto.idUser())
                    .orElseThrow(() -> new RuntimeException("User introuvable"));
            student.setUser(user);
        }

        StudentModel updated = studentRepository.save(student);
        return toDto(updated);

    }

    @Override
    public boolean deleteStudent(int id) {
        if (!studentRepository.existsById(id)) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

    private StudentModel toEntity(StudentRequestDto dto) {
        StudentModel student = new StudentModel();
        student.setName(dto.name());
        student.setFirstname(dto.firstname());
        student.setEmail(dto.email());
        student.setPhoneNumber(dto.phoneNumber());
        student.setMajor(dto.major());
        student.setDepartment(dto.department());
        student.setFaculty(dto.faculty());

        if (dto.idUser() != null) {
            UsersModel user = usersRepository.findById(dto.idUser())
                    .orElseThrow(() -> new RuntimeException("User introuvable"));
            student.setUser(user);
        }

        return student;
    }

    // Convert entity en DTO
    private StudentResponseDto toDto(StudentModel student) {
        Integer idUser = student.getUser() != null ? student.getUser().getIdUser() : null;
        String username = student.getUser() != null ? student.getUser().getUsername() : null;

        return new StudentResponseDto(
                student.getIdStudent(),
                student.getName(),
                student.getFirstname(),
                student.getEmail(),
                student.getPhoneNumber(),
                student.getMajor(),
                student.getDepartment(),
                student.getFaculty(),
                idUser,
                username
        );
    }
}
