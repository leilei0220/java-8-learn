package comple_table_future;

/**
 * @author lei
 * @version 1.0
 * @date 2021/8/13 8:30
 * @desc
 */
public class SupportService {
    public static String writeEnglishHomWork() throws Exception {
        Thread.sleep(1000 * 4);
        throw new RuntimeException("测试打印异常信息!");
    }

    public static String writeEnglishHomWorkNoEx() {
        try {
            Thread.sleep(1000 * 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "写完数学作业";

    }

    public static String writeMathHomeWork() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "写完数学作业";
    }
}
