package Pengirim;

import java.sql.*;

public class Connect_p {
    private Connection koneksi = null;
    private Statement state = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String[] id_send;
    private String[] nama_send;
    private String[] alamat_send;
    private String[] nohp_send;
    private String[] jenis_brg;
    private String[] berat_brg;
    private String[] nama_rcv;
    private String[] alamat_rcv;
    private String[] nohp_rcv;
    private int[] payment;
    
    public void connectDB() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = DriverManager.getConnection("jdbc:mysql://localhost/pbo_tubes?user=root&password=");
            
            state = koneksi.createStatement();
            rs = state.executeQuery("select * from pengirim");
            rs.last();
            
            this.id_send = new String[rs.getRow()];
            this.nama_send = new String[rs.getRow()];
            this.alamat_send = new String[rs.getRow()];
            this.nohp_send = new String[rs.getRow()];
            this.jenis_brg = new String[rs.getRow()];
            this.berat_brg = new String[rs.getRow()];
            this.nama_rcv = new String[rs.getRow()];
            this.alamat_rcv = new String[rs.getRow()];
            this.nohp_rcv = new String[rs.getRow()];
            this.payment = new int[rs.getRow()];
            
            int i = 0;
            rs = state.executeQuery("select * from pengirim");
            
            while(rs.next()) {
                this.id_send[i] = rs.getString("id_send");
                this.nama_send[i] = rs.getString("nama_send");
                this.alamat_send[i] = rs.getString("alamat_send");
                this.nohp_send[i] = rs.getString("nohp_send");
                this.jenis_brg[i] = rs.getString("jenis_brg");
                this.berat_brg[i] = rs.getString("berat_brg");
                this.nama_rcv[i] = rs.getString("nama_rcv");
                this.alamat_rcv[i] = rs.getString("alamat_rcv");
                this.nohp_rcv[i] = rs.getString("nohp_rcv");
                this.payment[i] = rs.getInt("payment");
                i++;
            }
        } catch(ClassNotFoundException | SQLException e) {
            throw e;
        }
    }
    public void inputDatabase(String nama_send, String alamat_send, String nohp_send, String jenis_brg, String berat_brg, 
            String nama_rcv, String alamat_rcv, String nohp_rcv, int payment) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            state.executeUpdate("INSERT INTO `pengirim` (`nama_send`, `alamat_send`, `nohp_send`, `jenis_brg`, `berat_brg`,"
                    + " `nama_rcv`, `alamat_rcv`, `nohp_rcv`, `payment`) VALUES ('"+nama_send+"', '"+alamat_send+"', '"+nohp_send+"',"
                            + " '"+jenis_brg+"', '"+berat_brg+"', '"+nama_rcv+"', '"+alamat_rcv+"', '"+nohp_rcv+"', '"+payment+"');");           
        } catch(ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            close();
        }
    }
    public void updateDatabase(String id_send, int payment) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            ps = koneksi.prepareStatement("UPDATE pengirim SET payment = ? WHERE id_send = ?;");
            ps.setInt(1, payment);
            ps.setString(2, id_send);
            ps.executeUpdate();
        } catch(ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            close();
        }
    }
    public String[] getID() {
        return id_send;
    }
    public String[] getNama_send() {
        return nama_send;
    }
    public String[] getAlamat_send() {
        return alamat_send;
    }
    public String[] getNohp_send() {
        return nohp_send;
    }
    public String[] getJenis_brg() {
        return jenis_brg;
    }
    public String[] getBerat_brg() {
        return berat_brg;
    }
    public String[] getNama_rcv() {
        return nama_rcv;
    }
    public String[] getAlamat_rcv() {
        return alamat_rcv;
    }
    public String[] getNohp_rcv() {
        return nohp_rcv;
    }
    public int[] getPayment() {
        return payment;
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
