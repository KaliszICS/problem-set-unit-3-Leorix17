/**
* File: Email Validator and Parser
* Author: Leo
* Date Created:	April 5, 2026
* Date Last Modified: April 7, 2026
*/

import java.util.Scanner;
public class ProblemSet {
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		System.out.print("Input two emails: ");
		String emails = input.nextLine();
		System.out.println(emailValidator(emails));
	}

	public static String emailValidator(String input) {
		String email1;
		String email2;

		if (input.contains(", ")) { //checks if emails are split by ", "
			int commaIndex = input.indexOf(", ");
			email1 = input.substring(0, commaIndex); //gets first email
			email2 = input.substring(commaIndex + 2); //gets second email
		}

		else if (input.contains(",")) { //checks if emails are split by ","
			int commaIndex = input.indexOf(",");
			email1 = input.substring(0, commaIndex); 
			email2 = input.substring(commaIndex + 1); 
		}

		else { //if there is no "," at all there is no second email
			email1 = input;
			email2 = "";
		}
		
		//validates both emails
		String result1 = basicEmailValidation(email1);
		String result2 = basicEmailValidation(email2);

		return formatter(email1, result1) + "\n" + formatter(email2, result2);
		
	}

	//extracts local and domain parts for valid emails and formats the email, local, domain and validation result
	public static String formatter(String email, String result) {
		if (result.startsWith("Valid")) {
			int atIndex = email.indexOf("@"); 
			String local = email.substring(0, atIndex); 
			String domain = email.substring(atIndex + 1);

			return email + ": " + result + " | Local: " + local + " | Domain: " + domain;
		}

		return email + ": " + result; 
	}	

	//checks all validation types
	public static String basicEmailValidation(String email) {
		//checks if email contains @ symbol
		if (!email.contains("@")) {
			return "Invalid: Missing @";
		}

		//splits local, domain, and domain extension parts of email into variables
		String local = email.substring(0, email.indexOf("@"));
		String domain = email.substring(email.indexOf("@") + 1);
		String domainExtension = email.substring(email.lastIndexOf(".") + 1);
		domain = domain.toLowerCase();

		if (domain.equals("gmail.com")) {
			email = email.replaceAll("\\.", "");
		}
		
		//checks if email has more than 1 @ symbol
		else if (email.indexOf("@") != email.lastIndexOf("@")) {
			return "Invalid: Multiple @";
		}

		//checks if email starts or ends with ".", "+" or "_"
		if (email.startsWith(".") || email.endsWith(".")) {
			if (email.startsWith("+") || email.endsWith("+")) {
				if (email.startsWith("_") || email.endsWith("_")) {
					return "Invalid: starts or ends with underscore";
				}
				return "Invalid: Starts or ends with plus";
			}
			return "Invalid: Starts or ends with dot";	
		}	
		
		//checks if email contains spaces
		if (email.contains(" ")) {
			return "Invalid: Contains spaces";
		}

		//checks if local is between 1-64 characters
		if (local.length() < 1) {
			return "Invalid: Local part too short";
		}

		else if (local.length() > 64) {
			return "Invalid: Local part too long";
		}

		//checks if domain contains dot
		if (!domain.contains(".")) {
			return "Invalid: No dot in domain";
		}

		//checks if domain starts with dot
		if (domain.startsWith(".")) {
			return "Invalid: Domain starts with dot";
		}

		//checks if domain after last dot is between 2-6 characters
		if (domainExtension.length() < 2 || domainExtension.length() > 6) {
			return "Invalid: Invalid domain extension length";
		}
		
		//checks if domain contains pluses or underscores
		if (domain.contains("+") || domain.contains("_")) {
			return "Invalid: Domain extension contains invalid characters";
		}
		
		//checks if email is Gmail normalized
		domain = domain.toLowerCase();
		if (domain.equals("gmail.com")) {
			return "Valid (Gmail normalized)";
		}

		//if passes all previous statements return "Valid"
		return "Valid";
	}
}
