package in.mohamedhalith.validator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestLoginValidator {

	@Test
	public void testAdminVerificationWithValidCredentials() {
		try {
			String username = "hradmin";
			String password = "realadmin";
			String role = "admin";
			boolean result = LoginValidator.adminVerification(username, password, role);
			assertTrue(result);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testAdminVerificationWithValidUsernameAndPasswordAndInvalidRole() {
		try {
			String username = "hradmin";
			String password = "realadmin";
			String role = "user";
			boolean result = LoginValidator.adminVerification(username, password, role);
			assertFalse(result);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testAdminVerificationWithValidUsernameAndRoleAndInvalidPassword() {
		try {
			String username = "hradmin";
			String password = "admin";
			String role = "admin";
			boolean result = LoginValidator.adminVerification(username, password, role);
			assertFalse(result);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testAdminVerificationWithValidUsernameAndIncorrectPasswordAndRole() {
		try {
			String username = "hradmin";
			String password = "1234admin";
			String role = "user";
			boolean result = LoginValidator.adminVerification(username, password, role);
			assertFalse(result);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testAdminVerificationWithIncorrectUsernameAndValidRoleAndPassword() {
		try {
			String username = "123admin";
			String password = "realadmin";
			String role = "admin";
			boolean result = LoginValidator.adminVerification(username, password, role);
			assertFalse(result);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testAdminVerificationWithInvalidUsernameAndRoleAndValidPassword() {
		try {
			String username = "admin";
			String password = "realadmin";
			String role = "user";
			boolean result = LoginValidator.adminVerification(username, password, role);
			assertFalse(result);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testAdminVerificationWithIncorrectUsernameAndPasswordValidRole() {
		try {
			String username = "123admin";
			String password = "hradmin2";
			String role = "admin";
			boolean result = LoginValidator.adminVerification(username, password, role);
			assertFalse(result);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testAdminVerificationWithInvalidRoleUsernameAndPassword() {
		try {
			String username = "admin";
			String password = "admin";
			String role = "user";
			boolean result = LoginValidator.adminVerification(username, password, role);
			assertFalse(result);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testEmployeeVerificationWithValidCredentials() {
		try {
			String username = "moha2627";
			String password = "2627moha";
			String role = "employee";
			boolean result = LoginValidator.employeeVerification(username, password, role);
			assertTrue(result);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testEmployeeVerificationWithValidUsernamePasswordAndInvalidRole() {
		try {
			String username = "moha2627";
			String password = "2627moha";
			String role = "user";
			boolean result = LoginValidator.employeeVerification(username, password, role);
			assertFalse(result);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testEmployeeVerificationWithValidUsernameAndRoleAndInvalidPassword() {
		try {
			String username = "moha2627";
			String password = "password";
			String role = "employee";
			boolean result = LoginValidator.employeeVerification(username, password, role);
			assertFalse(result);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testEmployeeVerificationWithValidUsernameAndInvalidPasswordAndRole() {
		try {
			String username = "moha2627";
			String password = "password";
			String role = "user";
			boolean result = LoginValidator.employeeVerification(username, password, role);
			assertFalse(result);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testEmployeeVerificationWithInvalidUsernameAndValidRoleAndPassword() {
		try {
			String username = "mohamed";
			String password = "2627moha";
			String role = "employee";
			boolean result = LoginValidator.employeeVerification(username, password, role);
			assertFalse(result);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testEmployeeVerificationWithInvalidUsernameAndRoleAndValidPassword() {
		try {
			String username = "mohamed";
			String password = "2627moha";
			String role = "user";
			boolean result = LoginValidator.employeeVerification(username, password, role);
			assertFalse(result);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testEmployeeVerificationWithIncorrectUsernameAndPasswordValidRole() {
		try {
			String username = "mohamed";
			String password = "password";
			String role = "employee";
			boolean result = LoginValidator.employeeVerification(username, password, role);
			assertFalse(result);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testEmployeeVerificationWithIncorrectRoleUsernameAndPassword() {
		try {
			String username = "mohamed";
			String password = "password";
			String role = "user";
			boolean result = LoginValidator.employeeVerification(username, password, role);
			assertFalse(result);
		} catch (Exception e) {
			fail();
		}
	}
}
