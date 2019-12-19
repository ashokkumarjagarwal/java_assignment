package com.test.serviveImpl;

import com.test.entity.Role;
import com.test.repositoryImpl.RoleRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepositoryImpl roleRepository;

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public void saveEntities(List<Role> roles){
        roleRepository.saveEntities(roles);
    }

}
