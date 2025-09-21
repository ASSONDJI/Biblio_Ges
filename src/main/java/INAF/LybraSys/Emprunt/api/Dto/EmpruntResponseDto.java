package INAF.LybraSys.Emprunt.api.Dto;


import java.util.Date;

public record EmpruntResponseDto(
        int idEmprunt,
        String userName,
        String exemplaireCode,
        Date dateEmprunt,
        Date dateRetourPrevu,
        Date dateRetourEffectif,
        String statut,
        String studentName,
        Integer idStudent

) {
}
