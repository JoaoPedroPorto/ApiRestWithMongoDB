package com.apirestwithmongodb.security;

import com.apirestwithmongodb.constant.MessageEnum;
import com.apirestwithmongodb.dao.ApplicationDAO;
import com.apirestwithmongodb.model.Applications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService {

    @Autowired
    ApplicationDAO applicationDAO;
    private static final Logger LOGGER = LoggerFactory.getLogger(ImplementsUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            Applications applicationDB = applicationDAO.findOneByLogin(login);
            if (applicationDB == null)
                throw new UsernameNotFoundException("Bad credentials");
            return applicationDB;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new UsernameNotFoundException(MessageEnum.INTERNAL_SERVER_ERROR.getMessage());
        }
    }

}
