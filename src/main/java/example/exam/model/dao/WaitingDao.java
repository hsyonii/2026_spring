package example.exam.model.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import example.exam.model.dto.WaitingDto;

public class WaitingDao extends BaseDao {
    private static final WaitingDao instance = new WaitingDao();
    private WaitingDao() {}
    public static WaitingDao getInstance() { return instance; }

    // 1. 등록 (Create)
    public boolean save(WaitingDto waitingDto) {
        try {
            String sql = "INSERT INTO waiting(phone, headcount) VALUES(?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, waitingDto.getPhone());
            ps.setInt(2, waitingDto.getHeadcount());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    // 2. 전체 조회 (Read)
    public ArrayList<WaitingDto> findAll() {
        ArrayList<WaitingDto> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM waiting";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                WaitingDto waitingDto = new WaitingDto();
                waitingDto.setNo( rs.getInt("no") ); // rs.get타입( "가져올속성명" )
                waitingDto.setPhone( rs.getString("phone") );
                waitingDto.setHeadcount( rs.getInt("headcount") );

                list.add( waitingDto );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    // 3. 수정 (Update)
    public boolean update(WaitingDto waitingDto) {
        try {
            String sql = "UPDATE waiting SET headcount = ? WHERE no = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, waitingDto.getHeadcount());
            ps.setInt(2, waitingDto.getNo());
            int result = ps.executeUpdate();
            if(result==1)
                return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    // 4. 삭제 (Delete)
    public boolean delete(int no) {
        try {
            String sql = "DELETE FROM waiting WHERE no = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,no);
            if( ps.executeUpdate() == 1 )
                 return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}