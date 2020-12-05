package pattern.template.course;

public class BigDataCourse extends NetworkCourse {
    private boolean needHomewrokFlag = false;

    public BigDataCourse(boolean needHomewrokFlag) {
        this.needHomewrokFlag = needHomewrokFlag;
    }

    @Override
    void checkHomework() {
        System.out.println("检查大数据作业");
    }

    @Override
    protected boolean needHomework() {
        return needHomewrokFlag;
    }
}
