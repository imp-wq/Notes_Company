import com.itheima.util.CheckCodeUtil;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class TestCheckCode {
    @Test
    public void testCheckCode() throws IOException {
        FileOutputStream fos = new FileOutputStream("/check-code.jpg");
        String code = CheckCodeUtil.outputVerifyImage(100, 50, fos, 4);

        System.out.println(System.getProperty("user.dir"));
        System.out.println(code);
    }
}
