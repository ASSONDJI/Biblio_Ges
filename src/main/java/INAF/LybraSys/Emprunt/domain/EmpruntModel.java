package INAF.LybraSys.Emprunt.domain;


import INAF.LybraSys.Exemplaire.domain.ExemplaireModel;
import INAF.LybraSys.Student.Domain.StudentModel;
import INAF.LybraSys.User.Domain.UsersModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "emprunt")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpruntModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmprunt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UsersModel user;

    @ManyToOne
    @JoinColumn(name = "exemplaire_id", nullable = false)
    private ExemplaireModel exemplaire;

    @Temporal(TemporalType.DATE)
    private Date dateEmprunt;

    @Temporal(TemporalType.DATE)
    private Date dateRetourPrevu;

    @Temporal(TemporalType.DATE)
    private Date dateRetourEffectif;

    private String statut;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentModel student;


}
