package algorithm.hyperloglog;

import java.util.concurrent.ThreadLocalRandom;

public class PfTest {

    static class BitKeeper {

        //一定次数统计随机数中，获取最大的连续低位为0的位数
        private int maxbit;

        public void random() {
            //生成 0 ~ 2的32次方 的 long 值
            long value = ThreadLocalRandom.current().nextLong(2L << 32);
            int bit = lowZeros(value);
            if (bit > this.maxbit) {
                this.maxbit = bit;
            }
        }

        private int lowZeros(long value) {
            int i = 0;
            for (; i < 32; i++) {
                //从1-31位 低位补0 与原值比较，若不等则记录不等的位数，表示从第i为为1，及低0~i位都为0
                if (value >> i << i != value) {
                    break;
                }
            }
            return i - 1;
        }
    }

    static class Experiment {

        private int n;
        private BitKeeper keeper;

        public Experiment(int n) {
            this.n = n;
            this.keeper = new BitKeeper();
        }

        public void work() {
            for (int i = 0; i < n; i++) {
                this.keeper.random();
            }
        }

        //Math.log(10)等价于：x = ln10 或 x = loge(10），即以e为底的自然对数。logx(y)=ln(y)/ln(x);
        public void debug() {
            System.out
                    .printf("%d %.2f %d\n", this.n, Math.log(this.n) / Math.log(2), this.keeper.maxbit);
        }
    }

    //记录下低位连续零位的最大长度 K，即为图中的 maxbit ，通过这个 K 值我们就可以估算出随机数的数量 N。
    //会发现K和N的对数之间存在显著的线性相关性：N 约等于 2 的k次方
    public static void main(String[] args) {
        System.out
                .printf("%s %s %s\n", "随机次数n", "log2n", "连续低位为0的位数的最大值");
        for (int i = 1000; i < 100000; i += 100) {
            Experiment exp = new Experiment(i);
            exp.work();
            exp.debug();
        }
    }
}
