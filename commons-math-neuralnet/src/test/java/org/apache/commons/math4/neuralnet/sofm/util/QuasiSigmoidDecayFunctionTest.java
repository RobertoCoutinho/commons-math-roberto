/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.math4.neuralnet.sofm.util;

import org.junit.Test;
import org.junit.Assert;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for {@link QuasiSigmoidDecayFunction} class
 */
public class QuasiSigmoidDecayFunctionTest {

    @Test
    public void testPrecondition1() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuasiSigmoidDecayFunction(0d, -1d, 2));
    }

    @Test
    public void testPrecondition3() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuasiSigmoidDecayFunction(1d, 0d, 100));
    }

    @Test
    public void testPrecondition4() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuasiSigmoidDecayFunction(1d, -1d, 0));
    }

    @Test
    public void testTrivial() {
        final int n = 65;
        final double init = 4;
        final double slope = -1e-1;
        final QuasiSigmoidDecayFunction f = new QuasiSigmoidDecayFunction(init, slope, n);

        Assert.assertEquals(init, f.applyAsDouble(0), 0d);
        // Very approximate derivative.
        Assert.assertEquals(slope, f.applyAsDouble(n + 1) - f.applyAsDouble(n), 1e-4);
        Assert.assertEquals(0, f.applyAsDouble(Long.MAX_VALUE), 0d);
    }

}
