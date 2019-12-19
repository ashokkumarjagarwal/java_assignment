package com.test.serviveImpl;

import com.test.entity.Role;
import com.test.entity.User;
import com.test.entity.UserRole;
import com.test.exception.ValidationException;
import com.test.repositoryImpl.UserRepositoryImpl;
import com.test.responseDTO.UserRegisterDto;
import com.test.util.CommonUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import static com.test.util.ApplicationConstant.*;

@Service
public class UserService {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CommonUtil commonUtil;


    public UserRegisterDto register(MultipartFile file){
        UserRegisterDto response = new UserRegisterDto();

        Map<String,Role> roleEntities = new HashMap<>();
        List<String> roleNameList = new ArrayList<>();

        List<Role> roleList = roleService.findAll();
        roleList.forEach(item->{
            roleEntities.put(item.getName(),item);
            roleNameList.add(item.getName());
        });

        List<String> registeredEmails = userRepository.getRegisteredUserEmails();
        int rowParsed[] ={0};
        String fileUrl = commonUtil.generateUUID()+".csv";
        try {
            CSVParser records = CSVFormat.EXCEL.withHeader().parse(new InputStreamReader(file.getInputStream()));
            List<String[]> errors = new ArrayList<>();
            List<User> createUserList = new ArrayList<>();
            records.forEach(item->{
                rowParsed[0]++;
                String email = item.get(EMAIL);
                String name = item.get(NAME);
                String role = item.get(ROLE);
                if (registeredEmails.contains(email)){
                    String arr[] = {name,email,role,DUPLICATE_EMAIL};
                    errors.add(arr);
                }else{
                    boolean isValidUser = true;
                    List<String>errorMgs = new ArrayList<>();
                    if (!commonUtil.isValidEmail(email)){
                        errorMgs.add(INVALID_EMAIL);
                        isValidUser=false;
                    }
                    if (commonUtil.isNullOrEmpty(name)){
                        isValidUser = false;
                        errorMgs.add(NAME_REQUIRED);
                    }
                    if (commonUtil.isNullOrEmpty(role)){
                        isValidUser = false;
                        errorMgs.add(ROLE_REQUIRED);
                    }
                    registeredEmails.add(email);
                    User u = new User();
                    u.setId(commonUtil.generateUUID());
                    u.setName(name);
                    u.setEmail(email);
                    u.setUserRole(new ArrayList<>());

                    for (String roleName:role.split("#")) {
                        if (roleNameList.contains(roleName)){
                            Role r = roleEntities.get(roleName);
                            UserRole userRole = new UserRole();
                            userRole.setId(commonUtil.generateUUID());
                            userRole.setRole(r);
                            userRole.setUser(u);
                            u.getUserRole().add(userRole);
                        }else {
                            errorMgs.add(INVALID_ROLE+" "+roleName);
                        }
                    }
                    if (errorMgs.size()>0){
                        String arr[] = {name,email,role,String.join("#",errorMgs)};
                        errors.add(arr);
                    }
                    if (isValidUser)
                        createUserList.add(u);

                }

            });
            if (createUserList.size()>0){
                userRepository.saveEntityList(createUserList);
            }
            if (errors.size()>0){
                writeErrorFIle(errors,fileUrl);
            }
            response.setNoOfrowParsed(rowParsed[0]);
            response.setErrorFileUrl(fileUrl);
            response.setNoOfUserCreated(createUserList.size());
            response.setNoOfrowFailed(errors.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public void writeErrorFIle(List<String[]> errors,String fileUrl){
        try{
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(System.getProperty("user.dir")+"/../"+fileUrl)); CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Name", "Email", "Role", "Errors"));) {
                for (String arr[]:errors){
                    csvPrinter.printRecord(arr);
                }
                csvPrinter.flush();
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public void download(String fileUrl,HttpServletResponse response) throws ValidationException {
        try {
            File file = new File(System.getProperty("user.dir")+"/../" + fileUrl);
            if (file.exists()) {
                response.setContentType("text/csv");
                response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
                response.setContentLength((int) file.length());
                InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                FileCopyUtils.copy(inputStream, response.getOutputStream());
            }else {
                throw new ValidationException(HttpStatus.BAD_REQUEST.value(),"File not found");
            }
        }catch (Exception e){
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(),"File not found");

        }
    }
}
