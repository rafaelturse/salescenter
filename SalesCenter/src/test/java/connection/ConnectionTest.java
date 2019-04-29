package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

public class ConnectionTest {

	Connection connection;
	Statement statement;

	@Before
	public void doConection() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbSalesCenter", "root", "root");
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void qtdRegistros() {
		try {
			ResultSet rs = statement.executeQuery("select count(*) as qtd FROM dbsalescenter.tb_user;");
		
			while (rs.next()) {
				String qtd = rs.getString("qtd");
				System.out.println("Quantidade: " + qtd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
