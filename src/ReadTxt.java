import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ReadTxt {
	/**
	 * 
	 * @param filePath
	 *            你的txt文件路径
	 * @param state
	 * @throws IOException
	 */
	
	Connection conn = null;// 创建一个数据库连接
	PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement

	public void readTxt(String filePath, PreparedStatement state)
			throws IOException {
		FileReader f = new FileReader(filePath);
//		BufferedReader bufferedreader = new BufferedReader((new InputStreamReader(new FileInputStream(new File(filePath)),"utf-8")));
		//编码方式为utf-8，txt保存时编码方式也要选择为utf-8
		BufferedReader bufferedreader = new BufferedReader(new FileReader(filePath) );
		String instring;
		String[] strArr = null;
		while ((instring = bufferedreader.readLine()) != null) {
			if (0 != instring.length()) {
				strArr = instring.split(" ");//与txt文件中的分隔符要一致
				addDataToState(strArr,state);
			}
		}
		f.close();
	}
	/**
	 * 添加数据到state预编译语句
	 * @param strArr
	 * @param state
	 */
	public void addDataToState(String[] strArr, PreparedStatement state) {
		try {
			state.setString(1, strArr[0]);
			state.setString(2, strArr[1]);
			state.setString(3, strArr[2]);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void saveData(String filePath) {
//		Connection conn = null;// 得到你的数据库连接
		conn=TryConnection() ;
		PreparedStatement state = null;
		try {
			conn.setAutoCommit(false);// 设置手动提交事务
//			state = conn.prepareStatement("insert into STUDENT(name,sex,age) values(?,?,?)");
			state = conn.prepareStatement("insert into STUDENT(name,sex,age) values(?,?,?)");
//			Insert into USER.EMPLOYEE VALUES (tbEmployeeName.Text, lbEmployeeGender.Text)
			this.readTxt(filePath, state);//第一个参数为txt文件路径
			conn.commit();//提交数据
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, state);
		}
	}
	public void close(Connection conn, PreparedStatement state) {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
			if (state != null) {
				state.close();
				state = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//public static void main(String[] args) {
//		ReadTxt rt = new ReadTxt();
//		rt.saveData("d:/Test.txt");//参数为你的txt文件路径
//	}

public Connection TryConnection() {
	// Connection con = null;

	// try {
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} // 加载Oracle驱动程序
	System.out.println("开始尝试连接数据库！");
	String url = "jdbc:oracle:" + "thin:@192.168.1.4:1521:ORACLEHH";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
	String user = "zhaohuihui";// 用户名,系统默认的账户名
	// String user = "sys as sysdba";// 用户名,系统默认的账户名

	String password = "1";// 你安装时选设置的密码
	try {
		conn = DriverManager.getConnection(url, user, password);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} // 获取连接

	return conn;
}


}