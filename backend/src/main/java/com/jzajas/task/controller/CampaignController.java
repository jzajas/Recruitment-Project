package com.jzajas.task.controller;

import com.jzajas.task.dto.CampaignCreationDTO;
import com.jzajas.task.dto.CampaignReturnDTO;
import com.jzajas.task.service.CampaignService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/campaigns")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CampaignController {

    private final CampaignService campaignService;

    @PostMapping
    public ResponseEntity<CampaignReturnDTO> createCampaign(@Valid @RequestBody CampaignCreationDTO dto) {
        CampaignReturnDTO created = campaignService.createCampaign(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampaignReturnDTO> getSingleCampaign(@PathVariable Long id) {
        CampaignReturnDTO found = campaignService.getCampaignById(id);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Page<CampaignReturnDTO>> getAllCampaigns(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(10) int size,
            @RequestParam(defaultValue = "fund") String sort
    ) {
        Page<CampaignReturnDTO> returnedPage = campaignService.getAllCampaigns(page, size, sort);
        return new ResponseEntity<>(returnedPage, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampaignReturnDTO> updateCampaign(
            @PathVariable Long id,
            @Valid @RequestBody CampaignCreationDTO dto
    ) {
        CampaignReturnDTO updated = campaignService.updateCampaignById(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCampaign(@PathVariable Long id) {
        String message = campaignService.deleteCampaignById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
