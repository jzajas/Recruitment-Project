package com.jzajas.task.service;

import com.jzajas.task.dto.UserCreationDTO;
import com.jzajas.task.dto.UserReturnDTO;
import com.jzajas.task.dto.UserUpdateDTO;
import com.jzajas.task.exception.UserNotFoundException;
import com.jzajas.task.model.User;
import com.jzajas.task.repository.UserRepository;
import com.jzajas.task.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private  static final String INCORRECT_EMAIL_MESSAGE = "User with that email already exists";
    private static final String USER_DOES_NOT_EXIST_MESSAGE = "User with that id does not exist";
    private static final String SUCCESSFUL_DELETION_MESSAGE = "User successfully deleted";

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    @Transactional
    public UserReturnDTO createUser(UserCreationDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) throw new UserNotFoundException(INCORRECT_EMAIL_MESSAGE);
        User user = userMapper.createObjectFromDto(dto);
        user.setCampaigns(new ArrayList<>());

        User saved = userRepository.save(user);

        return userMapper.createDtoFromObject(saved);
    }

    @Override
    public UserReturnDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_DOES_NOT_EXIST_MESSAGE));
        return userMapper.createDtoFromObject(user);
    }

    @Override
    @Transactional
    public UserReturnDTO updateUserById(Long userId, UserUpdateDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_DOES_NOT_EXIST_MESSAGE));

        User updatedUser = userMapper.updateObjectFromDto(dto, user);
        User saved = userRepository.save(updatedUser);
        return userMapper.createDtoFromObject(saved);
    }

    @Override
    @Transactional
    public String deleteUserById(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_DOES_NOT_EXIST_MESSAGE));

        userRepository.deleteById(userId);
        return SUCCESSFUL_DELETION_MESSAGE;
    }
}
