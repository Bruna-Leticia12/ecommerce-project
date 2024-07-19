package com.ecommerce.project.ecommerce.controllers;

import com.ecommerce.project.ecommerce.dto.ReportDTO;
import com.ecommerce.project.ecommerce.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/reports")
public class ReportController {

    @Autowired
    private ReportService service;

    @GetMapping(value = "/month-report/{year}/{month}")
    public ResponseEntity<ReportDTO> monthReport(@PathVariable Integer year, @PathVariable Integer month) {
        ReportDTO dto = service.monthReport(year, month);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/week-report/{year}/{month}/{day}")
    public ResponseEntity<ReportDTO> weekReport(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day) {
        ReportDTO dto = service.weekReport(year, month, day);
        return ResponseEntity.ok().body(dto);
    }
}