package INAF.LybraSys.Emprunt.api;

import INAF.LybraSys.Emprunt.api.Dto.EmpruntRequestDto;
import INAF.LybraSys.Emprunt.api.Dto.EmpruntResponseDto;
import INAF.LybraSys.Emprunt.application.Service.EmpruntService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprunt")

public class EmpruntController {

    private final EmpruntService empruntService;

    public EmpruntController(EmpruntService empruntService) {
        this.empruntService = empruntService;
    }

    @PostMapping
    public ResponseEntity<EmpruntResponseDto> createEmprunt(@RequestBody EmpruntRequestDto empruntRequestDto){
        EmpruntResponseDto saved = empruntService.createEmprunt(empruntRequestDto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<EmpruntResponseDto>> getAllEmprunt() {
        List<EmpruntResponseDto> emprunts = empruntService.getEmprunt();
        return ResponseEntity.ok(emprunts);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EmpruntResponseDto> updateEmprunt(@PathVariable int id, @RequestBody EmpruntRequestDto empruntRequestDto){
        EmpruntResponseDto updated = empruntService.updateEmprunt(id, empruntRequestDto);
        return  ResponseEntity.ok(updated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEmprunt(@PathVariable int id){
        Boolean deleted = empruntService.deleteEmprunt(id);
        return ResponseEntity.ok(deleted);
    }
}
