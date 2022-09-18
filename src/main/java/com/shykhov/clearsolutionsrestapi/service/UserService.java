package com.shykhov.clearsolutionsrestapi.service;

import com.shykhov.clearsolutionsrestapi.dao.entity.UserEntity;
import com.shykhov.clearsolutionsrestapi.dao.repository.UserRepository;
import com.shykhov.clearsolutionsrestapi.exeption.custom.UserDoesNotExistException;
import com.shykhov.clearsolutionsrestapi.model.request.UserDetailsRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.shykhov.clearsolutionsrestapi.utils.UserUtils.getUserEntity;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //very simple logic
    public UserEntity createUser(UserDetailsRequestModel userDetails) {
        UserEntity userEntity = getUserEntity(userDetails);
        return userRepository.save(userEntity);
    }

    //very simple logic
    public UserEntity replaceUser(long id, UserDetailsRequestModel userDetails) throws UserDoesNotExistException {
        if (!userRepository.existsById(id)) throw new UserDoesNotExistException(id);

        UserEntity userEntity = getUserEntity(userDetails);
        userEntity.setId(id);
        return userRepository.save(userEntity);
    }

    //very simple logic
    public void deleteUser(long id) throws UserDoesNotExistException {
        if (!userRepository.existsById(id)) throw new UserDoesNotExistException(id);

        userRepository.deleteById(id);
    }

    //very simple logic
    public List<UserEntity> getUsersTimeInterval(LocalDate fromDate, LocalDate toDate) {
        return userRepository.getUsersTimeInterval(fromDate, toDate);
    }
}
