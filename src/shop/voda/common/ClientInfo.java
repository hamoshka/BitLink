package shop.voda.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class ClientInfo {

	private HttpServletRequest request;
	public ClientInfo(HttpServletRequest request) {
		this.request=request;
	}
	
	
	
	public String getReferer() {
		final String referer = request.getHeader("referer");
		if (!StringUtils.isEmpty(referer)) {
			String tmp1 = referer.toLowerCase().replace("https://", "").replace("http://", "");
			return tmp1.substring(0,tmp1.indexOf("/",0));
		} else {
			return "Native";
		}
	}

	public String getFullURL() {
		final StringBuffer requestURL = request.getRequestURL();
		final String queryString = request.getQueryString();

		final String result = queryString == null ? requestURL.toString() : requestURL.append('?').append(queryString).toString();

		return result;
	}

	public String getClientIpAddr() {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	// http://stackoverflow.com/a/18030465/1845894
	public String getClientOS() {
		final String browserDetails = request.getHeader("User-Agent");

		// =================OS=======================
		if (!StringUtils.isEmpty(browserDetails)) {
			final String lowerCaseBrowser = browserDetails.toLowerCase();
			if (lowerCaseBrowser.contains("windows")) {
				return "Windows";
			} else if (lowerCaseBrowser.contains("mac")) {
				return "Mac";
			} else if (lowerCaseBrowser.contains("x11")) {
				return "Unix";
			} else if (lowerCaseBrowser.contains("android")) {
				return "Android";
			} else if (lowerCaseBrowser.contains("iphone")) {
				return "IPhone";
			} else {
				return "UnKnown";
			}
		} else {
			return "UnKnown";
		}
	
	}

	String browser = "";

	public String getClientBrowser() {
		final String browserDetails = request.getHeader("User-Agent");

		if (!StringUtils.isEmpty(browserDetails)) {
			final String user = browserDetails.toLowerCase();

			if (!StringUtils.isEmpty(user)) {
				// ===============Browser===========================
				if (user.contains("msie")) {
					String substring = browserDetails.substring(browserDetails.indexOf("MSIE")).split(";")[0];
					browser = substring.split(" ")[0].replace("MSIE", "IE") ;
				} else if (user.contains("safari") && user.contains("version")) {
					browser = (browserDetails.substring(browserDetails.indexOf("Safari")).split(" ")[0]).split("/")[0] ;
				} else if (user.contains("opr") || user.contains("opera")) {
					if (user.contains("opera"))
						browser = (browserDetails.substring(browserDetails.indexOf("Opera")).split(" ")[0]).split("/")[0] ;
					else if (user.contains("opr"))
						browser = ((browserDetails.substring(browserDetails.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
				} else if (user.contains("chrome")) {
					browser = (browserDetails.substring(browserDetails.indexOf("Chrome")).split(" ")[0]).split("/")[0];
				} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
					browser = "Netscape";

				} else if (user.contains("firefox")) {
					browser = (browserDetails.substring(browserDetails.indexOf("Firefox")).split(" ")[0]).split("/")[0];
				} else if (user.contains("rv")) {
					browser = "IE";
				} else {
					browser = "UnKnown";
				}

			}

		}

		return browser;
	}

	public String getUserAgent() {
		return request.getHeader("User-Agent");
	}

}
