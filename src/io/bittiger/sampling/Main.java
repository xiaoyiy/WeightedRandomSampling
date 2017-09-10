package io.bittiger.sampling;

public class Main {
    public static void main(String[] args) {
        // step 1. set inputs
        double[] weights = {0.1, 0.2, 0.3, 0, 0.4};
        int sampleCount = 100;

        // step 2. pass inputs to the sampler and print out results
        WeightedSampler sampler = new WeightedSampler(weights);
        for (int i = 0; i < sampleCount; i++) {
            System.out.print(sampler.getNextSample().toString() + ", ");
        }
    }
}