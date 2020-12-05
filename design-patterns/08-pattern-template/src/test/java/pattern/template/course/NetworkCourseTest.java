package pattern.template.course;

import org.junit.Test;

import static org.junit.Assert.*;

public class NetworkCourseTest {

    @Test
    public void createCourse() {
        JavaCourse java = new JavaCourse();
        java.createCourse();
        System.out.println("==========================================");
        BigDataCourse bigDataCourse = new BigDataCourse(false);
        bigDataCourse.createCourse();
        System.out.println("==========================================");
        BigDataCourse bigDataCourse1 = new BigDataCourse(true);
        bigDataCourse1.createCourse();


    }
}