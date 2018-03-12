package com.warumono.app.repositories;

import com.warumono.app.entities.User;

public interface UserRepository extends AbstractRepository<User>
{
	User findByUsername(String username);
}
