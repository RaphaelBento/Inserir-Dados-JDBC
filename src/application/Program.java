package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");/*Instacia um objeto SimpleDateFormat com nome sdf com valor nulo*/
		Connection conn =null;/*Instancia um objeto do tipo Connection com nome conn  iniciada com valor nulo*/
	PreparedStatement st =null;/*Instancia um objeto do tipo PreparedStantement com nome st com valor nulo*/
		
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(	"INSERT INTO seller " /*comando sql para manipular o banco de dados */
					 +"(Name, Email, BirthDate, BaseSalary, DepartmentId)"/*campos da tabela que seram inseridos os dados */
					 +"VALUES "/*comando sql para inserir valores*/
					+"(?, ?, ?, ?, ?)");/*cada ponto de interrogação representa uma coluna da tabela que vai receber uma informação*/
				
			
			/*Série de comandos para inserir dados nas colunas da tabela*/
			st.setString(1, "Djalma padilha");
			st.setString(2, "djalminhapadilha@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("03/08/1988").getDate()));
			st.setDouble(4, 200.0);
			st.setInt(5, 3);
			int rowsAffected = st.executeUpdate();/*mostra quantas linha sofrerão atualização*/
			System.out.println("pronto linhas afetadas: " + rowsAffected);
			
		}
		
		/*Tratamento das exceções geradas ao executar o código*/
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}