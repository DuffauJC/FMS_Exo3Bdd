package fr.fms;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TestJdbc {

	// récupérer une connection à partir d'une url + id + pwd
	private static final String url="jdbc:mariadb://localhost:3306/shop";
	private static final String login="root";
	private static final String password="fms2022";

/**
 * 
 * @param args
 * @throws Exception
 */
	public static void main(String[] args)throws Exception {
		
		
		ArrayList<Article> articles =new ArrayList<Article>();

		// enregistre la class auprès du driver manager
		// (charge le pilote)
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// insertion d'un article
		Article art=new Article("Antivrus","Norton",34.99 );
		insertArticle(art,url,login,password);

		// mise à jour article
		updateArticle(url,login,password);
		
		// suppression d'un article
		deleteArticle(url,login,password);
		
		// lecture d'un article
		readArticleById(articles,url,login,password);
		
		// lecture de la table articles
		readArticles(articles,url,login,password);

	}

/**
 * 
 * @param art
 * @param url
 * @param login
 * @param password
 * @throws SQLException
 */
	private static void insertArticle( Article art, String url, String login, String password) throws SQLException {
		try(Connection connection=DriverManager.getConnection(url,login,password)){// connection de java sql
			String strSql="INSERT INTO T_Articles(Description, Brand, UnitaryPrice) VALUES(?, ?, ?);";						// une fois connecté, réalisation d'un requête
			try(PreparedStatement ps =connection.prepareStatement(strSql)){ // de java.sql
				ps.setString(1, art.getDescription());
				ps.setString(2, art.getBrand());
				ps.setDouble(3, art.getUnitaryPrice());

				if (ps.executeUpdate()==1) {
					System.out.println("insertion ok");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}
	/**
	 * 
	 * @param url
	 * @param login
	 * @param password
	 * @throws SQLException
	 */
	private static void updateArticle(String url, String login, String password) throws SQLException {
		try(Connection connection=DriverManager.getConnection(url,login,password)){// connection de java sql
			String strSql="UPDATE t_articles SET brand = ?, UnitaryPrice = ? WHERE IdArticle = 4;";						// une fois connecté, réalisation d'un requête
			try(PreparedStatement ps =connection.prepareStatement(strSql)){ // de java.sql
				ps.setString(1, "Turban violet");
				ps.setDouble(2, 5.99);

				if (ps.executeUpdate()==1) {
					System.out.println("mise à jour ok");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
	}
	/**
	 * 
	 * @param url
	 * @param login
	 * @param password
	 * @throws SQLException
	 */
	private static void deleteArticle(String url, String login, String password) throws SQLException {
		try(Connection connection=DriverManager.getConnection(url,login,password)){// connection de java sql
			String strSql="DELETE FROM t_articles WHERE IdArticle = ?;";						// une fois connecté, réalisation d'un requête
			try(PreparedStatement ps =connection.prepareStatement(strSql)){ // de java.sql
				ps.setInt(1, 8);
			
				if (ps.executeUpdate()==1) {
					System.out.println("suppression ok");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
	}
	/**
	 * 
	 * @param articles
	 * @param url
	 * @param login
	 * @param password
	 * @throws SQLException
	 */
	private static void readArticleById(ArrayList<Article> articles, String url, String login, String password) throws SQLException {
		try(Connection connection=DriverManager.getConnection(url,login,password)){// connection de java sql
			String strSql="SELECT * FROM t_articles WHERE IdArticle = ?;";		// une fois connecté, réalisation d'un requête
			try(PreparedStatement ps =connection.prepareStatement(strSql)){ // de java.sql
				ps.setInt(1, 14);
				ResultSet rs=ps.executeQuery();  // ResultSet de java.sql
					while (rs.next()) {
						int rsidArticle=rs.getInt(1);  // soit index(de 1 à n) de la colonne, soit le nom de la colonne
						String rsdescription=rs.getString(2);
						String rsbrand=rs.getString(3);
						double rsunitaryPrice=rs.getDouble(4);
						articles.add((new Article(rsidArticle,rsdescription,rsbrand,rsunitaryPrice)));
					}
				
			
				if (ps.executeUpdate()==1) {
					System.out.println("lecture  ok");
				}
				
				System.out.println("---------------------------------");
				for (Article a : articles) {
					System.out.println(a.getIdArticle()+" - "+a.getDescription()+" - "+a.getBrand()+" - "+a.getUnitaryPrice());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
	}

	/**
	 * 
	 * @param articles
	 * @param url
	 * @param login
	 * @param password
	 */
	private static void readArticles(ArrayList<Article> articles, String url, String login, String password) {
		try(Connection connection=DriverManager.getConnection(url,login,password)){// connection de java sql
			String strSql="SELECT * FROM T_articles";						// une fois connecté, réalisation d'un requête
			try(Statement statement =connection.createStatement()){
				try(ResultSet resultSet=statement.executeQuery(strSql)){   // ResultSet de java.sql
					while (resultSet.next()) {
						int rsidArticle=resultSet.getInt(1);  // soit index(de 1 à n) de la colonne, soit le nom de la colonne
						String rsdescription =resultSet.getString(2);
						String rsbrand=resultSet.getString(3);
						double rsunitaryPrice=resultSet.getDouble(4);
						articles.add((new Article(rsidArticle,rsdescription,rsbrand,rsunitaryPrice)));
					}
				}
			}

			System.out.println("---------------------------------");
			for (Article a : articles) {
				System.out.println(a.getIdArticle()+" - "+a.getDescription()+" - "+a.getBrand()+" - "+a.getUnitaryPrice());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

