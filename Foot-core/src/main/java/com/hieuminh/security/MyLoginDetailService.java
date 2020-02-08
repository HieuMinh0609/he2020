package com.hieuminh.security;

import com.hieuminh.dto.LoginDTO;
import com.hieuminh.dto.RoleDTO;
import com.hieuminh.entity.LoginEntity;
import com.hieuminh.entity.RoleEntity;
import com.hieuminh.repository.LoginDao;
import com.hieuminh.service.LoginService;
import com.hieuminh.utils.MyLoginDetail;
import org.slf4j.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyLoginDetailService implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(MyLoginDetailService.class);

    @Autowired
    private LoginService loginService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginDTO  user= loginService.findByUserName("nameLogin",username);
        if(user==null ){
            log.error("User name not found");
            throw  new UsernameNotFoundException("User name not found");
        }else{
            if(user.getIdLoginUserEntity().getBlock()==0){
                List<GrantedAuthority>  authorities = new ArrayList<GrantedAuthority>();
       /* for (RoleDTO roleDTO: (List<RoleDTO>) user.getIdLoginUserEntity().getIdRoleEntity()){
            authorities.add(new SimpleGrantedAuthority(roleDTO.getRoleName()));
        }*/
                authorities.add(new SimpleGrantedAuthority(user.getIdLoginUserEntity().getIdRoleEntity().getRoleName()));

                MyLoginDetail myLoginDetail =new MyLoginDetail(username,user.getPassWord(),true,true,true,true,authorities);
                myLoginDetail.setSex(user.getIdLoginUserEntity().getSex());
                myLoginDetail.setNameFull(user.getIdLoginUserEntity().getNameFull());
                BeanUtils.copyProperties(user,myLoginDetail);

                return myLoginDetail;
            }else{
                log.error("User name not found");
                throw  new UsernameNotFoundException("User name not found");
            }
        }
        }

}
