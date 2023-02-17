import java.sql.*;

public class Customer {

    private String email;
    private String user_name;
    private String pass_word;
    private boolean approved;

    public Customer (String email, String user_name, String pass_word) throws ClassNotFoundException, SQLException {
        this.email = email;
        this.user_name = user_name;
        this.pass_word = pass_word;
        // first add a method to see if email already exists (remember capitilization doesn't matter)
        // second add a method to see if username already exists (capitilization does matter)
        if (check_email_user_name(this.email, this.user_name)) {
            customer_sign_up (this.email, this.user_name, this.pass_word);
            approved = true;
        } else {
            approved = false;
        }
    }

    private Connection get_connection () throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/restaurant_reservation";
    	String userName = "root";
    	String passWord = "Ipqt1690/";
    	
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, userName, passWord);

        return con;
    }

    private void customer_sign_up (String email, String user_name, String pass_word) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO customers (email, user_name, pass_word) VALUES ('"+email+"', '"+user_name+"', '"+pass_word+"');";

        Connection con = get_connection();
        Statement st = con.createStatement();
        st.executeQuery(query);
        
        st.close();
        con.close();
    }

    private boolean check_email_user_name (String email, String user_name) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM customers";

        Connection con = get_connection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();

        while (rs.next()) {
            if (rs.getString(2).toUpperCase() == this.email.toUpperCase() || rs.getString(3) == user_name) {
                return false;
            }
        }

        return false;
    }

    public void delete_this_customer () {
        String query = "";
    }
}