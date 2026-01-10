package com.jzajas.task.service;

import com.jzajas.task.dto.UserCreationDTO;
import com.jzajas.task.dto.UserReturnDTO;
import com.jzajas.task.dto.UserUpdateDTO;

public interface UserService {
    UserReturnDTO createUser(UserCreationDTO dto);
    UserReturnDTO getUserById(Long id);
    UserReturnDTO updateUserById(Long id, UserUpdateDTO dto);
    String deleteUserById(Long id);
}
