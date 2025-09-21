package INAF.LybraSys.Emprunt.application.ServiceImpl;

import INAF.LybraSys.Emprunt.api.Dto.EmpruntRequestDto;
import INAF.LybraSys.Emprunt.api.Dto.EmpruntResponseDto;
import INAF.LybraSys.Emprunt.application.Service.EmpruntService;
import INAF.LybraSys.Emprunt.domain.EmpruntModel;
import INAF.LybraSys.Emprunt.infrastructure.EmpruntRepository;
import INAF.LybraSys.Exemplaire.domain.ExemplaireModel;
import INAF.LybraSys.Exemplaire.infrastructure.ExemplaireRepository;
import INAF.LybraSys.Student.Domain.StudentModel;
import INAF.LybraSys.Student.Infrastructure.StudentRepository;
import INAF.LybraSys.User.Domain.UsersModel;
import INAF.LybraSys.User.Infrastructure.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmpruntServiceImpl implements EmpruntService {
    private final EmpruntRepository empruntRepository;
    private final ExemplaireRepository exemplaireRepository;
    private final UsersRepository usersRepository;
    private final StudentRepository studentRepository;

    public EmpruntServiceImpl(EmpruntRepository empruntRepository,
                              ExemplaireRepository exemplaireRepository,
                              UsersRepository usersRepository,
                              StudentRepository studentRepository) {
        this.empruntRepository = empruntRepository;
        this.exemplaireRepository = exemplaireRepository;
        this.usersRepository = usersRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public EmpruntResponseDto createEmprunt(EmpruntRequestDto dto) {
        UsersModel user = usersRepository.findById(dto.idUser())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        ExemplaireModel exemplaire = exemplaireRepository.findById(dto.exemplaireId())
                .orElseThrow(() -> new RuntimeException("Exemplaire introuvable"));

        StudentModel student = null;
        if (dto.idStudent() != null) {
            student = studentRepository.findById(dto.idStudent())
                    .orElseThrow(() -> new RuntimeException("Étudiant introuvable"));
        }

        EmpruntModel emprunt = new EmpruntModel();
        emprunt.setUser(user);
        emprunt.setExemplaire(exemplaire);
        emprunt.setStudent(student);
        emprunt.setDateEmprunt(dto.dateEmprunt());
        emprunt.setDateRetourPrevu(dto.dateRetourPrevu());
        emprunt.setDateRetourEffectif(dto.dateRetourEffectif());
        emprunt.setStatut(dto.statut());

        EmpruntModel saved = empruntRepository.save(emprunt);
        return toDto(saved);
    }

    @Override
    public List<EmpruntResponseDto> getEmprunt() {
        return empruntRepository.findAll()
                .stream()
                .map(e -> new EmpruntResponseDto(
                        e.getIdEmprunt(),
                        e.getUser() != null ? e.getUser().getUsername() : null,
                        e.getExemplaire() != null ? e.getExemplaire().getCodeExemplaire() : null,
                        e.getDateEmprunt(),
                        e.getDateRetourPrevu(),
                        e.getDateRetourEffectif(),
                        e.getStatut(),
                        e.getStudent() != null ? e.getStudent().getName() : null,
                        e.getStudent() != null ? e.getStudent().getIdStudent() : null
                ))

                .collect(Collectors.toList());
    }

    @Override
    public EmpruntResponseDto updateEmprunt(int id, EmpruntRequestDto dto) {
        EmpruntModel emprunt = empruntRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emprunt introuvable avec l'ID : " + id));

        UsersModel user = usersRepository.findById(dto.idUser())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'ID : " + dto.idUser()));
        ExemplaireModel exemplaire = exemplaireRepository.findById(dto.exemplaireId())
                .orElseThrow(() -> new RuntimeException("Exemplaire introuvable avec l'ID : " + dto.exemplaireId()));

        StudentModel student = null;
        if (dto.idStudent() != null) {
            student = studentRepository.findById(dto.idStudent())
                    .orElseThrow(() -> new RuntimeException("Étudiant introuvable avec l'ID : " + dto.idStudent()));
        }

        emprunt.setUser(user);
        emprunt.setExemplaire(exemplaire);
        emprunt.setStudent(student);
        emprunt.setDateEmprunt(dto.dateEmprunt());
        emprunt.setDateRetourPrevu(dto.dateRetourPrevu());
        emprunt.setDateRetourEffectif(dto.dateRetourEffectif());
        emprunt.setStatut(dto.statut());

        EmpruntModel updated = empruntRepository.save(emprunt);
        return toDto(updated);
    }

    @Override
    public boolean deleteEmprunt(int id) {
        if (!empruntRepository.existsById(id)) return false;
        empruntRepository.deleteById(id);
        return true;
    }

    private EmpruntResponseDto toDto(EmpruntModel e) {
        return new EmpruntResponseDto(
                e.getIdEmprunt(),
                e.getUser() != null ? e.getUser().getUsername() : null,
                e.getExemplaire() != null ? e.getExemplaire().getCodeExemplaire() : null,
                e.getDateEmprunt(),
                e.getDateRetourPrevu(),
                e.getDateRetourEffectif(),
                e.getStatut(),
                e.getStudent() != null ? e.getStudent().getName() : null,
                e.getStudent() != null ? e.getStudent().getIdStudent() : null
        );
    }

}
