package com.kingdee.spider.views;

import static org.junit.Assert.*;

import org.junit.Test;

public class JavaCodeTest {
	@Test
	public void testGetText() {
		JavaCodeModelBuilder builder=new JavaCodeModelBuilder();
		builder.buildTypeDeclaration("Demo");
		builder.endBuildTypeDeclaration("Demo");
		assertEquals(1, builder.getResult().length);
	}
}