package com.warumono.app.controllers.interfaces;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.warumono.app.enums.Provider;
import com.warumono.app.helpers.AppConstant.Swagger.DataType;
import com.warumono.app.helpers.AppConstant.Swagger.ParamType;
import com.warumono.app.helpers.AppConstant.Swagger.Tag;
import com.warumono.app.helpers.annotations.CurrentUsername;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "/", tags = { "ClientController" })
@RequestMapping(value = "/")
public interface AppControllerInterface
{
	@ApiOperation(value = "사용자 정보", notes = "사용자 정보를 조회한다.<br/>", tags = { Tag.INCOMPLETED })
	@ApiImplicitParams({})
	@GetMapping(value = "user/me")
	Principal me(Principal principal);
	
	@ApiOperation(value = "OAuth2 로그인", notes = "OAuth2 로그인 API 를 테스트한다.<br/><b>Usage in console</b> : <br/>curl -X POST -u client:secret http://localhost:8080/oauth/token -d \"grant_type=password&username=auser&password=apassword\"<br/>", tags = { Tag.COMPLETED })
	@ApiImplicitParams
	({
		@ApiImplicitParam(name = "provider_id", value = "Provider ID (facebook, kakao, ...)", dataType = "Provider", paramType = ParamType.PATH, required = false, defaultValue = "CHERRYPICA"), 
		@ApiImplicitParam(name = "username", value = "아이디", dataType = DataType.STRING, paramType = ParamType.QUERY, required = false, defaultValue = "auser"), 
		@ApiImplicitParam(name = "password", value = "비밀번호", dataType = DataType.STRING, paramType = ParamType.QUERY, required = false, defaultValue = "apassword")
	})
	@RequestMapping(value = "auth/{provider_id}", method = { RequestMethod.GET, RequestMethod.POST })
	ResponseEntity<Object> login
	(
		@PathVariable(value = "provider_id") Provider provider_id, 
		@RequestParam(value = "username", required = false) String username, @RequestParam(value = "password", required = false) String password
	);

	@PreAuthorize("hasRole('USER')")
	@ApiOperation(value = "사용자 접근 권한", notes = "사용자 정보 API 를 테스트한다.<br/>", tags = { Tag.INCOMPLETED })
	@ApiImplicitParams({})
	@GetMapping(value = "test/role")
	ResponseEntity<Object> testRole(@CurrentUsername String username, OAuth2Authentication authentication);
}
