package util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.util.ByteSource;

import java.io.File;
import java.security.MessageDigest;

public class MD5 {

	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}

	public static void main(String[] args) {
		/*System.out.println(md5("31119@qq.com" + "123456"));
		System.out.println(md5("1"));*/
		String [] myFile = new String[]{"Z"};
//		String hex = new Md5Hash(myFile).toHex();
		String encodedPassword =
				new Sha512Hash("123456", "itrider", 1024).toBase64();
		System.out.println(encodedPassword);
	}
	//基于配置文件加密
	public static String md5Hash(Object ojb) {
		return new Md5Hash(ojb).toHex();
	}

	//基于Sha512Hash加密
	public static String Sha512Hash(String str,Object salt,int count) {
		return new new Sha512Hash(str, salt, count).toBase64();;
	}
}
