// IRemoteService.aidl
package com.notbad.binder;

import com.notbad.binder.Student;
import com.notbad.binder.IRemoteCallBack;

// Declare any non-default types here with import statements

interface IRemoteService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int calculate(int a, int b);

    int getPid();

    void setCallBack(IRemoteCallBack remoteCallBack);

    Student upgradeStudent(in Student student);

    void insertStudent(in Student student);

}