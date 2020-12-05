package pattern.template.jdbc.dao;

import org.junit.Test;
import pattern.template.jdbc.Member;

import java.util.List;

public class MemberDaoTest {

    @Test
    public void selectAll() {
        MemberDao memberDao = new MemberDao();
        List<Member> members = memberDao.selectAll();
        System.out.println(members);

        List<Member> members2 = memberDao.selectAll();
        System.out.println(members2);

    }
}