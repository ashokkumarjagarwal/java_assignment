package com.test.repositoryImpl;

import com.test.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl extends AbstractJpaDao<User> {

    @PersistenceContext
    private EntityManager em;

    public void saveEntityList(List<User> users){
        for (User u:users) {
            this.save(u);
        }
    }

    public List<String> getRegisteredUserEmails() {
        Query q = em.createQuery("select u.email from User u");
        return q.getResultList();
    }
}
