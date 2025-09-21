package INAF.LybraSys.Student.api.Dto;

public record StudentRequestDto(
        String name,
        String firstname,
        String email,
        String phoneNumber,
        String major,
        String department,
        String faculty,
        Integer idUser
) {}
