package INAF.LybraSys.Student.api.Dto;

public record StudentResponseDto(
        int idStudent,
        String name,
        String firstname,
        String email,
        String phoneNumber,
        String major,
        String department,
        String faculty,
        Integer idUser,
        String username
) {}
