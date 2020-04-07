package com.example.todo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PreDestroy;

@Slf4j
public class EhCacheConfiguration {

	EhCacheCacheManager ehCacheCacheManager;

	@Bean
	@Primary
	public CacheManager appCacheManager() {
		ClassPathResource ehCacheConfigFile = new ClassPathResource("ehcache.xml");
		ehCacheCacheManager = new EhCacheCacheManager(EhCacheManagerUtils.buildCacheManager(ehCacheConfigFile));
		return ehCacheCacheManager;

	}

	@PreDestroy
	public void destroy() {
		log.info("Shutting down applicationCache.");
		this.ehCacheCacheManager.getCacheManager();
	}
}
