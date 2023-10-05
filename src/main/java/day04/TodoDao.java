package day04;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component // 해당 클래스를 스프링 컨테이너에 빈 등록
public class TodoDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    public TodoDao(){
        // SQL 연동
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springweb","root","1234");
            System.out.println("연동 성공");
        }catch(Exception e ){
            System.out.println("연동 실패 " + e);
        }
    }
    // [C]
    public boolean doPost(TodoDto todoDto){
        //sql
        try {
            String sql = "insert into todo( tcontent, tstate ) values( ? , ? )";
            ps = conn.prepareStatement(sql);
            ps.setString(1,todoDto.getTcontent()); ps.setBoolean(2, todoDto.isTstate());
            if( ps.executeUpdate() == 1) return true;
        }catch(Exception e){e.printStackTrace();}
        return false;
    }
    // [R]
    public List<TodoDto> doGet(){
        List<TodoDto> list = new ArrayList<>();
        //sql
        try {
            String sql = "select * from todo";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while( rs.next() ) {
                list.add( TodoDto.builder()
                        .tno(rs.getInt(1))
                        .tcontent(rs.getString(2))
                        .tstate(rs.getBoolean(3))
                        .build());
            }
        }catch (Exception e){e.printStackTrace();}
        return list;
    }

    // 3. [U]
    @PutMapping("")
    public boolean doPut(TodoDto todoDto){
        //sql
        try {
            String sql = "update todo set tstate = ? where tno = ?";
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, todoDto.isTstate());
            ps.setInt(2, todoDto.getTno());
            if( ps.executeUpdate() == 1)return true;
        }catch(Exception e){e.printStackTrace();}
        return false;
    }

    // 4. [D]
    @DeleteMapping("")
    public boolean doDelete(int tno ){
        //sql
        try {
            return conn.prepareStatement("delete from todo where tno = " + tno).executeUpdate() == 1;
        }catch (Exception e){e.printStackTrace();}
        return false;
    }
}