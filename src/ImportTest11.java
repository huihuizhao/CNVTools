import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImportTest11 {

	static Connection con = null;// 创建一个数据库连接
	PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		// GetCoordinate();

		try {
			// String url = "jdbc:oracle:thin:@IP:1521:orcl"; // orcl为数据库的SID
			// String user = "oracle";
			// String password = "oracle";

			// Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection con = (Connection)
			// DriverManager.getConnection(url,user,password);

			StringBuffer sql = new StringBuffer();
			sql.append("insert into CTDINFO (ID,STATIONID,METADATAID,DEPTH,TEMPERATURE,SALINITY) values (?,?,?,?,?,?)");

			con = TryConnection();
			// 关闭事务自动提交
			con.setAutoCommit(false);

			Long startTime = System.currentTimeMillis();
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql.toString());

//			 File root = new File("D:/FocusMap/Data/科学轮20150831主流系与西太平洋暖池变异机制调查航次第一航段/ctd");//0001
			File root = new File("D:/FocusMap/Data/数据2017011102/科学三号201405黄海春季调查航次/CTD/CTD-后处理/data");// 0002
//			 File root = new File("D:/FocusMap/Data/数据2017011102/201606热液与冷泉综合调查航次-1/CTD");//0003

			File[] files = root.listFiles();
			for (File file : files) {

				// FileReader f = new
				// FileReader("D:/FocusMap/Data/cnv/S01.cnv");
				FileReader f = new FileReader(file);
				BufferedReader bufferedreader = new BufferedReader(f);

				String instring;
				String[] strArr = null;
				while ((instring = bufferedreader.readLine()) != null) {
					if (instring.contains("*END*")) {
						break;

					}
				}
				 instring = bufferedreader.readLine();
				 if (0 != instring.length()) {
				 instring = instring.replaceAll("\\s+", " ");
				 instring = instring.trim();
				 strArr = instring.split(" ");// 与txt文件中的分隔符要一致
				
//				 //0001
//				 pst.setString(1, "1");				 
//				 pst.setString(2, file.getName().substring(0, file.getName().length() - 4));
//				 pst.setString(1, "1");
//				 pst.setString(3, strArr[4].trim());
//				 pst.setString(4, strArr[13].trim());
//				 pst.setString(5, strArr[12].trim());
				//
				// //0002
				 pst.setInt(1, id);			 
				 pst.setInt(2, file.getName().substring(0, file.getName().length() - 4));
				 pst.setInt(3, 1);
				 pst.setString(4, strArr[4].trim());
				 pst.setString(5, strArr[13].trim());
				 pst.setString(6, strArr[12].trim());
				
				 //0003
//				 pst.setString(1, "0003");
//				 pst.setString(2, file.getName().substring(0,file.getName().length() - 4));
//				 pst.setString(3, strArr[4].trim());
//				 pst.setString(4, strArr[12].trim());
//				 pst.setString(5, strArr[11].trim());
				 // 把一个SQL命令加入命令列表
				 pst.addBatch();
				 }

				while ((instring = bufferedreader.readLine()) != null) {

//					0001  0002 跳跃输入
					for (int i = 0; i < 1000; i++) {
						instring = bufferedreader.readLine();
					}
					if (instring != null && (0 != instring.length())) {
						instring = instring.replaceAll("\\s+", " ");
						instring = instring.trim();
						strArr = instring.split(" ");// 与txt文件中的分隔符要一致
//						// 0001
//						pst.setString(1, "0001");
//						pst.setString(2, file.getName().substring(0, file.getName().length() - 4));
//						pst.setString(3, strArr[4].trim());
//						pst.setString(4, strArr[13].trim());
//						pst.setString(5, strArr[12].trim());
						//
//						// 0002
						 pst.setString(1, "1");				 
						 pst.setString(2, file.getName().substring(0, file.getName().length() - 4));
						 pst.setString(1, "1");
						 pst.setString(3, strArr[4].trim());
						 pst.setString(4, strArr[13].trim());
						 pst.setString(5, strArr[12].trim());

						 //0003
//						 pst.setString(1, "0003");
//						 pst.setString(2, file.getName().substring(0,
//						 file.getName().length() - 4));
//						 pst.setString(3, strArr[4].trim());
//						 pst.setString(4, strArr[12].trim());
//						 pst.setString(5, strArr[11].trim());
						
						
						// 把一个SQL命令加入命令列表
						pst.addBatch();
					}
				}
				f.close();

				// 执行批量更新
				pst.executeBatch();
				// 语句执行完毕，提交本事务
				con.commit();
				Long endTime = System.currentTimeMillis();
				System.out.println(file.getName() + "用时：" + (endTime - startTime));

			}

			pst.close();
			con.close();

			// FileReader f = new FileReader("d:/Test.txt");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("finished");

	}

	public static Connection TryConnection() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 加载Oracle驱动程序
		System.out.println("开始尝试连接数据库！");
		String url = "jdbc:oracle:" + "thin:@192.168.1.4:1521:ORACLEHH";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
		String user = "iocasksh";// 用户名,系统默认的账户名
		// String user = "sys as sysdba";// 用户名,系统默认的账户名

		String password = "iocas6760";// 你安装时选设置的密码
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 获取连接

		return con;
	}

	public static void GetCoordinate() throws IOException {
		// File root = new
		// File("D:/FocusMap/Data/数据2017011102/201606热液与冷泉综合调查航次-1/CTD");
		File root = new File("D:/FocusMap/Data/科学轮20150831主流系与西太平洋暖池变异机制调查航次第一航段/ctd");
		// D:\FocusMap\Data\科学轮20150831主流系与西太平洋暖池变异机制调查航次第一航段\ctd
		File[] files = root.listFiles();
		for (File file : files) {
			FileReader f = null;
			try {
				f = new FileReader(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedReader bufferedreader = new BufferedReader(f);

			String instring;
			String[] strArr = null;
			String staCode = "";
			String longitude = "";
			String latitude = "";
			while ((instring = bufferedreader.readLine()) != null) {
				if (instring.contains("*END*")) {
					break;

				}
			}
			while ((instring = bufferedreader.readLine()) != null) {

				if (0 != instring.length()) {
					instring = instring.replaceAll("\\s+", " ");
					instring = instring.trim();
					strArr = instring.split(" ");// 与txt文件中的分隔符要一致
					staCode = file.getName().substring(0, file.getName().length() - 4);
					longitude = strArr[7].trim();
					latitude = strArr[6].trim();

				}
			}
			f.close();

			// 执行批量更新

			// 语句执行完毕，提交本事务

			// Long endTime = System.currentTimeMillis();
			System.out.println(staCode + " " + longitude + " " + latitude);

		}

	}

}
