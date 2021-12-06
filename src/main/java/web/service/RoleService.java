package web.service;

import web.model.Role;

import java.util.Set;

public interface RoleService {

    public Set<Role> getAllRoles();

    public void saveRole(Role role);

    public Role getRole(String name);

    public void deleteRole(String name);
}