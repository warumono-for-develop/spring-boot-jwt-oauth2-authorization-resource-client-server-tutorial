package com.warumono.app.services;

import org.springframework.stereotype.Service;

import com.warumono.app.entities.Role;
import com.warumono.app.repositories.AbstractRepository;

@Service
public class RoleService extends AbstractService<AbstractRepository<Role>, Role>
{
}
