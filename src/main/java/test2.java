import java.awt.*;
import java.sql.*;

/**
 * Project name(项目名称)：java_jdbc的使用
 * Package(包名): PACKAGE_NAME
 * Class(类名): test2
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/1/12
 * Time(创建时间)： 19:38
 * Version(版本): 1.0
 * Description(描述)： 批量处理SQL
 */

public class test2
{
    /**
     * 连接数据库
     */
    public static Connection getConnection() throws Exception
    {
        Connection connection = null;

        Class.forName("com.mysql.cj.jdbc.Driver");  //新版
        //旧版：com.mysql.jdbc.Driver
        //Loading class `com.mysql.jdbc.Driver'. This is deprecated.
        // The new driver class is `com.mysql.cj.jdbc.Driver'.
        // The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "20010713");

        return connection;
    }

    public static void main(String[] args)
    {
        Connection connection = null;
        //PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try
        {
            //加载驱动,获得链接
            connection = getConnection();
            //创建一个Statement对象
            statement = connection.createStatement();
            String sql = "select * from information";
            String sql1 = "insert into information values (9,'王武','男',21)";
            String sql2 = "insert into information values (10,'张六','男',19)";
            String sql3 = "insert into information values (11,'张琪','女',18)";
            statement.addBatch(sql1);
            statement.addBatch(sql2);
            statement.addBatch(sql3);
            statement.executeBatch();
            statement.clearBatch();
            //执行sql语句，返回结果集
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                System.out.println(resultSet.getString(1) + "  " + resultSet.getString(2) +
                        "  " + resultSet.getString(3) + "  " + resultSet.getString(4));
            }
        }
        catch (SQLException e)                   //数据库异常
        {
            Toolkit.getDefaultToolkit().beep();
            System.err.println("异常！异常内容为：" + e.getMessage());
            //调试使用：
            //e.printStackTrace();
        }
        catch (Exception e)                     //其它异常
        {
            e.printStackTrace();
        }
        finally                                 //关闭
        {
            if (resultSet != null)
            {
                try
                {
                    resultSet.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            if (statement != null)
            {
                try
                {
                    statement.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
