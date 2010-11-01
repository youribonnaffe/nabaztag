package com.appspot.nabaztag.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.nabaztag.api.model.Rabbit;

public class AuthFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
			ServletException {
		String sn = null;
		String token = null;
		HttpServletRequest httpReq = (HttpServletRequest) req;
		if (httpReq.getCookies() != null) {
			for (Cookie cookie : httpReq.getCookies()) {
				if (cookie.getName().equals("sn")) {
					sn = cookie.getValue();
				} else if (cookie.getName().equals("token")) {
					token = cookie.getValue();
				}
			}
		}

		if (sn != null && token != null) {
			Rabbit rabbit = new Rabbit(sn, token);
			req.setAttribute("rabbit", rabbit);
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
