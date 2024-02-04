package com.exam.coursework.security.command;


import com.exam.coursework.shared.service.ServiceLoader;
import com.exam.coursework.shared.web.Page;
import com.exam.coursework.user.User;
import com.exam.coursework.user.UserService;
import com.exam.coursework.utils.PathPageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.*;


import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore
public class SingInCommandPOSTTest {
    HttpServletResponse response;
    HttpServletRequest request;
    SingInCommandPOST signInCommand;
    ServiceLoader serviceLoader;
    UserService userService;

    @Before
    public void setUp() throws Exception {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        signInCommand = new SingInCommandPOST();
        serviceLoader = mock(ServiceLoader.class);
        userService = mock(UserService.class);
        when(serviceLoader.userService()).thenReturn(userService);
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("password");
    }

    @Test
    public void executeWithServiceLoader_whenFindUserReturnedNull() {
        when(userService.findByLoginAndPassword(anyString(),anyString())).thenReturn(null);
        Assert.assertEquals(new Page(PathPageManager.getProperty("page.sign-in")).setDispatchType(Page.DispatchType.FORWARD),signInCommand.executeWithServiceLoader(request,response,serviceLoader));
    }

    @Test
    public void executeWithServiceLoader() {
        when(userService.findByLoginAndPassword(anyString(),anyString())).thenReturn(new User());
        HttpSession httpSession = mock(HttpSession.class);
        when(request.getSession()).thenReturn(httpSession);
        Assert.assertEquals(new Page(Page.WebPath.HOME.getPath()).setDispatchType(Page.DispatchType.REDIRECT),signInCommand.executeWithServiceLoader(request,response,serviceLoader));
    }

    @After
    public void tearDown() throws Exception {
        response= null;
        request = null;
        signInCommand = null;
    }
}