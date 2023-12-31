package example.day01.webMvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WebDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    public WebDao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springweb","root","1234");
            System.out.println("연동 성공");
        }catch(Exception e ){
            System.out.println("연동 실패 " + e);
        }

    }
    public List<WebDto> doGet(){
        List<WebDto> list = new ArrayList<>();
        try{
            String sql = "select * from todo";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while( rs.next() ){
                WebDto dto = new WebDto(
                     rs.getInt(1),
                        rs.getString(2),
                        LocalDate.parse( rs.getString(3)),
                        rs.getBoolean(4)
                );
                list.add(dto);
            }
            return list;
        }catch(Exception e){e.printStackTrace();}
        return null;
    }

    public boolean doPost( WebDto dto ){
        try {
            String sql = "insert into todo(title,dueDate,finished) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString( 1 , dto.getTitle());
            ps.setString( 2 , dto.getDate().toString());
            ps.setBoolean(3,dto.isFinished());
            int count = ps.executeUpdate();
            if(count == 1)return true;
        }catch(Exception e){e.printStackTrace();}
        return false;
    }
}
