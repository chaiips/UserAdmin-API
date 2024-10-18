package com.alumnos.adminalumnos.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alumnos.adminalumnos.dto.UserDTO;
import com.alumnos.adminalumnos.model.UserEntity;
import com.alumnos.adminalumnos.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO create(UserDTO userDTO) {

        UserEntity user = mapToEntity(userDTO);
        UserEntity newUser = userRepository.save(user);
        return mapToDTO(newUser);

    }

    private UserEntity mapToEntity(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setAge(userDTO.getAge());
        user.setName(userDTO.getName());

        return user;
    }

    private UserDTO mapToDTO(UserEntity userEntity) {
        UserDTO userDto = new UserDTO();
        userDto.setAge(userEntity.getAge());
        userDto.setName(userEntity.getName());

        return userDto;
    }

    // Obtener usuario por ID
    public ResponseEntity<Object> getUserById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        
        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        }
    }

     // Actualizar usuario
     public ResponseEntity<Object> updateUser(Long id, UserDTO userDto) {
        Optional<UserEntity> updateUser = userRepository.findById(id);

        if (updateUser.isPresent()) {
            UserEntity user = updateUser.get();

            user.setName(userDto.getName());
            user.setAge(userDto.getAge());

    
            try {
                userRepository.save(user);
                return ResponseEntity.ok(user);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al modificar el usuario: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        }

    }

     // Eliminar usuario por ID
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("Usuario no encontrado.");
        }
        
        userRepository.deleteById(id);
    }
}
