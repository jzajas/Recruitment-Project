package com.jzajas.task.util;

import com.jzajas.task.dto.UserCreationDTO;
import com.jzajas.task.dto.UserReturnDTO;
import com.jzajas.task.dto.UserUpdateDTO;
import com.jzajas.task.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "campaigns", ignore = true)
    User createObjectFromDto(UserCreationDTO dto);

    UserReturnDTO createDtoFromObject(User object);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "campaigns", ignore = true)
    User updateObjectFromDto (
            UserUpdateDTO dto,
            @MappingTarget User object
    );
}

