package com.appspot.ginkotag;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TextSplitterUtilTest {

	@Test
	public void testSplitEmptyString() {
		String[] results = TextSplitterUtil.split("", 10);
		assertEquals(0, results.length);
	}

	@Test
	public void testSplitNullString() {
		String[] results = TextSplitterUtil.split(null, 10);
		assertEquals(0, results.length);
	}

	@Test
	public void testSplitCollatedString() {
		String[] results = TextSplitterUtil.split("bonjourmonsieur", 5);
		assertEquals(1, results.length);
		assertEquals("bonjourmonsieur", results[0]);
	}

	@Test
	public void testSplitSimpleString() {
		String[] results = TextSplitterUtil.split("bonjour monsieur", 5);
		assertEquals(2, results.length);
		assertEquals("bonjour", results[0]);
		assertEquals("monsieur", results[1]);
	}

	@Test
	public void testSplitString() {
		String[] results = TextSplitterUtil.split("bonjour monsieur dame", 5);
		assertEquals(3, results.length);
		assertEquals("bonjour", results[0]);
		assertEquals("monsieur", results[1]);
		assertEquals("dame", results[2]);
	}
}
