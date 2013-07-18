package com.kingdee.spider.views;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.kingdee.spider.model.IJavaCodeElement;
import com.kingdee.spider.model.ITreeNode;
import com.kingdee.spider.model.JavaCodeBuilder;
import com.kingdee.spider.model.TypeDeclarationCode;

public class JavaCodeTest {

	@Test
	public void testBuildOneType() {
		JavaCodeBuilder builder = new JavaCodeBuilder();
		builder.buildTypeDeclaration("Demo1");
		builder.buildMethodDeclaration("demo1_method1");
		builder.buildMethodInvocation("doBiz1");
		builder.buildMethodInvocation("doBiz2");
		builder.buildMethodInvocation("doBiz3");
		builder.buildMethodInvocation("doBiz4");
		builder.buildMethodDeclaration("demo1_method2");
		builder.buildMethodDeclaration("demo1_method3");
		builder.endBuildTypeDeclaration("Demo1");

		List<TypeDeclarationCode> result = builder.getResult();
		assertEquals(1, result.size());

		IJavaCodeElement demo1_type = result.get(0);
		List<ITreeNode> demo1_methods = demo1_type.getChildren();
		assertEquals(3, demo1_methods.size());

		ITreeNode treeNode = demo1_methods.get(0);
		assertEquals(4, treeNode.getChildren().size());
	}

	@Test
	public void testBuildTwoType() {
		JavaCodeBuilder builder = new JavaCodeBuilder();
		builder.buildTypeDeclaration("Demo1");
		builder.buildMethodDeclaration("demo1_method1");
		builder.buildMethodDeclaration("demo1_method2");
		builder.buildMethodDeclaration("demo1_method3");
		builder.endBuildTypeDeclaration("Demo1");

		builder.buildTypeDeclaration("Demo2");
		builder.buildMethodDeclaration("demo2_method1");
		builder.buildMethodDeclaration("demo2_method2");
		builder.endBuildTypeDeclaration("Demo2");

		List<TypeDeclarationCode> result = builder.getResult();
		assertEquals(2, result.size());

		IJavaCodeElement demo1_type = result.get(0);
		List<ITreeNode> demo1_methods = demo1_type.getChildren();
		assertEquals(3, demo1_methods.size());

		IJavaCodeElement demo2_type = result.get(1);
		List<ITreeNode> demo2_methods = demo2_type.getChildren();
		assertEquals(2, demo2_methods.size());
	}

	@Test
	public void testBuildNest() {
		JavaCodeBuilder builder = new JavaCodeBuilder();
		builder.buildTypeDeclaration("Demo1");
		builder.buildMethodDeclaration("demo1_method1");
		builder.buildTypeDeclaration("Demo2");
		builder.buildMethodDeclaration("demo2_method1");
		builder.buildMethodDeclaration("demo2_method2");
		builder.endBuildTypeDeclaration("Demo2");
		builder.buildMethodDeclaration("demo1_method2");
		builder.buildMethodDeclaration("demo1_method3");
		builder.endBuildTypeDeclaration("Demo1");

		List<TypeDeclarationCode> result = builder.getResult();
		assertEquals(2, result.size());

		IJavaCodeElement demo1_type = result.get(0);
		List<ITreeNode> demo1_methods = demo1_type.getChildren();
		assertEquals(3, demo1_methods.size());

		IJavaCodeElement demo2_type = result.get(1);
		List<ITreeNode> demo2_methods = demo2_type.getChildren();
		assertEquals(2, demo2_methods.size());
	}
}