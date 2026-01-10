package com.jzajas.task.service;

import com.jzajas.task.dto.CampaignCreationDTO;
import com.jzajas.task.dto.CampaignReturnDTO;
import com.jzajas.task.model.Campaign;
import com.jzajas.task.model.User;
import com.jzajas.task.repository.CampaignRepository;
import com.jzajas.task.repository.UserRepository;
import com.jzajas.task.util.CampaignMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {
    private static final String  SUCCESSFUL_CREATION_MESSAGE = "Campaign created successfully";
    private static final String SUCCESSFUL_UPDATE_MESSAGE = "Campaign updated successfully";
    private static final String SUCCESSFUL_DELETION_MESSAGE = "Campaign deleted successfully";
    private static final String USER_NOT_FOUND_MESSAGE = "Provided user does not exists";
    private static final String CAMPAIGN_NOT_FOUND_MESSAGE = "Provided campaign does not exists or has already ended";
    private static final String INSUFFICIENT_USER_BALANCE_MESSAGE = "Insufficient user balance";
    private static final String USER_OR_CAMPAIGN_ID_INCORRECT_MESSAGE = "Either specified user or campaign are incorrect";

    private final CampaignRepository campaignRepository;
    private final UserRepository userRepository;
    private final CampaignMapper campaignMapper;


    @Override
    @Transactional
    public String createCampaign(CampaignCreationDTO dto) {
        User user = userRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND_MESSAGE));

        BigDecimal userBalance = user.getBalance();
        BigDecimal campaignFund = dto.getFund();
        if (campaignFund.compareTo(userBalance) > 0) throw new RuntimeException(INSUFFICIENT_USER_BALANCE_MESSAGE);
        user.setBalance(userBalance.subtract(campaignFund));

        Campaign campaign = campaignMapper.fromDtoToObject(dto);
        campaign.setOwner(user);

        campaignRepository.save(campaign);

        return SUCCESSFUL_CREATION_MESSAGE;
    }

    @Override
    public CampaignReturnDTO getCampaignById(Long id) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(CAMPAIGN_NOT_FOUND_MESSAGE));
        if (!campaign.getStatus()) throw new RuntimeException(CAMPAIGN_NOT_FOUND_MESSAGE);

        return campaignMapper.fromObjectToDto(campaign);
    }

    @Override
    public List<CampaignReturnDTO> getAllCampaigns() {
        return campaignRepository.findAll().stream()
                .filter(Campaign::getStatus)
                .map(object -> campaignMapper.fromObjectToDto(object))
                .toList();
    }

    @Override
    @Transactional
    public String updateCampaignById(Long campaignId, CampaignCreationDTO dto) {
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new RuntimeException(CAMPAIGN_NOT_FOUND_MESSAGE));

        Long ownerId = dto.getOwnerId();
        User user = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND_MESSAGE));


        if (!Objects.equals(ownerId, campaign.getOwner().getId())) throw new IllegalArgumentException(USER_OR_CAMPAIGN_ID_INCORRECT_MESSAGE);

        handleFundsForUser(campaign.getFund(), dto.getFund(), user);

       Campaign updatedCampaign = campaignMapper.updateCampaignFromDto(dto, campaign);

        campaignRepository.save(updatedCampaign);
        return SUCCESSFUL_UPDATE_MESSAGE;
    }

    @Override
    @Transactional
    public String deleteCampaignById(Long campaignId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND_MESSAGE));
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new RuntimeException(CAMPAIGN_NOT_FOUND_MESSAGE));

        if (!Objects.equals(user.getId(), campaign.getOwner().getId())) throw new IllegalArgumentException(USER_OR_CAMPAIGN_ID_INCORRECT_MESSAGE);

        campaignRepository.deleteById(campaignId);

        return SUCCESSFUL_DELETION_MESSAGE;
    }

    private void handleFundsForUser(BigDecimal oldFund, BigDecimal newFund, User user) {
        BigDecimal userBalance = user.getBalance();
        int comparison = newFund.compareTo(oldFund);

        if (comparison == 0) return;
        if (comparison > 0) {
            BigDecimal difference = newFund.subtract(oldFund);

            if (difference.compareTo(user.getBalance()) > 0) {
                throw new RuntimeException(INSUFFICIENT_USER_BALANCE_MESSAGE);
            }
            user.setBalance(userBalance.subtract(difference));
        }

        if (comparison < 0) {
            BigDecimal refund = oldFund.subtract(newFund);
            user.setBalance(userBalance.add(refund));
        }
    }
}
