package io.bittiger.sampling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class WeightedSampler {
    private List<Double> cdf;
    private HashSet<Integer> ignoreIndexes;
    private final Random rn;


    public WeightedSampler(double[] weights) {
        rn = new Random();
        ignoreIndexes = new HashSet();
        double sumWeight = 0;
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] <= 0) {
                ignoreIndexes.add(i);
                continue;
            }
            sumWeight += weights[i];
        }
        cdf = new ArrayList(weights.length);
        double cumulation = 0;
        for (int i = 0; i < weights.length; i++) {
            cdf.add(cumulation / sumWeight);
            if (weights[i] > 0) {
                cumulation += weights[i];
            }
        }
    }

    public Integer getNextSample() {
        int idx = searchIndex(rn.nextDouble());
        while (ignoreIndexes.contains(idx)) {
            idx = searchIndex(rn.nextDouble());
        }
        return idx;
    }

    public List<Integer> getSamples(int sampleCount) {
        if (sampleCount <= 0)
            return new ArrayList();
        List<Integer> result = new ArrayList(sampleCount);
        for (int i = 0; i < sampleCount; i++) {
            result.add(getNextSample());
        }
        return result;
    }

    private Integer searchIndex(double prob) {
        if (prob < 0 || prob > 1)
            return -1;
        int start = 0;
        int end = cdf.size() - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (cdf.get(mid) > prob) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (cdf.get(end) <= prob) {
            return end;
        }
        return start;
    }

    public List<Double> getCdf() {
        return cdf;
    }
}
