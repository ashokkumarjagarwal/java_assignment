package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaAsignmentApplicationTests {

	@MockBean
	private RoleRepositoryImpl roleRepository;

	@MockBean
	private UserRepositoryImpl userRepository;

	@Autowired
	private UserService userService;

	@Test
	public void createUserSuccess() {
		Mockito.when(roleRepository.findAll()).thenReturn(getRoleList());
		List<String> userEmail = new ArrayList<>();
		Mockito.when(userRepository.getRegisteredUserEmails()).thenReturn(userEmail);
		Mockito.doNothing().when(userRepository).saveEntityList(new ArrayList<User>());
		String data = "Name,Email,Role,\nA,A@gmail.com,SA#ADMIN\nB,B@gmail.com,ADMIN,\nC,C@gmail.com,USER";
		MockMultipartFile multipartFile = new MockMultipartFile("xyz.csv","xyz.csv","text/csv",data.getBytes());
		UserService u = Mockito.mock(UserService.class);
		Mockito.doNothing().when(u).writeErrorFIle(new ArrayList<>(),"");

		UserRegisterDto userRegisterDto =userService.register(multipartFile);

		Assert.assertEquals(3,userRegisterDto.getNoOfrowParsed());
		Assert.assertEquals(0,userRegisterDto.getNoOfrowFailed());
		Assert.assertEquals(3,userRegisterDto.getNoOfUserCreated());
		Assert.assertEquals(true,userRegisterDto.getErrorFileUrl()!=null);
	}

	@Test
	public void createUserWithFailureRow() {
		Mockito.when(roleRepository.findAll()).thenReturn(getRoleList());
		List<String> userEmail = new ArrayList<>();
		Mockito.when(userRepository.getRegisteredUserEmails()).thenReturn(userEmail);
		Mockito.doNothing().when(userRepository).saveEntityList(new ArrayList<User>());
		String data = "Name,Email,Role,\nA,A@gmail.com,SA#ADMIN\nD,Dgmail.com,ADMIN,\nE,E@gmail.com,USER#SU\nF,F@gmail.com,USER";
		MockMultipartFile multipartFile = new MockMultipartFile("xyz.csv","xyz.csv","text/csv",data.getBytes());
		UserService u = Mockito.mock(UserService.class);
		Mockito.doNothing().when(u).writeErrorFIle(new ArrayList<>(),"");

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
