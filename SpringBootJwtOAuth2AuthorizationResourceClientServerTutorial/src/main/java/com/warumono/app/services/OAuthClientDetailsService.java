package com.warumono.app.services;

import org.springframework.stereotype.Service;

import com.warumono.app.entities.OAuthClientDetails;
import com.warumono.app.repositories.AbstractRepository;

@Service
public class OAuthClientDetailsService extends AbstractService<AbstractRepository<OAuthClientDetails>, OAuthClientDetails>
{
}
