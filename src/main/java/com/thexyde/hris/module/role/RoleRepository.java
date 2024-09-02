package com.thexyde.hris.module.role;

import org.springframework.data.jpa.repository.JpaRepository;
import com.thexyde.hris.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
