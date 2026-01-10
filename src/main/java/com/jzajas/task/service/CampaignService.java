package com.jzajas.task.service;

import com.jzajas.task.dto.CampaignCreationDTO;
import com.jzajas.task.dto.CampaignReturnDTO;
import org.springframework.data.domain.Page;

public interface CampaignService {
    CampaignReturnDTO createCampaign(CampaignCreationDTO dto);
    CampaignReturnDTO getCampaignById(Long id);
    Page<CampaignReturnDTO> getAllCampaigns(int page, int size, String sort);
    CampaignReturnDTO updateCampaignById(Long id, CampaignCreationDTO dto);
    String deleteCampaignById(Long campaignId);
}
