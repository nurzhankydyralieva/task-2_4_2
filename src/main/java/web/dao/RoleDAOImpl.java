package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<>(entityManager
                .createQuery("FROM Role", Role.class)
                .getResultList());
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role getRole(String name) {

        return entityManager
                .createQuery("SELECT r FROM Role r WHERE r.roleName = :roleName", Role.class)
                .setParameter("roleName", name)
                .getSingleResult();
    }

    @Override
    public void deleteRole(String name) {
        entityManager
                .createQuery("DELETE FROM Role r WHERE r.roleName = :roleName", Role.class)
                .setParameter("roleName", name)
                .executeUpdate();
    }
}