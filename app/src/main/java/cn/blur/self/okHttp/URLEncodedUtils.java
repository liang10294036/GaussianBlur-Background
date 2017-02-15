package cn.blur.self.okHttp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2015/12/24 0024.
 */
public class URLEncodedUtils {

    public static String format(final Map<String, String> parameters, final String encoding) {
        final StringBuilder result = new StringBuilder();
        Map<String, String> treeMap = new TreeMap<>(parameters);
        for (final String key : parameters.keySet()) {
            final String encodedName = encode(key, encoding);
            final String value = treeMap.get(key);
            final String encodedValue = value != null ? encode(value, encoding) : "";
            if (result.length() > 0)
                result.append(HTTP.PARAMETER_SEPARATOR);
            result.append(encodedName);
            result.append(HTTP.NAME_VALUE_SEPARATOR);
            result.append(encodedValue);
        }
        return result.toString();
    }

    private static String encode(final String content, final String encoding) {
        try {
            return URLEncoder.encode(content,
                    encoding != null ? encoding : HTTP.DEFAULT_CONTENT_CHARSET);
        } catch (UnsupportedEncodingException problem) {
            throw new IllegalArgumentException(problem);
        }
    }

}
