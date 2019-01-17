package com.web.wx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity //启用web权限
@EnableGlobalMethodSecurity(prePostEnabled = true) //启用方法验证
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


	/** 定义认证用户信息获取来源，密码校验规则等 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	}

	/** 定义安全策略 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); // 禁用CSRF
		http.headers().frameOptions().disable();
	}
	
	
}
