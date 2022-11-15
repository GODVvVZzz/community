package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.CommunityUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author VvV
 * @date 2022/7/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectByIdTest(){
        System.out.println(userMapper);
        User user = userMapper.selectById(11);
        System.out.println(user);
    }

    @Test
    public void selectByNameTest(){
        System.out.println(userMapper);
        User user = userMapper.selectByName("nowcoder11");
        System.out.println(user);
    }

    @Test
    public void selectByEmailTest(){
        System.out.println(userMapper);
        User user = userMapper.selectByEmail("nowcoder13@sina.com");
        System.out.println(user);
    }


    @Test
    public void insertUserTest(){
        User user = new User();
        user.setUsername("vvv");
        user.setPassword("2378478f8f89f9fg");
        user.setSalt("49f10");
        user.setEmail("vvv.@163.com");
        user.setType(1);
        user.setStatus(0);
        user.setHeaderUrl("http://images.nowcoder.com/head/333.png");
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        user.setCreateTime(date);

        int result = userMapper.insertUser(user);
        System.out.println(result);
    }

    @Test
    public void updateStatusTest(){
        int result = userMapper.updateStatus(11,0);
        System.out.println(result);
    }

    @Test
    public void updateHeaderTest(){
        int result = userMapper.updateHeader(150,"http://images.nowcoder.com/head/110.png");
        System.out.println(result);
    }

    @Test
    public void updatePassword(){
        int result = userMapper.updatePassword(150,"1100101");
        System.out.println(result);
    }

    @Test
    public void deleteByIdTest(){
        int result = userMapper.deleteById(150);
        System.out.println(result);
    }

    @Autowired
    private DiscussPostMapper discussPostMapper;

/*    @Test
    public void DiscussPostMapperTest(){
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(0, 0, 10);
        for (DiscussPost discussPost : discussPosts) {
            System.out.println(discussPost);
        }
        int rowsCount = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(rowsCount);
    }*/

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Test
    public void InsertLoginTicketTest(){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(1);
        loginTicket.setTicket(CommunityUtil.generateUUID());
        loginTicket.setStatus(0);
        //10min
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 * 10));
        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    @Test
    public void LoginTicketMapperTest(){
        loginTicketMapper.updateStatus("eb6d63a202894606b659f8d6e2052dd8",1);

        System.out.println(loginTicketMapper.selectByTicket("eb6d63a202894606b659f8d6e2052dd8"));
    }

}
