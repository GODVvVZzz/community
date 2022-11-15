package com.nowcoder.community;

import com.nowcoder.community.util.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author VvV
 * @date 2022/7/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTests {

    @Autowired
    private MailClient mailClient;

    @Test
    public void testTextMail(){
        mailClient.sendMail("2662446324@qq.com","Test","测试用代码发送邮件！2");
    }

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testHtmlMail(){
        //设置给html模板的参数
        Context context = new Context();
        context.setVariable("name","VvV");

        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);

        mailClient.sendMail("2662446324@qq.com","HtmlMailTest", content);
    }
}
