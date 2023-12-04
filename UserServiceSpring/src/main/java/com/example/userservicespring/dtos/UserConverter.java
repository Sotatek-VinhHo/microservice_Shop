package com.example.userservicespring.dtos;

import com.example.userservicespring.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserConverter {
     private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
     public UserDTO convertToDto (UserEntity user){
         UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
         return userDTO;
     } //nhan user lam tham so va tra ve userDTO, anh xa thuoc tinh cua user thanh userDTO
    public UserEntity convertToEntity(RegisterRequestDto registerRequestDto) {
         UserEntity userEntity =  modelMapper.map(registerRequestDto, UserEntity.class);
         if(registerRequestDto.getPassword() != null){
             userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
         }
         return userEntity;
    }

    public List<UserDTO> convertToDtoList(List<UserEntity> userList) {
        List<UserDTO> userDTOList = new ArrayList<>();

        for (UserEntity userEntity : userList) {
            UserDTO userDTO = convertToDto(userEntity); // Gọi phương thức chuyển đổi cho từng đối tượng UserEntity
            userDTOList.add(userDTO); // Thêm đối tượng UserDTO vào danh sách kết quả
        }
        return userDTOList;
    }
}
