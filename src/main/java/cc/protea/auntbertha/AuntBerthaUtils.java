package cc.protea.auntbertha;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.xml.bind.DatatypeConverter;

import com.fasterxml.jackson.core.type.TypeReference;

import cc.protea.util.http.Request;
import cc.protea.util.http.Response;

class AuntBerthaUtils {
	
	protected static String version = "0.1";

	<T> T options(final String key, final String url, final Class<T> type) {
		
		Response response = null;
		try {
			response = getService(key, url).optionsResource();
			return convert(response, type);
		} catch (AuntBerthaException e) {
			return addError(type, e);
		} catch (IOException e) {
			throw new AuntBerthaException(e, response);
		}
	}

	<T> T get(final String key, final String url, final Class<T> type) {
		Response response = null;
		try {
			response = getService(key, url).getResource();
			return convert(response, type);
		} catch (AuntBerthaException e) {
			return addError(type, e);
		} catch (IOException e) {
			throw new AuntBerthaException(e, response);
		}
	}

	<T> T get(final String key, final String url, final TypeReference<T> type) {
		Response response = null;
		try {
			response = getService(key, url).getResource();
			return convert(response, type);
		} catch (AuntBerthaException e) {
			return null;
//			return addError(type, e);
		} catch (IOException e) {
			throw new AuntBerthaException(e, response);
		}
	}

	<T> T delete(final String key, final String url, final Object bodyObject, final Class<T> type) {
		Response response = null;
		try {
			String body = convert(bodyObject);
			response = getService(key, url).setBody(body).deleteResource();
			return convert(response, type);
		} catch (AuntBerthaException e) {
			return addError(type, e);
		} catch (IOException e) {
			throw new AuntBerthaException(e, response);
		}
	}

	void delete(final String key, final String url, final Object bodyObject) {
		Response response = null;
		try {
			String body = convert(bodyObject);
			response = getService(key, url).setBody(body).deleteResource();
			checkResponse(response);
		} catch (IOException e) {
			throw new AuntBerthaException(e, response);
		}
	}

	<T> T put(final String key, final String url, final Object bodyObject, final Class<T> type) {
		Response response = null;
		try {
			String body = convert(bodyObject);
			response = getService(key, url).setBody(body).putResource();
			return convert(response, type);
		} catch (AuntBerthaException e) {
			return addError(type, e);
		} catch (IOException e) {
			throw new AuntBerthaException(e, response);
		}
	}

	<T> T post(final String key, final String url, final Object bodyObject, final Class<T> type) {
		Response response = null;
		try {
			String body = convert(bodyObject);
			response = getService(key, url).setBody(body).postResource();
			return convert(response, type);
		} catch (AuntBerthaException e) {
			return addError(type, e);
		} catch (IOException e) {
			throw new AuntBerthaException(e, response);
		}
	}

	static String encode(final String in) {
		try {
			return URLEncoder.encode(in, "UTF-8").replaceAll("\\+", "%20");
		} catch (UnsupportedEncodingException e) {
			return in;
		}
	}

	protected String getAuthorizationHeader(String key) {
		final String pair = key + ":";
		final String base64 = DatatypeConverter.printBase64Binary(pair.getBytes());
		return "Basic " + base64;
	}

	protected Request getService(final String apiKey, final String url) {
		String newUrl = addParameter(url, "api_key", apiKey);
		return new Request(newUrl)
				.addHeader("Authorization", getAuthorizationHeader(apiKey))
				.addHeader("Accept", "application/json")
				.addHeader("Content-Type", "application/json")
				.addHeader("User-Agent", "AuntBertha Java SDK v" + version);
	}

	<T> T convert(final Response response, final Class<T> type) {
		checkResponse(response);
		return convert(response.getBody(), type);
	}

	<T> T convert(final Response response, final TypeReference<T> type) {
		checkResponse(response);
		return convert(response.getBody(), type);
	}

	void checkResponse(Response response) {
		switch(response.getResponseCode()) {
			case 200:
				break; // success
			default:
				throw new AuntBerthaException(null, "" + response.getResponseCode(), response.getResponseMessage());
		}
	}

	public <T> T convert(final String json, final Class<T> type) {
		return convert(json, type, true);
	}

	<T> T convert(final String json, final TypeReference<T> type) {
		return convert(json, type, true);
	}


	@SuppressWarnings("unchecked")
	<T> T convert(final String json, final Class<T> type, final boolean handleErrors) {
		if (json == null) {
			return null;
		}
		if (String.class.equals(type)) {
			return (T) json;
		}
		return AuntBerthaJsonUtil.fromJson(json, type);
	}

	@SuppressWarnings("unchecked")
	<T> T convert(final String json, final TypeReference<T> type, final boolean handleErrors) {
		if (json == null || type == null) {
			return null;
		}
		if (String.class.equals(type.getClass())) {
			return (T) json;
		}
		return AuntBerthaJsonUtil.fromJson(json, type);
	}

	<T> T addError(final Class<T> type, final AuntBerthaException in) {
		try {
			return addError(type.newInstance(), in);
		} catch (AuntBerthaException se) {
			throw se;
		} catch (Exception e) {
			throw new AuntBerthaException(e);
		}
	}

	private <T> T addError(final T in, final AuntBerthaException e) {
		if (in instanceof AuntBerthaResponse) {
			AuntBerthaResponse response = (AuntBerthaResponse) in;
			response.success = false;
			response.errorMessage = e.errorMessage;
		}
		return in;
	}

	private String convert(final Object object) {
		return AuntBerthaJsonUtil.toJson(object);
	}

	public static boolean containsWhitespace(final CharSequence seq) {
		if (isEmpty(seq)) {
			return false;
		}
		final int strLen = seq.length();
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(seq.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	public static boolean isEmpty(final CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	public static boolean contains(final CharSequence seq, final CharSequence searchSeq) {
		if (seq == null || searchSeq == null) {
			return false;
		}
		return indexOf(seq, searchSeq, 0) >= 0;
	}

	static int indexOf(final CharSequence cs, final CharSequence searchChar, final int start) {
		return cs.toString().indexOf(searchChar.toString(), start);
	}

	public static final String EMPTY = "";
	public static final int INDEX_NOT_FOUND = -1;

	public static String substringAfter(final String str, final String separator) {
		if (isEmpty(str)) {
			return str;
		}
		if (separator == null) {
			return EMPTY;
		}
		final int pos = str.indexOf(separator);
		if (pos == INDEX_NOT_FOUND) {
			return EMPTY;
		}
		return str.substring(pos + separator.length());
	}

	public static String substringBefore(final String str, final String separator) {
		if (isEmpty(str) || separator == null) {
			return str;
		}
		if (separator.length() == 0) {
			return EMPTY;
		}
		final int pos = str.indexOf(separator);
		if (pos == INDEX_NOT_FOUND) {
			return str;
		}
		return str.substring(0, pos);
	}

	public static String trimToNull(final String str) {
		final String ts = trim(str);
		return isEmpty(ts) ? null : ts;
	}

	public static String trim(final String str) {
		return str == null ? null : str.trim();
	}

	public static boolean isNumeric(final CharSequence cs) {
		if (isEmpty(cs)) {
			return false;
		}
		final int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isDigit(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	public static String substring(final String str, int start) {
		if (str == null) {
			return null;
		}

		// handle negatives, which means last n characters
		if (start < 0) {
			start = str.length() + start; // remember start is negative
		}

		if (start < 0) {
			start = 0;
		}
		if (start > str.length()) {
			return EMPTY;
		}

		return str.substring(start);
	}

	public static String substring(final String str, int start, int end) {
		if (str == null) {
			return null;
		}

		// handle negatives
		if (end < 0) {
			end = str.length() + end; // remember end is negative
		}
		if (start < 0) {
			start = str.length() + start; // remember start is negative
		}

		// check length next
		if (end > str.length()) {
			end = str.length();
		}

		// if start is greater than end, return ""
		if (start > end) {
			return EMPTY;
		}

		if (start < 0) {
			start = 0;
		}
		if (end < 0) {
			end = 0;
		}

		return str.substring(start, end);
	}
	
	public static String addParameter(final String url, final String key, final String value) {
		return url + getAppendChar(url) + key + "=" + encode(value);
	}
		
	
	public static char getAppendChar(final String sb) {
		return sb.indexOf("?") != -1 ? '&' : '?';
	}



}
