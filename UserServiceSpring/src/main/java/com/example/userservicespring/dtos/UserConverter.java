package com.example.userservicespring.dtos;

import com.example.userservicespring.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserConverter {
     private final ModelMapper modelMapper;
     public UserDTO convertToDto (UserEntity user){
         UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
         return userDTO;
     } //nhan user lam tham so va tra ve userDTO, anh xa thuoc tinh cua user thanh userDTO
    public UserEntity convertToEntity(UserDTO user) {
         return modelMapper.map(user, UserEntity.class);
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
