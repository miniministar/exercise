package algorithm.hyperloglog;

import java.util.concurrent.ThreadLocalRandom;

public class PfTest2 {


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
        private int k;
        private BitKeeper[] keepers;


        public Experiment(int n) {
            this(n, 1024);
        }


        public Experiment(int n, int k) {
            this.n = n;
            this.k = k;
            this.keepers = new BitKeeper[k];
            for (int i = 0; i < k; i++) {
                this.keepers[i] = new BitKeeper();
            }
        }

        public void work() {
            for (int i = 0; i < this.n; i++) {
                long m = ThreadLocalRandom.current().nextLong(1L << 32);
                BitKeeper keeper = keepers[(int) (((m & 0xfff0000) >> 16) %
                        keepers.length)];
                keeper.random();
            }
        }

        public double estimate() {
            double sumbitsInverse = 0.0;
            for (BitKeeper keeper : keepers) {
                sumbitsInverse += 1.0 / (float) keeper.maxbit;
            }
            double avgBits = (float) keepers.length / sumbitsInverse;
            return Math.pow(2, avgBits) * this.k;
        }
    }

    //如果N介于 2的K次方和 2 K+1次方之间，用这种方式估计的值都等于 2 K次方，这明显是不合理的，所以我们可以使用 多个 BitKeeper	进行加权估计，就可以得到一个比较准确的值了：
    public static void main(String[] args) {
        for (int i = 100000; i < 1000000; i += 100000) {
            Experiment exp = new Experiment(i);
            exp.work();
            double est = exp.estimate();
            System.out.printf("%d %.2f %.2f\n", i, est, Math.abs(est - i) / i);
        }
    }
}
