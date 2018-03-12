package com.warumono.app.services;

import org.springframework.stereotype.Service;

import com.warumono.app.entities.User;
import com.warumono.app.repositories.AbstractRepository;

@Service
public class UserService extends AbstractService<AbstractRepository<User>, User>
{
}
