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
package org.apache.commons.math4.legacy.analysis.interpolation;

import org.apache.commons.math4.legacy.analysis.TrivariateFunction;
import org.apache.commons.statistics.distribution.ContinuousDistribution;
import org.apache.commons.statistics.distribution.UniformContinuousDistribution;
import org.apache.commons.math4.legacy.exception.DimensionMismatchException;
import org.apache.commons.math4.legacy.exception.MathIllegalArgumentException;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.apache.commons.math4.core.jdkmath.JdkMath;
import org.apache.commons.numbers.core.Precision;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test case for the bicubic function.
 */
public final class TricubicInterpolatingFunctionTest {
    /**
     * Test preconditions.
     */
    @Test
    public void testPreconditions() {
        double[] xval = new double[] { 3, 4, 5, 6.5 };
        double[] yval = new double[] { -4, -3, -1, 2.5 };
        double[] zval = new double[] { -12, -8, -5.5, -3, 0, 2.5 };
        double[][][] fval = new double[xval.length][yval.length][zval.length];

        @SuppressWarnings("unused")
        TrivariateFunction tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                fval, fval, fval, fval,
                fval, fval, fval, fval);

        double[] wxval = new double[] { 3, 2, 5, 6.5 };
        try {
            tcf = new TricubicInterpolatingFunction(wxval, yval, zval,
                    fval, fval, fval, fval,
                    fval, fval, fval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (MathIllegalArgumentException e) {
            // Expected
        }
        double[] wyval = new double[] { -4, -1, -1, 2.5 };
        try {
            tcf = new TricubicInterpolatingFunction(xval, wyval, zval,
                    fval, fval, fval, fval,
                    fval, fval, fval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (MathIllegalArgumentException e) {
            // Expected
        }
        double[] wzval = new double[] { -12, -8, -9, -3, 0, 2.5 };
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, wzval,
                    fval, fval, fval, fval,
                    fval, fval, fval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (MathIllegalArgumentException e) {
            // Expected
        }
        double[][][] wfval = new double[xval.length - 1][yval.length - 1][zval.length];
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    wfval, fval, fval, fval,
                    fval, fval, fval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    fval, wfval, fval, fval,
                    fval, fval, fval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    fval, fval, wfval, fval,
                    fval, fval, fval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    fval, fval, fval, wfval,
                    fval, fval, fval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    fval, fval, fval, fval,
                    wfval, fval, fval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    fval, fval, fval, fval,
                    fval, wfval, fval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    fval, fval, fval, fval,
                    fval, fval, wfval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    fval, fval, fval, fval,
                    fval, fval, fval, wfval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        wfval = new double[xval.length][yval.length - 1][zval.length];
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    wfval, fval, fval, fval,
                    fval, fval, fval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    fval, wfval, fval, fval,
                    fval, fval, fval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    fval, fval, wfval, fval,
                    fval, fval, fval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    fval, fval, fval, wfval,
                    fval, fval, fval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    fval, fval, fval, fval,
                    wfval, fval, fval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    fval, fval, fval, fval,
                    fval, wfval, fval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    fval, fval, fval, fval,
                    fval, fval, wfval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    fval, fval, fval, fval,
                    fval, fval, fval, wfval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        wfval = new double[xval.length][yval.length][zval.length - 1];
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    wfval, fval, fval, fval,
                    fval, fval, fval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    fval, wfval, fval, fval,
                    fval, fval, fval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    fval, fval, wfval, fval,
                    fval, fval, fval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    fval, fval, fval, wfval,
                    fval, fval, fval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    fval, fval, fval, fval,
                    wfval, fval, fval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    fval, fval, fval, fval,
                    fval, wfval, fval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    fval, fval, fval, fval,
                    fval, fval, wfval, fval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
        try {
            tcf = new TricubicInterpolatingFunction(xval, yval, zval,
                    fval, fval, fval, fval,
                    fval, fval, fval, wfval);
            Assert.fail("an exception should have been thrown");
        } catch (DimensionMismatchException e) {
            // Expected
        }
    }

    /**
     * Test for a plane.
     * <p>
     * f(x, y, z) = 2 x - 3 y - 4 z + 5
     * </p>
     */
    @Test
    public void testPlane() {
        final TrivariateFunction f = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return 2 * x - 3 * y - 4 * z + 5;
            }
        };

        final TrivariateFunction dfdx = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return 2;
            }
        };

        final TrivariateFunction dfdy = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return -3;
            }
        };

        final TrivariateFunction dfdz = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return -4;
            }
        };

        final TrivariateFunction zero = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return 0;
            }
        };

        /**
         * testInterpolation(-10, 3,
         * 4.5, 6,
         * -150, -117,
         * 7,
         * 1000,
         * f,
         * dfdx,
         * dfdy,
         * dfdz,
         * zero,
         * zero,
         * zero,
         * zero,
         * 1e-12,
         * 1e-11,
         * 1e-10,
         * false);
         */
    }

    /**
     * Test for a quadric.
     * <p>
     * f(x, y, z) = 2 x<sup>2</sup> - 3 y<sup>2</sup> - 4 z<sup>2</sup> + 5 x y + 6
     * x z - 2 y z + 3
     * </p>
     */
    @Test
    public void testQuadric() {
        final TrivariateFunction f = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return 2 * x * x - 3 * y * y - 4 * z * z + 5 * x * y + 6 * x * z - 2 * y * z + 3;
            }
        };

        final TrivariateFunction dfdx = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return 4 * x + 5 * y + 6 * z;
            }
        };

        final TrivariateFunction dfdy = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return -6 * y + 5 * x - 2 * z;
            }
        };

        final TrivariateFunction dfdz = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return -8 * z + 6 * x - 2 * y;
            }
        };

        final TrivariateFunction d2fdxdy = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return 5;
            }
        };

        final TrivariateFunction d2fdxdz = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return 6;
            }
        };

        final TrivariateFunction d2fdydz = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return -2;
            }
        };

        final TrivariateFunction d3fdxdydz = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return 0;
            }
        };

        /**
         * testInterpolation(-10, 3,
         * 4.5, 6,
         * -150, -117,
         * 7,
         * 5000,
         * f,
         * dfdx,
         * dfdy,
         * dfdz,
         * d2fdxdy,
         * d2fdxdz,
         * d2fdydz,
         * d3fdxdydz,
         * 1e-12,
         * 1e-11,
         * 1e-8,
         * false);
         */
    }

    /**
     * Wave.
     * <p>
     * f(x, y, z) = a cos (&omega; z - k<sub>x</sub> x - k<sub>y</sub> y)
     * </p>
     * with a = 5, &omega; = 0.3, k<sub>x</sub> = 0.8, k<sub>y</sub> = 1.
     */
    @Test
    public void testWave() {
        final double a = 5;
        final double omega = 0.3;
        final double kx = 0.8;
        final double ky = 1;

        final TrivariateFunction arg = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return omega * z - kx * x - ky * y;
            }
        };

        final TrivariateFunction f = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return a * JdkMath.cos(arg.value(x, y, z));
            }
        };

        final TrivariateFunction dfdx = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return kx * a * JdkMath.sin(arg.value(x, y, z));
            }
        };

        final TrivariateFunction dfdy = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return ky * a * JdkMath.sin(arg.value(x, y, z));
            }
        };

        final TrivariateFunction dfdz = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return -omega * a * JdkMath.sin(arg.value(x, y, z));
            }
        };

        final TrivariateFunction d2fdxdy = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return -ky * kx * a * JdkMath.cos(arg.value(x, y, z));
            }
        };

        final TrivariateFunction d2fdxdz = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return omega * kx * a * JdkMath.cos(arg.value(x, y, z));
            }
        };

        final TrivariateFunction d2fdydz = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return omega * ky * a * JdkMath.cos(arg.value(x, y, z));
            }
        };

        final TrivariateFunction d3fdxdydz = new TrivariateFunction() {
            @Override
            public double value(double x, double y, double z) {
                return omega * ky * kx * a * JdkMath.sin(arg.value(x, y, z));
            }
        };

        /*
         * testInterpolation(-10, 3,
         * 4.5, 6,
         * -150, -117,
         * 30,
         * 5000,
         * f,
         * dfdx,
         * dfdy,
         * dfdz,
         * d2fdxdy,
         * d2fdxdz,
         * d2fdydz,
         * d3fdxdydz,
         * 1e-3,
         * 1e-2,
         * 1e-12,
         * false);
         */
    }
}
