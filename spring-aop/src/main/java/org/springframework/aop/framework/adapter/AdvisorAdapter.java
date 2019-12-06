/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.aop.framework.adapter;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import org.springframework.aop.Advisor;

/**
 * Interface allowing extension to the Spring AOP framework to allow
 * handling of new Advisors and Advice types.
 *
 * <p>Implementing objects can create AOP Alliance Interceptors from
 * custom advice types, enabling these advice types to be used
 * in the Spring AOP framework, which uses interception under the covers.
 *
 * <p>There is no need for most Spring users to implement this interface;
 * do so only if you need to introduce more Advisor or Advice types to Spring.
 *
 * 接口允许对Spring AOP框架进行扩展，以允许处理新的顾问和建议类型。<p>实现对象
 * 可以从定制的通知类型创建AOP联盟拦截器，从而使这些通知类型能够在springaop框架中
 * 使用，该框架在底层使用拦截。<p>大多数Spring用户不需要实现这个接口；只有在需要向
 * Spring引入更多的Advisor或Advice类型时才需要这样做。
 *
 * @author Rod Johnson
 */
public interface AdvisorAdapter {

	/**
	 * Does this adapter understand this advice object? Is it valid to
	 * invoke the {@code getInterceptors} method with an Advisor that
	 * contains this advice as an argument?
	 * @param advice an Advice such as a BeforeAdvice
	 * @return whether this adapter understands the given advice object
	 * @see #getInterceptor(org.springframework.aop.Advisor)
	 * @see org.springframework.aop.BeforeAdvice
	 */
	boolean supportsAdvice(Advice advice);

	/**
	 * Return an AOP Alliance MethodInterceptor exposing the behavior of
	 * the given advice to an interception-based AOP framework.
	 * <p>Don't worry about any Pointcut contained in the Advisor;
	 * the AOP framework will take care of checking the pointcut.
	 * @param advisor the Advisor. The supportsAdvice() method must have
	 * returned true on this object
	 * @return an AOP Alliance interceptor for this Advisor. There's
	 * no need to cache instances for efficiency, as the AOP framework
	 * caches advice chains.
	 */
	MethodInterceptor getInterceptor(Advisor advisor);

}
