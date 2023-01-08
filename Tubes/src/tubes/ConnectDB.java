package tubes;

import java.sql.*;

public class ConnectDB {
    private Connection koneksi = null;
    private Statement state = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String[] username;
    private String[] password;
    private String[] role;
    
    public void connectDB() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = DriverManager.getConnection("jdbc:mysql://localhost/pbo_tubes?user=root&password=");
            
            state = koneksi.createStatement();
            rs = state.executeQuery("select * from akun");
            rs.last();
            
            this.username = new String[rs.getRow()];
            this.password = new String[rs.getRow()];
            this.role = new String[rs.getRow()];
            
            int i = 0;
            rs = state.executeQuery("select * from akun");
            
            while(rs.next()) {
                this.username[i] = rs.getString("username");
                this.password[i] = rs.getString("password");
                this.role[i] = rs.getString("role");
                i++;
            }
        } catch(ClassNotFoundException | SQLException e) {
            throw e;
        }
    }
    public String[] getUsr() {
        return this.username;
    }
    public String[] getPwd() {
        return this.password;
    }
    public String[] getRole() {
        return this.role;
    }
    private void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (state != null) {
                state.close();
            }
            if (koneksi != null) {
                koneksi.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
