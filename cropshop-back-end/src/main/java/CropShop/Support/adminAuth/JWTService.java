package CropShop.Support.adminAuth;

import java.util.ArrayList;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTService {
	static final long EXPIRATIONTIME = 864_000_00; // 1 day in milliseconds
	static final String SIGNINGKEY = "9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d9";
	static final String PREFIX = "Bearer";

	// Add token to Authorization header
	static public void addToken(HttpServletResponse res, String username) {
		SecretKey key = Keys.hmacShaKeyFor(SIGNINGKEY.getBytes());
		String JwtToken = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(key)
				.compact();
		System.out.println(JwtToken);
		res.addHeader("Authorization", PREFIX + " " + JwtToken);
		res.addHeader("Access-Control-Expose-Headers", "Authorization");
	//	res.addHeader("Location", "http://localhost:3000/admin/dashboard");
		//res.setStatus(HttpServletResponse.SC_FOUND);// This will give a 302 (temp redirection may lead to multiple form submissions or something like that)
	//	res.setStatus(HttpServletResponse.SC_SEE_OTHER);
	}

	// Get token from Authorization header
	static public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		SecretKey key = Keys.hmacShaKeyFor(SIGNINGKEY.getBytes());

		if (token != null) {
			try {
				String user = Jwts.parser()
						.verifyWith(key)
						.build()
						.parseClaimsJws(token.replace("Bearer ", ""))
						.getBody()
						.getSubject();
				if (user != null)
					return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
}
