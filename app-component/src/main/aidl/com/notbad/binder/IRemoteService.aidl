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

    oneway void upgradeStudent(in Student student); // 使用oneway就不会block客户端

    void insertStudent(in Student student);

}