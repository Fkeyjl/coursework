package com.exam.coursework.user;

import com.exam.coursework.exception.UnsuccessfulDAOException;
import com.exam.coursework.shared.dao.FactoryDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    @Mock
    FactoryDAO factoryDAO;

    @InjectMocks
    UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findByLoginAndPassword() {
        Connection connection = mock(Connection.class);
        UserDAO<Integer> userDAO = mock(UserDAO.class);
        when(factoryDAO.createConnection()).thenReturn(connection);
        when(factoryDAO.createUserDAO(connection)).thenReturn(userDAO);
        try {
            when(userDAO.findByLoginAndPassword("incorrect","incorrect")).thenThrow(UnsuccessfulDAOException.class);
        } catch (UnsuccessfulDAOException e) {
            e.printStackTrace();
        }
        assertEquals(null, userService.findByLoginAndPassword("incorrect", "incorrect"));

    }
}