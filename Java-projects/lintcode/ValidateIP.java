public class ValidateIP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(validate("128.0.0.1"));

	}

	public static boolean validate(String ip) {

		// ip = 123.42.56.77

		int dots = 0;
		int current = 0;
		String sub = findNextDot(ip, current);
		while (sub != null && current + sub.length() < ip.length()) {
			dots++;
			current += sub.length() + 1;
			sub = findNextDot(ip, current);
		}

		if (dots == 3 && sub != null && current + sub.length() >= ip.length())
			return true;
		else
			return false;
	}

	public static String findNextDot(String ip, int start) {
		if (start >= ip.length())
			return null;
		for (int i = start; i < ip.length(); ++i) {
			if (ip.charAt(i) == '.') {
				if (i == start) {
					return null;
				} else {
					if (new Integer(ip.substring(start, i)).intValue() > 255)
						return null;
					return ip.substring(start, i);
				}
			}
			if (ip.charAt(i) > '9' || ip.charAt(i) < '0')
				return null;

		}
		if (new Integer(ip.substring(start)).intValue() > 255)
			return null;
		return ip.substring(start);

	}
}

// #include <stdio.h>
// #include <stdlib.h>
// #include <string.h>
//
// #define DELIM "."
//
// /* return 1 if string contain only digits, else return 0 */
// int valid_digit(char *ip_str)
// {
// while (*ip_str) {
// if (*ip_str >= '0' && *ip_str <= '9')
// ++ip_str;
// else
// return 0;
// }
// return 1;
// }
//
// /* return 1 if IP string is valid, else return 0 */
// int is_valid_ip(char *ip_str)
// {
// int i, num, dots = 0;
// char *ptr;
//
// if (ip_str == NULL)
// return 0;
//
// // See following link for strtok()
// // http://pubs.opengroup.org/onlinepubs/009695399/functions/strtok_r.html
// ptr = strtok(ip_str, DELIM);
//
// if (ptr == NULL)
// return 0;
//
// while (ptr) {
//
// /* after parsing string, it must contain only digits */
// if (!valid_digit(ptr))
// return 0;
//
// num = atoi(ptr);
//
// /* check for valid IP */
// if (num >= 0 && num <= 255) {
// /* parse remaining string */
// ptr = strtok(NULL, DELIM);
// if (ptr != NULL)
// ++dots;
// } else
// return 0;
// }
//
// /* valid IP string must contain 3 dots */
// if (dots != 3)
// return 0;
// return 1;
// }

