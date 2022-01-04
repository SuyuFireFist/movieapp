package handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    
	private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();

	
    private String id;
    private String defaultUrl;
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

    	resultRedirectStrategy(request, response, authentication);
    }
    
    
    protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        
        if(savedRequest!=null) {
            String targetUrl = savedRequest.getRedirectUrl();
            redirectStratgy.sendRedirect(request, response, targetUrl);
        } else {
            redirectStratgy.sendRedirect(request, response, defaultUrl);
        }
        
    }



	
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    public String getDefaultUrl() {
        return defaultUrl;
    }
 
    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }
 
}