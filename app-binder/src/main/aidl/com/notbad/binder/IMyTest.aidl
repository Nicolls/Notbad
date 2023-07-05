// IMyTest.aidl
package com.notbad.binder;

// Declare any non-default types here with import statements

 interface IMyTest {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
      oneway void calculate(int a, int b);
}