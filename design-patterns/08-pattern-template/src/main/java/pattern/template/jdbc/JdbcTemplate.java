package pattern.template.jdbc;

import pattern.template.jdbc.dao.JdbcFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class JdbcTemplate {
    private DataSource dataSource;

    public JdbcTemplate() {
        this.dataSource = JdbcFactory.getDataSource();
    }

    public <T> List<T> executeQuery(String sql, RowMapper<T> rowMapper, Object[] values) throws SQLException {
        //1.获取连接
        Connection conn = this.getConnection();
        //2.创建语句集
        PreparedStatement pstm = this.createPreparedStatement(conn, sql);
        //3.执行语句集
        ResultSet rs = this.executeQuery(pstm, values);
        //4.处理结果集
        List<T> result = this.parseResultSet(rs, rowMapper);
        //5.关闭结果集
        this.closeStatement(pstm);
        //6.关闭连接
        this.closeConnection(conn);

        return result;
    }

    protected void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }

    protected void closeStatement(PreparedStatement pstm) throws SQLException {
        pstm.close();

    }

    protected <T> List<T> parseResultSet(ResultSet rs, RowMapper<T> rowMapper) throws SQLException {
        List<T> result = new ArrayList<>();
        int rowNum = 1;
        while (rs.next()) {
            result.add(rowMapper.mapRow(rs, rowNum ++));
        }
        return result;
    }

    protected ResultSet executeQuery(PreparedStatement pstm, Object[] values) throws SQLException {
        if(values!=null)
        for (int i = 0; i < values.length; i++) {
            pstm.setObject(i, values[i]);
        }
        return pstm.executeQuery();
    }

    protected PreparedStatement createPreparedStatement(Connection conn, String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
