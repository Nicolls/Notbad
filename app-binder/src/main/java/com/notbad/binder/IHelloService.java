package com.notbad.binder;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 手写一个binder服务
 */
public interface IHelloService extends android.os.IInterface{

    public static class Default implements IHelloService {

        @Override
        public IBinder asBinder() {
            return null;
        }


        @Override
        public String printMsg(String msg) throws android.os.RemoteException{
            return null;
        }
    }
    /** Local-side IPC implementation stub class. */
    public static abstract class Stub extends android.os.Binder implements IHelloService {
        private static String DESCRIPTOR = "com.notbad.binder.IHelloService";
        public Stub(){
            this.attachInterface(this,DESCRIPTOR);
        }
        /**
         * Cast an IBinder object into an com.notbad.binder.IMyTest interface,
         * generating a proxy if needed.
         */
        public static IHelloService asInterface(IBinder obj){
            if(obj==null){
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if(iin!=null&&(iin instanceof IHelloService)) {
                return (IHelloService) iin;
            }
            return new IHelloService.Stub.Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {

            String descriptor = DESCRIPTOR;
            switch (code) {
                case TRANSACTION_printMsg:
                    data.enforceInterface(descriptor);
                    String arg = data.readString();
                    String result = this.printMsg(arg);
                    reply.writeNoException();
                    reply.writeString(result);
                    return true;
                default:
                {
                    return super.onTransact(code, data, reply, flags);
                }
            }
        }

        private static class Proxy implements IHelloService {
            private IBinder remote;

            public Proxy(IBinder remote) {
                this.remote = remote;
            }

            @Override
            public IBinder asBinder() {
                return remote;
            }

            public String getInterfaceDescriptor()
            {
                return DESCRIPTOR;
            }

            @Override
            public String printMsg(String msg) throws RemoteException {
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                String result = "";
                try {
                    data.writeInterfaceToken(DESCRIPTOR);
                    data.writeString(msg);
                    boolean status = remote.transact(TRANSACTION_printMsg,data,reply,0);
                    if(!status&&getDefaultImpl()!=null){
                        return getDefaultImpl().printMsg(msg);
                    }
                    reply.readException();
                    result = reply.readString();
                }
                finally {
                    reply.recycle();
                    data.recycle();
                }
                return result;
            }

            public static IHelloService sDefaultImpl;


        }
        public static final int TRANSACTION_printMsg = Binder.FIRST_CALL_TRANSACTION + 0;

        public static boolean setDefaultImpl(IHelloService impl){
            if(Proxy.sDefaultImpl!=null){
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if(impl!=null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IHelloService getDefaultImpl(){
            return Proxy.sDefaultImpl;
        }

    }

    String printMsg(String msg) throws android.os.RemoteException;

}
