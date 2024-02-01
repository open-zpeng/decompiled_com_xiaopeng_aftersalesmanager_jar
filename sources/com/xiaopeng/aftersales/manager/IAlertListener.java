package com.xiaopeng.aftersales.manager;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IAlertListener extends IInterface {
    void alertDiagnosisError(int i, int i2, long j, String str) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IAlertListener {
        @Override // com.xiaopeng.aftersales.manager.IAlertListener
        public void alertDiagnosisError(int module, int errorCode, long time, String errorMsg) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IAlertListener {
        private static final String DESCRIPTOR = "com.xiaopeng.aftersales.manager.IAlertListener";
        static final int TRANSACTION_alertDiagnosisError = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAlertListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAlertListener)) {
                return (IAlertListener) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code != 1) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            long _arg2 = data.readLong();
            String _arg3 = data.readString();
            alertDiagnosisError(_arg0, _arg1, _arg2, _arg3);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IAlertListener {
            public static IAlertListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.xiaopeng.aftersales.manager.IAlertListener
            public void alertDiagnosisError(int module, int errorCode, long time, String errorMsg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(module);
                    _data.writeInt(errorCode);
                    _data.writeLong(time);
                    _data.writeString(errorMsg);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().alertDiagnosisError(module, errorCode, time, errorMsg);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAlertListener impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IAlertListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
