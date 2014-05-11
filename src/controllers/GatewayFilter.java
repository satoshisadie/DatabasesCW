package controllers;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GatewayFilter implements Filter {
    private List<String> exceptAddresses = Arrays.asList("authorization.html", "authorize.html", "registration.html", "createUser.html");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String requestUrl = httpServletRequest.getRequestURL().toString();
        for (String exceptAddress : exceptAddresses) {
            if (requestUrl.contains(exceptAddress)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        HttpSession session = httpServletRequest.getSession();

        if (session.getAttribute("authorized") != null && (Boolean) session.getAttribute("authorized")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        
        httpServletResponse.setStatus(HttpServletResponse.SC_FOUND);
        httpServletResponse.sendRedirect("authorization.html");
    }

    @Override
    public void destroy() {

    }
}
