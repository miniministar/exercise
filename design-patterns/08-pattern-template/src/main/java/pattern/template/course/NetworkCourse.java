package pattern.template.course;

public abstract class NetworkCourse {
    protected final void createCourse() {
        //1、发布预算资料
        this.postPreResource();
        //2、制作ppt课件
        this.createPPT();
        //3、在线直播
        this.liveVideo();
        //4、提交课件、课堂笔记
        this.postNote();
        //5、提交源码
        this.postSource();
        //6、布置作业
        if(needHomework()) {
            checkHomework();
        }
    }

    abstract void checkHomework();

    //钩子方法：实现流程的微调
    protected boolean needHomework() {
        return false;
    }

    final void postSource() {
        System.out.println("提交源码");
    }

    final void postNote() {
        System.out.println("提交课件、课堂笔记");
    }

    final void liveVideo() {
        System.out.println("在线直播");
    }

    final void createPPT() {
        System.out.println("制作ppt课件");
    }

    final void postPreResource() {
        System.out.println("发布预算资料");
    }
}
