package com.example.userservicespring.services;

import com.example.userservicespring.dtos.UpdateUserBalanceRequestDTO;
import com.example.userservicespring.dtos.UserConverter;
import com.example.userservicespring.dtos.UserDTO;
import com.example.userservicespring.entities.UserEntity;
import com.example.userservicespring.exceptions.UserNotFoundException;
import com.example.userservicespring.repositories.UserRepository;
import com.example.userservicespring.dtos.UdpateUserProfileRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserEntity findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
    private UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    public void updateUserProfile(UdpateUserProfileRequestDTO userDTO) {
            UserEntity foundUserEntity = getUserProfileNow();
            if (userDTO.phone() != null) {
                foundUserEntity.setPhone(userDTO.phone());
            }
            if (userDTO.address() != null) {
                foundUserEntity.setAddress(userDTO.address());
            }
            userRepository.save(foundUserEntity);
        }
    public void updateUserBalance(Long id, UpdateUserBalanceRequestDTO balanceRequestDTO) {
        UserEntity foundUserEntity = findUserById(id);
        userRepository.updateBalance(foundUserEntity.getId(), balanceRequestDTO.balance());
    }
//public void updateUserBalance(Long id, UpdateUserBalanceRequestDTO balanceRequestDTO) {
//    UserEntity foundUserEntity = findUserById(id);
//    foundUserEntity.setBalance(balanceRequestDTO.balance());
//    userRepository.save(foundUserEntity);
//}

    public UserDTO getUserProfile() {
        return userConverter.convertToDto(getUserProfileNow());
    }
    private UserEntity getUserProfileNow() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return findUserByEmail(email);
    }
    public List<UserDTO> getAllProfile() {
        List<UserEntity> userList = findAllUsers();
        List<UserDTO> userDTOList = userConverter.convertToDtoList(userList);
        return userDTOList;
    }


}

