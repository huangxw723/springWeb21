package util;

import java.io.*;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

	/**
	 * ���������λ����֤��
	 *
	 * @return
	 */
	public static int getRandomNum() {
		Random r = new Random();
		return r.nextInt(900000) + 100000;// (Math.random()*(999999-100000)+100000)
	}

	/**
	 * �ַ����Ƿ�Ϊ��(null,"","null")
	 *
	 * @param s
	 * @return ��Ϊ���򷵻�true�����򷵻�false
	 */
	public static boolean notEmpty(String s) {
		return s != null && !"".equals(s) && !"null".equals(s);
	}

	/**
	 * �ַ����Ƿ�Ϊ��?null,"","null")
	 *
	 * @param s
	 * @return Ϊ���򷵻�true�������򷵻�false
	 */
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s) || "null".equals(s);
	}

	/**
	 * �ַ���ת��Ϊ�ַ�������?
	 *
	 * @param str
	 *            �ַ���
	 * @param splitRegex
	 *            �ָ���
	 * @return
	 */
	public static String[] str2StrArray(String str, String splitRegex) {
		if (isEmpty(str)) {
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	 * ��Ĭ�ϵķָ��� ���ַ���ת��Ϊ�ַ�������
	 *
	 * @param str
	 *            �ַ�??
	 * @return
	 */
	public static String[] str2StrArray(String str) {
		return str2StrArray(str, ",\\s*");
	}

	/**
	 * ����yyyy-MM-dd HH:mm:ss�ĸ�ʽ������ת�ַ���
	 *
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date) {
		return date2Str(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * ����yyyy-MM-dd HH:mm:ss�ĸ�ʽ���ַ���ת����
	 *
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date) {
		if (notEmpty(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		} else {
			return null;
		}
	}

	/**
	 * ���ղ���format�ĸ�ʽ������ת�ַ���
	 *
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} else {
			return "";
		}
	}

	/**
	 * ��ʱ�����ʱ���֡���ת��Ϊʱ���
	 *
	 * @param StrDate
	 */
	public static String getTimes(String StrDate) {
		String resultTimes = "";

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now;

		try {
			now = new Date();
			Date date = df.parse(StrDate);
			long times = now.getTime() - date.getTime();
			long day = times / (24 * 60 * 60 * 1000);
			long hour = (times / (60 * 60 * 1000) - day * 24);
			long min = ((times / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long sec = (times / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

			StringBuffer sb = new StringBuffer();
			// sb.append("�����ڣ�");
			if (hour > 0) {
				sb.append(hour + "Сʱ");
			} else if (min > 0) {
				sb.append(min + "����");
			} else {
				sb.append(sec + "��ǰ");
			}

			resultTimes = sb.toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return resultTimes;
	}

	/**
	 * дtxt��ĵ�������
	 *
	 * @param filePath
	 *            �ļ�·��
	 * @param content
	 *            д�����??
	 */
	public static void writeFile(String fileP, String content) {
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../"; // ��Ŀ·��
		filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
		if (filePath.indexOf(":") != 1) {
			filePath = File.separator + filePath;
		}
		try {
			filePath = URLDecoder.decode(filePath, "utf-8");
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath), "utf-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(content);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��֤����
	 *
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * ��֤�ֻ�����
	 *
	 * @param mobiles
	 * @return
	 */
	public static boolean checkMobileNumber(String mobileNumber) {
		boolean flag = false;
		try {
			Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
			Matcher matcher = regex.matcher(mobileNumber);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * ????KEY�Ƿ���ȷ
	 *
	 * @param paraname
	 *            �������
	 * @param FKEY
	 *            ����??KEY
	 * @return Ϊ���򷵻�true�������򷵻�false
	 */
	public static boolean checkKey(String paraname, String FKEY) {
		paraname = (null == paraname) ? "" : paraname;
		return MD5.md5(paraname + DateUtil.getDays() + ",fh,").equals(FKEY);
	}

	/**
	 * ��ȡtxt��ĵ�������
	 *
	 * @param filePath
	 *            �ļ�·��
	 */
	public static String readTxtFile(String fileP) {
		try {

			String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../"; // ��Ŀ·��
			filePath = filePath.replaceAll("file:/", "");
			filePath = filePath.replaceAll("%20", " ");
			filePath = filePath.trim() + fileP.trim();
			if (filePath.indexOf(":") != 1) {
				filePath = File.separator + filePath;
			}
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // �ж��ļ��Ƿ����
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding); // ���ǵ������??
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					return lineTxt;
				}
				read.close();
			} else {
				System.out.println("�Ҳ���ָ�����ļ�,�鿴��·���Ƿ���??" + filePath);
			}
		} catch (Exception e) {
			System.out.println("��ȡ�ļ����ݳ���");
		}
		return "";
	}

	public static void main(String[] args) {
		String [] ss =str2StrArray("12 34,45");
		System.out.println(ss[0]+":");
	}

	 /**
     * �ж�List�Ƿ�Ϊ��,�ǿշ���true,���򷵻�false
     * 
     * @param list
     * @return boolean
     */
    public static boolean listIsEmpty(List<?> list) {
        if (null != list) {
            if ((list.size() > 0) && !list.isEmpty()) return true;
        } 
        return false;
    }

}
