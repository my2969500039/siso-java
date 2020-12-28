package com.siso.token;

import com.siso.dto.CMSPermissionDTO;
import com.siso.dto.CMSUserDTO;
import com.siso.entity.android.userManage.androidUser;
import com.siso.entity.web.userManage.adminUser;
import com.siso.exception.NormalException;
import com.siso.repository.web.userManage.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
/**
 * @author cnyuchu@gmail.com
 * @date 2019/3/4 15:07
 */
@Component
@Slf4j
public class TokenUtils {

    private static final String TOKEN_PREFIX = "TOKEN:USER:";

    private static final String USER_SURVIVE = "SURVIVE:";

    private static final String PHONE = "PHONE:";

    @Resource
    private  RedisTemplate<String, Serializable> redisCacheTemplate;

    @Autowired
    private UserRepository userRepository;


    /**
     * 根据token从redis获取用户
     *
     * @return
     */
    public CMSUserDTO getLoginUserDTO(String token) {
        final CMSUserDTO user = (CMSUserDTO) redisCacheTemplate.opsForValue().get(TOKEN_PREFIX + token);
        if (user != null) {
            if ((TOKEN_PREFIX + token).equals(redisCacheTemplate.opsForValue().get(USER_SURVIVE + user.getId()))) {
                return user;
            }
        }
        return null;
    }

    /**
     * 根据token从redis获取用户
     * @return
     */
    public CMSUserDTO getLoginUserDTO() {
        try {
            String token = SecurityUtils.getSubject().getPrincipal().toString();
            return this.getLoginUserDTO(token);
        } catch (Exception e) {   //token不存在
            return null;
        }
    }

    public String getToken() {
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        if (null == token) {
            throw new NormalException("token获取失败");
        }
        return token;
    }

    /**
     * 从数据库中查询
     *
     * @return
     */
    public CMSUserDTO getUserInfo() {
        try {
            String token = SecurityUtils.getSubject().getPrincipal().toString();
            CMSUserDTO loginUser = this.getLoginUserDTO(token);
            return this.getUserInfo(loginUser.getId());
        } catch (Exception e) {  //token不存在
            return null;
        }
    }


    /**
     * 从数据库中查询
     *
     * @return
     */
    public CMSUserDTO getUserInfo(Long userId) {
        Optional<adminUser> cmsUserOptional = userRepository.findById(userId);
        if (cmsUserOptional.isEmpty()) {
            return null;
        }
        adminUser cmsUser = cmsUserOptional.get();
        return this.convertCMSUser(cmsUser);
    }

    /**
     * 生成token---后端用户
     *
     * @param cmsUser
     * @return
     */
    public String generateTokeCode(adminUser cmsUser, List<CMSPermissionDTO> cmsPermissionDTOS) {
        CMSUserDTO cmsUserDTO = this.convertCMSUser(cmsUser);
        cmsUserDTO.setPermissions(cmsPermissionDTOS);
        String value = System.currentTimeMillis() + new Random().nextInt() + "";
        //获取数据指纹，指纹是唯一的
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] b = md.digest(value.getBytes());//产生数据的指纹
            //Base64编码
            String key = Base64.getEncoder().encodeToString(b);
            redisCacheTemplate.opsForValue().set(TOKEN_PREFIX + key, cmsUserDTO);
            redisCacheTemplate.expire(TOKEN_PREFIX + key, 6, TimeUnit.HOURS);
            redisCacheTemplate.opsForValue().set(USER_SURVIVE + cmsUserDTO.getId(), TOKEN_PREFIX + key);  //存活的token更新
            return key;
        } catch (NoSuchAlgorithmException e) {
            throw new NormalException("token生成失败");
        }
    }

    public String generateTokeCodeAndroid(androidUser cmsUser) {
        CMSUserDTO cmsUserDTO = this.convertCMSUser(cmsUser);
        String value = System.currentTimeMillis() + new Random().nextInt() + "";
        //获取数据指纹，指纹是唯一的
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] b = md.digest(value.getBytes());//产生数据的指纹
            //Base64编码
            String key = Base64.getEncoder().encodeToString(b);
            redisCacheTemplate.opsForValue().set(TOKEN_PREFIX + key, cmsUserDTO);
            redisCacheTemplate.expire(TOKEN_PREFIX + key, 6, TimeUnit.HOURS);
            redisCacheTemplate.opsForValue().set(USER_SURVIVE + cmsUserDTO.getId(), TOKEN_PREFIX + key);  //存活的token更新
            return key;
        } catch (NoSuchAlgorithmException e) {
            throw new NormalException("token生成失败");
        }
    }





    public String refreshToken() {
        CMSUserDTO cmsUserDTO = this.getLoginUserDTO();
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        redisCacheTemplate.opsForValue().set(TOKEN_PREFIX + token, cmsUserDTO);
        redisCacheTemplate.expire(TOKEN_PREFIX + token, 6, TimeUnit.HOURS);
        redisCacheTemplate.opsForValue().set(USER_SURVIVE + cmsUserDTO.getId(), TOKEN_PREFIX + token);  //存活的token更新
        return token;
    }

    /**
     * 设置token过期
     */
    public void setExpired() {
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        redisCacheTemplate.delete(token);
    }

    /**
     * 设置token过期
     */
    public void setExpired(List<String> ids) {
        ids.stream().map(id -> (String) redisCacheTemplate.opsForValue().get(USER_SURVIVE + id)).forEach(token -> {
            if (token != null && redisCacheTemplate.hasKey(token)) {
                redisCacheTemplate.delete(token);
            }
        });
    }

    private CMSUserDTO convertCMSUser(adminUser cmsUser) {
        CMSUserDTO parkUserDTO = new CMSUserDTO();
        BeanUtils.copyProperties(cmsUser, parkUserDTO);
        return parkUserDTO;
    }


    private CMSUserDTO convertCMSUser(androidUser cmsUser) {
        CMSUserDTO parkUserDTO = new CMSUserDTO();
        BeanUtils.copyProperties(cmsUser, parkUserDTO);
        return parkUserDTO;
    }


}
