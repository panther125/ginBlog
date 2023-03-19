package com.panther.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panther.common.ResponseResult;
import com.panther.common.SystemConstants;
import com.panther.enums.AppHttpCodeEnum;
import com.panther.exception.SystemException;
import com.panther.mapper.UserMapper;
import com.panther.model.DTO.UserDTO;
import com.panther.model.VO.UserDetailVo;
import com.panther.model.VO.UserInfoVo;
import com.panther.model.VO.UserListVo;
import com.panther.model.VO.UserVo;
import com.panther.model.entity.Role;
import com.panther.model.entity.User;
import com.panther.service.RoleService;
import com.panther.service.UserService;
import com.panther.utils.BeanCopyUtils;
import com.panther.utils.SecurityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author Gin 琴酒
 * @data 2023/3/14 23:24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private RoleService roleService;
    @Override
    public ResponseResult userInfo() {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        //根据用户id查询用户信息
        User user = getById(userId);
        //封装成UserInfoVo
        UserInfoVo vo = BeanCopyUtils.copyBean(user,UserInfoVo.class);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult register(User user) {
        //对数据进行非空判断
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        //对数据进行是否存在的判断
        if(userNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(nickNameExist(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
        //...
        //对密码进行加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        //存入数据库
        save(user);
        return ResponseResult.okResult();
    }

    @Override
    public UserVo selectUserAndRoles(Long id) {
        // 查询用户ID所对应的角色ID
        List<Long> longs = baseMapper.queryRoleIDByUserId(id);
        // 查询所有的角色信息
        List<Role> list = roleService.list(null);
        // 查询用户信息封装成VO
        User user = baseMapper.selectById(id);
        UserDetailVo userDetailVo = BeanCopyUtils.copyBean(user, UserDetailVo.class);
        // 组合返回
        return new UserVo(longs,list,userDetailVo);
    }

    @Override
    public UserListVo Userlist(int pageNum, int pageSize) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        Page<User> userpage = new Page<>(pageNum,pageSize);
        page(userpage,wrapper);
        return new UserListVo(userpage.getRecords(),userpage.getTotal());
    }

    @Override
    public void updateUserAndRole(UserDTO userDTO) {
        // 跟新用户
        User user = BeanCopyUtils.copyBean(userDTO, User.class);
        baseMapper.updateById(user);
        // 更新对应角色ID
        List<Long> roleIds = userDTO.getRoleIds();
//        String sqlStatement = this.getSqlStatement(SqlMethod.INSERT_ONE);
//        this.executeBatch(roleIds, 1000, (sqlSession, entity) -> {
//            sqlSession.insert(sqlStatement, entity);
//        });
    }

    private boolean nickNameExist(String nickName) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getNickName,nickName);
        // 判断用户在数据库中是否存在
        if(Objects.isNull(baseMapper.selectOne(wrapper))){
            return false;
        }
        return true;
    }

    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,userName);
        // 判断用户在数据库中是否存在
        if(Objects.isNull(baseMapper.selectOne(wrapper))){
            return false;
        }
        return true;
    }
}
