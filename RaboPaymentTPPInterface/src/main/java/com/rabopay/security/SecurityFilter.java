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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rabopay.util.Constants;

@Configuration
@Component
public class SecurityFilter implements Filter {

	/** protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String headerId = request.getHeader("X-Request-Id");
		String signature = request.getHeader("Signature");
		String signatureCertificate = request.getHeader("Signature-Certificate");
		
		System.out.println(
				"header>" + headerId + "<signature>" + signature + "<signatureCertificate>" + signatureCertificate);

		if (StringUtils.isEmpty(headerId) || StringUtils.isEmpty(signature)
				|| StringUtils.isEmpty(signatureCertificate)) {
			response.sendError(Constants.GENERAL_ERROR_STATUS, Constants.GENERAL_ERROR_MSG);
			throw new SecurityException();
		}

		signature = base64Decode(signature);
		signatureCertificate = base64Decode(signatureCertificate);
		StringBuilder stringBuilder = new StringBuilder();
		char[] charBuffer = new char[128];
		int bytesRead = -1;
		while ((bytesRead = request.getReader().read(charBuffer)) > 0) {
            stringBuilder.append(charBuffer, 0, bytesRead);
        }
		
		filterChain.doFilter(request, response);

	} **/
	
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("########## Initiating Custom filter ##########");
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
		
		LOGGER.info(
				"header>" + headerId + "<signature>" + signature + "<signatureCertificate>" + signatureCertificate);

		
		if (StringUtils.isEmpty(headerId) || StringUtils.isEmpty(signature)
				|| StringUtils.isEmpty(signatureCertificate)) {
			LOGGER.error("Mandatory Request Header Details Missing");
			response.sendError(Constants.ERROR_STATUS_500, Constants.ErrorReasonCode.GENERAL_ERROR.toString());
			
		}else if(!signatureCertificate.contains("Sandbox-TPP")) {
			LOGGER.error("Mandatory Request Header Details Missing");
			response.sendError(Constants.ERROR_STATUS_500,Constants.ErrorReasonCode.UNKNOWN_CERTIFICATE.toString());
		}

		/*signature = base64Decode(signature);
		signatureCertificate = base64Decode(signatureCertificate);
		StringBuilder stringBuilder = new StringBuilder();
		char[] charBuffer = new char[128];
		int bytesRead = -1;
		while ((bytesRead = request.getReader().read(charBuffer)) > 0) {
            stringBuilder.append(charBuffer, 0, bytesRead);
        }*/
		
		filterChain.doFilter(request, response);
		
	}

}
