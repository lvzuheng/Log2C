package sz.lzh.LogListener.view.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	/**
	 * MD5加密算法
	 * @param str   需要转化为MD5码的字符串
	 * @return  返回一个32位16进制的字符串
	 */
	public static String getMd5(String str) {
		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(str.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			return (new BigInteger(1, md.digest()).toString(16)).toString();
		} catch (Exception e) {
		}
		return null;
	}
	/**
	 * 思路过程：
	 * 1、str.getBytes()：将字符串转化为字节数组。字符串中每个字符转换为对应的ASCII值作为字节数组中的一个元素
	 * 2、将字节数组通过固定算法转换为16个元素的有符号哈希值字节数组
	 * 3、将哈希字节数组的每个元素通过0xff与运算转换为两位无符号16进制的字符串
	 * 4、将不足两位的无符号16进制的字符串前面加0
	 * 5、通过StringBuffer.append()函数将16个长度为2的无符号进制字符串合并为一个32位String类型的MD5码
	 */
}
