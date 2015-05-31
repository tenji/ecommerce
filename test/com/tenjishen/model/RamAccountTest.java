package com.tenjishen.model;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import com.tenjishen.BaseJUnitTest;
import com.tenjishen.model.affiliate.RamAccount;

public class RamAccountTest extends BaseJUnitTest {
	
	private static Validator validator;
	
	@BeforeClass
    public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
    }
	
	@Test
	public void loginNameIsNull() {
		RamAccount ramAccount = new RamAccount(null, null);
//		HibernateUtil.validate(ramAccount);
		
		Set<ConstraintViolation<RamAccount>> constraintViolations = validator.validate( ramAccount );
		
		assertEquals( 1, constraintViolations.size() );
        assertEquals( "may not be null", constraintViolations.iterator().next().getMessage() );
	}

}
