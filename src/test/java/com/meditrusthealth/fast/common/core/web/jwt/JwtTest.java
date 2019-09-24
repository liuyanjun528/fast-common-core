/**
 *  www.meditrusthealth.com Copyright © MediTrust Health 2017
 */
package com.meditrusthealth.fast.common.core.web.jwt;

import org.joda.time.DateTime;

import com.alibaba.fastjson.JSON;
import com.meditrusthealth.fast.common.core.utils.RSAKeyUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>
 * 由于过期时间刷新问题 综合考虑弃用JWT
 * </p>
 *
 * @author xiaoyu.wang
 * @date 2017年11月21日 上午10:50:03
 * @version 1.0.0
 */
public class JwtTest {

	public static void main(String[] args) throws Exception {
		JWTInfo jwtInfo = new JWTInfo();
		jwtInfo.setUsername("json web token");
		jwtInfo.setName("fast");
		jwtInfo.setUserId("22134");
		String pubFilePath = RSAKeyUtils.class.getClassLoader().getResource("pub.key").getPath();
		String priFilePath = RSAKeyUtils.class.getClassLoader().getResource("pri.key").getPath();
		String token = generateToken(jwtInfo, priFilePath, 4);
		System.out.println(token);
		System.out.println(getInfoFromToken(token, pubFilePath));
	}

	public static String generateToken(JWTInfo jwtInfo, String priKeyPath, int expire) throws Exception {
		String compactJws = Jwts.builder().setSubject(jwtInfo.getUsername()).claim("i", jwtInfo.getUserId()).claim("k", jwtInfo.getName()).setExpiration(DateTime.now().plusSeconds(expire).toDate()).signWith(SignatureAlgorithm.RS256, RSAKeyUtils.getPrivateKey(priKeyPath)).compact();
		return compactJws;
	}

	public static Jws<Claims> parserToken(String token, String pubKeyPath) throws Exception {
		Jws<Claims> claimsJws = Jwts.parser().setSigningKey(RSAKeyUtils.getPublicKey(pubKeyPath)).parseClaimsJws(token);
		return claimsJws;
	}

	public static JWTInfo getInfoFromToken(String token, String pubKeyPath) throws Exception {
		Jws<Claims> claimsJws = parserToken(token, pubKeyPath);
		Claims body = claimsJws.getBody();
		System.out.println(JSON.toJSONString(body));
		return new JWTInfo(body.getSubject(), getObjectValue(body.get("i")), getObjectValue(body.get("k")));
	}

	public static String getObjectValue(Object obj) {
		return obj == null ? "" : obj.toString();
	}
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
class JWTInfo {

	private String username;
	private String userId;
	private String name;

}