package school.sptech.projetoconsultorio.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoconsultorio.constants.AppointmentConstants;
import school.sptech.projetoconsultorio.dto.appointment.AppointmentMapper;
import school.sptech.projetoconsultorio.dto.appointment.AppointmentRequest;
import school.sptech.projetoconsultorio.dto.appointment.AppointmentResponse;
import school.sptech.projetoconsultorio.service.AppointmentService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(AppointmentConstants.BASE_PATH)
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponse> create(
            @Valid @RequestBody AppointmentRequest body){
        return ResponseEntity.created(null).body(AppointmentMapper.toDto(appointmentService.create(body)));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> list(){
        return ResponseEntity.ok(AppointmentMapper.toDto(appointmentService.list()));
    }

    @GetMapping(AppointmentConstants.LIST_BY_ID_PATH)
    public ResponseEntity<AppointmentResponse> listById(
            @PathVariable int id){
        return ResponseEntity.ok(AppointmentMapper.toDto(appointmentService.listById(id)));
    }

    @GetMapping(AppointmentConstants.LIST_BY_DOCTOR_PATH)
    public ResponseEntity<List<AppointmentResponse>> listByDoctor(
            @PathVariable int doctorId){
        return ResponseEntity.ok(AppointmentMapper.toDto(appointmentService.listByDoctor(doctorId)));
    }

    @GetMapping(AppointmentConstants.AVG_PRICE_PATH)
    public ResponseEntity<Double> avgPrice(){
        return ResponseEntity.ok(appointmentService.avgPrice());
    }

    @GetMapping(AppointmentConstants.SUM_PRICE_BY_DOCTOR_PATH)
    public ResponseEntity<Double> sumPriceByDoctor(
            @PathVariable int doctorId){
        return ResponseEntity.ok(appointmentService.sumPriceByDoctor(doctorId));
    }

    @GetMapping(AppointmentConstants.LIST_BY_DOCTOR_AND_DATE_PATH)
    public ResponseEntity<List<AppointmentResponse>> listByDoctorAndDate(
            @PathVariable int doctorId,
            @RequestParam LocalDate dateInitial,
            @RequestParam LocalDate dateFinal){
        return ResponseEntity.ok(AppointmentMapper.toDto((appointmentService.listByDoctorAndDate(doctorId, dateInitial, dateFinal))));
    }

    @PatchMapping(AppointmentConstants.UPDATE_NAME_PATH)
    public ResponseEntity<Void> updateName(
            @PathVariable int id,
            @RequestParam String name){
        return ResponseEntity.ok().body(appointmentService.updateName(id, name));
    }

    @DeleteMapping(AppointmentConstants.REMOVE_PATH)
    public ResponseEntity<Void> remove(
            @PathVariable int id){
        return ResponseEntity.ok().body(appointmentService.remove(id));
    }
}
