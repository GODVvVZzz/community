package com.nowcoder.community;
import com.nowcoder.community.util.SensitiveFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author VvV
 * @date 2022/7/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class SensitiveTests {

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSensitiveFilter() {
        String text = "这里可以赌博，可以嫖娼，可以吸毒，可以开票，哈哈哈！";
        text = sensitiveFilter.filter(text);
        System.out.println(text);

        text = "这里可以赌博，可以★嫖☆娼☆，可以★吸★毒★，可以★开★票★，★哈哈哈！";
        text = sensitiveFilter.filter(text);
        System.out.println(text);
    }

    @Test
    public void test(){
        int num = 211;
        String str = String.valueOf(num);
        int i = 2;
        System.out.println(str.charAt(i - 1));
        System.out.println(str.substring(i - 1, i + 1));
    }

}
