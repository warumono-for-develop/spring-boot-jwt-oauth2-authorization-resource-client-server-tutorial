package com.warumono.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warumono.app.entities.OAuthClientDetails;

public interface OAuthCleintDetailsRepository extends JpaRepository<OAuthClientDetails, Long>
{

}
