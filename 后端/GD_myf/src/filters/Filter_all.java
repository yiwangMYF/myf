package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 全局过滤器，用于结局中文乱码和跨域权限问题
 * @author 毛燕丰
 * @caeateTime 2019年4月30日下午3:32:40
   @package_name filters
	@file_name Filter_all.java
 */
@WebFilter("/*")
public class Filter_all implements Filter {

    public Filter_all() {
        
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 解决中文乱码,适用于post提交方式
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain;charset=UTF-8");
		//解决跨域权限问题
		res.setHeader("Access-Control-Allow-Credentials", "true");//允许携带cookie
		res.setHeader("Access-Control-Allow-Origin",req.getHeader("Origin"));
		chain.doFilter(req, res);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
