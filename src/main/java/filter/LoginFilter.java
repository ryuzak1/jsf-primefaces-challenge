package filter;

import manageBean.AuxBean;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Filtro, responsável pelo gerenciamento de acesso as páginas
 * - Robson Murilo: 29/08/2020 -
 */
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String url = req.getRequestURL().toString();
        System.out.println(url);
        if (url.contains("/restricted")&&AuxBean.getInstance().isLogado()==false){
            res.sendRedirect(req.getServletContext().getContextPath()+"/index.xhtml");
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
