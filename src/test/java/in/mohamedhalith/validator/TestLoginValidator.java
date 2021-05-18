package in.mohamedhalith.validator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestLoginValidator {

	@Test
	public void testAdminVerificationWithValidCredentials() {
		String username = "hradmin";
		String password = "realadmin";
		String role = "admin";
		boolean result = LoginValidator.adminVerification(username, password, role);
		assertTrue(result);
	}

	@Test
	public void testAdminVerificationWithValidUsernameAndPasswordAndInvalidRole() {
		String username = "hradmin";
		String password = "realadmin";
		String role = "user";
		boolean result = LoginValidator.adminVerification(username, password, role);
		assertFalse(result);
	}

	@Test
	public void testAdminVerificationWithValidUsernameAndRoleAndInvalidPassword() {
		String username = "hradmin";
		String password = "admin";
		String role = "admin";
		boolean result = LoginValidator.adminVerification(username, password, role);
		assertFalse(result);
	}

	@Test
	public void testAdminVerificationWithValidUsernameAndInvalidPasswordAndRole() {
		String username = "hradmin";
		String password = "admin";
		String role = "user";
		boolean result = LoginValidator.adminVerification(username, password, role);
		assertFalse(result);
	}

	@Test
	public void testAdminVerificationWithInvalidUsernameAndValidRoleAndPassword() {
		String username = "admin";
		String password = "realadmin";
		String role = "admin";
		boolean result = LoginValidator.adminVerification(username, password, role);
		assertFalse(result);
	}

	@Test
	public void testAdminVerificationWithInvalidUsernameAndRoleAndValidPassword() {
		String username = "admin";
		String password = "realadmin";
		String role = "user";
		boolean result = LoginValidator.adminVerification(username, password, role);
		assertFalse(result);
	}

	@Test
	public void testAdminVerificationWithInvalidUsernameAndPasswordValidRole() {
		String username = "admin";
		String password = "hradmin";
		String role = "admin";
		boolean result = LoginValidator.adminVerification(username, password, role);
		assertFalse(result);
	}

	@Test
	public void testAdminVerificationWithInvalidRoleUsernameAndPassword() {
		String username = "admin";
		String password = "admin";
		String role = "user";
		boolean result = LoginValidator.adminVerification(username, password, role);
		assertFalse(result);
	}

	@Test
	public void testEmployeeVerificationWithValidCredentials() {
		String username = "moha2627";
		String password = "2627moha";
		String role = "employee";
		boolean result = LoginValidator.employeeVerification(username, password, role);
		assertTrue(result);
	}

	@Test
	public void testEmployeeVerificationWithValidUsernamePasswordAndInvalidRole() {
		String username = "moha2627";
		String password = "2627moha";
		String role = "user";
		boolean result = LoginValidator.employeeVerification(username, password, role);
		assertFalse(result);
	}

	@Test
	public void testEmployeeVerificationWithValidUsernameAndRoleAndInvalidPassword() {
		String username = "moha2627";
		String password = "password";
		String role = "employee";
		boolean result = LoginValidator.employeeVerification(username, password, role);
		assertFalse(result);
	}

	@Test
	public void testEmployeeVerificationWithValidUsernameAndInvalidPasswordAndRole() {
		String username = "moha2627";
		String password = "password";
		String role = "user";
		boolean result = LoginValidator.employeeVerification(username, password, role);
		assertFalse(result);
	}

	@Test
	public void testEmployeeVerificationWithInvalidUsernameAndValidRoleAndPassword() {
		String username = "mohamed";
		String password = "2627moha";
		String role = "employee";
		boolean result = LoginValidator.employeeVerification(username, password, role);
		assertFalse(result);
	}

	@Test
	public void testEmployeeVerificationWithInvalidUsernameAndRoleAndValidPassword() {
		String username = "mohamed";
		String password = "2627moha";
		String role = "user";
		boolean result = LoginValidator.employeeVerification(username, password, role);
		assertFalse(result);
	}

	@Test
	public void testEmployeeVerificationWithInvalidUsernameAndPasswordValidRole() {
		String username = "mohamed";
		String password = "password";
		String role = "employee";
		boolean result = LoginValidator.employeeVerification(username, password, role);
		assertFalse(result);
	}

	@Test
	public void testEmployeeVerificationWithInvalidRoleUsernameAndPassword() {
		String username = "mohamed";
		String password = "password";
		String role = "user";
		boolean result = LoginValidator.employeeVerification(username, password, role);
		assertFalse(result);
	}
}
