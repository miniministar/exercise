package pattern.template.jdbc.dao;

import pattern.template.jdbc.JdbcTemplate;
import pattern.template.jdbc.Member;
import pattern.template.jdbc.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao extends JdbcTemplate {

    public List<Member> selectAll() {
        String sql = "select * from t_member";
        try {
            return super.executeQuery(sql, new RowMapper<Member>() {
                @Override
                public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Member member = new Member();
                    member.setUsername(rs.getString("username"));
                    //字段过多，原型模式
                    member.setUsername(rs.getString("username"));
                    member.setPassword(rs.getString("password"));
                    member.setAge(rs.getInt("age"));
                    member.setAddr(rs.getString("addr"));
                    return member;
                }
            }, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
