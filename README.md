HW3 - Weighted Random Sampling

This repository contains 2 java class files.
1. Main.Java - servers as the starter. It passes weights array to the Sampler object and print out the sample results.
  Note that the sum of the weights array doesn't need to sum to exactly 1, as the Sample class will normalize the probabilities automatically. Non-positive weights will be treated as 0.

2. WeightedSampler - takes an array of weights in constructor. The getSampling method will generate samples based on the weighted probabilities and number of samples desired.

RUNNING INSTRUCTION
set weights array and sampleCount in Main method.
