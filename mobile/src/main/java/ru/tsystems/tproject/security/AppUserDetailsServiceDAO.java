package ru.tsystems.tproject.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tsystems.tproject.DAO.API.UserDAO;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring security class needed for authorisation.
 */
@Service("userDetailsService")
public class AppUserDetailsServiceDAO implements UserDetailsService {
    private static final Logger logger = Logger.getLogger(AppUserDetailsServiceDAO.class);
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user;
        try {
            user = this.userDAO.getUserByLogin(username);
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), true, true, true, true, authorities);
        } catch (CustomDAOException ex) {
            throw new UsernameNotFoundException(username + " not found");
        } catch (Exception ex) {
            logger.error("Exception caught: " + ex);
            return null;
        }

    }
}
