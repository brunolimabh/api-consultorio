package school.sptech.projetoconsultorio.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoconsultorio.constants.DoctorConstants;
import school.sptech.projetoconsultorio.dto.doctor.DoctorMapper;
import school.sptech.projetoconsultorio.dto.doctor.DoctorRequest;
import school.sptech.projetoconsultorio.dto.doctor.DoctorRequestUpdate;
import school.sptech.projetoconsultorio.dto.doctor.DoctorResponse;
import school.sptech.projetoconsultorio.service.DoctorService;

import java.util.List;

@RestController
@RequestMapping(DoctorConstants.BASE_PATH)
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorResponse> create(
            @Valid @RequestBody DoctorRequest body){
        return ResponseEntity.created(null).body(DoctorMapper.toDto(doctorService.create(body)));
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponse>> list(){
        return ResponseEntity.ok(DoctorMapper.toDto(doctorService.list()));
    }

    @GetMapping(DoctorConstants.LIST_BY_ID_PATH)
    public ResponseEntity<DoctorResponse> listById(
            @PathVariable int id){
        return ResponseEntity.ok(DoctorMapper.toDto(doctorService.listById(id)));
    }

    @PutMapping(DoctorConstants.UPDATE_NAME_PATH)
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody DoctorRequestUpdate request){
        return ResponseEntity.ok().body(doctorService.update(id, request));
    }

    @DeleteMapping(DoctorConstants.REMOVE_PATH)
    public ResponseEntity<Void> remove(
            @PathVariable int id){
        return ResponseEntity.ok().body(doctorService.remove(id));
    }
}
