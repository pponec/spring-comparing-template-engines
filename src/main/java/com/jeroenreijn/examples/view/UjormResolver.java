package com.jeroenreijn.examples.view;

import org.springframework.web.servlet.view.AbstractTemplateViewResolver;

public final class UjormResolver extends AbstractTemplateViewResolver {
	public UjormResolver() {
		this.setViewClass(this.requiredViewClass());
	}

	@Override
	protected Class<?> requiredViewClass() {
		return UjormView.class;
	}
}
