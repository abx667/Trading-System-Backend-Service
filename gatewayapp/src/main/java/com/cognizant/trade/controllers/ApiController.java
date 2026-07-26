package com.cognizant.trade.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

import com.cognizant.trade.services.TokenService;

import reactor.core.publisher.Mono;

@RestController
public class ApiController {
	@Autowired
	private TokenService tokenService;
	@GetMapping(value = "/token")
	public Mono<String> getHome(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
		return Mono.just(authorizedClient.getAccessToken().getTokenValue());
	}

	@GetMapping("/")
	public Mono<String> index(WebSession session) {
		return Mono.just(session.getId());
	}
	 @GetMapping("/token/admin")
	    public Mono<String> developerToken() {
	        return tokenService.getToken("keycloak-with-admin-scope");
	    }

	    @GetMapping("/token/trader")
	    public Mono<String> testerToken() {
	        return tokenService.getToken("keycloak-with-trader-scope");
	    }

}