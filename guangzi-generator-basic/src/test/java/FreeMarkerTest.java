import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By Shiguang On 2024/6/17 20:42
 */
public class FreeMarkerTest {

    @Test
    public void test() throws IOException, TemplateException {
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 指定模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        // FreeMarker 使用Java平台的本地化敏感的数字格式信息。
        // 默认的本地化数字格式可能是分组或其他不想要的格式。
        // 为了避免这种情况，你不得不使用 FreeMarker 设置 中的 number_format 来重写Java平台建议的数字格式
        // 设置数字格式不显示逗号分隔符
        configuration.setNumberFormat("0.######");

        // 创建模板对象，加载指定模板
        Template template = configuration.getTemplate("myweb.html.ftl");

        // 数据模型
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("currentYear", 2024);

        List<Map<String, Object>> menuItems = new ArrayList<>();
        Map<String, Object> menuItem1 = new HashMap<>();
        menuItem1.put("url", "https://shiguang666.eu.org");
        menuItem1.put("label", "時光主页");

        Map<String, Object> menuItem2 = new HashMap<>();
        menuItem2.put("url", "https://blog.shiguang666.eu.org");
        menuItem2.put("label", "時光博客园子");

        Map<String, Object> menuItem3 = new HashMap<>();
        menuItem3.put("url", "https://nav.shiguang666.eu.org");
        menuItem3.put("label", "時光导航站");

        menuItems.add(menuItem1);
        menuItems.add(menuItem2);
        menuItems.add(menuItem3);
        dataModel.put("menuItems", menuItems);


        File outputDir = new File("src/main/resources/outputdir");

        if (!outputDir.exists()){
            outputDir.mkdirs();
        }

        String outputFile = outputDir + File.separator + "myweb.html";

        System.out.println("输出文件："+outputFile);

        Writer out = new FileWriter(outputFile);

        template.process(dataModel, out);

        // 关闭输出流
        out.close();
    }

}
