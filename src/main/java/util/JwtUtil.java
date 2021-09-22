package util;

import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import authen.UserPrincipal;
import net.minidev.json.JSONObject;

public class JwtUtil {
	private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	private static final String USER = "toan";
	private static final String SECRET = "here Mr Toan the secret length must be at least 256 bits. Please no reveal!";
	
	
	public Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + 864000000);
	}

	
	public String generateToken(UserPrincipal user) {
		String token = null;
		try {
			JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
			
			builder.claim(USER, user);
			builder.expirationTime(generateExpirationDate());
			JWTClaimsSet claimsSet = builder.build();
			
			SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
			JWSSigner signer = new MACSigner(SECRET.getBytes());
			signedJWT.sign(signer);
			
			token = signedJWT.serialize();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		
		return token;
	}
	
	//--------------- getClaimsFromToken ----------------
	
	private JWTClaimsSet getClaimsFromToken(String token) {
		JWTClaimsSet claims = null;
		try {
			SignedJWT signedJWT = SignedJWT.parse(token);
			JWSVerifier verifier = new MACVerifier(SECRET.getBytes());
			if(signedJWT.verify(verifier)) {
				claims = signedJWT.getJWTClaimsSet();
			}
		} catch (ParseException | JOSEException e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		
		return claims;
	}
	
	//--------------- getExpirationDateFromToken ----------------
	private Date getExpirationDateFromToken(JWTClaimsSet claims) {
		return claims != null ? claims.getExpirationTime() : new Date();
	}
	
	//--------------- isTokenExpired ----------------
	private boolean isTokenExpired(JWTClaimsSet claims) {
		return getExpirationDateFromToken(claims).after(new Date());
	}
	
	
	//--------------- getUserFromToken ----------------
	public UserPrincipal getUserFromToken(String token) {
		UserPrincipal user = null;
		try {
			JWTClaimsSet claims = getClaimsFromToken(token);
			if(claims != null && isTokenExpired(claims)) {
				JSONObject jsonObject = (JSONObject) claims.getClaim(USER);
				user = new ObjectMapper()
						.readValue(jsonObject.toJSONString(), UserPrincipal.class);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		
		return user;
	}


}
