package com.notbad.binder;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    public String name;
    public int age;

    public Student(){

    }

    public Student(String name, int age){
        this.name = name;
        this.age = age;
    }
    private Student(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel inParcel) {
        name = inParcel.readString();
        age = inParcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }

    @Override
    public String toString() {
        return "Student{" +
                "hashCode=" + hashCode() +'\'' +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
