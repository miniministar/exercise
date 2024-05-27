package com.exercise.interview;

import java.sql.ResultSet;

public interface IRowMapper<T> {
    //处理结果集
    T mapping(ResultSet rs) throws Exception;
}
//
//    public static <T> T query(String sql, IRowMapper<T> rsh, Object... params) {
//        Connectionconn = null;
//        PreparedStatementps = null;
//        ResultSet rs = null;
//        try {
//            Util.getConnection();
//            ps = conn.prepareStatement(sql);
//            //设置值
//            for (inti = 0; i < params.length; i++) {
//                ps.setObject(i + 1, params[i]);
//            }
//            rs = ps.executeQuery();
//            return rsh.mapping(rs);
//            // 5. 释放资源
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            JdbcUtil.close(rs, ps, conn);
//        }
//        return null;
//    }