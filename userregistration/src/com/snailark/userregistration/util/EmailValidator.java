/*
 * Email Validation Assumption :-
 * Email is cannot be  null or empty.
 * Only one @ is valid.
 * Only one . is valid.
 * Before @ at least one character is required.
 * Did not start with character other than alphabet. 
 * Between @ and . at least one number or alphanumeric is required. 
 * After . two or three only alphabet is valid.
 */

package com.snailark.userregistration.util;

public class EmailValidator {

	public static boolean validateEmail(String mailid) {
		boolean atrate = false;
		int atratecount = 0;
		int dotcount = 0;
		boolean dot = false;
		if(mailid==null)
			return false;
		char mail[] = mailid.toCharArray();
		for (int i = 0; i < mail.length; i++) {

			if (!(((int) mail[i] >= 65 && (int) mail[i] <= 90)
					|| ((int) mail[i] >= 97 && (int) mail[i] <= 122)
					|| mail[i] == '@' || mail[i] == '.' || mail[i] == '_' || ((int) mail[i] >= 48 && (int) mail[i] <= 57))) {
				return false;
			}

			if ('@' == mail[i]) {
				atrate = true;
				atratecount++;
				if (atratecount > 1) {
					return false;
				}
			}
			if ('.' == mail[i]) {
				dotcount++;
				if (dotcount > 1 || mail[0] == '.' || mail[i - 1] == '@'
						|| mail[0] == '@'
						|| ((int) mail[0] >= 48 && (int) mail[0] <= 97)
						|| mail[0] == '_')
					return false;
				dot = true;
				String str = mailid.substring(i + 1, mail.length);
				if (!(str.length() == 2 || str.length() == 3))

					return false;

				char str1[] = mailid.substring(i + 1, mail.length)
						.toCharArray();
				int j=0;
				if (str.length() == 2) {
					if ((str1[j] >= 48 && str1[j] <= 97)
							|| ((str1[j + 1] >= 48 && str1[j + 1] <= 97)))						
						return false;
				}
				if (str.length() == 3) {
					if ((str1[j] >= 48 && str1[j] <= 97)
							|| ((str1[j + 1] >= 48 && str1[j + 1] <= 97))
							|| ((str1[j + 2] >= 48 && str1[j + 2] <= 97)))
						return false;
				}
			}
			if ('_' == mail[i]) {
				if (atrate == true)
					return false;
			}

		}
		if (dot == false || atrate == false)
			return false;

		return true;
	}
}
