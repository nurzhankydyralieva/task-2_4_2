package web.dao;


import web.model.Role;

import java.util.Set;

public interface RoleDAO {
    Set<Role> getAllRoles();

    void saveRole(Role role);

    Role getRole(String name);

    void deleteRole(String name);
}