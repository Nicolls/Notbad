// IRemoteService.aidl
package com.notbad.binder;

import com.notbad.binder.Student;

// Declare any non-default types here with import statements

interface IRemoteCallBack {

    void onNewStudent(in Student student);

}