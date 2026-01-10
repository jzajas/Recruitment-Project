package com.jzajas.task.service;

import com.jzajas.task.dto.CampaignCreationDTO;
import com.jzajas.task.dto.CampaignReturnDTO;

import java.util.List;

public interface CampaignService {
    String createCampaign(CampaignCreationDTO dto);
    CampaignReturnDTO getCampaignById(Long id);
    List<CampaignReturnDTO> getAllCampaigns();
    String updateCampaignById(Long id, CampaignCreationDTO dto);
    String deleteCampaignById(Long campaignId, Long userId);
}
