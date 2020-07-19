package com.rabopay.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import com.rabopay.util.Constants;

@Slf4j
@Configuration
@Component
public class SecurityFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("########## Initiating Custom filter ##########");
    }


	private static String base64Decode(String value) {
		try {
			byte[] decodedValue = Base64.getDecoder().decode(value);
			return new String(decodedValue, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
		String headerId = request.getHeader("X-Request-Id");
		String signature = request.getHeader("Signature");
		String signatureCertificate = request.getHeader("Signature-Certificate");
		
		log.info(
				"header>" + headerId + "<signature>" + signature + "<signatureCertificate>" + signatureCertificate);

		
		if (StringUtils.isEmpty(headerId) || StringUtils.isEmpty(signature)
				|| StringUtils.isEmpty(signatureCertificate)) {
			log.error("Mandatory Request Header Details Missing");
			response.sendError(Constants.ERROR_STATUS_500, Constants.ErrorReasonCode.GENERAL_ERROR.toString());
			
		}else if(!signatureCertificate.contains("Sandbox-TPP")) {
			log.error("Mandatory Request Header Details Missing");
			response.sendError(Constants.ERROR_STATUS_500,Constants.ErrorReasonCode.UNKNOWN_CERTIFICATE.toString());
		}

		
		filterChain.doFilter(request, response);
		
	}

}
