package com.jzajas.task.service;

import com.jzajas.task.dto.CampaignCreationDTO;
import com.jzajas.task.dto.CampaignReturnDTO;

import java.util.List;

public interface CampaignService {
    CampaignReturnDTO createCampaign(CampaignCreationDTO dto);
    CampaignReturnDTO getCampaignById(Long id);
    List<CampaignReturnDTO> getAllCampaigns();
    CampaignReturnDTO updateCampaignById(Long id, CampaignCreationDTO dto);
    String deleteCampaignById(Long campaignId, Long userId);
}
