package com.techzen.techlearn.controller;

import com.techzen.techlearn.dto.request.TeacherCalendarRequestDTO;
import com.techzen.techlearn.dto.response.ResponseData;
import com.techzen.techlearn.dto.response.TeacherCalendarResponseDTO;
import com.techzen.techlearn.enums.ErrorCode;
import com.techzen.techlearn.service.TeacherCalendarService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/teacher-calendar")
public class TeacherCalendarController {

    TeacherCalendarService teacherCalendarService;

    @PostMapping
    public ResponseData<?> addTeacherCalendar(@RequestBody @Valid TeacherCalendarRequestDTO request) {
        return ResponseData.builder()
                .status(HttpStatus.OK.value())
                .code(ErrorCode.ADD_SUCCESSFUL.getCode())
                .message(ErrorCode.ADD_SUCCESSFUL.getMessage())
                .result(teacherCalendarService.addTeacherCalendar(request))
                .build();
    }

//    @GetMapping
//    public ResponseData<?> getSchedule() {
//        return ResponseData.builder()
//                .status(HttpStatus.OK.value())
//                .code(ErrorCode.GET_SUCCESSFUL.getCode())
//                .message(ErrorCode.GET_SUCCESSFUL.getMessage())
//                .result(teacherCalendarService.findAll())
//                .build();
//    }

    @GetMapping("/")
    public List<TeacherCalendarResponseDTO> getSchedule(@RequestParam("StartDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                        @RequestParam("EndDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return teacherCalendarService.findAll();
    }

    @GetMapping("/students")
    public ResponseData<?> getAppointmentsByTechnicalAndTeacher(@RequestParam String nameTechnical,
                                                                @RequestParam String nameTeacher) {
        return ResponseData.builder()
                .status(HttpStatus.OK.value())
                .code(ErrorCode.GET_SUCCESSFUL.getCode())
                .message(ErrorCode.GET_SUCCESSFUL.getMessage())
                .result(teacherCalendarService.findAppointments(nameTechnical, nameTeacher))
                .build();
    }

}
