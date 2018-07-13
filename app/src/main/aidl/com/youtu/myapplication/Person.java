package com.youtu.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author sunwei
 *         邮箱：tianmu19@gmail.com
 *         时间：2018/7/13 9:58
 *         包名：com.youtu.myapplication.bean
 *         <p>description:            </p>
 */

public class Person implements Parcelable {
    private String mName;

    public Person() {
    }

    public Person(String mName) {
        this.mName = mName;
    }

    public Person(Parcel parcel) {
        mName = parcel.readString();
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel parcel) {
            return new Person(parcel);
        }

        @Override
        public Person[] newArray(int i) {
            return new Person[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "mName='" + mName + '\'' +
                '}';
    }
}
