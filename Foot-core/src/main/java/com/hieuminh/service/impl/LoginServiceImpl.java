package com.hieuminh.service.impl;

import com.hieuminh.constant.ConfigurationConstant;
import com.hieuminh.converter.LoginConverter;
import com.hieuminh.dto.LoginDTO;
import com.hieuminh.dto.MailDTO;
import com.hieuminh.dto.UserDTO;
import com.hieuminh.entity.ConfigurationEntity;
import com.hieuminh.entity.LoginEntity;
import com.hieuminh.entity.UserEntity;
import com.hieuminh.repository.ConfigurationDao;
import com.hieuminh.repository.LoginDao;
import com.hieuminh.service.LoginService;
import com.hieuminh.service.MailService;
import com.hieuminh.utils.StringGenerate;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private MailService mailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ConfigurationDao configurationDao;


    @Override
    public void saveLogin(LoginDTO loginDTO) {

        LoginEntity entity = LoginConverter.dtoToEntity(loginDTO);
        entity.setPassWord(passwordEncoder.encode(loginDTO.getPassWord()));
        loginDao.save(entity);
    }

    @Override
    public LoginDTO findByUserName(String property, Object value) {
        try{
            LoginEntity loginEntity = loginDao.findEqualUnique(property,value);
            LoginDTO loginDTO = LoginConverter.entityToDto(loginEntity);


            return loginDTO;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public LoginDTO findByIdUser(String property,Object value) {
        LoginEntity loginEntity = loginDao.findEqualUnique(property, value);
        LoginDTO loginDTO = LoginConverter.entityToDto(loginEntity);
        return loginDTO;
    }
    @Override
    public LoginDTO updateLogin(LoginDTO loginDTO) {
        LoginEntity loginEntity = LoginConverter.dtoToEntity(loginDTO);
        loginEntity =  loginDao.update(loginEntity);
        loginDTO = LoginConverter.entityToDto(loginEntity);
        return loginDTO;
    }

    @Override
    public Integer deleteLogin(List<Integer> ids) throws HibernateException {
        Integer result = loginDao.delete(ids);
        return result;
    }

    private void sentMailToUser(LoginEntity user, String pass) {
        MailDTO mail = new MailDTO();

        mail.setMailFrom("hieu6908@gmail.com");
        String[] mailTo = new String[]{user.getIdLoginUserEntity().getEmail()};
        mail.setMailTo(mailTo);
        Map<String, Object> model = new HashMap<>();
        model.put("nameLogin", user.getNameLogin());
        model.put("password", pass);
        mail.setModel(model);

        ConfigurationEntity configuration = configurationDao.findEqualUnique("key",ConfigurationConstant.TEMPLATE_USER_CREATE);
        mail.setTemplate(configuration.getValue());
        mailService.sendEmail(mail);
    }

    @Override
    public LoginDTO resetPassword(String email) {
            LoginEntity loginEntity = loginDao.findEqualUnique("idLoginUserEntity.email",email);
            String passwordGenerate = StringGenerate.generateValue(8);
            loginEntity.setPassWord(passwordEncoder.encode(passwordGenerate));
            sentMailToUser(loginEntity, passwordGenerate);
            return LoginConverter.entityToDto(loginEntity);
    }
}
