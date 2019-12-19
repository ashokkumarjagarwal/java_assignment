package com.test;

import com.test.entity.Role;
import com.test.entity.User;
import com.test.repositoryImpl.RoleRepositoryImpl;
import com.test.repositoryImpl.UserRepositoryImpl;
import com.test.responseDTO.UserRegisterDto;
import com.test.serviveImpl.UserService;
import com.test.util.FileUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaAsignmentApplicationTests {

	@MockBean
	private RoleRepositoryImpl roleRepository;

	@MockBean
	private UserRepositoryImpl userRepository;

	@Autowired
	private UserService userService;

	@MockBean
	private FileUtil fileUtil;

	@Description("Create all user successfully")
	@Test
	public void createUserSuccess() {
		Mockito.when(roleRepository.findAll()).thenReturn(getRoleList());
		List<String> userEmail = new ArrayList<>();
		Mockito.when(userRepository.getRegisteredUserEmails()).thenReturn(userEmail);
		Mockito.doNothing().when(userRepository).saveEntityList(new ArrayList<User>());
		String data = "Name,Email,Role,\nA,A@gmail.com,SA#ADMIN\nB,B@gmail.com,ADMIN,\nC,C@gmail.com,USER";
		MockMultipartFile multipartFile = new MockMultipartFile("xyz.csv","xyz.csv","text/csv",data.getBytes());
		Mockito.doNothing().when(fileUtil).writeErrorFIle(new ArrayList<>(),"");

		UserRegisterDto userRegisterDto =userService.register(multipartFile);

		Assert.assertEquals(3,userRegisterDto.getNoOfrowParsed());
		Assert.assertEquals(0,userRegisterDto.getNoOfrowFailed());
		Assert.assertEquals(3,userRegisterDto.getNoOfUserCreated());
		Assert.assertEquals(true,userRegisterDto.getErrorFileUrl()!=null);
	}

	@Description("Create some user successfully and some failure")
	@Test
	public void createUserWithFailureRow() {
		Mockito.when(roleRepository.findAll()).thenReturn(getRoleList());
		List<String> userEmail = new ArrayList<>();
		Mockito.when(userRepository.getRegisteredUserEmails()).thenReturn(userEmail);
		Mockito.doNothing().when(userRepository).saveEntityList(new ArrayList<User>());
		String data = "Name,Email,Role,\nA,A@gmail.com,SA#ADMIN\nD,Dgmail.com,ADMIN,\nE,E@gmail.com,USER#SU\nF,F@gmail.com,USER";
		MockMultipartFile multipartFile = new MockMultipartFile("xyz.csv","xyz.csv","text/csv",data.getBytes());
		Mockito.doNothing().when(fileUtil).writeErrorFIle(new ArrayList<>(),"");

		UserRegisterDto userRegisterDto =userService.register(multipartFile);

		Assert.assertEquals(4,userRegisterDto.getNoOfrowParsed());
		Assert.assertEquals(2,userRegisterDto.getNoOfrowFailed());
		Assert.assertEquals(3,userRegisterDto.getNoOfUserCreated());
		Assert.assertEquals(true,userRegisterDto.getErrorFileUrl()!=null);
	}

	private List<Role> getRoleList(){
		List<Role> roleList = new ArrayList<Role>();
		Role r1 = new Role();
		r1.setName("SA");
		Role r2 = new Role();
		r2.setName("ADMIN");
		Role r3 = new Role();
		r3.setName("USER");
		roleList.add(r1);
		roleList.add(r2);
		roleList.add(r3);
		return roleList;
	}

}
