package com.jzajas.task.util;

import com.jzajas.task.dto.CampaignCreationDTO;
import com.jzajas.task.dto.CampaignReturnDTO;
import com.jzajas.task.dto.CampaignUpdateDTO;
import com.jzajas.task.model.Campaign;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface CampaignMapper {

    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "id", ignore = true)
    Campaign fromDtoToObject(CampaignCreationDTO dto);

    @Mapping(target = "ownerName", source = "object.owner.firstname")
    @Mapping(target = "ownerSurname", source = "object.owner.surname")
    CampaignReturnDTO fromObjectToDto(Campaign object);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)

    Campaign updateObjectFromDto(
            CampaignUpdateDTO dto,
            @MappingTarget Campaign campaign
    );
}
