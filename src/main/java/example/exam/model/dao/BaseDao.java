package example.exam.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

//BaseDao + ~~Dao 파일로 분리
//BaseDao : DB연결
public class BaseDao {
    //이후 java에서 sql 작성하기 위해 필요
    protected Connection conn;

    //생성자
    public BaseDao() {
        //DB연결 / 클래스로딩(Class.forName)시 예외처리 필수!!
        try {
            //JDBC( MySQL전용 ) 드라이버 클래스 로딩
            //Class.forName("문자열") : "문자열" = 로딩할 클래스 경로
            Class.forName("com.mysql.cj.jdbc.Driver");

            //아래 정보(url / user / password)로 DB 연결
            //conn = DB와 연결되는 통로
            this.conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sample",
                "root",
                "1234"
            );
        } catch (Exception e) {
            System.out.println("DB 연결 실패: " + e);
        }
    }
}
