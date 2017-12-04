package com.bridgelabz.utility;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtils {

	private static final String KEY = "secret";

	public static String generate(String role) {

		Date issuedAt = new Date();

		Date expiresAt = new Date(issuedAt.getTime() + 1000 * 60 * 60);

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		JwtBuilder builder = Jwts.builder();

		builder.setSubject("accessToken");

		builder.setIssuedAt(issuedAt);

		builder.setExpiration(expiresAt);

		builder.setIssuer(role);

		builder.signWith(signatureAlgorithm, KEY);

		String compactJwt = builder.compact();

		System.out.println("Generated jwt: " + compactJwt);

		return compactJwt;
	}

	public static String verify(String token) {
		JwtParser parser = Jwts.parser();
		try {
			Claims claims = parser.setSigningKey(KEY).parseClaimsJws(token).getBody();
			return claims.getIssuer();
		} catch (Exception e) {
			return null;
		}
	}

}
