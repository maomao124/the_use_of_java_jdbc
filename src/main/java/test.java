import java.awt.*;
import java.sql.*;

/**
 * Project name(项目名称)：java_jdbc的使用
 * Package(包名): PACKAGE_NAME
 * Class(类名): test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/1/12
 * Time(创建时间)： 18:32
 * Version(版本): 1.0
 * Description(描述)： Connection
 * Jdbc程序中的Connection，它用于代表数据库的链接，Collection是数据库编程中最重要的一个对象，客户端与数据库所有交互都是通过connection对象完成的，创建方法为：
 * Connection conn = DriverManager.getConnection(url,user,pass);
 * 这个对象的常用方法：
 * 方法	                                    描述
 * createStatement()	创建向数据库发送sql的statement对象。
 * prepareStatement(sql)	创建向数据库发送预编译sql的PrepareSatement对象。
 * prepareCall(sql)	创建执行存储过程的callableStatement对象。
 * setAutoCommit(boolean autoCommit)	设置事务是否自动提交。
 * commit()	在链接上提交事务。
 * rollback()	在此链接上回滚事务。
 * <p>
 * Statement
 * Jdbc程序中的Statement对象用于向数据库发送SQL语句，创建方法为：
 * Statement st = conn.createStatement();
 * <p>
 * Statement对象常用方法：
 * <p>
 * 方法	                                含义
 * executeQuery(String sql) 	用于向数据发送查询语句。
 * executeUpdate(String sql)	用于向数据库发送insert、update或delete语句
 * execute(String sql)	用于向数据库发送任意sql语句
 * addBatch(String sql)	把多条sql语句放到一个批处理中。
 * executeBatch()	向数据库发送一批sql语句执行。
 * <p>
 * 4、获取结果
 * Jdbc程序中的ResultSet用于代表Sql语句的执行结果。Resultset封装执行结果时，采用的类似于表格的方式，
 * ResultSet 对象维护了一个指向表格数据行的游标，初始的时候，游标在第一行之前，调用ResultSet.next() 方法，
 * 可以使游标指向具体的数据行，进行调用方法获取该行的数据。
 * <p>
 * 1、获取行
 * ResultSet提供了对结果集进行滚动的方法：
 * <p>
 * next()：移动到下一行
 * Previous()：移动到前一行
 * absolute(int row)：移动到指定行
 * beforeFirst()：移动resultSet的最前面。
 * afterLast() ：移动到resultSet的最后面。
 * 2、获取值
 * ResultSet既然用于封装执行结果的，所以该对象提供的都是用于获取数据的get方法：
 * <p>
 * 获取任意类型的数据
 * getObject(int index)
 * getObject(string columnName)
 * <p>
 * 获取指定类型的数据，例如：
 * getString(int index)
 * getString(String columnName)
 */

public class test
{
    /**
     * 连接数据库
     */
    public static Connection getConnection()
    {
        Connection connection = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");  //新版
            //旧版：com.mysql.jdbc.Driver
            //Loading class `com.mysql.jdbc.Driver'. This is deprecated.
            // The new driver class is `com.mysql.cj.jdbc.Driver'.
            // The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "20010713");
        }
        catch (Exception e)
        {
            Toolkit.getDefaultToolkit().beep();
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args)
    {
        Connection connection = getConnection();
        if (connection == null)
        {
            System.out.println("错误！");
            System.exit(1);
        }
        try
        {
            Statement statement = connection.createStatement();
            String sql = "select * from information";
            ResultSet resultSet = statement.executeQuery(sql);

            /*
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.executeQuery();
             */

            resultSet.next();
            System.out.println(resultSet.getInt("no"));
            //System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("sex"));
            System.out.println(resultSet.getString("age"));
            resultSet.next();
            System.out.println(resultSet.getInt("no"));
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("sex"));
            System.out.println(resultSet.getString("age"));
            resultSet.next();
            System.out.println(resultSet.getInt("no"));
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("sex"));
            System.out.println(resultSet.getString("age"));

            System.out.println();
            //resultSet.beforeFirst();
            while (resultSet.next())
            {
                System.out.println(resultSet.getInt("no"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("sex"));
                System.out.println(resultSet.getString("age"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}
