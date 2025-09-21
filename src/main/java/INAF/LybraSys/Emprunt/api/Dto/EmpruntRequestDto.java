package INAF.LybraSys.Emprunt.api.Dto;

import java.util.Date;
import java.util.List;

public record EmpruntRequestDto(
        int idUser,
        int exemplaireId,
        Integer idStudent,
        Date dateEmprunt,
        Date dateRetourPrevu,
        Date dateRetourEffectif,
        String statut
) {
}
