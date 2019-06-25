package com.challenge.api.interceptor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationInterceptorTest {
	
	private final String API_KEY = "Api-Key";
	
	@Mock
	HttpServletRequest mockRequest;
	
	@Mock
	HttpServletResponse mockResponse;
	
	@Mock
	Object mockHandler;
	
	@Mock
	PrintWriter mockPrintWriter;
	
	private AuthenticationInterceptor interceptor = new AuthenticationInterceptor(); 
	
	
	@Before
	public void setUp() throws IOException {
		when(mockResponse.getWriter()).thenReturn(mockPrintWriter);
	}
	
	@Test
	public void whenApiKeyIsNotPresentThenReturnFalse() throws Exception {
		when(mockRequest.getHeader(API_KEY)).thenReturn(null);
		
		Boolean isApiKeyValid = interceptor.preHandle(mockRequest, mockResponse, mockHandler);
		
		assertEquals(false, isApiKeyValid);
	}
	
	@Test
	public void whenApiKeyIsPresentAndIsNotValidThenReturnFalse() throws Exception {
		when(mockRequest.getHeader(API_KEY)).thenReturn("123456");
		
		Boolean isApiKeyValid = interceptor.preHandle(mockRequest, mockResponse, mockHandler);
		
		assertEquals(false, isApiKeyValid);
	}
	
	@Test
	public void whenApiKeyIsPresentAndIsValidThenReturnTrue() throws Exception {
		when(mockRequest.getHeader(API_KEY)).thenReturn("5634563846456");
		
		Boolean isApiKeyValid = interceptor.preHandle(mockRequest, mockResponse, mockHandler);
		
		assertEquals(true, isApiKeyValid);
	}
	
}
