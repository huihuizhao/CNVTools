import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ImportTest {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Connection con = null;// 创建一个数据库连接
//		PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
//		ResultSet result = null;// 创建一个结果集对象
//		String reply = "";
//
//		BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new FileReader("D:\\1.txt"));
//            String msg = null;
//            String tmp = null;
//            while ((tmp = reader.readLine()) != null) {
//                msg = tmp;
//            }
//            reader.close();
//            String[] data = msg.split("\\|");
//            //上面已经生成了数组
//             
//            String sql  = "insert into Table(a,b,c) value(*，*，*)"; //对应字段的值
//             
//            //下面是插入数据表操作
//            //.................
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e1) {
//                }
//            }
//        }
//		
//		
//
//		try {
//			 Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
//			 System.out.println("开始尝试连接数据库！");
//			 String url = "jdbc:oracle:" +
//			 "thin:@192.168.1.4:1521:ORACLEHH";//
////			 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
//			 String user = "zhaohuihui";// 用户名,系统默认的账户名
//			 // String user = "sys as sysdba";// 用户名,系统默认的账户名
//			
//			 String password = "1";// 你安装时选设置的密码
//			 con = DriverManager.getConnection(url, user, password);// 获取连接
//			 System.out.println("连接成功！");
//
//			String sql = "";// 预编译语句，“？”代表参数
//			// String sql = "select * from VOYAGE where NAME=?";// 预编译语句，“？”代表参数
//			pre = con.prepareStatement(sql);// 实例化预编译语句
//			// pre.setString(1, "科学三号201405黄海春季调查航次");//
//			// 设置参数，前面的1表示参数的索引，而不是表中列名的索引
//			pre.setString(1, "");// 设置参数，前面的1表示参数的索引，而不是表中列名的索引
//
//			result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
//			while (result.next()) {
//				// 当结果集不为空时
//
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
//				// 注意关闭的顺序，最后使用的最先关闭
//				if (result != null)
//					result.close();
//				if (pre != null)
//					pre.close();
//				if (con != null)
//					con.close();
//				System.out.println("数据库连接已关闭！");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
////		return null;		
//		
//
//	}

}
