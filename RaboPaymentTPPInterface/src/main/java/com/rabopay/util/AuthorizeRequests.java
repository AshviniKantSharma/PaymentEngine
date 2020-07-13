package com.rabopay.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabopay.model.PaymentInitiationRequest;

import sun.misc.BASE64Encoder;
import sun.security.pkcs.ContentInfo;
import sun.security.pkcs.PKCS9Attribute;
import sun.security.pkcs.PKCS9Attributes;

@SuppressWarnings("restriction")
public class AuthorizeRequests {
	 private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizeRequests.class);
	 
	 private static AuthorizeRequests authReq;
	 
	 private AuthorizeRequests() {
		 
	 }
	private String strPk = "-----BEGIN RSA PRIVATE KEY-----" + 
    		"MIIEowIBAAKCAQEAryLyouTQr1dvMT4qvek0eZsh8g0DQQLlOgBzZwx7iInxYEAg\r\n" + 
    		"MNxCKXiZCbmWHBYqh6lpPh+BBmrnBQzB+qrSNIyd4bFhfUlQ+htK08yyL9g4nyLt\r\n" + 
    		"0LeKuxoaVWpInrB5FRzoEY5PPpcEXSObgr+pM71AvyJtQLxZbqTao4S7TRKecUm3\r\n" + 
    		"2Wwg+FWY/StSKlox3QmEaxEGU7aPkaQfQs4hrtuUePwKrbkQ2hQdMpvI5oXRWzTq\r\n" + 
    		"afvEQvND+IyLvZRqf0TSvIwsgtJd2tch2kqPoUwng3AmUFleJbMjFNzrWM7TH9Lk\r\n" + 
    		"KPItYtSuMTzeSe9o0SmXZFgcEBh5DnETZqIVuQIDAQABAoIBAAfjgxpjRCMhxRhq\r\n" + 
    		"vHIhdwOjQTwt6d+bycd7DbeukEHuNLkpKkoJIdHMZNhTS7eoJ/JEZ0EtGhq35gAM\r\n" + 
    		"OxCXcTB8xP/NhZ6nFsatRAmWRtBw5NwGLsAgBFe5LUZ7qxm6yTlyO+HOjzM6ii/H\r\n" + 
    		"0tFo4K478Bar7k/kLAMX2eFTsTwfb23J8KhbVCXK/Oh02lqlqbf8/T4MowS1px25\r\n" + 
    		"LGQlS7KFW9CkuUVQ83IyCw9EbDJSMMr+Hkw8Bo5VllJ1s8RK++Fn3KwveCjOmZub\r\n" + 
    		"6luzMAJFdeDPvCi0mNctZzkB0abOmOcJQt1fHTJQJ9x69q4JRD9N+FvG3QwqgMLL\r\n" + 
    		"z0i63IECgYEA5v6G52aBUzKTLl3kn/P0WJ85RteOo2BHsIP+0toD6RMKNREdSCLs\r\n" + 
    		"itwsF/DKH3t4id0thiAK2cLRdY2SbWp0l0jXLyXd2VcZwTkEA2iQJJ9OLrGvDka/\r\n" + 
    		"j1CzdTjAj9ZBHsZzH93OoL+xSYeOhUMnIoWRzVe1cRSz3G83HeCBhGUCgYEAwhhz\r\n" + 
    		"3AszQjpjIt2P/eBeiEZALVsmt3lFm3cSuvNlo3b05it3OP/aVSfABSS5xfi4XqGB\r\n" + 
    		"YgDk4UbiRQxGlixht8ZoQWqdTDWKnSJi56uGEFg9F361kAVfZb7zVIBxddGfEuga\r\n" + 
    		"OaigNGe0M7J0fbWdwbx8EVsZOwXWp/TbbSblJMUCgYBKc3EBtj0qlptvj1233EY+\r\n" + 
    		"Jhus5J8Zs1eH4hNI3HH0NmnMztZUQMViwDIKCVbsLLyeGsaoez1kEHG4ZMf0MiKf\r\n" + 
    		"/B83GApYGcW4TGspuhLzatElJanZfR4S0Bz3RDJ0accVZzsF41TM5Nv8ag+ajhlX\r\n" + 
    		"/BsRRxq49sY93y6xl4HHLQKBgF9E8VmIhdh0IET0y8CpaL0q/kVFAHP+KpRsldz9\r\n" + 
    		"q13Y/cwceaCYtOonYLElnan2s0h/raoVFkMdL+MEa4E6t5wk3vd9BUhq32bRggqE\r\n" + 
    		"voE3ToVBxIy0lmaym21Wvlo+Uf5NvtGeW0Rdwq29YkBx7MUzZxJ9zJyT+RDntuyU\r\n" + 
    		"stShAoGBAOQMrfyQCFcInYo0aNdtm6spUbTEfGNnMVKq4hdLsKv5Yv1LotcELeWs\r\n" + 
    		"Avx59tOuhYNOod7oAWfGLQjjKZk1FOHjTD+CUg1Iixw2gwJ8Kz8OQZsSvNjMIJX8\r\n" + 
    		"qfeCXIBPrtblS37vxqk0t+V2vREC0575yzkckQmWaGBFPEALxI3t\r\n" + 
    		"-----END RSA PRIVATE KEY-----";
	
	synchronized public static AuthorizeRequests requestInstance() {
		
		if(authReq == null) {
			authReq = new AuthorizeRequests();
		}
		
		return authReq;
	}
	
	public static String signVerifyPaymentRequest (PaymentInitiationRequest paymentInitiationReq,
			HttpServletRequest request) {
		
		
		
		String paymentReqStr  = objectToString(paymentInitiationReq);
		byte[] signature = null;
		try {
			KeyPair keyPair = getKeyPair();
			 PKCS9Attributes authed = new PKCS9Attributes(new PKCS9Attribute[]{
		                new PKCS9Attribute(PKCS9Attribute.CONTENT_TYPE_OID, ContentInfo.DATA_OID),
		                new PKCS9Attribute(PKCS9Attribute.MESSAGE_DIGEST_OID, getSHA(paymentReqStr)),
		            });

		            Signature signObj = Signature.getInstance("SHA256withRSA");
		            signObj.initSign(keyPair.getPrivate());
		            signObj.update(authed.getDerEncoding());
		            signature = signObj.sign();
		            LOGGER.info("Request Signed -> "+signature);
		            
		} catch (NoSuchAlgorithmException | IllegalArgumentException | IOException | InvalidKeyException | SignatureException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Error during signVerifyPaymentRequest",e);
		}
		
		return new BASE64Encoder().encode(signature);
	}
	
	private static byte[] getSHA(String input) throws NoSuchAlgorithmException 
    {  
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 
	
	private static KeyPair getKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        return kpg.genKeyPair();
    }
	
	private static String objectToString(PaymentInitiationRequest paymentInitiationReq) {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr="";
		try {
		  jsonStr = mapper.writeValueAsString(paymentInitiationReq);
		  LOGGER.info("ResultingJSONstring = " + jsonStr);
		  
		} catch (JsonProcessingException e) {
		   LOGGER.error("Error during object parsing",e);
		}
		return jsonStr;
		
		
	}

}
