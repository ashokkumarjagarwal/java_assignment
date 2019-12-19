package com.test.repositoryImpl;

import com.test.entity.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class RoleRepositoryImpl extends AbstractJpaDao<Role> {

    @PersistenceContext
    private EntityManager em;

    public List<Role> findAll() {
        return (List<Role>) em.createQuery("FROM Role", Role.class).getResultList();
    }

    public void saveEntities(List<Role> roles) {
        for (Role r:roles) {
            super.save(r);
        }
    }

}
